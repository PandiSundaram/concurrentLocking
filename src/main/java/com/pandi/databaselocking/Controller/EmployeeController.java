package com.pandi.databaselocking.Controller;


import com.pandi.databaselocking.Entities.Employee;
import com.pandi.databaselocking.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {


    @Autowired
     EmployeeService employeeService;

    @PostMapping
    public ResponseEntity saveEmployee(@RequestBody Map<String,String> requestMap){

        return new ResponseEntity( employeeService.saveEmployee(requestMap), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/{role}")
    public ResponseEntity updateEmployee(@PathVariable Long  id,@PathVariable String role ){

          employeeService.updateEmployee(id,role);
          return  new ResponseEntity("success",HttpStatus.OK);

    }

    @PutMapping("/named/{name}/{id}")
    public ResponseEntity findrecodeByName(@PathVariable("name") String name,@PathVariable("id") Long id){
          employeeService.findRecordusingName(name,id);

         return  new ResponseEntity("success",HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity findByName(@PathVariable("name") String name){
        Employee employee = employeeService.findbyName(name);
        System.out.println(employee);

        return  new ResponseEntity(employee,HttpStatus.OK);
    }


}
