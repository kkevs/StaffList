package model;

public class Staff {

	private String id;
	private String name;
	private String surname;
	private int age;
	private String city;

	public Staff() {

	}

	public Staff(String id,String name, String surname, int age, String city) {
		super();
		this.id=id;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.city = city;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
