package interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Map;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.DynamicTimeSeriesCollection;
import org.jfree.data.time.Second;
import archivo.Escribir;

@SuppressWarnings("serial")
public class LaminaPrincipal extends JPanel{
	
	private JTextArea area;
	private JTextField pinArea,nombreArea;
	private JButton agregar, quitar;
    public String auxPin, auxNombre;
	private JLabel pin;
	private JLabel nombre;
	private Map<String, String> mapaPines;
	private InterfazMain interfazMain;
	private DynamicTimeSeriesCollection dataset;
	private JFreeChart chart;
	private float[] valor = new float[1];
	private arduino.Sensor sensorActual = null;
	private DateAxis eje;
	private Escribir graba = new Escribir();
	private JRadioButton opcion = new JRadioButton("Digital", true);
	private JRadioButton opcion2 = new JRadioButton("Analogico", false);
	private LaminaActuadores lamA=null;
	public JComboBox<String> desplegable = new JComboBox<String>();
	private JButton iniciar;
	
	
	private javax.swing.Timer timer = new javax.swing.Timer(1000, new java.awt.event.ActionListener() {

		private String f;
		public void actionPerformed(java.awt.event.ActionEvent evt) {
			
			if (sensorActual != null){
				
				if (opcion.isSelected()){
					
					f=mapaPines.get("D" + sensorActual.pin);
					valor[0]=Integer.parseInt(f);
					graba.escribir(sensorActual.nombre ,mapaPines.get("D"+sensorActual.pin), dataset.advanceTime());
					
		         }
		         else{
		        	 
		        	 f=mapaPines.get("A" + sensorActual.pin);
					 valor[0]=Integer.parseInt(f);		
					 graba.escribir(sensorActual.nombre ,mapaPines.get("A"+sensorActual.pin), dataset.advanceTime());
		    	 
		         }
		     
			 }
			
			 dataset.appendData(valor);
			
		 }

	});

	public javax.swing.Timer timerRead = new javax.swing.Timer(1000, new java.awt.event.ActionListener() {

		public void actionPerformed(java.awt.event.ActionEvent evt) {

			mapaPines = interfazMain.comunicacion.readAll();
		
		}

	});
	
	public LaminaPrincipal(InterfazMain _interfazMain){
		
		interfazMain = _interfazMain;
		
		timerRead.start();
		
		//LAYOUT PRINCIPAL
		
		setLayout(new BorderLayout());
				
		//POSICION DEL COMBOBOX DE LOS SENSORES
		
		desplegable.setAlignmentX(LEFT_ALIGNMENT);
		
		JPanel panelDes = new JPanel(new FlowLayout());
		
		//EVENTO DEL COMBOBOX
		
		desplegable.addActionListener(new ActionListener(){
		
			public void actionPerformed(ActionEvent e) {
				auxNombre = desplegable.getSelectedItem() + "";
			sensorActual = interfazMain.mapaSensores.get(auxNombre);
				 
				 if (sensorActual.tipo.compareTo("digital") == 0){
					 area.setText("Sensor: "+auxNombre + "\n" + "Numero de PIN: " + sensorActual.pin + "\nValor: " + mapaPines.get("D"+sensorActual.pin) + "\nTipo: Digital");
				 }
				 if (sensorActual.tipo.compareTo("analogico") == 0){
					 area.setText("Sensor: "+auxNombre + "\n" + "Numero de PIN: " + sensorActual.pin + "\nValor: " + mapaPines.get("A"+sensorActual.pin)+ "\nTipo: Analogico");
				 }
			}
			  
		}); 
		
		//AGREGA EL COMBOBOX AL PANEL 
		
		JPanel panelIzq = new JPanel(new BorderLayout());
		
		//TEXT PARA MOSTRAR LA INFORMACION DEL SENSOR
		
	    area=new JTextArea(5,30);
	 
		//BARRAS DE DESPLAZAMIENTO DEL TEXT
		
		JScrollPane barrasA=new JScrollPane(area);
		barrasA.setAutoscrolls(true);
		barrasA.setViewportView(area);
		barrasA.add(area);
		
		area.setLineWrap(true);
		
		//AGREGA EL TEXT AL PANEL 
		
		//LABEL DEL TEXTO "PIN"
		
		JPanel panelDer = new JPanel(new BorderLayout());
		
		panelDer.setLayout(new GridLayout(4,2,8,8));
		
		JPanel panelDerecho = new JPanel(new FlowLayout());
		
	    pin=new JLabel("PIN:");
	    
	    //TIPO, ESTILO T TAMAÑO DE LA LETRA DEL LABEL
	    
	    pin.setFont(new Font("Serif",Font.BOLD, 16));
	    pin.setHorizontalAlignment(SwingConstants.LEFT);
	    pin.setAlignmentX(LEFT_ALIGNMENT);
	  
	    //AGREGA EL LABEL AL PANEL 
	    
	    panelDer.add(pin);
	    
	    //TEXT PARA EL PIN
	    
	    pinArea=new JTextField(5);
	    
		//AGREGA EL TEXT AL PANEL
		
	    panelDer.add(pinArea);
		
		//LABEL PARA EL TEXTO "Nombre:"
		
		nombre=new JLabel("Nombre:");
		
		//TIPO, ESTILO T TAMAÑO DE LA LETRA DEL LABEL
		
		nombre.setHorizontalAlignment(SwingConstants.LEFT);
		nombre.setFont(new Font("Serif",Font.BOLD, 16));
		nombre.setAlignmentX(LEFT_ALIGNMENT);
	
		//AGREGA EL LABEL AL PANEL
		
		panelDer.add(nombre);
		
		//TEXT PARA COLOCAR EL NOMBRE DEL SENSOR
		
		nombreArea=new JTextField(15);
		
		panelDer.add(nombreArea);
		
		//BOTON AGREGAR
		
		ButtonGroup grupo = new ButtonGroup();
		grupo.add(opcion);
		grupo.add(opcion2);
		
		panelDer.add(opcion);
		panelDer.add(opcion2);
		agregar=new JButton("Agregar");
		
		//EVENTO DEL BOTON AGREGAR
		 
		agregar.addActionListener(new ActionListener(){
			public javax.swing.DefaultComboBoxModel<String> comboModelo= new javax.swing.DefaultComboBoxModel<String> () ;  
			public void actionPerformed(ActionEvent e) {
				
				auxPin=pinArea.getText();
				auxNombre=nombreArea.getText();
				desplegable.setSelectedItem(auxNombre);
				if (opcion.isSelected()){
					//area.setText("Sensor: "+auxNombre + "\n" + "Numero de PIN: " + sensorActual.pin + "\nValor: " + mapaPines.get("D"+sensorActual.pin) + "\nTipo: Digital");
					interfazMain.mapaSensores.put(auxNombre, new arduino.SensorDigital(Integer.parseInt(auxPin), auxNombre));
			 	}
				else{
					//area.setText("Sensor: "+auxNombre + "\n" + "Numero de PIN: " + sensorActual.pin + "\nValor: " + mapaPines.get("A"+sensorActual.pin)+ "\nTipo: Analogico");  	 			
					interfazMain.mapaSensores.put(auxNombre, new arduino.SensorAnalogico(Integer.parseInt(auxPin), auxNombre));
			 	}
				
			     try{
			    	 auxPin=pinArea.getText();
						auxNombre=nombreArea.getText().toString();
			    	
			    	comboModelo.addElement(auxNombre);
			    	
			    	
			     }catch(Exception en){
			    	 
			    	 JOptionPane.showMessageDialog(null,"Felicidades, otro error");
			     }
				
				//desplegable.setModel(comboModelo);  
				//comboModelo.addElement(auxNombre);
				///desplegable.setSelectedIndex(-1);
				
				
			     desplegable.setModel(comboModelo);
			    
			    pinArea.setText("");
			    nombreArea.setText("");
		  
			}
		});
		
		//AGREGA EL BOTON AGREGAR AL PANEL
		
		panelDer.add(agregar);
		
		//BOTON DE QUITAR
		
		quitar=new JButton("Quitar");
		
		//EVENTO DEL BOTON QUITAR
		
		quitar.addActionListener(new ActionListener(){
			   
			public void actionPerformed(ActionEvent e) {
			    
				auxPin=pinArea.getText();
				auxNombre=nombreArea.getText().toString();
				desplegable.setSelectedItem(auxNombre);
			    desplegable.removeItem(auxNombre);
				     
			    pinArea.setText("");
			    nombreArea.setText("");
			    	 
			    		  
			}
			});

		//AGREGA EL BOTON QUITAR AL PANEL
		
		panelDer.add(quitar);
		
		dataset = new DynamicTimeSeriesCollection(2, 2000, new Second());
		dataset.setTimeBase(new Second(0, 0, 0, 31, 1, 2017));
		dataset.addSeries(new float[1], 0, auxNombre);
		
		chart = ChartFactory.createTimeSeriesChart("", "Tiempo", "Medicion", dataset, true, true, true);
		
		XYPlot plot = chart.getXYPlot();
		
		eje = (DateAxis) plot.getDomainAxis();
		
		eje.setFixedAutoRange(50000);
		eje.setDateFormatOverride(new SimpleDateFormat("ss.SS"));
		
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500,100)); 
		panelIzq.add(chartPanel, BorderLayout.CENTER);
		
		JPanel botones = new JPanel(new FlowLayout());
		JPanel bIniciar= new JPanel(new FlowLayout());
		JPanel bParar= new JPanel(new FlowLayout());
		
		iniciar = new JButton("Iniciar Sensor");
		
		iniciar.addActionListener(new ActionListener(){
			  
			public void actionPerformed(ActionEvent e) {
				  
				String a;
				a = desplegable.getSelectedItem().toString();
				if (a!=null){
					timer.start(); 
					 
				}
				else{
					JOptionPane.showMessageDialog(null,"Seleccione un sensor");
				}
			}
		 });

		JButton parar = new JButton("Detener Sensor");
		
		parar.addActionListener(new ActionListener(){
			  
			public void actionPerformed(ActionEvent e) {
				   
				timer.stop();
			}
		});
		
		bIniciar.add(iniciar);
		bParar.add(parar);
		
		panelDes.add (desplegable);
		
		botones.add(bIniciar);
		botones.add(bParar);
		botones.add(panelDes);
		
		panelIzq.add(botones, BorderLayout.NORTH);
		
		JPanel inter = new JPanel(new BorderLayout());
		
		add(panelIzq, BorderLayout.WEST);
		inter.add(panelDer, BorderLayout.CENTER);
		
		JPanel panelArea = new JPanel(new FlowLayout());
		
		panelArea.add(area);
		inter.add(panelArea, BorderLayout.SOUTH);
		
		panelDerecho.add(inter);
		add(panelDerecho, BorderLayout.EAST);
		
	}

	public String getAuxNombre() {
		return auxNombre;
	}

	public void setAuxNombre(String auxNombre) {
		this.auxNombre = auxNombre;
	}

	public JComboBox<String> getDesplegable() {
		return desplegable;
	}

	public void setDesplegable(JComboBox<String> desplegable) {
		this.desplegable = desplegable;
	}
	
	

}