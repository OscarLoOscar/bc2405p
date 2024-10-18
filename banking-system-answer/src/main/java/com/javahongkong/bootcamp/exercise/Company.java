package com.javahongkong.bootcamp.exercise;

import java.util.Objects;

public class Company extends AccountHolder {
	private String companyName;

	public Company(String companyName, int taxId) {
		super(taxId);
		this.companyName = companyName;
	}

	public String getCompanyName() {
		// complete the function
		return this.companyName;
	}


	@Override
	public String toString() {
		return this.companyName;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(super.equals(obj)))
			return false;
		if (!(obj instanceof Company))
			return false;
		Company company = (Company) obj;
		return Objects.equals(this.companyName, company.getCompanyName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), this.companyName);
	}
}
