package org.example.models;

import org.example.entities.UserInfo;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import org.example.entities.UserInfo;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Getter
@Setter
public class UserInfoDto extends UserInfo {
    private String firstName;
    private String lastName;
    private String userName;
    private Long phoneNo;
    private String email;
}
