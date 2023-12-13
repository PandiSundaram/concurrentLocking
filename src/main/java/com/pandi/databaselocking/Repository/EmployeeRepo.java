package com.pandi.databaselocking.Repository;

import com.pandi.databaselocking.Entities.Employee;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends CrudRepository<Employee,Long> {



    @Transactional
   // @Lock(LockModeType.PESSIMISTIC_FORCE_INCREMENT)  -> not working with update
    @Modifying
    @Query("update Employee e set e.name = ?1 where e.id= ?2")
    public void updatenamebyid(@Param("name")String name,@Param("id") Long id);



    @Transactional
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value="select e from Employee e where e.name= ?1")
    public Employee readdata(@Param("name") String name);
}
