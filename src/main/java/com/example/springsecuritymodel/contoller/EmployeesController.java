package com.example.springsecuritymodel.contoller;

import com.example.springsecuritymodel.dao.EmployeesDao;
import com.example.springsecuritymodel.entities.Employee;
import com.example.springsecuritymodel.security.annotations.employees.IsEmployeeCreate;
import com.example.springsecuritymodel.security.annotations.employees.IsEmployeeDelete;
import com.example.springsecuritymodel.security.annotations.employees.IsEmployeeRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class EmployeesController {

    @Autowired
    private EmployeesDao employeesDao;


    @PreAuthorize("hasRole('ROLE_EMPLOYEES_READ')")
    @GetMapping("/employees")
    public ModelAndView index(){
        return new ModelAndView("employees","employees",employeesDao.findAll());
    }


    @PreAuthorize("hasRole('ROLE_EMPLOYEES_READ')")
    @GetMapping("/employees/create")
    public ModelAndView create(){
        return new ModelAndView("employees-create","employee",new Employee());
    }


    @PreAuthorize("'TEST'.equals(#employee.getFirstName()) || hasRole('ROLE_EMPLOYEES_CREATE')")
    @PostMapping("/employees/create")
    public String create(@ModelAttribute @Valid Employee employee, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "employees-create";
        } else {
            employeesDao.save(employee);
        }
        return "redirect:/employees";
    }


    @PreAuthorize("#id <= 2 || hasRole('ROLE_EMPLOYEES_DELETE')")
    @GetMapping("/employees/delete/{id}")
    public String delete(@PathVariable int id){
        employeesDao.deleteById(id);
        return "redirect:/employees";
    }
}
