package arduino;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JOptionPane;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;



public class Api{
	private int TIMEOUT = 20, DATA_RATE = 115200;
	final String PUERTO = "COM3";
	private SerialPort serialPort;
	private OutputStream output;
	private Pin[] pinesdigitales = new Pin[13];
	private Pin[] pinesanalogicos = new Pin[6];
	public  Api(){
		new Pin("2","G");
		pinesdigitales[2]= new Pin("2","G");
		Pin tres = new Pin("3","H");
		pinesdigitales[3]=tres;
		Pin cuatro = new Pin("4","I");
		pinesdigitales[4]=cuatro;
		Pin cinco = new Pin("5","J");
		pinesdigitales[5]=cinco;
		Pin seis = new Pin("6","K");
		pinesdigitales[6]=seis;
		Pin siete = new Pin("7","L");
		pinesdigitales[7]=siete;
		Pin ocho = new Pin("8","M");
		pinesdigitales[8]=ocho;
		Pin nueve = new Pin("9","N");
		pinesdigitales[9]=nueve;
		Pin diez = new Pin("10","O");
		pinesdigitales[10]=diez;
		Pin once = new Pin("11","P");
		pinesdigitales[11]=once;
		Pin doce = new Pin("12","Q");
		pinesdigitales[12]=doce;
		Pin acero = new Pin("A","S");
		pinesanalogicos[0]=acero;
		Pin auno = new Pin("B","T");
		pinesanalogicos[1]=auno;
		Pin ados = new Pin("C","U");
		pinesanalogicos[2]=ados;
		Pin atres = new Pin("D","V");
		pinesanalogicos[3]=atres;
		Pin acuatro = new Pin("E","W");
		pinesanalogicos[4]=acuatro;
		Pin acinco = new Pin("F","X");
		pinesanalogicos[5]=acinco;
		
	}
	public Map<String, String> map(String datos) {
		
		Map<String, String> map = new TreeMap<String, String>();
		String[] cadenas;
		
		// D2:1 . D3:0
		cadenas = datos.split("\\.");
		
		for(String s : cadenas){
			
			// D2 : 1
			if (s.compareTo("+") != 0){
				map.put(s.split(":")[0], s.split(":")[1]);
			}
			
		}
		
		return map;

	} 

	@SuppressWarnings("rawtypes")
	public void inicializarConexion() {
		
		CommPortIdentifier puertoID = null;
		Enumeration puertoEnum = CommPortIdentifier.getPortIdentifiers();
		while (puertoEnum.hasMoreElements()) {
			CommPortIdentifier actualPuertoID = (CommPortIdentifier) puertoEnum.nextElement();
			if (PUERTO.equals(actualPuertoID.getName())) {
				puertoID = actualPuertoID;
				break;
			}
		}
		if (puertoID == null) {
			System.out.println("Error: Puerto no valido");
		}
		try {
			serialPort = (SerialPort) puertoID.open(this.getClass().getName(), TIMEOUT);
			serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);
			output = serialPort.getOutputStream();
		} catch (Exception e) {
		}
	}

	public void escribir(String datos) {
		try {
			output.write(datos.getBytes());
			
		} catch (Exception e) {
			System.out.println("Error De Escritura "+e.getMessage());
			System.out.println(e.getStackTrace());
		}
	}
	public String leer() {
		
		String data = "";
		try {
			
			java.io.InputStream isStream = serialPort.getInputStream();
			int availableBytes = isStream.available();
			while (availableBytes == 0) {
				availableBytes = serialPort.getInputStream().available();
			}
			byte[] readBuffer = new byte[availableBytes];
			isStream.read(readBuffer, 0, availableBytes);
			data = new String(readBuffer, 0, availableBytes);
			
		} catch (Exception e) {
			System.out.println("Error De Lectura");
		}
		
		return data;
		
	}
	
	public String leerRapido(){
		
		String datos = "";
		while (!datos.contains("+")){
			
			try {
				
				Thread.sleep(2);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "La excepción es:"+e.getMessage());
			}
			datos += leer();
			
		}
		
		return datos;
		
	}
	
	public void digitalWriteUp(int numerodePin){
		
		  pinesdigitales[numerodePin].digitalWriteUp(this);
	  }
	  public void digitalWriteDown(int numerodePin){
		  pinesdigitales[numerodePin].digitalWriteDown(this);
	  }
	  public void analogWriteUp(int numerodePin){
		  pinesanalogicos[numerodePin].analogWriteUp(this);
	  }
	  public void analogWriteDown(int numerodePin){
		  pinesanalogicos[numerodePin].analogWriteDown(this);
	  }
	  public Map<String, String> readAll(){
		 
		  escribir("R");
		 
		  return map(leerRapido());
		  
	  }

}
