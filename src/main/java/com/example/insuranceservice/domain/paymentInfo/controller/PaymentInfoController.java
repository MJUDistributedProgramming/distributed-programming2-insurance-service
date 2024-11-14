package com.example.insuranceservice.domain.paymentInfo.controller;

import com.example.insuranceservice.domain.automatic.dto.AutomaticDto;
import com.example.insuranceservice.domain.bank.dto.BankDto;
import com.example.insuranceservice.domain.card.dto.CardDto;
import com.example.insuranceservice.domain.paymentInfo.dto.PaymentInfoDetailDto;
import com.example.insuranceservice.domain.paymentInfo.dto.PaymentInfoDto;
import com.example.insuranceservice.domain.paymentInfo.service.PaymentInfoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/paymentInfo")
@RestController
public class PaymentInfoController {

    private PaymentInfoService paymentInfoService;

    private PaymentInfoController(PaymentInfoService paymentInfoService){
        this.paymentInfoService = paymentInfoService;
    }
    @PostMapping("/create")
    private int createPayment(@RequestBody PaymentInfoDto paymentInfoDto){
       return paymentInfoService.createPayment(paymentInfoDto);
    }
    @PostMapping("/setCardInfo")
    private void setCardInfo(@RequestBody CardDto dto, @PathVariable int payementInfoId){
        paymentInfoService.setCardInfo(dto,payementInfoId);

    }
    @PostMapping("/setBankInfo")
    private void setBankInfo(@RequestBody BankDto dto, @PathVariable int payementInfoId){
        paymentInfoService.setBankInfo(dto,payementInfoId);
    }
    @PostMapping("/setAutomaticInfo")
    private void setAutomaticInfo(@RequestBody AutomaticDto dto, @PathVariable int payementInfoId){
        paymentInfoService.setAutomaticInfo(dto,payementInfoId);
    }
    @GetMapping("/list")
    private List<PaymentInfoDetailDto> getAllPaymentInfo(){
        return paymentInfoService.getAllPaymentInfo();
    }

    @GetMapping("/list/{id}")
    private PaymentInfoDetailDto getAllPaymentInfo(@PathVariable Integer id){
        return paymentInfoService.getAllPaymentInfo(id);
    }

}
