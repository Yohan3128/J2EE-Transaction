package com.hnys.jta;

import com.hnys.jta.ejb.UserBean;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/ejb_test")
public class EJBTest extends HttpServlet {

    @EJB
    private UserBean userBean;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        userBean.register("Yohan","supun@gmail.com","1234");

        userBean.transfer(1002000401L,1002000402L, new BigDecimal(5000));
    }
}
