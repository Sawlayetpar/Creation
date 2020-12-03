package phone.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class Phone_Call {

	private int id;
	private String phone_number;
	private String call_number;
	private int amount;
	private int duration;
	private LocalTime time;
	private LocalDate date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getCall_number() {
		return call_number;
	}

	public void setCall_number(String call_number) {
		this.call_number = call_number;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

}
