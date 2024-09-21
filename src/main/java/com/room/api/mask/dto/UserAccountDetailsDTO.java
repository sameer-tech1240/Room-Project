package com.room.api.mask.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.room.api.annotation.AccountNumberMaskSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountDetailsDTO {
    private String userName;

    @JsonSerialize(using = AccountNumberMaskSerializer.class)
    private String accountNumber;

    @JsonSerialize(using = AccountNumberMaskSerializer.class)
    private String panNumber;
}
