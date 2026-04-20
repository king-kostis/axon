package com.axon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TelegramChat {
    private Long id;
    private String first_name;
    private String username;
    private String type;
}
