package interfaz;

import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class LaminaEstadistica extends JPanel{
	
	public LaminaEstadistica(){
		
		
		//setBackground(Color.lightGray);
		setLayout(new FlowLayout());
		CuadroEstadistico cuadro=new CuadroEstadistico();
		
		add(cuadro);
		
	}

}
