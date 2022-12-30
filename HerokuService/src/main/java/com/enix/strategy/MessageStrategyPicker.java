package com.enix.strategy;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MessageStrategyPicker {

    private final Map<String, Class<? extends MessageStrategy>> strategyMap;
    private final ApplicationContext context;

    public MessageStrategyPicker(ApplicationContext context) {
        this.context = context;
        strategyMap = new HashMap<>();
        strategyMap.put("IPMessageStrategy", IPMessageStrategy.class);
    }

    public MessageStrategy getStrategy(String text) {

        if (text.contains("我的IP查詢"))
            return context.getBean(strategyMap.get("IPMessageStrategy"));

        return context.getBean(UnknowMessageStrategy.class);
    }
}
