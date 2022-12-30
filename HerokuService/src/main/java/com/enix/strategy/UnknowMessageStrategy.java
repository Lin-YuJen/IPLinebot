package com.enix.strategy;

import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import org.springframework.stereotype.Component;

@Component
public class UnknowMessageStrategy implements MessageStrategy{

    @Override
    public Message getResultMessage() {

        return new TextMessage("Sorry，不支援此種訊息內容。");
    }
}
