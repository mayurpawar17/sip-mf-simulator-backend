package com.example.sipmfsimulatorbackend.features.auth.entity;

import com.example.sipmfsimulatorbackend.core.utils.enums.Role;
import jakarta.persistence.*;
import lombok.*;
//import org.springframework.data.annotation.Id;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String mobileNumber;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean enabled = true;

}


