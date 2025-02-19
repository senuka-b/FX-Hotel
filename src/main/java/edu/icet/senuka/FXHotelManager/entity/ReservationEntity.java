package edu.icet.senuka.FXHotelManager.entity;

import edu.icet.senuka.FXHotelManager.util.types.ReservationStatusType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "reservation")
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "checkInCheckOutID", nullable = true)
    private CheckInOutEntity checkInOut;

    @ManyToOne
    @JoinColumn(nullable = false)
    private CustomerEntity customer;

    @OneToOne
    @JoinColumn(nullable = false)
    private RoomEntity room;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatusType status;


}
