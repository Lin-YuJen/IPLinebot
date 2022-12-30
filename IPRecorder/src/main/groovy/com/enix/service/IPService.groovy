package com.enix.service

import groovy.util.logging.Slf4j
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.Duration

@Slf4j
class IPService {

    def url = "https://www.whatismyip.com.tw/tw/"
    def uri = URI.create(url)

    /**
     * 爬取網站取得 IP
     * @return
     */
    String getMyPublicIP() {
        // 建立 HttpClient
        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofMillis(3000))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .version(HttpClient.Version.HTTP_1_1)
                .build()

        // 建立 HttpRequest
        HttpRequest request = HttpRequest.newBuilder(uri)
                .timeout(Duration.ofMillis(3000))
                .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/604.5.6 (KHTML, like Gecko) Version/11.0.3 Safari/604.5.6")
                .GET()
                .build()

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString())
            if (response.statusCode() == 200) {
                Document doc = Jsoup.parse(response.body())
                Element element = doc.selectFirst("span[data-ip]")
                if (element.hasText()) {
                    return element.text()
                }
                log.info("找不到IP的標籤。")
            } else {
                log.info("Fail to get IP. StatusCode is " + response.statusCode())
            }
        } catch (IOException | InterruptedException e) {
            log.error("Fail to get IP. {}", e.getMessage(), e)
        }
        return ""
    }
}
