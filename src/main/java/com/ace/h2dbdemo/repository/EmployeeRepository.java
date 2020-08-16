package com.ace.h2dbdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ace.h2dbdemo.entity.EmployeeEn;
@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEn, Integer>{

	@Query("select e from EmployeeEn e where e.firstName = :firstName and e.lastName = :lastName and e.expireTms is null ")
	public List<EmployeeEn> findByFirstNameAndLastName(@Param ("firstName") String firstName,@Param ("lastName") String lastName);
	
}
