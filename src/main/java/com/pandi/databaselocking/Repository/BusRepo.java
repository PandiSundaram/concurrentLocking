package com.pandi.databaselocking.Repository;

import com.pandi.databaselocking.Entities.Bus;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface BusRepo extends CrudRepository<Bus,Long> {


    @Transactional
    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    @Query(value = "select b from Bus b where id=?1")
    public Bus getbusdetails(@Param("id") Long id);

}
