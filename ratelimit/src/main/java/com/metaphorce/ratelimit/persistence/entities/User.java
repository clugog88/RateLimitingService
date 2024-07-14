package com.metaphorce.ratelimit.persistence.entities;

import com.metaphorce.ratelimit.persistence.entities.enums.UserRoleEnum;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "_User")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    private Long id;
    private String name;
    private String lastname;
    private String email;
    private String phone;
    private Integer age;
    private UserRoleEnum role;

}
