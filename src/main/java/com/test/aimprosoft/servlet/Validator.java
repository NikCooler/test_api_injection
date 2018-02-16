package com.test.aimprosoft.servlet;

import com.test.aimprosoft.bean.EmployeeBean;
import com.test.aimprosoft.dao.department.DeptDao;
import com.test.aimprosoft.dao.department.DeptDaoImpl;
import com.test.aimprosoft.dao.TransactionManager;
import com.test.aimprosoft.dao.TransactionManagerImpl;
import com.test.aimprosoft.dao.user.UserDao;
import com.test.aimprosoft.dao.user.UserDaoImpl;
import com.test.aimprosoft.entity.Department;
import com.test.aimprosoft.util.DataBaseException;

import javax.servlet.ServletException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 *
 */
public class Validator {

    private static final String CHECK_MAIL_REGEX = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";

    protected Map<String,String> deptValidate(Department newDepartment) throws Exception{

       final String name = newDepartment.name;

        Map<String,String> errors= new HashMap<>();
       if(name.length()<3){
           errors.put("nameLength", "Name should be more then 2 characters");
           return errors;
       } else if(name.length()>=100){
           errors.put("nameLength", "Name should be less then 100 characters");
           return errors;
       }
        final DeptDao deptDao = new DeptDaoImpl();
        TransactionManager txManager = new TransactionManagerImpl();
        Department department = txManager.doInTransaction(() -> deptDao.selectByName(name));

        if(department != null){
            errors.put("NotUniqueName", "Department with this name is already registered!");
        }
        return errors;

    }

    protected Map<String,String> emplValidate(EmployeeBean newEmployee){

        String firstName = newEmployee.firstName;
        String lastName = newEmployee.lastName;
        String absenteeism = newEmployee.absenteeism;
        Integer departmentId = newEmployee.department.departmentId;

        Map<String,String> errors= new HashMap<>();

        if(firstName.length()<2){
            errors.put("firstname", "Firstname should be more then 2 characters");
        }else if(firstName.length()>15){
            errors.put("firstname", "Firstname should be less then 15 characters");
        }
        if(lastName.length()<2){
            errors.put("lastname", "Lastname should be more then 2 characters");
        }else if(lastName.length()>15){
            errors.put("lastname", "Lastname should be less then 15 characters");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            sdf.parse(newEmployee.dateOfBirth);
        } catch (ParseException e) {
            errors.put("dateOfBirth", "Date must be entered correct");
        }

        try {
            Integer tmpAbsenteeism = Integer.valueOf(absenteeism);
            if(tmpAbsenteeism == null || !(0<=tmpAbsenteeism && tmpAbsenteeism < 100)){

                errors.put("absenteeism", "Absenteeism must be entered (from 0 to 100)");
            }
        }catch (NumberFormatException e){
            errors.put("absenteeism", "Absenteeism must be entered (from 0 to 100)");
        }

        if(departmentId == null){
            errors.put("departmentId", "department should be selected" );
        }
        return errors;
    }

    protected String validateRegister(final String email, String password1, String password2) throws ServletException{

        Boolean isUserExist = false;
        if(!email.matches(CHECK_MAIL_REGEX)){
            return "Invalid email!";
        }
        TransactionManager txManager = new TransactionManagerImpl();
        final UserDao userDao = new UserDaoImpl();
        try{
            isUserExist = txManager.doInTransaction(() -> userDao.isUserExist(email));
        }catch (DataBaseException e){
            throw new ServletException(e);
        }catch (Exception e){
            throw new ServletException(e);
        }
        if(isUserExist)
            return "This user is already exist";

        if(password1.length()<6)
            return "Password must be more than 6 symbols";

        if(password1.length()>60)
            return "Password must be less than 60 symbols";

        if(!password1.equals(password2))
            return "Passwords are not equals";

        return null;
    }

}
