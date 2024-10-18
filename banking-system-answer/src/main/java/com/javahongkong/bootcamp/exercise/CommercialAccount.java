package com.javahongkong.bootcamp.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Account implementation for commercial (business) customers.<br>
 * <br>
 *
 * Private Variables:<br>
 * {@link #authorizedUsers}: List&lt;Person&gt;<br>
 */
public class CommercialAccount extends Account {
	private List<Person> authorizedUsers;

	public CommercialAccount(Company company, Long accountNumber, int pin,
			double startingDeposit) {
		super(company, accountNumber, pin, startingDeposit);
	}

	/**
	 * @param person The authorized user to add to the account.
	 */
	protected void addAuthorizedUser(Person person) {
		this.authorizedUsers.add(person);
	}

	/**
	 * @param person
	 * @return true if person matches an authorized user in {@link #authorizedUsers}; otherwise, false.
	 */
	public boolean isAuthorizedUser(Person person) {
		// complete the function
		if (person == null)
			return false;
		return this.authorizedUsers.contains(person);
	}

		@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof CommercialAccount))
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
