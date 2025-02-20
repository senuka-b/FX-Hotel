package edu.icet.senuka.fxhotel_manager.entity;

import edu.icet.senuka.fxhotel_manager.util.types.RoleType;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "User")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private RoleType role;

}
