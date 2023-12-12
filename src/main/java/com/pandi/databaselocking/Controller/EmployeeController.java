package com.pandi.databaselocking.Controller;


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


}
