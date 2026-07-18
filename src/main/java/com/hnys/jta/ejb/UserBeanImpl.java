package com.hnys.jta.ejb;

import com.hnys.jta.entity.User;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;

import java.math.BigDecimal;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UserBeanImpl implements UserBean{

    @PersistenceContext(unitName = "JTA-PU")
    private EntityManager em;

    @EJB
    AccountBean accountBean;


//    @Resource
//    private UserTransaction tr;

    @Override
    public boolean login(String username, String password) {
        return false;
    }

    @PostConstruct
    public void test(){

        System.out.println(
                em.getMetamodel()
                        .getEntities()
        );

    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void register(String name, String email, String password) {

//        Session session = em.unwrap(Session.class); /// this not recommend for SessionBean and MessageDrivenBean

//        Transaction transaction = session.beginTransaction();

//        try {
//            tr.begin();
//        } catch (NotSupportedException e) {
//            throw new RuntimeException(e);
//        } catch (SystemException e) {
//            throw new RuntimeException(e);
//        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        em.persist(user);

//        try {
//            tr.commit();
//        } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SystemException e) {
//            throw new RuntimeException(e);
//        }

//        session.persist(user);
//        transaction.commit();

    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void transfer(Long from, Long to, BigDecimal amount) {

        EntityTransaction transaction = em.getTransaction();
        System.out.println("transfer : "+System.identityHashCode(transaction));

        accountBean.debit(from,amount); //JTA + IIOP
        accountBean.credit(to,amount);
    }
}
