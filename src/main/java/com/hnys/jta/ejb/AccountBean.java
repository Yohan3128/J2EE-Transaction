package com.hnys.jta.ejb;

import jakarta.ejb.Local;

import java.math.BigDecimal;

@Local
public interface AccountBean {
    void credit(Long accountNo, BigDecimal amount);
    void debit(Long accountNo, BigDecimal amount);
}
