package com.hnys.jta.ejb;

import com.hnys.jta.entity.Account;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import java.math.BigDecimal;

@Stateless
public class AccountBeanImpl implements AccountBean {

    @PersistenceContext(unitName = "JTA-PU")
    private EntityManager em;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void credit(Long accountNo, BigDecimal amount) {
        EntityTransaction transaction = em.getTransaction();
        System.out.println("credit : "+System.identityHashCode(transaction));
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
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void debit(Long accountNo, BigDecimal amount) {
        EntityTransaction transaction = em.getTransaction();
        System.out.println("debit : "+System.identityHashCode(transaction));
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
