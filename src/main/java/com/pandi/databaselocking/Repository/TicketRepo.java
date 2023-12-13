package com.pandi.databaselocking.Repository;

import com.pandi.databaselocking.Entities.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepo extends CrudRepository<Ticket,Long> {
}
