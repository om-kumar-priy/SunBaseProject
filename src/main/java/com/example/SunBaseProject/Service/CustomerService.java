package com.example.SunBaseProject.Service;

import com.example.SunBaseProject.Model.Customer;
import com.example.SunBaseProject.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements UserDetailsService
{
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
   private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> userInfo = customerRepository.findByEmail(username);
        return userInfo.map(UserInfoDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"+username));
   }





    public String addCustomer(Customer customer){

        // we can check Email here valid on not By customer.getMail then check all character ie @ ,Number,alphabet & length
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
       customerRepository.save(customer);//save in DB
        return "User added successfully";
    }
    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();//give list of Customer
    }
    public Customer getCustomerById(Integer id){
        return customerRepository.findById(id).get();
    }

    public void deleteCustomer(Integer id){

        customerRepository.deleteById(id);
    }


    public String  updateCustomer(Customer customer) {
        Customer c=customerRepository.findById(customer.getId()).get();
        if(c==null)
            return "Customer not found";
        c.setFirst_name(customer.getFirst_name());
        c.setLast_name(customer.getLast_name());
        c.setCity(customer.getCity());
        c.setAddress(customer.getAddress());
        c.setPhone(customer.getPhone());
        c.setStreet(customer.getStreet());
        customerRepository.save(c);
       return "Customer updated";
    }
}