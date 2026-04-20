package com.axon.controller;

import com.axon.dto.TelegramUpdate;
import com.axon.service.TelegramBotService;
import feign.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.util.JSONPObject;

@RestController
@RequestMapping("/axon/v1")
public class TelegramController {
    @Autowired
    private TelegramBotService botService;

    @PostMapping("/getMessage")
    public ResponseEntity<Void> message(@RequestBody TelegramUpdate telegramUpdate){
        botService.sendMessage(telegramUpdate);
        return ResponseEntity.ok().build();
    }
}