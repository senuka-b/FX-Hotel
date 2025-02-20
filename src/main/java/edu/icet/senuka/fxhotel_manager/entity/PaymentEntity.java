package edu.icet.senuka.fxhotel_manager.entity;

import edu.icet.senuka.fxhotel_manager.util.types.PaymentType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;

@Data
@Entity
@Table(name = "payment")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private ReservationEntity reservation;

    private Double amountPaid;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
}
