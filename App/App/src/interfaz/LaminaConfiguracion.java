package interfaz;

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;


@SuppressWarnings("serial")
public class LaminaConfiguracion extends JPanel{
	
	private JComboBox<String> desplegable1 = new JComboBox<String>();
	private JPanel panelDesplegable = new JPanel(new FlowLayout());
	private JPanel panel2 = new JPanel(new FlowLayout());
	public DefaultComboBoxModel<String> comboModelo;
	//CONSTRUCTOR DE LA LAMINA DE CONFIGURACION
	
	public LaminaConfiguracion(MarcoInterfaz marcoInterfaz){
	
		//LLLAMA DE LOS COMBOBOX
		
		JPanel panelDesplegable2 = new JPanel(new FlowLayout());
	
		//LLAMADA DEL SECTOR DE RANGO Y CONDICIONES
		panelDesplegable.add(desplegable1);
		panel2.add(panelDesplegable);
		
		desplegable1.addItem("");
		desplegable1.addItem("Fuego");
		desplegable1.addItem("Humedad");
		desplegable1.addItem("Luz");
		desplegable1.addItem("Movimiento");
		desplegable1.addItem("Sonido");
		desplegable1.addItem("Temperatura");
		
		desplegable1.addActionListener(new ActionListener(){
			   public void actionPerformed(ActionEvent e) {
				  
				   
			   }
		   });  
		
		JComboBox<String> desplegable2 = new JComboBox<String>();
		
		desplegable2.addItem("");
		desplegable2.addItem("Activar Alarma");
		desplegable2.addItem("Encender Luz");
		desplegable2.addItem("Enviar Correo");
		
		desplegable2.addActionListener(new ActionListener(){
			   public void actionPerformed(ActionEvent e) {
				  
				   
			   }
		   });  
		//LLAMADA DEL SECTOR DE RANGO Y CONDICIONES
		panelDesplegable2.add(desplegable2);
		panel2.add(panelDesplegable2);
		
		JPanel superior = new JPanel(new FlowLayout());
		
		superior.add(panel2);
		
		CuadroRango inferior=new CuadroRango(marcoInterfaz);
		
		//LAYOUT DE LA LAMINA
		
		setLayout(new BorderLayout());
		
		//AGREGA LOS COMBOBOX Y EL CUADRO DE RANGO Y CONDICIONES
		
		add(superior, BorderLayout.NORTH);
		add(inferior,BorderLayout.CENTER);
		
		
    }
	
	

}

