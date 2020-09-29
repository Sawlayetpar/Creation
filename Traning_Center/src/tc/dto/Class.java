package tc.dto;

import java.util.Date;

public class Class {

	private int id;
	private Date start_date;
	private Date end_date;
	private Course course;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	

//	@SuppressWarnings("unused")
//	private java.sql.Date getCurrentDate() {
//	    java.util.Date today = new java.util.Date();
//	    return new java.sql.Date(today.getTime());
//	}

}
