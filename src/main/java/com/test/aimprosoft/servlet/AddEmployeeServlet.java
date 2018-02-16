package com.test.aimprosoft.servlet;

import com.test.aimprosoft.bean.EmployeeBean;
import com.test.aimprosoft.dao.department.DeptDao;
import com.test.aimprosoft.dao.employee.EmployeeDao;
import com.test.aimprosoft.dao.TransactionManager;
import com.test.aimprosoft.util.DataBaseException;
import com.test.aimprosoft.entity.Department;
import com.test.aimprosoft.entity.Employee;
import com.test.aimprosoft.inject.Inject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;


public class AddEmployeeServlet extends DependencyInjectionServlet {

    @Inject("deptDao")
    private DeptDao deptDao;
    @Inject("txManager")
    private TransactionManager txManager;
    @Inject("employeeDao")
    private EmployeeDao employeeDao;

    private static final String DEPARTMENT_ID = "departmentId";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String command = req.getParameter("command");
        try {
            List<Department> departmentList = txManager.doInTransaction(new Callable<List<Department>>() {
                @Override
                public List<Department> call() throws Exception {

                    return deptDao.selectAll();
                }
            });
            if(command != null) req.setAttribute("command", "employeesOfDept");
            req.setAttribute("departmentList", departmentList);
        } catch (Exception e) {
            throw new ServletException(e);
        }
        String forward = Path.PAGE_ADD_EMPLOYEE;
        req.getRequestDispatcher(forward).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String command = req.getParameter("command"); // employees of dept

        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String tmpDateOfBirth = req.getParameter("dateOfBirth");
        String absenteeism = req.getParameter("absenteeism");
        String tmpdepartmentId = req.getParameter(DEPARTMENT_ID);
        Integer departmentId;
        try {
            departmentId = Integer.valueOf(tmpdepartmentId);
        }catch (NumberFormatException e){
             departmentId = null;
        }

        Department department = new Department(departmentId);
        EmployeeBean employeeBean = new EmployeeBean(
                firstname,
                lastname,
                tmpDateOfBirth,
                absenteeism,
                department);

        Map<String,String> errors = new Validator()
                .emplValidate(employeeBean);
        try {
            if(errors.isEmpty()){

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                java.util.Date dateOfbirth = sdf.parse(tmpDateOfBirth);

                final Employee employee = new Employee(
                        firstname,
                        lastname,
                        dateOfbirth,
                        Integer.valueOf(absenteeism),
                        new Department(Integer.valueOf(tmpdepartmentId))
                );
                if(!command.equals("")){
                    final Integer id = departmentId;
                        List<Employee> employeeList = txManager.doInTransaction(new Callable<List<Employee>>() {
                        @Override
                        public List<Employee> call() throws Exception {
                            employeeDao.insert(employee);
                            return employeeDao.selectEmployeesOfDepartment(id);
                        }
                    });
                    req.setAttribute("employeeList", employeeList);
                    String forward = Path.PAGE_EMPLOYEES_OF_DEPARTMENT;
                    req.getRequestDispatcher(forward).forward(req, resp);
                }else{
                List<Employee> employeeList = txManager.doInTransaction(new Callable<List<Employee>>() {
                    @Override
                    public List<Employee> call() throws Exception {
                        employeeDao.insert(employee);
                        return employeeDao.selectAll();
                    }
                });
                req.setAttribute("employeeList", employeeList);
                String forward = Path.PAGE_LIST_EMPLOYEE;
                req.getRequestDispatcher(forward).forward(req, resp);
                }
            }else {

                List<Department> departmentList = txManager.doInTransaction(() -> deptDao.selectAll());
                if(!command.equals("")) {
                    req.setAttribute("command", command);
                }
                req.setAttribute("errors", errors);
                req.setAttribute("departmentList", departmentList);
                req.setAttribute("employee", employeeBean);

                String forward = Path.PAGE_ADD_EMPLOYEE;
                req.getRequestDispatcher(forward).forward(req, resp);
            }

        } catch (DataBaseException e) {
            throw new ServletException(e);
        } catch (ParseException e) {
            throw new ServletException(e);
        } catch (Exception e){
            throw new ServletException(e);
        }
    }
}
