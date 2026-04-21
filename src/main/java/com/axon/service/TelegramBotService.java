package com.axon.service;

import com.axon.dto.SendMessageRequest;
import com.axon.dto.TelegramUpdate;
import com.axon.extenal_clients.TelegramApiClient;
import com.axon.model.User;
import com.axon.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class TelegramBotService {
    @Autowired
    private TelegramApiClient telegramApi;

    @Autowired
    private UserRepository userRepository;

    @Value("${TELEGRAM_TOKEN}")
    private String token;

    public void sendMessage(TelegramUpdate update){
        String message = handleMessage(update);

        Long chatId = update.getMessage().getChat().getId();
        SendMessageRequest request = new SendMessageRequest(chatId, message);
        telegramApi.sendMessage(token, request);
    }

    private String handleMessage(TelegramUpdate update){
        String text = update.getMessage().getText();
        String message = "";

        if(text.equalsIgnoreCase("/start")) {
            message += """
                    Hello! My name is Axon. Your personal task management assistant.
                    
                    To register for Axon type '/register'.
                    """;
        } else if(text.equalsIgnoreCase("/register")) {
            User user = registerUser(update);

            if (isUserRegistered(user)) {
                message = "You are already registered";
            }
            else{
                if (isValidUserName(user)) {
                    message += """
                            Great! Now your are properly registered on Axon.
                            
                            However, it seems that your telegram account does not have your first name.
                            
                            What would you like me to call you?
                            
                            Type the command '/name_change=YOUR_NEW_NAME' to update how axon calls you anytime.
                            """;
                } else {
                    message += "Great! Now your are properly registered on Axon. " +
                            "\n\nWelcome " + user.getFirstName();
                }
            }

        } else if(text.startsWith("/name_change=")){
            User user = changeFirstName(update, text);
            message = "Your name has been successfully updated.";
        } else {
            message += "Sorry I can't reply to properly to this message at the moment";
        }

        return message;
    }

    @Transactional
    private User registerUser(TelegramUpdate update){
        User user = new User();
        user.setUserId(UUID.randomUUID());
        user.setFirstName(update.getMessage().getChat().getFirst_name());
        user.setUserName(update.getMessage().getChat().getUsername());
        user.setTelegramUserId(update.getMessage().getChat().getId());
        return user;
    }

    private boolean isValidUserName(User user) {
        return user.getFirstName() == null;
    }

    @Transactional
    private User changeFirstName(TelegramUpdate update, String text){
        int nameIndex = text.indexOf("=");
        String newName = text.substring(nameIndex+1);

        User user = userRepository.findById(update.getMessage().getChat().getId())
                .orElseThrow();
        user.setFirstName(newName);
        return user;
    }

    private boolean isUserRegistered(User user){
        return userRepository.findById(user.getId()).isPresent();
    }
}
