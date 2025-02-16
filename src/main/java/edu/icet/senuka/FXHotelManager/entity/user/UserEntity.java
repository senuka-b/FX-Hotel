package edu.icet.senuka.FXHotelManager.entity.user;

import edu.icet.senuka.FXHotelManager.util.RoleType;
import jakarta.persistence.*;



@Entity
@Table(name = "User")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private RoleType role;

}
