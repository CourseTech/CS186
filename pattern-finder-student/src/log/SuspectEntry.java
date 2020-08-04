/*
 * Copyright 2017 Marc Liberatore.
 * Modified 2018 David Wemhoener
 */

package log;

public class SuspectEntry implements Comparable<SuspectEntry>{
	private String name;
	private String phoneNumber;
	private String passportNumber;
	private String cardNumber;
	
	public SuspectEntry(String name, String phoneNumber, String passportNumber, String cardNumber) {
	this.name=name;
	this.phoneNumber=phoneNumber;
	this.passportNumber=passportNumber;
	this.cardNumber=cardNumber;
	}

	public String getName() {
		return name;
		}
	
	public String getPhone() {
		return phoneNumber;
		}
	
	public String getPassport() {
		return passportNumber;
	}
	
	public String getCard() {
		return cardNumber;
	}
	
	@Override
	public String toString() {
		return this.getName() + "," + this.getPhone() + "," +this.getPassport()+ "," +this.getCard();
	}

	public int compareTo(SuspectEntry filler) {
		if (this.passportNumber.compareTo(filler.passportNumber)==0) {
			if (this.name.compareTo(filler.name)==0) {
				return this.phoneNumber.compareTo(filler.phoneNumber);
			}
			else {
				return this.name.compareTo(filler.name);
			}
		}
		return this.passportNumber.compareTo(filler.passportNumber);
	}	
	
	@Override 
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((passportNumber == null) ? 0 : passportNumber.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((cardNumber == null) ? 0 : cardNumber.hashCode());
		return result;
	}
		
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SuspectEntry other = (SuspectEntry) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (passportNumber == null) {
			if (other.passportNumber != null)
				return false;
		} else if (!passportNumber.equals(other.passportNumber))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (cardNumber == null) {
			if (other.cardNumber != null)
				return false;
		} else if (!cardNumber.equals(other.cardNumber))
			return false;
		return true;
	}
}
	



