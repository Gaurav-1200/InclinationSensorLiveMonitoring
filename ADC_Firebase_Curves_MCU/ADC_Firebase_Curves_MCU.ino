#include <ESP8266WiFi.h>
#include <NTPClient.h>
#include <WiFiUdp.h>
#include <FirebaseArduino.h>
#include <ArduinoJson.h>

#define ARDUINOJSON_USE_DOUBLE 1
#define FIREBASE_HOST "fir-fa33d-default-rtdb.firebaseio.com"
#define FIREBASE_AUTH "FW5aHnnOSQAMO7jWhslJU6MOXBioJSQMYjNqcwPf"
#define WIFI_SSID "ONEPLUS"
#define WIFI_PASSWORD "1234668899"

const int analogInPin = A0; 
int sensorValue = 0; 
double voltage,strain, angle;
double a,b,c,d; //y=ax3+bx2+cx+d;

unsigned long epochTime;

WiFiUDP ntpUDP;
NTPClient timeClient(ntpUDP, "pool.ntp.org");

unsigned long getTime() {
  timeClient.update();
  unsigned long now = timeClient.getEpochTime();
  return now;
}

void setup() {
  Serial.begin(115200);

  // connect to wifi.
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("connecting");
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(500);
  }
  Serial.println();
  Serial.print("connected: ");
  Serial.println(WiFi.localIP());
  timeClient.begin();
  
  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
}
void loop() {
  sensorValue = analogRead(analogInPin);
  epochTime = getTime();
  Serial.print("Epoch Time: ");
  Serial.println(epochTime);
  Serial.print("sensor = ");
  Serial.print(sensorValue);
  voltage =(sensorValue*0.0029785);
  a=0.0023;
  b=-0.000000384;
  c=0.00019;
  d=0.0152;
  angle= a*voltage*voltage*voltage+b*voltage*voltage+c*voltage+d;  //in radians
  //num=1.237152*sin(theta); //6*0.263*9.8*0.08*sine(angle)
  //den=86.4 //3.2*pow(10,9)*0.03*0.03*0.03
  strain=14318.88889*sin(angle); // to be calculted in Microstrain
  String adc = String(sensorValue) ;
  Firebase.pushString("/Node/check", adc); 
  StaticJsonBuffer<200> jsonBuffer;
  JsonObject& root = jsonBuffer.createObject();
  root["voltage"] = voltage;
  root["strain"]=strain;
  root["angle"]=angle;
  root["time"]=epochTime;
  root.printTo(Serial);
  
  Firebase.push("data",root);
  delay(5000);
}
