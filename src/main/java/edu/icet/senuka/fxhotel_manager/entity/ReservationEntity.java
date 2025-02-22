package edu.icet.senuka.fxhotel_manager.entity;

import edu.icet.senuka.fxhotel_manager.util.types.ReservationStatusType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CustomerEntity customer;

    @OneToOne
    @JoinColumn(nullable = false)
    private RoomEntity room;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatusType status;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PaymentEntity> payments;


}
