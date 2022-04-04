package arduino;

public abstract class Sensor {
	
	public int pin;
	public String nombre;
	public int valor;
	public String tipo;
	
	public Sensor(int _pin, String _nombre){
		
		pin = _pin;
		nombre = _nombre;
		
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
