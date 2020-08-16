package com.ace.h2dbdemo.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name="emp_email")
public class EmployeeEmailEn implements Serializable{

	private static final long serialVersionUID = 4428072090246303319L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="email_id")
	private Integer id;
	
	@Column(name="emp_id")
	private Integer empId;
	
	@Column(name="email_address")
	private String emailAddress;
	
	@Column(name="crt_tms")
	private Timestamp createTms;
	@Column(name="exp_tms")
	private Timestamp expireTms;
	
	
	@ToString.Exclude

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })

	@JoinColumn(name = "emp_id", insertable = false, updatable = false)
	private EmployeeEn employeeEn;

}
