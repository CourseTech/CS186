/*
 * Copyright 2017 Marc Liberatore.
 */

package log;

public class SuspectEntry implements Comparable<SuspectEntry>{
	String name;
	String phoneNumber;
	String passportNumber;
	
	public SuspectEntry(String name, String phoneNumber, String passportNumber) {
	this.name=name;
	this.phoneNumber=phoneNumber;
	this.passportNumber=passportNumber;
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
		return true;
	}


@Override 
public String toString() {
	return (name+ "," + phoneNumber + "," + passportNumber);
}
}