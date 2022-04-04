package archivo;


import java.util.LinkedList;
import java.io.*;

public class Leer {
	
	public LinkedList<String> leer(String c) {
	   
		LinkedList<String> lista = new LinkedList<String>();
		
		FileReader archivo = null;
		try {
			archivo = new FileReader(c + ".txt");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		BufferedReader br = new BufferedReader(archivo);
		String palabra = null;
			
		try {
			while((palabra = br.readLine()) != null){
				
				lista.add(palabra);
					
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    
		return lista;
   
	}
	
}