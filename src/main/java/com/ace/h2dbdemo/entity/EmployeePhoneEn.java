package com.ace.h2dbdemo.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "emp_ph_no")
/*
 * @NamedQuery(name = "EmployeePhone.namedQueryFindByEmpId", query =
 * "select e from EmployeePhone e  where e.employee.employeeId = :empId")
 */

public class EmployeePhoneEn implements Serializable {
	private static final long serialVersionUID = 693279378442417063L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ph_id")
	private Integer phoneId;

	 @Column(name="emp_id")
	 private Integer empId;

	@Column(name = "ph_no")
	private String phoneNumber;

	@Column(name = "ph_cntry_cde")
	private String phoneCountryCode;

	@Column(name = "crt_tms")
	private Timestamp createTms;
	@Column(name = "exp_tms")
	private Timestamp expireTms;

	// one to many mapping for one employee many phone
	//@JsonBackReference
	
	  @ToString.Exclude  
	  @ManyToOne
	  @JoinColumn(name = "emp_id", insertable = false, updatable = false ) 
	  private EmployeeEn employeeEn;

	
	  
}
