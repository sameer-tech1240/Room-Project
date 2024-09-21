package com.room.api.mask.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserAccountDetailsEntity {
    @Id
    private String userName;

    private String accountNumber;

    private String panNumber;
}
