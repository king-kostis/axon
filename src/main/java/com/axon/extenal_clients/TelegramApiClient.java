package com.axon.extenal_clients;

import com.axon.dto.SendMessageRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "telegramApi", url = "${TELEGRAM_API_BASE_URL}")
public interface TelegramApiClient {
    @PostMapping("/bot{token}/sendMessage")
    void sendMessage(@PathVariable("token") String token, @RequestBody SendMessageRequest request);
}
