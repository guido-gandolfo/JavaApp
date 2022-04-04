package interfaz;

import java.util.Map;
import java.util.TreeMap;

import javax.swing.*;

public class InterfazMain {
	
	//PRINCIPAL DE LA APLICACION
	public arduino.Api comunicacion = new arduino.Api();
	public Map<String, arduino.Sensor> mapaSensores = new TreeMap<String, arduino.Sensor>();
	public Map<String, arduino.Actuador> mapaActuadores = new TreeMap<String, arduino.Actuador>();
	
	
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
				e.printStackTrace();
		}
		new InterfazMain();
		
	}
	
	public InterfazMain() {
		
		comunicacion.inicializarConexion();
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			System.out.println("La excepción es:"+e.getMessage());
		}
		
		//CREA LA VARIABLE programa DE TIPO MarcoIterfaz
		MarcoInterfaz programa = new MarcoInterfaz(this);
		//HACE VISIBLE LA VENTANA DE LA APLICACION
		programa.setVisible(true);
		//AL CERRAR LA VENTANA SE SALE DE LA APLICACION
		programa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}
	
}




