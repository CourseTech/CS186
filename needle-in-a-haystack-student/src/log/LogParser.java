/*
 * Copyright 2017 Marc Liberatore.
 */

package log;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.opencsv.CSVReader;

public class LogParser {	
	/**
	 * Returns a list of SuspectEntries corresponding to the CSV data supplied by the given Reader.
	 * 
	 * The data contains one or more lines of the format:
	 * 
	 * Marc,413-545-3061,1234567890
	 * 
	 * representing a name, phone number, and passport number.
	 * 
	 * @param r an open Reader object
	 * @return a list of SuspectEntries
	 * @throws IOException
	 */
	CSVReader reader;
	
	public static List<SuspectEntry> parseLog(Reader r) throws IOException {
		CSVReader reader = new CSVReader(r);	    
		List<String[]> myEntries = reader.readAll();
		reader.close();
		List<SuspectEntry> list = new ArrayList<SuspectEntry>();
		for (int i=0;i<myEntries.size();i++) {
					SuspectEntry temp =	new SuspectEntry(myEntries.get(i)[0],myEntries.get(i)[1],myEntries.get(i)[2]);
					list.add(temp);
			}
		return list;	
	}
	/**
	 * Returns a sorted list of SuspectEntries whose passport numbers are common to all 
	 * of the supplied entryLists.
	 * 
	 * The list is sorted lexicographically by passport number, breaking ties by name 
	 * and then by phone number.
	 * 
	 * @param entryLists a list of lists of SuspectEntries
	 * @return a sorted list of SuspectEntries whose passport numbers are common to all 
	 * of the supplied entryLists
	 */
	public static List<SuspectEntry> findCommonEntries(List<List<SuspectEntry>> entryLists) {
		if (entryLists.size()==0) {
			return new ArrayList<SuspectEntry>();
		}
		else {
		List<SuspectEntry> finallist=new ArrayList<SuspectEntry>();
		Set<String> copy=new HashSet<String>();
		
		for(int i = 0; i < entryLists.get(0).size(); i++) {
			copy.add(entryLists.get(0).get(i).passportNumber);
		}
		
		for (int i=0;i<entryLists.size();i++) {
			Set<String> fullpassportnumber=new HashSet<String>();
			for (int j=0;j<entryLists.get(i).size();j++) {
				fullpassportnumber.add(entryLists.get(i).get(j).passportNumber);
			}
			copy.retainAll(fullpassportnumber); 
		}
		for (int i=0;i<entryLists.size();i++) {
			for (int j=0;j<entryLists.get(i).size();j++) {
				if (copy.contains(entryLists.get(i).get(j).passportNumber)){
					if(!(finallist.contains(entryLists.get(i).get(j)))) {
						finallist.add(entryLists.get(i).get(j));
					}
					else {
						continue;
					}
				}
			}
		}
		
		finallist.sort(null);
		return finallist;
		}
	}
}

		
		
	