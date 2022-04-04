package interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class CuadroRango extends JPanel{
	
	private String auxArea1, auxArea2;
	private JPanel panelRango=new JPanel();
	private JPanel panelCondicion=new JPanel();
	private JPanel panelBoton=new JPanel();
	private JPanel panelCentrado=new JPanel();
	private JPanel titledBorders = new JPanel();
	private JPanel titledBorders2 = new JPanel();
	private JCheckBox check= new JCheckBox();
	private Border lineaNegra;
	
	
	public CuadroRango(MarcoInterfaz marcoInterfaz){
		
		//LAYOUT PRINCIPAL
		setLayout(new BorderLayout());
		
		//LAYOUT PARA EL PANEL DEL CUADRO DE LOS JLABEL DEL RANGO
		panelRango.setLayout(new GridBagLayout());
		
		GridBagConstraints contenedor = new GridBagConstraints();
			
		//LABEL PARA EL TEXTO "RANGO"
		
		JLabel rangoTitulo=new JLabel("Rango");
		
		setAlignmentX(LEFT_ALIGNMENT);
		
		contenedor.gridx = 0; 
		contenedor.gridy = 2; 
		contenedor.gridwidth = 3; 
		contenedor.gridheight = 1; 
		contenedor.weighty = 0.0; 
		rangoTitulo.setFont(new Font("Serif",Font.PLAIN, 16));
		rangoTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		
		//AGREGA EL TEXTO AL PANEL DEL RANGO
		
		panelRango.add(rangoTitulo, contenedor);
		
		//PRIMER TEXT
		
		JTextField rangoArea1=new JTextField(5);
	   
		contenedor.gridx = 0; 
		contenedor.gridy = 3; 
		contenedor.gridwidth = 1; 
		contenedor.gridheight = 1; 
		contenedor.weighty = 0.0; 
		
		//AGREGA EL TEXT AL PANEL DEL RANGO
		
		panelRango.add(rangoArea1, contenedor);
		
		//LABEL PARA EL TEXTO "<=  X  =>"
		
		JLabel rango=new JLabel("<=  X  =>");
		
		rango.setFont(new Font("Serif",Font.PLAIN, 16));
		rango.setHorizontalAlignment(SwingConstants.LEFT);
		
		contenedor.gridx = 1; 
		contenedor.gridy = 3; 
		contenedor.gridwidth = 1; 
		contenedor.gridheight = 1; 
		contenedor.weighty = 0.0; 
		
		// AGREGA EL TEXTO ANTERIOR AL PANEL DEL RANGO
		panelRango.add(rango, contenedor);
		
		//SEGUNDO TEXT
		
		JTextField rangoArea2=new JTextField(5);
		
		contenedor.gridx = 2; 
		contenedor.gridy = 3; 
		contenedor.gridwidth = 1; 
		contenedor.gridheight = 1; 
		contenedor.weighty = 0.0; 
		
		//AGREGA EL TEXT AL PANEL DEL RANGO
		
		panelRango.add(rangoArea2, contenedor);
		
		//PRIMER BOTON DE "APLICAR" 
		
		JButton botonR = new JButton("Aplicar");
		
		contenedor.gridx = 3; 
		contenedor.gridy = 3; 
		contenedor.gridwidth = 1; 
		contenedor.gridheight = 1;
		contenedor.anchor = GridBagConstraints.CENTER; 
		contenedor.weighty = 0.0; 
		botonR.addActionListener(new ActionListener(){
			   public void actionPerformed(ActionEvent e) {
				   Boolean h,j;
				   auxArea1=rangoArea1.getText();
				   auxArea2=rangoArea2.getText();
			     
				   h=esNumero(auxArea1);
				   j=esNumero(auxArea2);
			     
				   if(h && j){
			    	 
					   crearCondicion(Integer.parseInt(auxArea1),Integer.parseInt(auxArea2));
			    
					   panelCondicion.add(check);
					
					   check.setVisible(true);
			       
					   rangoArea1.setText("");
					   rangoArea2.setText("");
				   }
				   else{
			    	 
					   JOptionPane.showMessageDialog(null,"Por favor, ingrese un numero entero en el rango");
				   }
			   	}
		 });
		
		//AGREGA EL BOTON AL PANEL DEL RANGO
		
		panelRango.add(botonR,contenedor);
		
		//LABEL PARA EL TEXTO "MENOR QUE"
		
		JLabel menor=new JLabel("Menor que: ");
		
		rango.setFont(new Font("Serif",Font.PLAIN, 16));
		rango.setHorizontalAlignment(SwingConstants.LEFT);

		contenedor.gridx = 0; 
		contenedor.gridy = 4; 
		contenedor.gridwidth = 1; 
		contenedor.gridheight = 1; 
		contenedor.weighty = 0.0; 
		
		//AGREGA EL TEXTO AL PANEL DEL RANGO
		
		panelRango.add(menor, contenedor);
		
		//LABEL PARA EL TEXTO "X<= "
		
		JLabel menorX=new JLabel("X<= ");
		
		rango.setFont(new Font("Serif",Font.PLAIN, 16));
		rango.setHorizontalAlignment(SwingConstants.LEFT);
		
		contenedor.gridx = 0; 
		contenedor.gridy = 5; 
		contenedor.gridwidth = 1; 
		contenedor.gridheight = 1; 
		contenedor.weighty = 0.0; 
		
		//AGREGA EL LABEL AL PANEL DEL RANGO
		panelRango.add (menorX, contenedor);
		
		//TEXT PARA LA OPCION DE MENOR
		
		JTextField menorArea=new JTextField(5);
		
		contenedor.gridx = 1; 
		contenedor.gridy = 5; 
		contenedor.gridwidth = 1; 
		contenedor.gridheight = 1; 
		contenedor.weighty = 0.0; 
		
		//AGREGA EL TEXT AL PANEL DEL RANGO
		
		panelRango.add (menorArea, contenedor);
		
		//BOTON DE "Aplicar" PARA EL RANGO MENOR
		
		JButton botonMin = new JButton("Aplicar");
		
		contenedor.gridx = 2; 
		contenedor.gridy = 5; 
		contenedor.gridwidth = 1; 
		contenedor.gridheight = 1;
		contenedor.anchor = GridBagConstraints.CENTER; 
		contenedor.weighty = 0.0; 
		
		botonMin.addActionListener(new ActionListener(){
			   public void actionPerformed(ActionEvent e) {
			       Boolean h;
				   auxArea1=menorArea.getText();
				   
				   
				   h=esNumero(auxArea1);
				   
				   if(h){
				    	 
				        crearCondicionMenor(Integer.parseInt(auxArea1));
				    
						panelCondicion.add(check);
						
					
				        check.setVisible(true);
				       
				        menorArea.setText("");
				    }
				    else{
				    	 
				    	 JOptionPane.showMessageDialog(null,"Por favor, ingrese un numero entero en el rango");
				    }
				   
				   
			     
			   }
		});
		
		//AGREGA EL BOTON AL PANEL DERL RANGO
		
		panelRango.add(botonMin,contenedor);
		
		//LABEL PARA EL TEXTO "Mayor que: "
		
		JLabel mayor=new JLabel("Mayor que: ");
		
		rango.setFont(new Font("Serif",Font.PLAIN, 16));
		rango.setHorizontalAlignment(SwingConstants.LEFT);
		
		contenedor.gridx = 0; 
		contenedor.gridy = 6; 
		contenedor.gridwidth = 1; 
		contenedor.gridheight = 1; 
		contenedor.weighty = 0.0; 
		
		//AGREGA EL LABEL AL PANEL DEL RANGO
		
		panelRango.add (mayor, contenedor);
		
		//LABEL PARA EL TEXTO "X>= "
		
		JLabel mayorX=new JLabel("X>= ");
		
		rango.setFont(new Font("Serif",Font.PLAIN, 16));
		rango.setHorizontalAlignment(SwingConstants.LEFT);
		
		contenedor.gridx = 0; 
		contenedor.gridy = 7; 
		contenedor.gridwidth = 1; 
		contenedor.gridheight = 1; 
		contenedor.weighty = 0.0; 
		
		//AGREGA EL LABEL AL PANEL DEL RANGO
		
		panelRango.add (mayorX, contenedor);
		
		//TEXT PARA PONER EL RANGO MAYOR
		
		JTextField mayorArea=new JTextField(5);
		
		contenedor.gridx = 1; 
		contenedor.gridy = 7; 
		contenedor.gridwidth = 1; 
		contenedor.gridheight = 1; 
		contenedor.weighty = 0.0; 
		
		//AGREGA EL TEXT AL PANEL DEL RANGO
		
		panelRango.add (mayorArea, contenedor);
		
		//BOTON DE "Aplicar" PARA PONER EL RANGO MAYOR
		
		JButton botonMax = new JButton("Aplicar");
		
		contenedor.gridx = 2; 
		contenedor.gridy = 7; 
		contenedor.gridwidth = 1; 
		contenedor.gridheight = 1;
		contenedor.anchor = GridBagConstraints.CENTER; 
		contenedor.weighty = 0.0; 
		
		botonMax.addActionListener(new ActionListener(){
			   public void actionPerformed(ActionEvent e) {
				   Boolean h; 
				   auxArea1=mayorArea.getText();
				   h=esNumero(auxArea1);
					  
				   if(h){
			    	 
					   crearCondicionMayor(Integer.parseInt(auxArea1));
			    
					   panelCondicion.add(check);
					
					   //check.setBackground(Color.LIGHT_GRAY);
					   check.setVisible(true);
			       
					   mayorArea.setText("");
				   }
				   else{
			    	 
					   JOptionPane.showMessageDialog(null,"Por favor, ingrese un numero entero en el rango");
				   }
			 
			   }
	    });
		
		//AGREGA EL BOTON AL PANEL DEL RANGO
		
		panelRango.add(botonMax,contenedor);
		
		//LAYOUT PARA EL PANEL DE LOS CHECKBOX
		
		panelCondicion.setLayout(new GridLayout(6,1));
		
		//COLOR PARA EL PANEL DE LOS CHECKBOX
		
		//panelCondicion.setBackground(Color.lightGray);
		
		//LINEA DE CONTORNO DE LOS PANELES
		
		lineaNegra = BorderFactory.createLineBorder(Color.WHITE,2);
	    
	    addCompForBorder(lineaNegra, "", panelCentrado);
	    
	    //panelCondicion.add(check);
	    titledBorders.setLayout(new BoxLayout(titledBorders, BoxLayout.Y_AXIS));
	    titledBorders2.setLayout(new BoxLayout(titledBorders, BoxLayout.Y_AXIS));
	    TitledBorder titled, tituloCondicion;
	    
	    titled = BorderFactory.createTitledBorder(lineaNegra, "Condicion que dispare la acción");
	    addCompForTitledBorder(titled, "titled line border" + " (centered, default pos.)", TitledBorder.CENTER, TitledBorder.CENTER, panelRango, Color.gray);
	   
	    tituloCondicion = BorderFactory.createTitledBorder(lineaNegra, "Condiciones");
	    addCompForTitledBorder2(tituloCondicion, "titled line border" + " (centered, default pos.)", TitledBorder.CENTER, TitledBorder.CENTER, titledBorders2, Color.gray);
	   
	   //LAYOUT PARA CONTENER EL PANEL DE LOS CHECKBOX Y EL BOTON DE QUITAR
	    
	    panelCentrado.setLayout(new BorderLayout());
	    
	    //AGREGA EL PANEL DE LOS CHECKBOX AL PANEL CENTRADO
	    panelCentrado.add(panelCondicion,BorderLayout.CENTER);
	    
	    //LAYOUT PARA EL PANEL DEL BOTON QUITAR
	    
	    panelBoton.setLayout(new FlowLayout());
	    
	    //BOTON QUITAR LOS CHECKBOX
	    
		JButton botonQuitar= new JButton("Quitar");
		
		botonQuitar.addActionListener(new ActionListener(){
			  
			public void actionPerformed(ActionEvent e) {
				  
				   Component[] componentes = panelCondicion.getComponents();
				  
				   for (Component component : componentes) {
					   JCheckBox checkbutton = (JCheckBox)component;
					   
					   if(checkbutton.isSelected()){
						   
						   panelCondicion.remove(checkbutton);
						  
					   }
					 }	
				   panelCondicion.revalidate();
				   panelCondicion.repaint();
					
				}
				
		});
		
		//AGREGA EL BOTON AL PANEL DEL BOTON
		
		panelBoton.add(botonQuitar);
		
		//COLOR DEL PANEL DEL BOTON
		
		//panelBoton.setBackground(Color.LIGHT_GRAY);
		
		//AGREGA EL PANEL DEL BOTON AL PANEL CENTRADO
		
		panelCentrado.add(panelBoton,BorderLayout.SOUTH);
		
		//AGREGA EL PANEL CENTRADO AL PANEL PRINCIPAL
		JScrollPane scrollPane = new JScrollPane(panelCentrado);
		JPanel panelAmpliado = new JPanel(new BorderLayout());
		
		panelAmpliado.add(scrollPane, BorderLayout.CENTER);
		panelAmpliado.add(panelBoton, BorderLayout.SOUTH);
		
		add(panelAmpliado, BorderLayout.CENTER);
		
	}
	
	void addCompForBorder(Border border, String description, Container contenedor) {
	   
		panelRango.setBorder(border);
	    add(panelRango, BorderLayout.WEST);
	    titledBorders.setBorder(border);
	    ;
	    contenedor.add(Box.createRigidArea(new Dimension(0, 10)));
	    contenedor.add(titledBorders);
	  }

	void addCompForBorder2(Border border, String description, Container contenedor) {
	    
	    panelCondicion.setBorder(border);
	    add(panelCondicion, BorderLayout.CENTER);
	    titledBorders.setBorder(border);
	    ;
	    
	    contenedor.add(Box.createRigidArea(new Dimension(0, 10)));
	    contenedor.add(titledBorders2);
	  }	

	void addCompForBorder3(Border border, String description, Container contenedor) {
    
		panelCondicion.setBorder(border);
		add(panelCondicion, BorderLayout.EAST);
		titledBorders.setBorder(border);
		;
    
		contenedor.add(Box.createRigidArea(new Dimension(0, 10)));
		contenedor.add(titledBorders2);
	}	
	
	void addCompForTitledBorder(TitledBorder border, String description,
		int justification, int position, Container container, Color color) {
	    border.setTitleJustification(justification);
	    border.setTitlePosition(position);
	 
	}
	
	void addCompForTitledBorder2(TitledBorder border, String description,
        int justification, int position, Container container, Color color) {
	    border.setTitleJustification(justification);
	    border.setTitlePosition(position);
       
	}
	
	public void crearCondicion(int r1, int r2){
		
		check=new JCheckBox(r1+" <=  X  => "+r2);
		
		panelCondicion.add(check);
		
		this.validate();
		this.repaint();
		
		
		
		
	}
	
	public void crearCondicionMenor(int r1){
		
		check=new JCheckBox(" X<= "+r1);
		panelCondicion.add(check);
		
		this.validate();
		this.repaint();
	
		
	}
	
	public void crearCondicionMayor(int r1){
		
		check=new JCheckBox(" X>= "+r1);
		panelCondicion.add(check);
			
		this.validate();
		this.repaint();
	
	}
	

	
	private static boolean esNumero(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	
	
}

