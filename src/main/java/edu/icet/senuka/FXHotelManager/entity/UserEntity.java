package edu.icet.senuka.FXHotelManager.entity;

import edu.icet.senuka.FXHotelManager.util.types.RoleType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CollectionId;


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
