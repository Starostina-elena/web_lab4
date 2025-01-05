package org.lia.lab4_v3.DBEntity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;


@Entity
@Table(name = "users", schema = "s414257")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }
}