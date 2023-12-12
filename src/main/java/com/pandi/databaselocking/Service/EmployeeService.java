package com.pandi.databaselocking.Service;


import com.pandi.databaselocking.Entities.Employee;
import com.pandi.databaselocking.Repository.EmployeeRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class EmployeeService {


    @Autowired
    EmployeeRepo employeeRepo;

    public Employee saveEmployee(Map<String,String> data){

        Employee employee = new Employee();
        employee.setName(data.get("name"));
        employee.setRole(data.get("role"));

       return employeeRepo.save(employee);

    }

    public void updateEmployee(Long id ,String role){

       ExecutorService executors =  Executors.newFixedThreadPool(2);

       Runnable r1 = () -> updateEmployee1(id,role);
       Runnable r2 = () -> {
           try {
               updateEmployee2(id,role);
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }
       };

       executors.execute(r1);
       executors.execute(r2);

       executors.shutdown();

    }

    @Transactional
    public void updateEmployee1(Long id, String role){
        Optional<Employee> employeeOptional = employeeRepo.findById(id);
        Employee employee = employeeOptional.get();
        System.out.println("version first"+ employee.getVersion());
        employee.setRole("user");
        employeeRepo.save(employee);
    }

    @Transactional
    public void updateEmployee2(Long id,String role) throws InterruptedException {

        Thread.sleep(3000);
        Optional<Employee> employeeOptional = employeeRepo.findById(id);

        Employee employee = employeeOptional.get();
        System.out.println("version second"+ employee.getVersion());
        employee.setRole("staff");
        employeeRepo.save(employee);

    }
}
