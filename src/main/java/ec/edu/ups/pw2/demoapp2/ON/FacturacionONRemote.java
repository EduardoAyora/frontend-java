package ec.edu.ups.pw2.demoapp2.ON;

import java.util.List;

import ec.edu.ups.pw2.demoapp2.model.Factura;

public interface FacturacionONRemote {
	public void insertar(Factura p) throws Exception;
	
	public List<String> getFacturas();
}
