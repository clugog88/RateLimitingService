package com.metaphorce.ratelimit.persistence.entities;

import java.io.Serializable;

import com.metaphorce.ratelimit.persistence.entities.enums.UserRoleEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue
    private Long id;
    private String name;
    private String lastname;
    private String email;
    private String phone;
    private Integer age;
    private UserRoleEnum role;

}
