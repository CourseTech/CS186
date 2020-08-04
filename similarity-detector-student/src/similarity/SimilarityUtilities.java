/*
 * Copyright 2017 Marc Liberatore.
 */

package similarity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import sets.SetUtilities;

public class SimilarityUtilities {
	
	/**
	 * Returns the set of non-empty lines contained in a text, trimmed of
	 * leading and trailing whitespace.
	 * 
	 * @param text
	 * @return the trimmed set of lines
	 */
	public static Set<String> trimmedLines(String text) {
		String s1="";
		Set<String>Words=new HashSet<String>();
		String[] intoWord=text.split("\\n");
		if(intoWord.length<=0) 
			return new HashSet<String>();
		else
		   for(String whiteSpace:intoWord) {
				  s1=whiteSpace.trim();
			   if(s1.length()>0) {
		      Words.add(s1);
		}}
		return Words;
	}

	/**
	 * Returns a list of words in the text, in the order they appeared in the text, 
	 * converted to lowercase.
	 * 
	 * Words are defined as a contiguous sequence of letters and numbers.
	 *
	 * @param text
	 * @return a list of lowercase words
	 */
	public static List<String> asLowercaseWords(String text) {
		List<String> lowerC=new ArrayList<String>();
		String[] intoWord=text.split("\\W+");
			if(intoWord!=null && intoWord.length>0) { 
				for(String word:intoWord) {
					if(word.length()>0)
				lowerC.add(word.toLowerCase());
		}
	}
	return lowerC;
	
}	

	/**
	 * Returns the line-based similarity of two texts.
	 * 
	 * The line-based similarity is the Jaccard index between each text's line
	 * set.
	 * 
	 * A text's line set is the set of trimmed lines in that text, as defined by
	 * trimmedLines.
	 * 
	 * @param text1
	 *            a text
	 * @param text2
	 *            another text
	 * @return
	 */
	public static double lineSimilarity(String text1, String text2) {
		Set<String>firstSet=trimmedLines(text1);
		Set<String>secondSet=trimmedLines(text2);
		return SetUtilities.jaccardIndex(firstSet,secondSet);
	}

	/**
	 * Returns the line-based similarity of two texts.
	 * 
	 * The line-based similarity is the Jaccard index between each text's line
	 * set.
	 * 
	 * A text's line set is the set of trimmed lines in that text, as defined by
	 * trimmedLines, less the set of trimmed lines from the templateText. Removes
	 * the template text from consideration after trimming lines, not before.
	 * 
	 * @param text1
	 *            a text
	 * @param text2
	 *            another text
	 * @param templateText
	 *            a template, representing things the two texts have in common
	 * @return
	 */
	public static double lineSimilarity(String text1, String text2, String templateText) {
		Set<String>testt1=SetUtilities.setDifference((trimmedLines(text1)),trimmedLines(templateText));
		Set<String>testt2=SetUtilities.setDifference((trimmedLines(text2)),trimmedLines(templateText));
		double result=SetUtilities.jaccardIndex(testt1,testt2);
		return result;
	}

	/**
	 * Returns a set of strings representing the shingling of the given length
	 * of a list of words.
	 * 
	 * A shingling of length k of a list of words is the set of all k-shingles
	 * of that list.
	 * 
	 * A k-shingle is the concatenation of k adjacent words.
	 * 
	 * For example, a 3-shingle of the list: ["a" "very" "fine" "young" "man"
	 * "I" "know"] is the set: {"averyfine" "veryfineyoung" "fineyoungman"
	 * "youngmanI" "manIknow"}.
	 * 
	 * @param words
	 * @param shingleLength
	 * @return 
	 */
	public static Set<String> shingle(List<String> words, int shingleLength) {
		Set<String>joint=new HashSet<String>();
			if(shingleLength>words.size()) {
				return joint;
				}
			else { 	
				for(int n=shingleLength -1;n<words.size();n++) {
					String shingle="";
				for(int a=n-shingleLength+1;a<=n;a++) {
					shingle=shingle+words.get(a);
			
				}
					joint.add(shingle);
				}
		return joint;
		}
	}
	
	

	/**
	 * Returns the shingled word similarity of two texts.
	 * 
	 * The shingled word similarity is the Jaccard index between each text's
	 * shingle set.
	 * 
	 * A text's shingle set is the set of shingles (of the given length) for the
	 * entire text, as defined by shingle and asLowercaseWords, 
	 * less the shingle set of the templateText. Removes the templateText 
	 * from consideration after shingling, not before.
	 * 
	 * @param text1
	 * @param text2
	 * @param templateText
	 * @param shingleLength
	 * @return
	 */
	public static double shingleSimilarity(String text1, String text2, String templateText, int shingleLength) {
		List<String>intoText=asLowercaseWords(text1);
		List<String>intoText1=asLowercaseWords(text2);
		List<String>intoText3=asLowercaseWords(templateText);
		Set<String>testt1=SetUtilities.setDifference(shingle(intoText,shingleLength),shingle(intoText3,shingleLength));
		Set<String>testt2=SetUtilities.setDifference(shingle(intoText1,shingleLength),shingle(intoText3,shingleLength));
		return SetUtilities.jaccardIndex(testt1, testt2);
	}

	
}
