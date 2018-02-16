package com.test.aimprosoft.servlet;

import com.test.aimprosoft.dao.employee.EmployeeDao;
import com.test.aimprosoft.dao.TransactionManager;
import com.test.aimprosoft.util.DataBaseException;
import com.test.aimprosoft.entity.Employee;
import com.test.aimprosoft.inject.Inject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;


public class ListEmployeeServlet extends DependencyInjectionServlet {

    @Inject("txManager")
    private TransactionManager txManager;
    @Inject("employeeDao")
    private EmployeeDao employeeDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
                List<Employee> employeeList = txManager.doInTransaction(() -> employeeDao.selectAll());

                req.setAttribute("employeeList", employeeList);
        } catch (DataBaseException e) {
            throw new ServletException(e);
        } catch (Exception e){
            throw new ServletException(e);
        }
        String forward = Path.PAGE_LIST_EMPLOYEE;
        req.getRequestDispatcher(forward).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
