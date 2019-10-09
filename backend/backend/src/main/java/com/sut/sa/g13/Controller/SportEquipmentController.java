package com.sut.sa.g13.Controller;

import java.util.Collection;
import java.util.stream.Collectors;

import com.sut.sa.g13.Entity.*;
import com.sut.sa.g13.Repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class SportEquipmentController {
    @Autowired
    private SportEquipmentRepository sportEquipmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private TypeEquipmentRepository typeEquipmentRepository;

        @GetMapping("/sportEquipment")
        public Collection<SportEquipment> sportEquipment(){
            return sportEquipmentRepository.findAll().stream().collect(Collectors.toList());
        }

     

        @GetMapping("/status")
        public Collection<Status> status(){
            return statusRepository.findAll().stream().collect(Collectors.toList());
        }

        @GetMapping("/typeEquipment")
        public Collection<TypeEquipment> typeEquipment(){
            return typeEquipmentRepository.findAll().stream().collect(Collectors.toList());
        }


        @PostMapping("/sportEquipment/{employeename}/{typeequipmentid}/{type}")
        public SportEquipment newsportEquipment(SportEquipment newsportEquipment,
                @PathVariable Long employeename, //edit
                @PathVariable Long typeequipmentid,
                @PathVariable Long type //edit
                ) {
                    
                    Employee employee = employeeRepository.findByEmployeeid(employeename); //edit
                    TypeEquipment typeEquipment = typeEquipmentRepository.findByTypeequipmentid(typeequipmentid);
                    Status status = statusRepository.findBystatusid(type);//edit

                    newsportEquipment.setEmployeeid(employee); //edit
                    newsportEquipment.setTypeequipmentid(typeEquipment);
                    newsportEquipment.setType(status);
                    return sportEquipmentRepository.save(newsportEquipment);

            }

}

