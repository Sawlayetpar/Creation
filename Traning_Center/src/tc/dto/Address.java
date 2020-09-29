package tc.dto;

public class Address {

	private String street;
	private String city;
	private String township;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTownship() {
		return township;
	}

	public void setTownship(String township) {
		this.township = township;
	}

	
	public String address() {
		String address = getStreet() + "," + getTownship() + "," + getCity();
		return address;
	}
	
	
	
}
