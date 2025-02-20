package edu.icet.senuka.fxhotel_manager.dto;

import edu.icet.senuka.fxhotel_manager.util.types.RoleType;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User {
    private Long id;
    private String username;
    private String password;
    private String email;
    private RoleType role;
}
