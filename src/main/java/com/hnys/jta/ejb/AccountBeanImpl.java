package com.hnys.jta.ejb;

import com.hnys.jta.entity.Account;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import java.math.BigDecimal;

@Stateless
public class AccountBeanImpl implements AccountBean {

    @PersistenceContext(unitName = "JTA-PU")
    private EntityManager em;

    @Override
    public void credit(Long accountNo, BigDecimal amount) {

        try {
            Account account = em.createNamedQuery("Account.findByAccountNo", Account.class)
                    .setParameter("accountNo", accountNo)
                    .getSingleResult();

            account.setBalance(account.getBalance().add(amount));

        } catch (NoResultException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void debit(Long accountNo, BigDecimal amount) {
        try {
            Account account = em.createNamedQuery("Account.findByAccountNo", Account.class)
                    .setParameter("accountNo", accountNo)
                    .getSingleResult();

            account.setBalance(account.getBalance().subtract(amount));

        } catch (NoResultException e) {
            e.printStackTrace();
        }
    }
}
