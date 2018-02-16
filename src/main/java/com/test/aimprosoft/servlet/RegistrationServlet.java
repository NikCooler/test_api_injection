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

/**
 *
 */
public class RegistrationServlet  extends DependencyInjectionServlet{

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
        final String password1 = req.getParameter("password1");
        final String password2 = req.getParameter("password2");
        String error = new Validator().validateRegister(email, password1, password2);

            if(error != null){
                req.setAttribute("email", email);
                req.setAttribute("error", error);
                req.getRequestDispatcher(Path.PAGE_REGISTRATION).forward(req, resp);

            } else{

                try{
                    txManager.doInTransaction(() -> {
                        userDao.insert(new User(email, password1));
                        return null;
                        });
                }catch (DataBaseException e){
                    throw new ServletException(e);
                }catch (Exception e){
                    throw new ServletException(e);
                }
                resp.sendRedirect(getServletContext().getContextPath()+Path.PAGE_LOGIN);

            }



    }
}
