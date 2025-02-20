package edu.icet.senuka.fxhotel_manager.entity;
import edu.icet.senuka.fxhotel_manager.util.types.AvailabilityType;
import edu.icet.senuka.fxhotel_manager.util.types.RoomType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "Room")
@EqualsAndHashCode
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoomType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AvailabilityType availability = AvailabilityType.Available;

    @Column(nullable = false)
    private Double pricePerNight;
}
