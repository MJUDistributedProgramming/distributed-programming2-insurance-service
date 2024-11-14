package com.example.insuranceservice.domain.card.entity;

import com.example.insuranceservice.domain.paymentInfo.entity.PaymentInfo;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Entity
@Builder
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Integer id;
    private String cardNum;
    private String cvcNum;
    private String password;

    @ManyToOne
    @JoinColumn(name = "payment_info_id")
    private PaymentInfo paymentInfo;
}
