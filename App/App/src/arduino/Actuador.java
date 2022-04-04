package arduino;

import interfaz.InterfazMain;

public class Actuador {

	public int pin;
	public String nombre;
	public InterfazMain interfazMain;
	
	public Actuador(int _pin, String _nombre){
		
		
		pin = _pin;
		nombre = _nombre;
		
	}
	
	public void encender(){
		
		interfazMain.comunicacion.digitalWriteUp(pin);
		
	}
	
	public void apagar(){
		
		interfazMain.comunicacion.digitalWriteDown(pin);
		
	}
	
}

