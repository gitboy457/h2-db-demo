package com.ace.h2dbdemo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.ace.h2dbdemo.entity.EmployeePhoneEn;

//@Transactional(readOnly = true)
public interface EmployeePhoneRepo extends JpaRepository<EmployeePhoneEn, Integer> {
	
	
	//
	//below three the method provide  the same result only difference is in first one the query is created by spring jpa
	//in second the query is provided by developer (a native query)
	//in third we used named query
	
	//spring boot jpa provide this method we have to just declare here
	//List<EmployeePhone> findByEmployee(Employee employee);
	
	//native query example
	@Query(value="SELECT * FROM emp_ph_no where emp_id= :empId ",nativeQuery = true)
	public List<EmployeePhoneEn> nativeQueryFindByEmpId(@Param ("empId")Integer empId );
	
	@Query(value="select ep from EmployeePhoneEn ep  where ep.phoneNumber = :phoneNumber and ep.phoneCountryCode = :phoneCountryCode and ep.expireTms is null")
	public List<EmployeePhoneEn> findByPhoneNumber(@Param ("phoneNumber") String phoneNumber,@Param("phoneCountryCode") String phoneCountryCode);
	
	//namedQuery example
	//we can directly write name query here or write in EmployeePhoneClass both will provide same result
	//@Query(value="select e from EmployeePhone e  where employee.employeeId = :empId")
	//public List<EmployeePhone> namedQueryFindByEmpId(@Param ("empId")Integer empId );
	
	//
}
