package com.axon.service;

import com.axon.dto.SendMessageRequest;
import com.axon.dto.TelegramUpdate;
import com.axon.extenal_clients.TelegramApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TelegramBotService {
    @Autowired
    private TelegramApiClient telegramApi;

    @Value("${TELEGRAM_TOKEN}")
    private String token;

    public void sendMessage(TelegramUpdate update){
        String text = update.getMessage().getText();
        String message = "";

        if(text.equalsIgnoreCase("/start")){
            message += "Hello! My name is Axon. Your personal task management assistant";
        }

        Long chatId = update.getMessage().getChat().getId();
        SendMessageRequest request = new SendMessageRequest(chatId, message);
        telegramApi.sendMessage(token, request);
    }
}
