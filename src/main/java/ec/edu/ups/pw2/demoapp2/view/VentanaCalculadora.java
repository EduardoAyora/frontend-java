package ec.edu.ups.pw2.demoapp2.view;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import ec.edu.ups.pw2.demoapp2.ON.CalculadoraONRemote;

public class VentanaCalculadora {
	
	private CalculadoraONRemote calcRemote;
	
	public void conectar() throws Exception {
		try {  
            final Hashtable<String, Comparable> jndiProperties =  
                    new Hashtable<String, Comparable>();  
            jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,  
                    "org.wildfly.naming.client.WildFlyInitialContextFactory");  
            jndiProperties.put("jboss.naming.client.ejb.context", true);  
              
            jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
            jndiProperties.put(Context.SECURITY_PRINCIPAL, "demoapp2");  
            jndiProperties.put(Context.SECURITY_CREDENTIALS, "demoapp2");  
              
            final Context context = new InitialContext(jndiProperties);  
              
            final String lookupName = "ejb:/demoapp2/CalculadoraON!ec.edu.ups.pw2.demoapp2.ON.CalculadoraONRemote";  
              
            this.calcRemote = (CalculadoraONRemote) context.lookup(lookupName);
              
        } catch (Exception ex) {  
            ex.printStackTrace();  
            throw ex;  
        }  
	}
	
	public void sumar() {
		double d = this.calcRemote.suma(89, 10);
		System.out.println("SUMA " + d);
	}
	
	public static void main(String[] args) {
		VentanaCalculadora vtnCalc = new VentanaCalculadora();
		try {
			vtnCalc.conectar();
			vtnCalc.sumar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
