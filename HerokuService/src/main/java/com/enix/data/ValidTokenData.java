package com.enix.data;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ValidTokenData {

    private List<String> tokenList;

    public ValidTokenData() {
        tokenList = new ArrayList<>();
        tokenList.add("wahaha");
    }

    public boolean isTokenExisted(String token) {
        return tokenList.contains(token);
    }
}
