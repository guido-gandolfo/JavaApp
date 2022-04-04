package arduino;

public class Pin {
	private String up,down;
	
	public Pin(String up, String down){
		this.up=up;
		this.down=down;
	}

	public void digitalWriteUp(Api api){
		api.escribir(this.up);}
	public void digitalWriteDown(Api api) {
		api.escribir(this.down);}
	public void analogWriteUp(Api api) {
		api.escribir(this.up);}
	public void analogWriteDown(Api api) {
		api.escribir(this.down);}
	
}
