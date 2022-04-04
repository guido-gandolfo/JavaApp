package arduino;

public class SensorDigital extends Sensor{
	
	public SensorDigital(int _pin, String _nombre){
		
		super(_pin, _nombre);
		tipo = "digital";
		
	}
	
}
