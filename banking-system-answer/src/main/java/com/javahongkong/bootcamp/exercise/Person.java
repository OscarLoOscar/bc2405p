package com.javahongkong.bootcamp.exercise;

import java.util.Objects;

public class Person extends AccountHolder {
	private String firstName;
	private String lastName;
	// private int idNumber;

	public Person(String firstName, String lastName, int idNumber) {
		super(idNumber);
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		// complete the function
		return this.firstName;
	}

	public String getLastName() {
		// complete the function
		return this.lastName;
	}

	@Override
	public String toString() {
		return this.firstName + " " + this.lastName;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(super.equals(obj)))
			return false;
		if (!(obj instanceof Person))
			return false;
		Person person = (Person) obj;

		return Objects.equals(this.firstName, person.getFirstName())
				&& Objects.equals(this.lastName, person.getLastName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), this.firstName, this.lastName);
	}
}
