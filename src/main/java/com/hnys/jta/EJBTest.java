package com.hnys.jta;

import com.hnys.jta.ejb.UserBean;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/ejb_test")
public class EJBTest extends HttpServlet {

    @EJB
    private UserBean userBean;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userBean.register("Yohan","Yohansilva@gmail.com","1234");
    }
}
