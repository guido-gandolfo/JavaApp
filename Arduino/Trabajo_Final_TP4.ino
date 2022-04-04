#include <DHT11.h>

String letra;
int codigo;
byte pin;
int valor,i;
DHT11 dht11(8);

void Humedad_Temperatura(){
  int err;
  float temp, hum;
  if((err = dht11.read(hum, temp)) == 0){ // Si devuelve 0 es que ha leido bien
    Serial.print("Temperatura: ");
    Serial.print(temp);
    Serial.print(" Humedad: ");
    Serial.print(hum);
    Serial.println();}
   else{
    Serial.println();
    Serial.print("Error Num :");
    Serial.print(err);
    Serial.println();}
}
void DatosPin(){ 
  if (Serial.available()>0){
    codigo=Serial.read();
    switch (codigo){ //Con Arduino, el Switch no se puede usar un String ni tampoco te deja leer dos datos en diferentes variables
        case 50: pinMode(2,INPUT); digitalWrite(2,HIGH); break; 
        case 51: pinMode(3,INPUT); digitalWrite(3,HIGH); break;
        case 52: pinMode(4,INPUT); digitalWrite(4,HIGH); break;
        case 53: pinMode(5,INPUT); digitalWrite(5,HIGH); break;
        case 54: pinMode(6,INPUT); digitalWrite(6,HIGH); break;
        case 55: pinMode(7,INPUT); digitalWrite(7,HIGH); break;
        case 56: pinMode(8,INPUT); digitalWrite(8,HIGH); break;
        case 57: pinMode(9,INPUT); digitalWrite(9,HIGH); break;
        case 58:pinMode(10,INPUT);digitalWrite(10,HIGH);break;
        case 59:pinMode(11,INPUT);digitalWrite(11,HIGH);break;
        case 60:pinMode(12,INPUT);digitalWrite(12,HIGH);break;
        case 65: pinMode(0,INPUT); analogWrite(0,HIGH); break;
        case 66: pinMode(1,INPUT); analogWrite(1,HIGH); break;
        case 67: pinMode(2,INPUT); analogWrite(2,HIGH); break;
        case 68: pinMode(3,INPUT); analogWrite(3,HIGH); break;
        case 69: pinMode(4,INPUT); analogWrite(4,HIGH); break;
        case 70: pinMode(5,INPUT); analogWrite(5,HIGH); break;
        case 71: pinMode(2,INPUT); digitalWrite(2,LOW); break;
        case 72: pinMode(3,INPUT); digitalWrite(3,LOW); break;
        case 73: pinMode(4,INPUT); digitalWrite(4,LOW); break;
        case 74: pinMode(5,INPUT); digitalWrite(5,LOW); break;
        case 75: pinMode(6,INPUT); digitalWrite(6,LOW); break;
        case 76: pinMode(7,INPUT); digitalWrite(7,LOW); break;
        case 77: pinMode(8,INPUT); digitalWrite(8,LOW); break;
        case 78: pinMode(9,INPUT); digitalWrite(9,LOW); break;
        case 79: pinMode(10,INPUT); digitalWrite(10,LOW); break;
        case 80: pinMode(11,INPUT); digitalWrite(11,LOW); break;
        case 81: pinMode(12,INPUT); digitalWrite(12,LOW); break;
        case 82: for (i=2;i<14;i++){
                    valor=digitalRead(i);
                    Serial.print("D");
                    Serial.print(i);
                    Serial.print(":"+String(valor)+".");
                    }
                  for (i=0;i<6;i++){
                    valor=analogRead(i);
                    Serial.print("A");
                    Serial.print(i);
                    Serial.print(":"+String(valor)+".");}
                  Serial.print("+"); break;
        case 83: pinMode(0,INPUT); analogWrite(0,LOW); break;
        case 84: pinMode(1,INPUT); analogWrite(1,LOW); break;
        case 85: pinMode(2,INPUT); analogWrite(2,LOW); break;
        case 86: pinMode(3,INPUT); analogWrite(3,LOW); break;
        case 87: pinMode(4,INPUT); analogWrite(4,LOW); break;
        case 88: pinMode(5,INPUT); analogWrite(5,LOW); break;
        //case 89: Humedad_Temperatura(); break;
        default:  Serial.println("Comando no valido");  break;}}}
 
void setup() {
  for (i=0;i<14;i++)
    pinMode(i,INPUT);
  Serial.begin(115200);
}

void loop() {
  DatosPin();  
  delay(10);
}

