package com.sut.sa.g13.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;

import com.sut.sa.g13.Entity.Customer;
import com.sut.sa.g13.Entity.Employee;
import com.sut.sa.g13.Entity.Category;
import com.sut.sa.g13.Entity.PackageType;
import com.sut.sa.g13.Entity.PaymentMember;
import com.sut.sa.g13.Repository.CustomerRepository;
import com.sut.sa.g13.Repository.EmployeeRepository;
import com.sut.sa.g13.Repository.CategoryRepository;
import com.sut.sa.g13.Repository.PackageTypeRepository;
import com.sut.sa.g13.Repository.PaymentMemberRepository;


import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class PaymentMemberController {
    @Autowired
    private PaymentMemberRepository paymentmemberRepository;
    @Autowired
    private PackageTypeRepository packagetypeRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CategoryRepository categoryRepository;

/*     PaymentMemberController(PaymentMemberRepository paymentmemberRepository) {
        this.paymentmemberRepository = paymentmemberRepository;
    } */

    @GetMapping("/paymentmember")
    public Collection<PaymentMember>  PaymentMembers() {
        return paymentmemberRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/packagetype")
    public Collection<PackageType> Packagetypes() {
        return packagetypeRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/paymentmember/{customername}/{employeename}/{categoryid}/{packagetypeid}")
    public PaymentMember newPaymentMember(PaymentMember newPaymentMember,
    @PathVariable Long customername, //edit
    @PathVariable Long employeename, //edit
    @PathVariable Long categoryid, //edit
    @PathVariable Long packagetypeid) {

    Customer customer = customerRepository.findByCustomerid(customername); //edit
    Employee employee = employeeRepository.findByEmployeeid(employeename); //edit
    Category category  = categoryRepository.findByCategoryid(categoryid); //edit
    PackageType type = packagetypeRepository.findByPackagetypeid(packagetypeid);
        
    newPaymentMember.setCustomerid(customer); //edit
    newPaymentMember.setEmployeeid(employee); //edit
    newPaymentMember.setCategoryid(category); //edit
    newPaymentMember.setPackagetypeid(type);

    return paymentmemberRepository.save(newPaymentMember);
    
    }
}