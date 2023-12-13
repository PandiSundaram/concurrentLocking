package com.pandi.databaselocking.Service;


import com.pandi.databaselocking.Entities.Bus;
import com.pandi.databaselocking.Entities.Ticket;
import com.pandi.databaselocking.Repository.BusRepo;
import com.pandi.databaselocking.Repository.TicketRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class TicketService {

    @Autowired
    BusRepo busRepo;

    @Autowired
    TicketRepo ticketRepo;
    public Bus saveBusDetails(Map<String,String> payload){

        Bus bus = new Bus();
        bus.setBusName(payload.get("name"));
        bus.setCapacity(Integer.parseInt(payload.get("capacity")));
        busRepo.save(bus);
        return bus;
    }


    @Transactional
    public void bookticket(){

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Runnable r1 = () -> person1(1L);
        Runnable r2 = () -> {
            try {
                person2(1L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        executorService.execute(r1);
        executorService.execute(r2);
    }

    @Transactional
    public void person1(Long id){

       Bus bus = busRepo.getbusdetails(id);
       System.out.println("bus1"+ bus);
       if(bus.getTickets()!=null && bus.getCapacity() <= bus.getTickets().size()){
           System.out.println("ticket filled");
       }
       else {
           Ticket ticket = new Ticket();
           ticket.setCustomerName("pandi");
           ticket.setBus(bus);
           ticketRepo.save(ticket);
       }
    }

    @Transactional
    public void person2(Long id) throws InterruptedException {
        Thread.sleep(1000);
        Bus bus = busRepo.getbusdetails(id);
        System.out.println("bus2"+ bus);

        if(bus.getTickets()!=null && bus.getCapacity() <= bus.getTickets().size()){
            System.out.println("ticket filled");
        }else {

            Ticket ticket = new Ticket();
            ticket.setCustomerName("sundaram");
            ticket.setBus(bus);
            ticketRepo.save(ticket);
        }

    }

}
