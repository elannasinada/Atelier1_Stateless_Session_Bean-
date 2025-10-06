package ma.ensias.ejb3.tp1.client;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ma.ensias.ejb3.tp1.SearchBookFacadeRemote;

public class SearchBookClient {
	
	public SearchBookClient() {
	}
	
	public static void main(String[] args) {
		SearchBookClient searchFacadeTest = new SearchBookClient();
		searchFacadeTest.doTest();
	}
	
	InitialContext getInitialContext() throws javax.naming.NamingException {
		Properties p = new Properties();
		
		p.put(Context.INITIAL_CONTEXT_FACTORY, 
			"org.jnp.interfaces.NamingContextFactory");
		p.put(Context.URL_PKG_PREFIXES, 
			"jboss.naming:org.jnp.interfaces");
		p.put(Context.PROVIDER_URL, "jnp://localhost:1099");
		
		return new InitialContext(p);
	}
	
	void doTest() {
	    try {
	        InitialContext ic = getInitialContext();
	        System.out.println("SearchFacade Lookup");
	        
	        SearchBookFacadeRemote searchFacade = (SearchBookFacadeRemote) 
	            ic.lookup("SearchBookFacade/remote");
	        
	        System.out.println("Searching books");
	        List<String> bookList = searchFacade.bookSearch("java");
	        
	        System.out.println("Printing books list");
	        for (String book : bookList) {
	            System.out.println(" -- " + book);
	        }
	        
	        // Tester la nouvelle méthode searchBookByCountry
	        System.out.println("\nSearching books by country: Morocco");
	        List<String> moroccoBooks = searchFacade.searchBookByCountry("Morocco");
	        for (String book : moroccoBooks) {
	            System.out.println(" -- " + book);
	        }
	        
	    } catch (NamingException e) {
	        e.printStackTrace();
	    }
	}
}