package interfaz;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;



public class Actuadores {

	private JPanel panel;
	private JComboBox<String> comboA=new JComboBox<String>();
	
	public Actuadores(){
		
		panel = new JPanel(new FlowLayout());
		
		
		comboA.addItem("");
		comboA.addItem("Encender Luz");
		comboA.addItem("Activar Alarma");
		comboA.addItem("Enviar E-Mail");
		
		
		//EVENTO DEL COMBOBOX DE LOS ACTUADORES
		
		comboA.addActionListener(new ActionListener(){
			   public void actionPerformed(ActionEvent e) {
				   
				   //comboA.getSelectedItem() + "";
				   
			   }
	    });  
		
		
		panel.add(comboA);

	}
}
