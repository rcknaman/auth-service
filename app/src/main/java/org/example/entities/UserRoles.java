package org.example.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class UserRoles {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    public long id;

    @Column(name = "name")
    public String name;

}
