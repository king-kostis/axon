package com.axon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TelegramUpdate {
    private TelegramMessage message;
}
