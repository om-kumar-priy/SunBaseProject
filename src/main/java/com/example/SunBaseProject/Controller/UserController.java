package com.example.SunBaseProject.Controller;

import com.example.SunBaseProject.Model.Login;
import com.example.SunBaseProject.Model.Customer;
import com.example.SunBaseProject.Service.CustomerService;
import com.example.SunBaseProject.Service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;



    @PostMapping("/login")
    public String addUser(@RequestBody Login authRequest){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        if(authenticate.isAuthenticated()){
            return jwtService.generateToken(authRequest.getUserName());
        }else {
            throw new UsernameNotFoundException("Invalid user request");
        }
    }


    @PostMapping("/addCustomer")
    public String addCustomer(@RequestBody Customer customer){
        return customerService.addCustomer(customer);

    }


    @GetMapping("/getUsers")
    @PreAuthorize("hasAuthority('ADMIN_ROLES')")
    public List<Customer> getAllCustomer(){
        return customerService.getAllCustomer();
    }



    @GetMapping("/getUsers/{id}")
    @PreAuthorize("hasAuthority('USER_ROLES')")
    public Customer getCustomerById(@PathVariable Integer id){
        return customerService.getCustomerById(id);
    }




    @DeleteMapping("/{Id}/delete")
    @PreAuthorize("hasAuthority('USER_ROLES')")
    public String deleteCustomer(@PathVariable int Id) {
        //delete a Customer

        customerService.deleteCustomer(Id);
        return "Customer Deleted";
    }




@PutMapping("/updateCustomer")
@PreAuthorize("hasAuthority('USER_ROLES')")
    public String  updateCustomer(@RequestBody Customer customer)
    {
        return customerService.updateCustomer(customer);

    }
}