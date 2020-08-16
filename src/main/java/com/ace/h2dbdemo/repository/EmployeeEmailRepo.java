package com.ace.h2dbdemo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.ace.h2dbdemo.entity.EmployeeEmailEn;


//@Transactional(readOnly = true)
public interface EmployeeEmailRepo extends JpaRepository<EmployeeEmailEn, Integer> {
	
	@Query("select ee from EmployeeEmailEn ee where ee.emailAddress= :emailAddress and ee.expireTms is null ")
	public List<EmployeeEmailEn> findByEmployeeEmail(@Param ("emailAddress") String emailAddress);

}
