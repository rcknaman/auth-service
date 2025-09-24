package org.example.request;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RefreshTokenDto {
    private String token;

}
