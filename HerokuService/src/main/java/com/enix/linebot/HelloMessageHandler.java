package com.enix.linebot;

import com.enix.strategy.MessageStrategy;
import com.enix.strategy.MessageStrategyPicker;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@LineMessageHandler
public class HelloMessageHandler {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final MessageStrategyPicker messageStrategyPicker;

    @Autowired
    public HelloMessageHandler(MessageStrategyPicker messageStrategyPicker) {
        this.messageStrategyPicker = messageStrategyPicker;
    }

    @EventMapping
    public Message handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        log.info("event: " + event);
        String originalMessageText = event.getMessage().getText();
        String userId = event.getSource().getUserId();
        log.info("userId is " + userId);
        log.info("message is " + originalMessageText);
        MessageStrategy strategy = messageStrategyPicker.getStrategy(originalMessageText);

        return strategy.getResultMessage();
    }

}
