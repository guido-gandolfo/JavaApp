package interfaz;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class MarcoInterfaz extends JFrame{

	//DECLARACION DE VARIABLES
	private InterfazMain interfazMain;
	private JPanel panel1 = new JPanel();
	private JPanel panel2 = new JPanel();
	private JPanel panel3 = new JPanel();
	private JPanel panel4 = new JPanel();
	 
	//CONSTRUCTOR DEL MARCO
	
	public MarcoInterfaz(InterfazMain _interfazMain){
		
		interfazMain = _interfazMain;
		
		//TITULO DE LA APLICACION
		
		setTitle("APLICACION PARA SENSORES");
		
		//POSICION Y TAMAÑO
		
		Toolkit miPantalla= Toolkit.getDefaultToolkit();  
		miPantalla.getScreenSize();
		setBounds(200, 200, 1000, 500);
		
		//BORDE DEL MENU DE LA APLICACION
		
		Border lineaNegra;
	    lineaNegra = BorderFactory.createLineBorder(Color.black);
	    
	    //FUNCIONALIDAD DEL MENU DE LA APLICACION
	    
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
	    Marco(lineaNegra, "", panel1);
	    
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
	    Marco(lineaNegra, "", panel2);
	    
	    panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
	    Marco(lineaNegra, "", panel3);
	    
	    panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));
	    Marco(lineaNegra, "", panel4);
	   
	    //MENU DE LA APLICACION
	    
	    JTabbedPane menu = new JTabbedPane();
	    menu.addTab("PRINCIPAL", null, panel1, null);
	    //menu.addTab("CONFIGURACION", null, panel2, null);
	    menu.addTab("CONFIGURACION", null, panel3, null);
	    menu.addTab("ESTADISTICA", null, panel4, null);
	    
	    menu.setSelectedIndex(0);
	    
	    //AGREGA EL MENU AL MARCO
	    
	    add(menu);
	    
	    //COMPACTA LOS COMPONENTES
	    
	    pack();
	    
	}
	
	//FUNCION QUE DA LA LAMINA DEL MENU SELECCIONADO
	
	void Marco(Border border, String description, Container contenedor) {
	    
		JPanel comp = new JPanel(new GridLayout(1, 1), true);
	    LaminaPrincipal lam = new LaminaPrincipal(interfazMain);
	    //LaminaConfiguracion lam2 = new LaminaConfiguracion(this);
	    LaminaActuadores lam3 = new LaminaActuadores(interfazMain);
	    LaminaEstadistica lam4 = new LaminaEstadistica();
	    
	    // LLAMADA A LA LAMINA CORRESPONDIENTE SEGUN MENU SELECCIONADO
	    
	    comp.setBorder(border);
	    if(contenedor.equals(panel1)){
	    	comp.add(lam);
	    
	    }
	   // else if(contenedor.equals(panel2)){
	    //	comp.add(lam2);
	    
	   // }
	    else if(contenedor.equals(panel3)){
	    	comp.add(lam3);
	    }
	    else if(contenedor.equals(panel4)){
	    	comp.add(lam4);
	    }	
	    
	    contenedor.add(Box.createRigidArea(new Dimension(10, 10)));
	    contenedor.add(comp);
	  }
	
}
