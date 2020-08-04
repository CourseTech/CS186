/*
 * Copyright 2017 Marc Liberatore.
 * Modified 2018 David Wemhoener
 */

package crawler;


import java.net.URI;


import java.util.HashSet;

import java.util.List;
import java.util.Set;
import java.util.Stack;
import crawler.CrawlerUtils;
import org.jsoup.nodes.Document;

import document.RetrievedDocument;

/**
 * A simplified web crawler, specialized to crawl local URIs rather
 * than to retrieve remote documents.
 * 
 * @author liberato
 *
 */
public class UriCrawler {
  private int visitAttempt;
  private HashSet<URI> visited=new HashSet<>();
  private Stack<URI> unvisit=new Stack<>();
  private HashSet<RetrievedDocument>Document=new HashSet<>();
  /**
	 * Instantiates a new UriCrawler. The maximum number of documents a crawler
	 * will attempt to visit, ever, is limited to visitQuota.
	 * 
	 * @param visitQuota
	 *            the maximum number of documents a crawler will attempt to
	 *            visit
	 * @throws IllegalArgumentException
	 *             if maximumRetrievalAttempts is less than one
	 */
	public UriCrawler(int visitQuota) throws IllegalArgumentException {
		if (visitQuota < 1) {
			throw new IllegalArgumentException();
		}
		this.visitAttempt=visitQuota;
		// TODO
	}

	/**
	 * Returns the set of URIs that this crawler has attempted to visit
	 * (successfully or not).
	 * 
	 * @return the set of URIs that this crawler has attempted to visit
	 */
	public Set<URI> getVistedUris() {
		return visited;
	}
	
	/**
	 * Returns the set of RetrievedDocuments corresponding to the URIs
	 * this crawler has successfully visited.
	 * 
	 * @return the set of RetrievedDocuments corresponding to the URIs
	 * this crawler has successfully visited
	 */
	public Set<RetrievedDocument> getVisitedDocuments() {
		return Document;
	}

	/**
	 * Adds a URI to the collections of URIs that this crawler should attempt to
	 * visit. Does not visit the URI.
	 * 
	 * @param uri
	 *            the URI to be visited (later!)
	 */
	public void addUri(URI uri) {
		unvisit.push(uri);
		
	}
	
	/**
	 * Retrieves a URI from the collections of URIs that this crawler should attempt to
	 * visit.
	 * 
	 * @return the next URI the crawler should attempt to visit.
	 */
	public URI getNext() {
		return unvisit.pop();
	}
	

	/**
	 * Attempts to visit a single as-yet unattempted URI in this crawler's
	 * collection of to-be-visited URIs.
	 * 
	 * Visiting a document entails parsing the text and links from the URI.
	 * 
	 * If the parse succeeds:
	 * 
	 * - The "file:" links should be added to this crawler's collection of
	 * to-be-visited URIs.
	 * 
	 * - A new RetrievedDocument should be added to this crawler's collection of
	 * successfully visited documents.
	 * 
	 * If the parse fails, this method considers the visit attempted but
	 * unsuccessful.
	 * 
	 * @throws MaximumVisitsExceededException
	 *             if this crawler has already attempted to visit its quota of
	 *             visits
	 * @throws NoUnvisitedUrisException
	 *             if no more unattempted URI remain in this crawler's
	 *             collection of URIs to visit
	 */
	public void visitOne() throws MaximumVisitsExceededException, NoUnvisitedUrisException {
		if(unvisit.size()==0) {
			throw new NoUnvisitedUrisException();
		}
		
		if(visitAttempt<=0) {
			throw new MaximumVisitsExceededException();
		}
		
			URI getVisit=getNext();
			visited.add(getVisit);
			String NotNull="";
			try {
				NotNull=CrawlerUtils.parse(getVisit).text();
				}
			catch (NullPointerException ioe) {
				return;
			}
			RetrievedDocument needtoAdd=new RetrievedDocument(getVisit,NotNull, CrawlerUtils.getFileUriLinks(CrawlerUtils.parse(getVisit)));
			List<URI> newURI= CrawlerUtils.getFileUriLinks(CrawlerUtils.parse(getVisit));
			for(URI e: newURI) {
				addUri(e);
			}
			Document.add(needtoAdd);
			visitAttempt--;
		}
		
		
		// TODO
	

	/**
	 * Attempts to visit all URIs in this crawler (and any URIs they reference,
	 * and so on).
	 * 
	 * This method will not raise a MaximumVisitsExceededException if there are
	 * more URIs than can be visited. It will instead stop once the UriCrawler's
	 * quota has been reached.
	 */
	public void visitAll() {
		int n=0;
		while(n<=visitAttempt) {
			try {
				visitOne();
				}
			catch(MaximumVisitsExceededException ioe) {
				return;
			}
			catch(NoUnvisitedUrisException catchme) {
				return;
			}
		}
	}
	
  }

