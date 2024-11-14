package com.example.insuranceservice.domain.card.dto;

import com.example.insuranceservice.domain.card.entity.Card;
import com.example.insuranceservice.domain.paymentInfo.entity.PaymentInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Builder
@Slf4j
@AllArgsConstructor
public class CardDto {
    private String cardNum;
    private String cvcNum;
    private String password;
    private PaymentInfo paymentInfo;


    public Card toEntity() {
        return Card.builder()
                .cardNum(this.cardNum)
                .cvcNum(this.cvcNum)
                .password(this.password)
                .paymentInfo(this.paymentInfo)
                .build();
    }

    public CardDto (Card card) {
        this.cardNum = card.getCardNum();
        this.cvcNum = card.getCvcNum();
        this.password = card.getPassword();
        this.paymentInfo = card.getPaymentInfo();
    }
}
