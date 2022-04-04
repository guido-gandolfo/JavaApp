package arduino;

public class SensorAnalogico extends Sensor{
	public SensorAnalogico(int _pin, String _nombre){
		
		super(_pin, _nombre);
		tipo = "analogico";
		
	}
	
}
