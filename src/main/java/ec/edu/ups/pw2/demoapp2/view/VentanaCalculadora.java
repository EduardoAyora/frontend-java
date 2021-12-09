package ec.edu.ups.pw2.demoapp2.view;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import ec.edu.ups.pw2.demoapp2.ON.CalculadoraONRemote;
import ec.edu.ups.pw2.demoapp2.ON.PersonaONRemote;

public class VentanaCalculadora {
	
	private CalculadoraONRemote calcRemote;
	private PersonaONRemote prOnRemote;
	
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
            this.prOnRemote = (PersonaONRemote) context.lookup("ejb:/demoapp2/PersonaON!ec.edu.ups.pw2.demoapp2.ON.PersonaONRemote");
              
        } catch (Exception ex) {  
            ex.printStackTrace();  
            throw ex;  
        }  
	}
	
	public void sumar() {
		double d = this.calcRemote.suma(89, 10);
		System.out.println("SUMA " + d);
	}
	
	public void crearPersona(String cedula, String nombre, String direccion) {
		this.prOnRemote.crear(cedula, nombre, direccion);
		System.out.println("Persona creada con éxito " + cedula + " " + nombre + " " + direccion);
	}
	
	/*
	public void main(String[] args) {
		VentanaCalculadora vtnCalc = new VentanaCalculadora();
		try {
			vtnCalc.conectar();
			vtnCalc.sumar();
			vtnCalc.crearPersona();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/


}
