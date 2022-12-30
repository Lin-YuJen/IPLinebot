package com.enix.strategy;

import com.linecorp.bot.model.message.Message;

public interface MessageStrategy {

    default String getStrategyName(){
        return "Unknown";
    }
    Message getResultMessage();
}
