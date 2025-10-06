package ma.ensias.ejb3.tp1;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class SecurityInterceptor {
    
    @AroundInvoke
    public Object checkSecurity(InvocationContext ctx) throws Exception {
        System.out.println("*** SecurityInterceptor: Checking security for method: " 
                         + ctx.getMethod().getName());
                
        Object result = ctx.proceed();
        
        System.out.println("*** SecurityInterceptor: Security check passed");
        return result;
    }
}
