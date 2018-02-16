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


public class DeleteEmployeeServlet extends DependencyInjectionServlet {

    @Inject("txManager")
    private TransactionManager txManager;
    @Inject("employeeDao")
    private EmployeeDao employeeDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String command = req.getParameter("command");
        final Integer employeeId = Integer.parseInt(req.getParameter("employeeId"));
        final Integer departmentId = Integer.parseInt(req.getParameter("departmentId"));
        try {
            if(command != null){

                List<Employee> employeeList = txManager.doInTransaction(() -> {
                    employeeDao.deleteById(employeeId);
                    return employeeDao.selectEmployeesOfDepartment(departmentId);
                });
                req.setAttribute("employeeList", employeeList);
                String forward = Path.PAGE_EMPLOYEES_OF_DEPARTMENT;
                req.getRequestDispatcher(forward).forward(req, resp);

            }else{

                List<Employee> employeeList = txManager.doInTransaction(new Callable<List<Employee>>() {
                    @Override
                    public List<Employee> call() throws Exception {
                        employeeDao.deleteById(employeeId);
                        return employeeDao.selectAll();
                    }
                });
                req.setAttribute("employeeList", employeeList);
                String forward = Path.PAGE_LIST_EMPLOYEE;
                req.getRequestDispatcher(forward).forward(req, resp);

            }
        } catch (DataBaseException e) {
            throw new ServletException(e);
        } catch (Exception e){
            throw new ServletException(e);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
