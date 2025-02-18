package edu.icet.senuka.FXHotelManager.entity;
import edu.icet.senuka.FXHotelManager.util.types.Availability;
import edu.icet.senuka.FXHotelManager.util.types.RoomType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Room")
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoomType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Availability availability = Availability.Available;

    @Column(nullable = false)
    private Double pricePerNight;
}
