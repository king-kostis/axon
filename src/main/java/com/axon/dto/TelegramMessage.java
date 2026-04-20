package com.axon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TelegramMessage {
    private Long message_id;
    private TelegramChat chat;
    private String text;
}
