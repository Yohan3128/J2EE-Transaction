package com.hnys.jta;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/test")
public class Test extends HttpServlet {

    @PersistenceContext(unitName = "JTA-PU")
    private EntityManager em;

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JTA-PU");
//        EntityManager em = emf.createEntityManager();

//        EntityManager em = ManagerFactory.getEntityManager();

        List<Object[]> resultList = em.createNativeQuery("select * from users").getResultList();
        resultList.forEach(r -> {
            System.out.println(r[0] +" : "+r[1] +" : "+r[2] +" : "+r[3]);
        });

//        System.out.println(em);
    }
}
