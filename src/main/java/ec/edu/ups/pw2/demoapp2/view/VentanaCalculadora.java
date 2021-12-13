package ec.edu.ups.pw2.demoapp2.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import ec.edu.ups.pw2.demoapp2.ON.CalculadoraONRemote;
import ec.edu.ups.pw2.demoapp2.ON.FacturacionONRemote;
import ec.edu.ups.pw2.demoapp2.ON.PersonaONRemote;
import ec.edu.ups.pw2.demoapp2.model.Factura;
import ec.edu.ups.pw2.demoapp2.model.Persona;

public class VentanaCalculadora {
	
	private CalculadoraONRemote calcRemote;
	private PersonaONRemote prOnRemote;
	private FacturacionONRemote facturacionONRemote;
	
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
            this.facturacionONRemote = (FacturacionONRemote) context.lookup("ejb:/demoapp2/FacturacionON!ec.edu.ups.pw2.demoapp2.ON.FacturacionONRemote");
        } catch (Exception ex) {  
            ex.printStackTrace();  
            throw ex;  
        }  
	}
	
	public void insertarFactura(Factura factura) {
		try {
			this.facturacionONRemote.insertar(factura);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Persona buscarCliente(String cedula) {
		Persona persona = null;
		try {
			persona = this.prOnRemote.buscar(cedula);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return persona;
	}
	
	public List<String> listarFacturas() {
		List<String> facturas = new ArrayList<String>();
		try {
			 facturas = this.facturacionONRemote.getFacturas();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return facturas;
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
