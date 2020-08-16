package com.ace.h2dbdemo.entity;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="emp")
public class EmployeeEn implements Serializable{

	private static final long serialVersionUID = -4518361946660732010L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="emp_id")
	private Integer employeeId;
	
	@Column(name="emp_fs_nm")
	private String firstName;
	@Column(name="emp_ls_nm")
	private String lastName;
	@Column(name="crt_tms")
	private Timestamp createTms;
	@Column(name="upd_tms")
	private Timestamp updateTms;
	@Column(name="exp_tms")
	private Timestamp expireTms;
	
	//one to many mapping
	//one employee many phone number
	
	//  @ToString.Exclude 
	
	  @OneToMany( mappedBy = "employeeEn", cascade = CascadeType.ALL)
	  
	  private List<EmployeePhoneEn> employeePhoneList;
	 
	  
	 // @ToString.Exclude 
		
		@OneToMany(mappedBy = "employeeEn" ,cascade = CascadeType.ALL)
		private List<EmployeeEmailEn> employeeEmailList;
		 

	
	 
	
	/*
	 * @ToString.Exclude
	 * 
	 * @OneToMany(fetch = FetchType.EAGER)
	 * 
	 * @JoinColumn(name = "emp_id") //@Fetch(FetchMode.SUBSELECT) //@OneToMany(
	 * mappedBy="employee",fetch = FetchType.EAGER) private List<EmployeePhone>
	 * employeePhoneList ;
	 */





	
	  
	
}
