package com.enix.action

import com.enix.service.HerokuService
import com.enix.service.IPService
import groovy.util.logging.Slf4j

@Slf4j
class RecordAction {

    static void main(String[] args) {

        def ipService = new IPService()
        def herokuService  = new HerokuService()

        def ip = ipService.getMyPublicIP()
        log.info("取得之 IP 為 ${ip}")
        herokuService.sendIPInfo(ip)
    }
}
