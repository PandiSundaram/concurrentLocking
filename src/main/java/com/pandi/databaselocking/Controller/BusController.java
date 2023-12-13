package com.pandi.databaselocking.Controller;


import com.pandi.databaselocking.Entities.Bus;
import com.pandi.databaselocking.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/bus")
public class BusController {


    @Autowired
    TicketService ticketService;

    @PostMapping
    public ResponseEntity saveBusDetails(@RequestBody Map<String,String> payload){

        Bus bus = ticketService.saveBusDetails(payload);
        return new ResponseEntity(bus, HttpStatus.CREATED);
    }

    @GetMapping("/ticket")
    public ResponseEntity bookTicket(){
        ticketService.bookticket();
       return new ResponseEntity(true,HttpStatus.OK);
    }


}
