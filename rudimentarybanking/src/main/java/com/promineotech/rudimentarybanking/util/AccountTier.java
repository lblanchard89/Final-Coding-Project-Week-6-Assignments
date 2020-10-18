package com.promineotech.rudimentarybanking.util;

public enum AccountTier {
	
	STUDENT_CHECKING(.025),
	STUDENT_SAVINGS(.25),
	BASIC_CHECKING(.05),
	PREMIER_CHECKING(.06),
	SUPREME_CHECKING(.07),
	BASIC_SAVINGS(.50),
	PREMIER_SAVINGS(.60),
	SUPREME_SAVINGS(.70);
	
	private double interest;
	
	AccountTier(double interest) {
		this.interest = interest;
	}
	
	public double getInterest()
	{
		return interest;
	}
}
