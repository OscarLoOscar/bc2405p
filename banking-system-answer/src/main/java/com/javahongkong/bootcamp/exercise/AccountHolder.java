package com.javahongkong.bootcamp.exercise;

public abstract class AccountHolder {
	private int idNumber;

	/**
	 * @param idNumber The government-issued ID used during account setup.
	 */
	public AccountHolder(int idNumber) {
		// complete the function
		if (idNumber <= 0)
			throw new IllegalArgumentException(
					"Invalid ID number <=0. ID number must > 0");
		this.idNumber = idNumber;
	}

	/**
	 * @return private int {@link AccountHolder#idNumber}
	 */
	public int getIdNumber() {
		// complete the function
		return this.idNumber;
	}

	// public abstract int getSomething();

}
