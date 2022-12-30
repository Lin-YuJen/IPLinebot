package com.enix.service;

import com.enix.data.ValidTokenData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private ValidTokenData validTokenData;

    public TokenService(ValidTokenData validTokenData) {
        this.validTokenData = validTokenData;
    }

    public boolean isTokenValid(String token) {
        if (token == null) return false;
        return validTokenData.isTokenExisted(token);
    }
}
