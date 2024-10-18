package com.javahongkong.bootcamp.exercise;

import java.util.Objects;

public class ConsumerAccount extends Account {

	public ConsumerAccount(Person person, Long accountNumber, int pin,
			double currentBalance) {
		super(person, accountNumber, pin, currentBalance);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof ConsumerAccount))
			return false;

		if (!super.equals(obj))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode());
	}
}
