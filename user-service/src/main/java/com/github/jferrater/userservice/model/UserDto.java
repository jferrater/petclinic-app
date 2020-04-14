package com.github.jferrater.userservice.model;

import com.github.jferrater.userservice.repository.document.UserType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME;

/**
 * @author joffryferrater
 */
@Data
@NoArgsConstructor
public class UserDto {

    private String id;
    @NotNull
    private String organization;
    @NotNull
    private String username;
    @NotNull
    private String password;
    private UserType userType;
    private String[] roles;
    private String[] permissions;
    private String fullName;
    private String address;
    private String contactNumber;
    @DateTimeFormat(iso = DATE_TIME)
    private Date created;
    @DateTimeFormat(iso = DATE_TIME)
    private Date updated;
}
