package com.javahongkong.bootcamp.exercise;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.Objects;

public class Bank implements BankInterface {
	private LinkedHashMap<Long, Account> accounts; // object reference
	// Add this line;
	private static long accNumCount = 0L;

	public Bank() {
		// complete the function
		this.accounts = new LinkedHashMap<>();
	}

	@Override
	public Account getAccount(Long accountNumber) {
		// complete the function
		if (!this.accounts.containsKey(accountNumber))
			throw new IllegalArgumentException("Invalid account number");
		return this.accounts.get(accountNumber);
	}

	@Override
	public Long openCommercialAccount(Company company, int pin,
			double startingDeposit) {
		// complete the function
		CommercialAccount commercialAccount =
				new CommercialAccount(company, ++accNumCount, pin, startingDeposit);
		this.accounts.put(accNumCount, commercialAccount);
		return accNumCount;
	}

	@Override
	public Long openConsumerAccount(Person person, int pin,
			double startingDeposit) {
		Objects.requireNonNull(person);

		if (pin <= 0)
			throw new IllegalArgumentException("Pin must be >0");

		if (startingDeposit < 0)
			throw new IllegalArgumentException("startingDeposit must be >0");

		ConsumerAccount consumerAccount =
				new ConsumerAccount(person, ++accNumCount, pin, startingDeposit);
		this.accounts.put(accNumCount, consumerAccount);
		return accNumCount;
	}

	@Override
	public boolean authenticateUser(Long accountNumber, int pin) {
		// complete the function
		return this.getAccount(accountNumber).validatePin(pin);
	}

	public double getBalance(Long accountNumber) {
		// complete the function
		return this.getAccount(accountNumber).getBalance();
	}

	@Override
	public void credit(Long accountNumber, double amount) {
		// complete the function
		if (amount <= 0)
			throw new IllegalArgumentException("Amount must be >0");

		this.getAccount(accountNumber).creditAccount(amount);
	}

	@Override
	public boolean debit(Long accountNumber, double amount) {
		// complete the function
		if (amount <= 0)
			throw new IllegalArgumentException("Amount must be >0");
		if (amount > this.getAccount(accountNumber).getBalance()) {
			return false;
		}
		return this.getAccount(accountNumber).debitAccount(amount);
	}
}
