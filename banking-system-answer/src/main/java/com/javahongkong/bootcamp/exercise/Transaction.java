package com.javahongkong.bootcamp.exercise;

import java.util.Objects;

public class Transaction implements TransactionInterface {
	private Long accountNumber;
	private Bank bank;

	/**
	 *
	 * @param bank The bank where the account is housed.
	 * @param accountNumber The customer's account number.
	 * @param attemptedPin The PIN entered by the customer.
	 * @throws Exception Account validation failed.
	 */
	public Transaction(Bank bank, Long accountNumber, int attemptedPin)
			throws Exception {
		// complete the function
		Objects.requireNonNull(bank);

		this.accountNumber = accountNumber;
		this.bank = bank;

		if (!(this.bank.authenticateUser(accountNumber, attemptedPin)))
			throw new IllegalArgumentException("Invalid pin or account number");

	}

	@Override
	public double getBalance() {
		// complete the function
		return this.bank.getBalance(accountNumber);
	}

	@Override
	public void credit(double amount) {
		// complete the function
		if (amount <= 0)
			throw new IllegalArgumentException("amount must >0");
		this.bank.credit(accountNumber, amount);
	}

	@Override
	public boolean debit(double amount) {
		// complete the function
		if (amount <= 0)
			throw new IllegalArgumentException("amount must >0");
		return this.bank.debit(this.accountNumber, amount);
	}
}
