import java.util.List;
import java.util.Map;
import java.util.Stack;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Deque;
import java.util.HashMap;
public class test{
	private static Map<String,Set<DocumentId>> search;
	private static final String DOCUMENT1 = "this is a a sample";
	private static final DocumentId DOCUMENT1_ID = new DocumentId("DOCUMENT1");
	public static void main(String[] args) throws IOException {
		System.out.println("Hello world");
		
		System.out.println("I'm fucked");
		System.out.println(addOdd(5));
		System.out.println(isPalindrome("yolo"));
	
	}
		 
	public static boolean Dupledrome(String str) {
		if(str!=null) {
			char[] test=str.toCharArray();
			for(int n=1;n<test.length;n++) {
				if(test[n]==test[n-1]) {
					return true;
				}
			}
		}
		return false;
	}

	public static <E> List<E> Nth(List<E>list, int n){
		List<E>result=new ArrayList<>();
		for(int a=0;a<list.size();a+=n) {
			result.add(list.get(a));
		}
		return result;
	}
	
	public static boolean isPalindrome(String str) {
		char []array=str.toCharArray();
		for(int n=0;n<array.length/2;n++) {
			if(array[n]!=array[array.length-n-1]) {
					return false;
				}
			}
		return true;
	}
	
	public static int addOdd(int a) {
		if(a<=0) 
			return 0;
		if(a%2==1)  
			return (a+addOdd(a-=1));
		else
			return addOdd(a-=1);
	}
	
	public static <E> List<E> deduplicated(List<E>list){
		List<E>test= new ArrayList<>(list);
		System.out.print(test);
		List<E>result=new ArrayList<>();
		for(int n=0;n<test.size();n++) {
			E temp=list.get(n);
			//int b=n+1;
			for(int a=n+1;a<test.size();a++) { // sha bi
				if(test.get(a).equals(temp)){
					test.remove(a);
				}
				//else
				//b++;
			}
		}
		result.addAll(test);
		return result;
	}
	
	}
	/*public static List<Integer> Difference(List<Integer> list){
		List<Integer>output=new ArrayList<>();
		if(!list.isEmpty()) {
			for(int n=1;n<list.size();n++) {
				int a=list.get(n);
				int b=list.get(n-1);
				output.add(b-a);
			}
		}
			return output;
		}
	*/
	/*public static String read(DocumentId documentId, Reader reader) throws IOException {
		BufferedReader read =new BufferedReader(reader);
		
		String result="";
		String[] split=read.readLine().split("\\W+");
		//System.out.print(split);
		for(String e:split) {
			result+=e+" ";
		}
		read.close();
		//try {
		return result;
		//}
	//	catch(IOException e) {
			//throw new IOException();
		//}
		//for(String a:split){
		//	if(frequency.get(documentId).containsKey(a.toLowerCase())) {
			//	frequency.get(documentId).put(a.toLowerCase(),frequency.get(documentId).get(a.toLowerCase())+1);
			//}
		//	else {
			//	frequency.get(documentId).put(a.toLowerCase(), 1);
			}
			//search.put(a.toLowerCase(), new HashSet<DocumentId>());
			//Set<DocumentId> temp=search.get(a.toLowerCase());
			//temp.add(documentId);
		//}
	
	
	}	
	*/
	



	
