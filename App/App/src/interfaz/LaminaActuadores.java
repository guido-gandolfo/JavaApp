package interfaz;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.*;

import org.jfree.data.time.DynamicTimeSeriesCollection;

import arduino.Actuador;
import arduino.Sensor;
import correo.EnvioCorreo;
import correo.Ingreso;


public class LaminaActuadores extends JPanel {
	
	
	private JComboBox<String> comboB=new JComboBox<String>();
	private String auxPin, auxNombre;
	private JTextField pinArea;
	private JLabel nombre;
	private JTextField nombreArea;
	private InterfazMain interfazMain;
	private JButton agregar;
	private JRadioButton opcion = new JRadioButton("Digital", true);
	private JRadioButton opcion2 = new JRadioButton("Analogico", false);
	private Map<String, String> mapaPines;
	private Actuador actuador;
	private DynamicTimeSeriesCollection dataset;
	public JComboBox<String> comboA = new JComboBox();
	private LaminaPrincipal lamina;
	public javax.swing.DefaultComboBoxModel<String>  comboModelo= new javax.swing.DefaultComboBoxModel<String>() ;
	
	
	



	public javax.swing.Timer timerRead = new javax.swing.Timer(1000, new java.awt.event.ActionListener() {
		
		public void actionPerformed(java.awt.event.ActionEvent evt) {

			mapaPines = interfazMain.comunicacion.readAll();
			
		}

	});
	private MarcoInterfaz marcoInterfaz;
	
	
	public LaminaActuadores(InterfazMain _interfazMain){
		
		interfazMain = _interfazMain;
		
		timerRead.start();
		
		//LAYOUT DEL PANEL PRINCIPAL
		
		setLayout(new BorderLayout());	
		
		//PANEL DEL COMBOBOX	
	
		JPanel panelCombo=new JPanel(new FlowLayout());	
		
		
		//comboA.setSelectedItem(comboModelo);
		//comboA.setModel(comboModelo);
		JTextArea area=new JTextArea(5,50);	
		comboA.setModel(comboModelo);
		repaint();
		
		//COLOR DEL PANEL
		comboA.addItem("");
		comboA.addItem("Fuego");
		comboA.addItem("Humedad");
		comboA.addItem("Luz");
		comboA.addItem("Movimiento");
		comboA.addItem("Sonido");
		comboA.addItem("Temperatura");
		//COMBOBOX DE LOS ACTUADORES
		
		comboB.addItem("");
		comboB.addItem("Enviar Correo");
		
		//EVENTO DEL COMBOBOX DE LOS SENSORES
		JPanel panelDes = new JPanel(new FlowLayout());
		
		panelDes.add(comboA);
		panelCombo.add(comboB);
		
		JPanel panelCont = new JPanel(new GridLayout(4, 1, 4, 4));
		
		panelCont.add(panelDes);
		
		panelCont.add(panelCombo);
		
		comboB.addActionListener(new ActionListener(){
		   public void actionPerformed(ActionEvent e) {
			   
			   auxNombre= comboB.getSelectedItem() + "";
			   auxPin= "";
			   
			   if (auxNombre=="Enviar E-Mail"){
				   EnvioCorreo correo = null;
			       correo.enviar();
			   }
			   
			   comboB.getSelectedItem();
			    area.setText(auxNombre);
		
			   //interfazMain.mapaSensores.put(auxNombre, new arduino.Api().digitalWriteUp(sensorActual.pin)); 
				
		   }
		  
		});
		
		JPanel bIniciar= new JPanel(new FlowLayout());
		JPanel bParar= new JPanel(new FlowLayout());
		
		JButton iniciar = new JButton("Activar");
	
		iniciar.addActionListener(new ActionListener(){
			   
			public void actionPerformed(ActionEvent e) {
				  
			try{
				
				/*Actuador act = new Actuador(Integer.parseInt(auxPin), auxNombre);
				//act.encender();
				act.interfazMain.mapaActuadores.get(auxPin).encender();*/
						}catch(Exception ea){
							ea.printStackTrace();
			}
				
			}
		});

		JButton parar = new JButton("Desactivar");
	
		parar.addActionListener(new ActionListener(){
			  
			public void actionPerformed(ActionEvent e) {
				   
				Actuador act = null;
				act.apagar();
			   }
		   });
		
		bIniciar.add(iniciar);
		bParar.add(parar);
		
		panelCont.add(bIniciar);
		
		panelCont.add(bParar);
		
		JPanel inter2 = new JPanel(new FlowLayout());
		
		inter2.add(panelCont);
		add(inter2, BorderLayout.WEST);
		
		JLabel pin=new JLabel("PIN:");
		
		JPanel panelDer = new JPanel(new BorderLayout());
		
		JPanel panelMedio = new JPanel(new BorderLayout());
		panelDer.setLayout(new GridLayout(4,2,8,8));
		
		panelDer.add(pin);
		
		//TEXT PARA EL PIN
	    
	    pinArea=new JTextField(5);
	    
		//AGREGA EL TEXT AL PANEL
		
	    panelDer.add(pinArea);
	    
		nombre=new JLabel("Actuador:");
		panelDer.add(nombre);
		
		nombreArea=new JTextField(15);
		
		panelDer.add(nombreArea);
	
		ButtonGroup grupo = new ButtonGroup();
		grupo.add(opcion);
		grupo.add(opcion2);
		
		
		panelDer.add(opcion);
		panelDer.add(opcion2);
		agregar=new JButton("Agregar");
		
		agregar.addActionListener(new ActionListener(){
			  
			public void actionPerformed(ActionEvent e) {
			    
				auxPin=pinArea.getText();
			     auxNombre=nombreArea.getText();
			 
			     if (opcion.isSelected()){
			    	 area.setText("Pin: " + auxPin + "\nActuador: " + auxNombre + "\nTipo: Digital");
			    	interfazMain.mapaActuadores.put(auxNombre, actuador);
			     }
			     else{
			    	 area.setText("Pin: " + auxPin + "\nActuador: " + auxNombre + "\nTipo: Analogico");		
			    	//interfazMain.mapaSensores.put(auxNombre, new arduino.SensorAnalogico(Integer.parseInt(auxPin), auxNombre));
			    	 interfazMain.mapaActuadores.put(auxPin, new arduino.Actuador(Integer.parseInt(auxPin), auxNombre));
			    	 System.out.println(actuador);
			    	 System.out.println(auxNombre);
			     }
			     
			     comboB.addItem(auxNombre);
			    
			     pinArea.setText("");
			     nombreArea.setText("");
			 
			}
		});
		
		panelDer.add(agregar);
		
		JButton quitar=new JButton("Quitar");
		
		//EVENTO DEL BOTON QUITAR
		
		quitar.addActionListener(new ActionListener(){
					  
			public void actionPerformed(ActionEvent e) {
					    
				auxPin=pinArea.getText();
				auxNombre=nombreArea.getText();
					     
				comboB.removeItem(auxNombre);
					     
				pinArea.setText("");
				nombreArea.setText("");
			}
		});
		
		panelDer.add(quitar);	
		
		CuadroRango inferior=new CuadroRango(marcoInterfaz);
		
		
		
		JPanel inter = new JPanel(new FlowLayout());
				
		JPanel contenedor = new JPanel(new BorderLayout());
		
		JPanel panelArea = new JPanel(new FlowLayout());
		
		inter.add(panelDer);
		panelArea.add(area);
		panelMedio.add(inter, BorderLayout.WEST);
		panelMedio.add(panelArea, BorderLayout.CENTER);
		
		JPanel derecho = new JPanel(new BorderLayout());
		
		derecho.add(panelMedio, BorderLayout.NORTH);
		derecho.add(inferior, BorderLayout.CENTER);
		
		
		//contenedor.add(derecho, BorderLayout.WEST);
		add(derecho, BorderLayout.CENTER);
				
		
				
		//add(panelArea, BorderLayout.EAST);
	
	}


	

}
