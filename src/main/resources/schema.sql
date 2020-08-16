drop table emp_email if exists;
drop table emp_ph_no if exists;
drop table emp if exists;
commit;

CREATE TABLE emp (
  emp_id int(11) unsigned NOT NULL AUTO_INCREMENT primary key,
  emp_fs_nm varchar(50) NOT NULL,
  emp_ls_nm varchar(50) NOT NULL,
  crt_tms timestamp NOT NULL,
  upd_tms timestamp ,
  exp_tms timestamp 
 
);

CREATE TABLE emp_email (
  email_id int(11)  unsigned NOT NULL AUTO_INCREMENT,
  emp_id int(11) NOT NULL,
  email_address varchar(100) DEFAULT NULL,
  crt_tms timestamp NOT NULL,
  exp_tms timestamp ,
  PRIMARY KEY (email_id),
  FOREIGN KEY (emp_id) REFERENCES emp (emp_id)
);

CREATE TABLE emp_ph_no (
  ph_id int(11)  unsigned NOT NULL AUTO_INCREMENT,
  emp_id int(11) NOT NULL,
  ph_no varchar(10) not null,
  ph_cntry_cde varchar(5) not null,
  crt_tms timestamp NOT NULL,
  exp_tms timestamp ,
  PRIMARY KEY (ph_id),
 FOREIGN KEY (emp_id) REFERENCES emp (emp_id)
);

