package com.enix.strategy;

import com.enix.data.FamilyIPData;
import com.linecorp.bot.model.action.MessageAction;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.quickreply.QuickReply;
import com.linecorp.bot.model.message.quickreply.QuickReplyItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class IPMessageStrategy implements MessageStrategy {

    private final String strategyName;
    private final DateTimeFormatter dateTimeFormatter;
    private final FamilyIPData familyIPData;

    @Autowired
    public IPMessageStrategy(FamilyIPData familyIPData,
                             DateTimeFormatter dateTimeFormatter) {
        this.familyIPData = familyIPData;
        this.dateTimeFormatter = dateTimeFormatter;
        this.strategyName = "IP查詢";
    }

    @Override
    public String getStrategyName() {
        return strategyName;
    }

    @Override
    public Message getResultMessage() {

        String ip = familyIPData.getIp();
        LocalDateTime updateTime = familyIPData.getUpdateTime();

        StringBuilder message = new StringBuilder("IP 資訊如下：\n");
        message.append("IP 位置：").append(ip).append("\n");
        message.append("IP 更新時間：");
        if (updateTime != null) message.append(dateTimeFormatter.format(updateTime));
        else message.append("未知");

        MessageAction action = new MessageAction("我的IP查詢", "我的IP查詢");
        QuickReplyItem item = QuickReplyItem.builder().action(action).build();
        QuickReply quickReply = QuickReply.builder().item(item).build();

        return new TextMessage(message.toString(), quickReply);
    }
}
