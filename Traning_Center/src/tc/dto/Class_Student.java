package tc.dto;

import java.util.Date;

public class Class_Student {

	private Class class_id;
	private Student student_id;
	private int paid;
	private int fee;
	private Date registration_date;

	public Class getClass_id() {
		return class_id;
	}

	public void setClass_id(Class class_id) {
		this.class_id = class_id;
	}

	public Student getStudent_id() {
		return student_id;
	}

	public void setStudent_id(Student student_id) {
		this.student_id = student_id;
	}

	public int getPaid() {
		return paid;
	}

	public void setPaid(int paid) {
		this.paid = paid;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public Date getRegistration_date() {
		return registration_date;
	}

	public void setRegistration_date(Date registration_date) {
		this.registration_date = registration_date;
	}

}
