package interfaz;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class CuadroActuadores extends JPanel{
	
	private String auxActuador;
	
	
	public CuadroActuadores(JComboBox<String> combo){
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints ubicacion = new GridBagConstraints();
		
		JLabel pin=new JLabel("Pin");
		
		ubicacion.gridx = 0; 
		ubicacion.gridy = 0; 
		ubicacion.gridwidth = 2; 
		ubicacion.gridheight = 1; 
		ubicacion.weighty = 0; 
		
		ubicacion.weighty = 0.5; 
		
		pin.setFont(new Font("Serif",Font.BOLD, 16));
	    pin.setHorizontalAlignment(SwingConstants.LEFT);
	    pin.setAlignmentX(LEFT_ALIGNMENT);
	    
	    add(pin,ubicacion);
	    
	    JTextField pinArea=new JTextField(5);
	    
	    ubicacion.gridx = 1; 
		ubicacion.gridy = 1; 
		ubicacion.gridwidth = 1; 
		ubicacion.gridheight = 1; 
		ubicacion.weighty = 0; 
		
		ubicacion.weighty = 0.3; 
		  
		add(pinArea, ubicacion);
		
		JLabel accion=new JLabel("Acción");
		
		ubicacion.gridx = 0; 
		ubicacion.gridy = 2; 
		ubicacion.gridwidth = 2; 
		ubicacion.gridheight = 1; 
		ubicacion.weighty = 0; 
		
		ubicacion.weighty = 0.5; 
		accion.setFont(new Font("Serif",Font.BOLD, 16));
	    accion.setHorizontalAlignment(SwingConstants.LEFT);
	    accion.setAlignmentX(LEFT_ALIGNMENT);
	    
	    add(accion, ubicacion);
	    
	    JTextField accionArea=new JTextField(15);
	    
	    ubicacion.gridx = 1; 
		ubicacion.gridy = 3; 
		ubicacion.gridwidth = 2; 
		ubicacion.gridheight = 1; 
		ubicacion.weighty = 0; 
		
		ubicacion.weighty = 0.5;  
		add(accionArea, ubicacion);
	
		JButton agregar=new JButton("Agregar");
	
		ubicacion.gridx = 0; 
		ubicacion.gridy = 5; 
		ubicacion.gridwidth = 2; 
		ubicacion.gridheight = 1; 
		ubicacion.weighty = 1; 
	
		ubicacion.weighty = 1.0;  
		add(agregar, ubicacion);
	
		agregar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
			   pinArea.getText();
			   auxActuador=accionArea.getText();
			   combo.addItem(auxActuador);
			   combo.setSelectedItem(auxActuador);
			   pinArea.setText("");
			   accionArea.setText("");
		     
			}
		});
		
		JButton quitar=new JButton("Quitar");
  
		ubicacion.gridx = 4; 
		ubicacion.gridy = 5; 
		ubicacion.gridwidth = 2; 
		ubicacion.gridheight = 1; 
		ubicacion.weighty = 0; 
	
		ubicacion.weighty = 1.0;  
		add(quitar, ubicacion);
	
		quitar.addActionListener(new ActionListener(){
		   public void actionPerformed(ActionEvent e) {
			   pinArea.getText();
			   auxActuador=accionArea.getText();
			     
			   combo.removeItem(auxActuador);
			     
			   pinArea.setText("");
			   accionArea.setText("");
		   }
		});
	}

}
