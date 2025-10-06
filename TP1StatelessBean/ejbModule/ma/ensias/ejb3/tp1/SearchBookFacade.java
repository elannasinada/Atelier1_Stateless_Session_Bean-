package ma.ensias.ejb3.tp1;
import java.util.*;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.interceptor.Interceptors;


@Stateless
@Interceptors({SecurityInterceptor.class})

public class SearchBookFacade implements SearchBookFacadeRemote, SearchBookFacadeLocal {

    private HashMap<String, String> countryBookMap = new HashMap<String, String>();

    public SearchBookFacade() {
    }

    public List<String> bookSearch(String bookType) {
        List<String> bookList = new ArrayList<String>();
        
        if (bookType.equals("java")) {
            bookList.add("Java for dummies");
            bookList.add("Beginning Java 6");
        } else if (bookType.equals("C++")) {
            bookList.add("C++ for dummies");
        }
        
        return bookList;
    }
    
    public List<String> searchBookByCountry(String country) {
        List<String> bookList = new ArrayList<String>();
        
        for (Entry<String, String> entry : countryBookMap.entrySet()) {
            if (entry.getKey().equals(country)) {
                bookList.add(entry.getValue());
            }
        }
        return bookList;
    }
    
    @PostConstruct
    public void initializeCountryBookList() {
        System.out.println("========================================");
        System.out.println("@PostConstruct: Initializing countryBookMap");
        System.out.println("========================================");
        
        countryBookMap.put("Australia", "Welcome to Australia");
        countryBookMap.put("Australia", "Australia History");
        countryBookMap.put("Morocco", "Welcome to Morocco");
        countryBookMap.put("Morocco", "Morocco History");
    }
    
    @AroundInvoke
    public Object timerLog(InvocationContext ctx) throws Exception {
        String beanClassName = ctx.getTarget().getClass().getName();
        String businessMethodName = ctx.getMethod().getName();
        String target = beanClassName + "." + businessMethodName;
        
        long startTime = System.currentTimeMillis();
        System.out.println(">>> Invoking " + target);
        
        try {
            return ctx.proceed();
        } finally {
            System.out.println("<<< Exiting " + target);
            long totalTime = System.currentTimeMillis() - startTime;
            System.out.println(">>> Business method {" + businessMethodName + 
                             "} takes " + totalTime + "ms to execute");
        }
    }
    
    @PreDestroy
    public void destroyBookList() {
        System.out.println("========================================");
        System.out.println("@PreDestroy: Clearing countryBookMap");
        System.out.println("========================================");
        countryBookMap.clear();
    }
}