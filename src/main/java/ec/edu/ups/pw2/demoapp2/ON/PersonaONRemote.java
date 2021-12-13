package ec.edu.ups.pw2.demoapp2.ON;

import ec.edu.ups.pw2.demoapp2.model.Persona;

public interface PersonaONRemote {
	public void crear(String cedula, String nombre, String direccion);
	public Persona buscar(String cedula);
}
