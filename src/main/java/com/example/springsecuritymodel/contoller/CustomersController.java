package com.example.springsecuritymodel.contoller;

import com.example.springsecuritymodel.dao.CustomersDao;
import com.example.springsecuritymodel.entities.Customer;
import com.example.springsecuritymodel.security.annotations.customers.IsCustomerCreate;
import com.example.springsecuritymodel.security.annotations.customers.IsCustomerDelete;
import com.example.springsecuritymodel.security.annotations.customers.IsCustomerRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class CustomersController {

    @Autowired
    private CustomersDao customersDao;

    @IsCustomerRead
    @GetMapping("/customers")
    public ModelAndView index(){
        return new ModelAndView("customers","customers",customersDao.findAll());
    }

    @IsCustomerCreate
    @GetMapping("/customers/create")
    public ModelAndView create(){
        return new ModelAndView("customers-create","customer",new Customer());
    }

    @IsCustomerCreate
    @PostMapping("/customers/create")
    public String create(@ModelAttribute @Valid Customer customer, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "customers-create";
        } else {
            customersDao.save(customer);
           return "redirect:/customers"; // reach @GetMapping("/customers")
            // destory old request and start new request for /customers
           // return "forward:/customers"; // same request untill reach /customers
        }

    }

    @IsCustomerDelete
    @GetMapping("customers/delete/{id}")
    public String delete(@PathVariable Integer id){
        customersDao.deleteById(id);
        return "redirect:/customers";
    }
}
