/*
 * Copyright 2017 Marc Liberatore.
 * Modified 2018 David Wemhoener.
 */

package index;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import comparators.TfIdfComparator;
import documents.DocumentId;

/**
 * A simplified document indexer and search engine.
 * 
 * Documents are added to the engine one-by-one, and uniquely identified by a DocumentId.
 *
 * Documents are internally represented as "terms", which are lowercased versions of each word 
 * in the document. 
 * 
 * Queries for terms are also made on the lowercased version of the term. Terms are 
 * therefore case-insensitive.
 * 
 * Lookups for documents can be done by term, and the most relevant document(s) to a specific term 
 * (as computed by tf-idf) can also be retrieved.
 *
 * See:
 * - <https://en.wikipedia.org/wiki/Inverted_index>
 * - <https://en.wikipedia.org/wiki/Search_engine_(computing)> 
 * - <https://en.wikipedia.org/wiki/Tf%E2%80%93idf>
 * 
 * @author Marc Liberatore
 *
 */
public class SearchEngine {
	private BufferedReader read;
	private Map<String,Set<DocumentId>> search=new HashMap<>();
	private Map<DocumentId,Map<String, Integer>> frequency=new HashMap<>();
	/**
	 * Inserts a document into the search engine for later analysis and retrieval.
	 * 
	 * The document is uniquely identified by a documentId; attempts to re-insert the same 
	 * document are ignored.
	 * 
	 * The document is supplied as a Reader; this method stores the document contents for 
	 * later analysis and retrieval.
	 * 
	 * @param documentId
	 * @param reader
	 * @throws IOException if the reader throws an exception 
	 */
	public void addDocument(DocumentId documentId, Reader reader) throws IOException {
		frequency.put(documentId, new HashMap<String,Integer>());
		read =new BufferedReader(reader);
		String[] split=read.readLine().split("\\W+");
		
		for(String e:split) {
			search.put(e,new HashSet<DocumentId>());
		}
			
		for(String a:split){
			if(frequency.get(documentId).containsKey(a.toLowerCase())) {
				frequency.get(documentId).put(a.toLowerCase(),frequency.get(documentId).get(a.toLowerCase())+1);
			}
			else {
				frequency.get(documentId).put(a.toLowerCase(), 1);
			}
		
		}
		read.close();
	}
	/**
	 * Returns the set of DocumentIds contained within the search engine that contain a given term.
	 * 
	 * @param term
	 * @return the set of DocumentIds that contain a given term
	 */
	public Set<DocumentId> indexLookup(String term) {
		Set<DocumentId> result=new HashSet<>();
		String temp= term.toLowerCase();
		for(DocumentId key:frequency.keySet()) {
			for(String same:frequency.get(key).keySet()) {
				if(same.equals(temp))
			
			result.add(key);		
			}
		}
		return result;
	}
	
	/**
	 * Returns the term frequency of a term in a particular document.
	 * 
	 * The term frequency is number of times the term appears in a document.
	 * 
	 * See 
	 * @param documentId
	 * @param term
	 * @return the term frequency of a term in a particular document
	 * @throws IllegalArgumentException if the documentId has not been added to the engine
	 */
	public int termFrequency(DocumentId documentId, String term) throws IllegalArgumentException {
		if(!frequency.containsKey(documentId)) {
			throw new IllegalArgumentException();
		}
		int count=frequency.get(documentId).getOrDefault(term,0);
		return count;
	}
	
	/**
	 * Returns the inverse document frequency of a term across all documents in the index.
	 * 
	 * For our purposes, IDF is defined as log ((1 + N) / (1 + M)) where 
	 * N is the number of documents in total, and M
	 * is the number of documents where the term appears.
	 * 
	 * @param term
	 * @return the inverse document frequency of term 
	 */
	public double inverseDocumentFrequency(String term) {
			String temp=term.toLowerCase();
			if(!search.containsKey(temp)) {
			return  Math.log((1+frequency.keySet().size()));
			}
	
			double N=frequency.keySet().size();
			double M=indexLookup(temp).size();
		// first calculate N, the number of documents plus one
		// loop through all of the documents to calculate M
		// finally, calculate and return log ((1 + N) / (1 + M))
		// use Math.log to compute the logarithm
		return Math.log((1+N)/(1+M));
		
	}
	
	/**
	 * Returns the tfidf score of a particular term for a particular document.
	 * 
	 * tfidf is the product of term frequency and inverse document frequency for the given term and document.
	 * 
	 * @param documentId
	 * @param term
	 * @return the tfidf of the the term/document
	 * @throws IllegalArgumentException if the documentId has not been added to the engine
	 */
	public double tfIdf(DocumentId documentId, String term) throws IllegalArgumentException {
		if(!frequency.containsKey(documentId)) {
			throw new IllegalArgumentException();
			}
		double N=termFrequency(documentId,term);
		double M=inverseDocumentFrequency(term);
		return  N*M;
	}
	
	/**
	 * Returns a sorted list of documents, most relevant to least relevant, for the given term.
	 * 
	 * A document with a larger tfidf score is more relevant than a document with a lower tfidf score.
	 * 
	 * Each document in the returned list must contain the term.
	 * 
	 * @param term
	 * @param max the maximum number of documents to return (you may return fewer)
	 * @return a list of documents sorted in descending order by tfidf
	 */
	public List<DocumentId> relevanceLookup(String term, int max) {
		String temp=term.toLowerCase();
		int limit=0;
		List<DocumentId>result =new ArrayList<>();
		List<DocumentId>temporary=new ArrayList<>(); 
		      for(DocumentId a:frequency.keySet()) {
			      for(String o:frequency.get(a).keySet()) {
				      if(o.equals(temp)) {
				    	  temporary.add(a);
				      }
			      	}
			     }
		
		temporary.sort(new TfIdfComparator(this,term));
		while(limit<max){
			if(!temporary.isEmpty()) {
			result.add(temporary.get(limit));
			}
			limit++;
		}
		return result;
		
	}
}
