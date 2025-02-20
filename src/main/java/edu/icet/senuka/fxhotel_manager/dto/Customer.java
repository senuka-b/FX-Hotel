package edu.icet.senuka.fxhotel_manager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    private Long id;
    private String fullName;
    private String phoneNumber;
    private String address;

    @Override
    public String toString() {
        return String.format("[%d] %s", id, fullName);
    }

}
