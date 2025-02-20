package edu.icet.senuka.fxhotel_manager.dto;

import edu.icet.senuka.fxhotel_manager.util.types.AvailabilityType;
import edu.icet.senuka.fxhotel_manager.util.types.RoomType;
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
    private AvailabilityType availability;
    private Double pricePerNight;

    @Override
    public String toString() {
        return String.format("[%d] %s", id, roomType);
    }
}
