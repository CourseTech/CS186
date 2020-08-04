package ClassControl;
import java.io.*;
import java.util.Scanner;



public class ClassApp {
	public static void main(String[] args) {
	
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter a String :");
	String Option=sc.next();
	
	
	//reverseString(Option);
	System.out.println(countVowel(Option));
	System.out.println(reverseString(Option));
	sc.close();	
	}

	
	

	 public static String reverseString(String str){

	 if(str.length() == 1){

	 return str;

	 } 
	 else {
	String reverse="";
	 reverse += str.charAt(str.length()-1)

	 +reverseString(str.substring(0,str.length()-1));

	 return reverse;

	 }
}
	 public static int  countVowel(String str) {
		 int sum=0;
		 for(int n=0;n<str.length();n++) {
			 if("AEIOUaeiou".indexOf(str.charAt(n))!=-1) {
				 sum++;
			 }
			}
			 return sum;
		 }
				 
	 }

