package com.room.api.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonHolderResponseDTO {
    private String userId;
    private String id;
    private String title;
    private String body;

}
