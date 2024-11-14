package com.example.insuranceservice.domain.paymentInfo.repository;

import com.example.insuranceservice.domain.paymentInfo.entity.PaymentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentInfoRepository extends JpaRepository<PaymentInfo, Integer> {
    List<PaymentInfo> findByIdBetween(int a, int b);
}
