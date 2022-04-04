package interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;

import javax.swing.*;

import org.jfree.chart.*;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.RectangleInsets;

import com.toedter.calendar.JDateChooser;

import archivo.Leer;

@SuppressWarnings("serial")
public class CuadroEstadistico extends JPanel{
	
	private JPanel panelPrincipal=new JPanel();
	private JPanel panelCombo=new JPanel();
	private JPanel panelContenedorCalendario=new JPanel();
	private JPanel panelCalendario=new JPanel();
	private JComboBox<String> desplegable=new JComboBox<String>();
	private JFreeChart chart;
	private Leer fichero=new Leer();
	private String auxNombre;
	private LaminaPrincipal lamina;
	public javax.swing.DefaultComboBoxModel<String>  comboModelo= new javax.swing.DefaultComboBoxModel<String>() ;
	
	public CuadroEstadistico(){
		  
		Fecha fecha=new Fecha();
			
		//LAYOUT PRINCIPAL
		
		setLayout(new FlowLayout());
		
		
		panelPrincipal.setLayout(new GridLayout(4,1));
		
		//LAYOUT PARA EL PANEL DEL COMBO DE SENSORES
		
		panelCombo.setLayout(new FlowLayout());
		
		//COLOR PARA EL PANEL DEL COMBO DE SENSORES
		
		//desplegable.setModel(comboModelo);
		//desplegable.setModel(comboModelo);
		desplegable.setAlignmentX(LEFT_ALIGNMENT);
		desplegable.setModel(comboModelo);
		desplegable.addItem("");
		desplegable.addItem("Fuego");
		desplegable.addItem("Humedad");
		desplegable.addItem("Luz");
		desplegable.addItem("Movimiento");
		desplegable.addItem("Sonido");
		desplegable.addItem("Temperatura");		
		desplegable.addActionListener(new ActionListener(){
			   public void actionPerformed(ActionEvent e) {
				   
				   auxNombre = desplegable.getSelectedItem().toString();
				   
				   desplegable.setModel(comboModelo);
			   }
			  
		   }); 
		
		//AGREGA EL COMOBOBOX AL PANEL DEL COMBOBOX
		
		panelCombo.add(desplegable);
		
		//AGREGA EL PANEL DEL COMBOBOX AL PANEL PRINCIPAL
		
		panelPrincipal.add(panelCombo, BorderLayout.NORTH);
       
		//CREA LOS BOX DEL CALENDARIO
		
		JDateChooser calendario=new JDateChooser();
		JDateChooser calendario2=new JDateChooser();
		
		//LAYOUT PARA EL PANEL QUE CONTIENE EL PANEL DE CALENDARIOS
		
		panelCalendario.setLayout(new FlowLayout());
		
		//LAYOUT PARA EL PANEL DE LOS BOXES DE CALENDARIO 
		
		panelContenedorCalendario.setLayout(new FlowLayout());
		
		//AGREGA LOS BOXES DE CALENDARIO AL PANEL CONTENEDOR CALENDARIO
		
		//panelCalendario.add(calendario);
		//panelContenedorCalendario.add(calendario2);
		
		//COLOR PARA EL PANEL
		
		
		//AGREGA EL PANEL CONTENEDOR DE CALENDARIOS AL PANEL 
		
		
		//PANEL DEL BOTON
		
		JPanel panelBoton=new JPanel();
		
		//AGREGA COLOR Y LAYOUT AL PANEL
		
		//panelBoton.setBackground(Color.lightGray);
		panelBoton.setLayout(new FlowLayout());
		
JPanel panelCuadro=new JPanel();
		
		panelCuadro.setLayout(new FlowLayout());
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		
		
		chart=ChartFactory.createTimeSeriesChart(auxNombre,"", "", dataset, true, true, false);
				
		chart.setBackgroundPaint(Color.white);
		
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setBaseShapesVisible(true);
            renderer.setBaseShapesFilled(true);
            renderer.setDrawSeriesLineAsPath(true);
           
        }

        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("HH:mm"));
		
          
                TimeSeries s1 = new TimeSeries("");
                
                
                
              //  devuelve=Integer.parseInt(leyendo.leer(auxNombre));
              
                dataset.addSeries(s1);
              

		
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500,250));
		add(chartPanel, BorderLayout.WEST);
	
		
		add(panelCuadro, BorderLayout.CENTER);
		
		//BOTON "FILTRAR"
		
		JButton botonFiltrar=new JButton("Filtrar");
		
		botonFiltrar.addActionListener(new ActionListener(){
			   public void actionPerformed(ActionEvent e) {
				   
				   LinkedList<String> lista;
				   
				   String c;
				   
				   Calendar fecha1 = calendario.getCalendar();
				   c = desplegable.getSelectedItem().toString();
				   System.out.println(fecha1);
				   //System.out.println(fecha2);
				   
				   lista = fichero.leer(c);
				   
				   for (String linea : lista){
					   
					   String fecha = linea.split(";")[0];
					   
					   String dia = fecha.split(" ")[0];
					   String mes = fecha.split(" ")[1];;
					   String hora = fecha.split(" ")[2].split(":")[0];
					   String minuto= fecha.split(" ")[2].split(":")[1];
					   String segundos = fecha.split(" ")[2].split(":")[2];
					   String anio = fecha.split(" ")[3];
					   
					   s1.add(new Second(Integer.parseInt(segundos), Integer.parseInt(minuto), Integer.parseInt(hora), Integer.parseInt(dia), Integer.parseInt(mes), Integer.parseInt(anio)), Float.parseFloat(linea.split(";")[1]));
					   
				   }
				   
			      }
			   });
		
		//AGREGA EL BOTON AL PANEL
		
		panelBoton.add(botonFiltrar);
		
		//AGREGA EL PANEL QUE CONTIENE EL BOTON AL PANEL CONTENEDOR
		
		//panelCalendario.add(panelBoton, BorderLayout.CENTER);
		
		/*AGREGA EL PANEL PRINCIPAL QUE CONTIENE LOS BOXES DE CALENDARIO A 
		LA PARTE CENTRAL DEL PANEL DEL LADO OESTE DEL PANEL PRINCIPAL*/
		panelPrincipal.add(panelCombo);
		panelPrincipal.add(panelCalendario);
		panelPrincipal.add(panelContenedorCalendario);
		panelPrincipal.add(panelBoton);
		
		add(panelPrincipal, BorderLayout.WEST);
		
		
		
	}
	public class Fecha{
		
		SimpleDateFormat Formato=new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
		
		public String getFecha(JDateChooser jd){
			
			if(jd.getDate() != null){
				return Formato.format(jd.getDate());
			}
			else{
				return null;
			}
		}
	}
	
	
	
}


