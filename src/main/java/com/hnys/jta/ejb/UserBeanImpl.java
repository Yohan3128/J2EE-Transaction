package com.hnys.jta.ejb;

import com.hnys.jta.entity.User;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Stateless
public class UserBeanImpl implements UserBean{

    @PersistenceContext(unitName = "JTA-PU")
    private EntityManager em;

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
    public boolean register(String name, String email, String password) {

//        Session session = em.unwrap(Session.class);

//        Transaction transaction = session.beginTransaction();

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        em.persist(user);
//        session.persist(user);
//        transaction.commit();

        return true;
    }
}
