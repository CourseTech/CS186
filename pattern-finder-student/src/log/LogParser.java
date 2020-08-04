/*
 * Copyright 2017 Marc Liberatore.
 * Modified 2018 David Wemhoener
 */

package log;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.opencsv.CSVReader;

public class LogParser {	
	CSVReader read;
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
	public static List<SuspectEntry> parseLog(Reader r) throws IOException {
		CSVReader read = new CSVReader(r);
		List<SuspectEntry>SuspectList=new ArrayList<SuspectEntry>();
		String[]nextLine;
		while ((nextLine = read.readNext()) != null) {
			SuspectEntry a=new SuspectEntry(nextLine[0], nextLine[1], nextLine[2], nextLine[3]);
			SuspectList.add(a);
		}
		read.close();
		return SuspectList;
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
		List<SuspectEntry>finalList=new ArrayList<SuspectEntry>();
		if(entryLists.isEmpty()) {
			return finalList;	
		}
		else {
		List<SuspectEntry>temp=new ArrayList<SuspectEntry>();
		Set <String> passportList=new HashSet<String>();
		for(int go=0;go<entryLists.size();go++) {
			temp=entryLists.get(go);
		}
		
		for(int b=0;b<temp.size();b++) {
				passportList.add(temp.get(b).getPassport()); 
		}
		for(List<SuspectEntry>a:entryLists) {
				Set<String>removeUncommon= new HashSet<String>();	
		for(SuspectEntry hint:a) {
			removeUncommon.add(hint.getPassport());
				}
			passportList.retainAll(removeUncommon);
		}
			for (int i=0;i<entryLists.size();i++) {
				for (int j=0;j<entryLists.get(i).size();j++) {
					if (passportList.contains(entryLists.get(i).get(j).getPassport())){
						if(!(finalList.contains(entryLists.get(i).get(j)))) {
							finalList.add(entryLists.get(i).get(j));
						}
						else {
							continue;
						}
					}
				}
			}
		finalList.sort(null); 
	    return finalList;
		}
	}
}

		

