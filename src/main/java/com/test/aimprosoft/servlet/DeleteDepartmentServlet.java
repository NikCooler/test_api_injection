package com.test.aimprosoft.servlet;

import com.test.aimprosoft.dao.department.DeptDao;
import com.test.aimprosoft.dao.TransactionManager;
import com.test.aimprosoft.util.DataBaseException;
import com.test.aimprosoft.entity.Department;
import com.test.aimprosoft.inject.Inject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;


public class DeleteDepartmentServlet extends DependencyInjectionServlet {

    @Inject("deptDao")
    private DeptDao deptDao;
    @Inject("txManager")
    private TransactionManager txManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final Integer departmentId = Integer.valueOf(req.getParameter("departmentId"));
        try {
            List<Department> departmentList = txManager.doInTransaction(() -> {
                deptDao.deleteById(departmentId);
                return deptDao.selectAll();
            });

            req.setAttribute("departmentList", departmentList);
        } catch (DataBaseException e) {
            throw new ServletException(e);
        } catch (Exception e){
            throw new ServletException(e);
        }
        String forward = Path.PAGE_DEPARTMENT;
        req.getRequestDispatcher(forward).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
