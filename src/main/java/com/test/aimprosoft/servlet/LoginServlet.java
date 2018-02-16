package com.test.aimprosoft.servlet;

import com.test.aimprosoft.dao.TransactionManager;
import com.test.aimprosoft.dao.user.UserDao;
import com.test.aimprosoft.util.DataBaseException;
import com.test.aimprosoft.entity.User;
import com.test.aimprosoft.inject.Inject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.Callable;


public class LoginServlet extends DependencyInjectionServlet {

    @Inject("txManager")
    private TransactionManager txManager;

    @Inject("userDao")
    private UserDao userDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = null;
        try{
            user = txManager.doInTransaction(() -> userDao.selectByEmail(email));
        }catch (DataBaseException e){
            throw new ServletException(e);

        }catch (Exception e){
            throw new ServletException(e);
        }
        if(user == null || !password.equals(user.getPassword())){
            req.setAttribute("email", email);
            req.setAttribute("error", "Incorrect email or password!");
            req.getRequestDispatcher(Path.PAGE_LOGIN).forward(req, resp);

        } else {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect(getServletContext().getContextPath()+Path.PAGE_START);
        }

    }

}
