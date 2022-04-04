package archivo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JOptionPane;

import org.jfree.data.time.RegularTimePeriod;

public class Escribir
{

	
	private int q;

	public void escribir(String nombre,String string, RegularTimePeriod data)
	{

		SimpleDateFormat dateFormat = new SimpleDateFormat("d M HH:mm:ss yyyy", Locale.US);
		String fecha = dateFormat.format(new Date());
		
		try
		{
			//Crear un objeto File se encarga de crear o abrir acceso a un archivo que se especifica en su constructor
			FileWriter archivo = new FileWriter(nombre + ".txt", true);
			BufferedWriter bw = new BufferedWriter(archivo);
			PrintWriter pw = new PrintWriter(bw);
			q=Integer.parseInt(string);
			
			
			pw.format("%s;%d",fecha,q);
			pw.println();
			//pw.print(" "  + q + "\n");
			//archivo.write( "\n " + dataset.toString() + " | ");
			//archivo.write(string);
			//System.out.println(data);
			//System.out.println(q);
	
			//Cerramos la conexion
	
			pw.close();
		}

		//Si existe un problema al escribir cae aqui
		catch(IOException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Error al abrir archivo");

		}
	}
}

