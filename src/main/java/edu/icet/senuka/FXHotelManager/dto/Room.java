package edu.icet.senuka.FXHotelManager.dto;

import edu.icet.senuka.FXHotelManager.util.types.Availability;
import edu.icet.senuka.FXHotelManager.util.types.RoomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {
    private Long id;
    private RoomType roomType;
    private Availability availability;
    private Double pricePerNight;
}
