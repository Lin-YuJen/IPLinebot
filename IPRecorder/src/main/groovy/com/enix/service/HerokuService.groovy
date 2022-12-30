package com.enix.service

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import groovy.json.StringEscapeUtils
import groovy.util.logging.Slf4j

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Slf4j
class HerokuService {

    def formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    def jsonSlurper = new JsonSlurper()

    void sendIPInfo(String ip) {

        def url = "https://anonymous.herokuapp.com/raspberry/updateIPInfo"
        def method = "POST"
        def jsonMap = [
                "token"     : "",
                "ip"        : ip,
                "updateTime": LocalDateTime.now().format(formatter),
                "userName"  : ""
        ]
        def content = new JsonBuilder(jsonMap).toPrettyString()
        println "input json is "
        println content

        def post = new URL(url).openConnection()
        post.setRequestMethod(method)
        post.setDoOutput(true)
        post.setRequestProperty("Content-Type", "application/json")
        post.getOutputStream().write(content.getBytes("UTF-8"))
        def postRC = post.getResponseCode()
        if (postRC == 200) {
            def text = post.getInputStream().getText("UTF-8")
            def responsJson = jsonSlurper.parseText(text)
            println "Response is"
            println StringEscapeUtils.unescapeJava(new JsonBuilder(responsJson).toPrettyString())
            if (responsJson.code == '0000') log.info("成功更新。")
            else log.info("更新失敗")
        } else {
            log.info "Fail to send IP info. Response code is ${postRC}."
        }
        println "END=="
    }
}
