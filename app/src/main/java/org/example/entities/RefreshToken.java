package org.example.entities;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.time.Instant;

@Builder
@NoArgsConstructor
@Entity
@Table(name = "refresh_token")
@Data
@AllArgsConstructor
public class RefreshToken {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String token;

    private Instant expiryDate;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "user_id")
    private UserInfo userInfo;
}
