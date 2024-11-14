package com.example.insuranceservice.domain.paymentInfo.dto;

import com.example.insuranceservice.domain.automatic.dto.AutomaticDto;
import com.example.insuranceservice.domain.bank.dto.BankDto;
import com.example.insuranceservice.domain.card.dto.CardDto;
import com.example.insuranceservice.domain.contract.dto.ContractDetailDto;
import com.example.insuranceservice.domain.paymentInfo.entity.PaymentInfo;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class PaymentInfoDetailDto {
    private String paymentType;
    private String fixedMonthlyPaymentDate;
    private Integer fixedMonthlyPayment;
    private ContractDetailDto contractDetailDto;
    private List<CardDto> cardList;
    private List<BankDto> bankList;
    private List<AutomaticDto> automaticList;

    public PaymentInfoDetailDto(PaymentInfo paymentInfo) {
        this.paymentType = paymentInfo.getPaymentType();
        this.fixedMonthlyPaymentDate = paymentInfo.getFixedMonthlyPaymentDate();
        this.fixedMonthlyPayment = paymentInfo.getFixedMonthlyPayment();
        this.contractDetailDto = new ContractDetailDto(paymentInfo.getContract());
        this.cardList = paymentInfo.getCardList().stream().map(CardDto::new).collect(Collectors.toList());
        this.bankList = paymentInfo.getBankList().stream().map(BankDto::new).collect(Collectors.toList());
        this.automaticList = paymentInfo.getAutomaticList().stream().map(AutomaticDto::new).collect(Collectors.toList());
    }
}
