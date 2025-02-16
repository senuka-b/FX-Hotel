package edu.icet.senuka.FXHotelManager.dto;

import edu.icet.senuka.FXHotelManager.util.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private Long id;
    private String username;
    private String password;
    private RoleType role;
}
