#include <WiFi.h>
#include <HTTPClient.h>
#include <DHT.h>

const char *ssid = "robotica";
const char *password = "12345678";

#define DHTPIN 26
#define DHTTYPE DHT11
DHT dht(DHTPIN, DHTTYPE);

const char* serverUrl = "http://192.168.43.203:8080/statedata/";

unsigned long previousMillis = 0;
const long interval = 30000; 

void setup() {
  Serial.begin(9600);

  dht.begin();

  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
    Serial.println("Conectando ao WiFi...");
  }
  Serial.println("WiFi conectado!");
}

void loop() {
  unsigned long currentMillis = millis();
  if (currentMillis - previousMillis >= interval) {
    previousMillis = currentMillis;

    //float temperature = dht.readTemperature();
    float temperature = random(20, 35);
    //float humidity = dht.readHumidity();
    float humidity = random(30, 50);

    if (isnan(temperature) || isnan(humidity)) {
      Serial.println("Falha ao ler do sensor DHT!");
      return;
    }

    if (WiFi.status() == WL_CONNECTED) {
      HTTPClient http;

      http.begin(serverUrl);
      http.addHeader("Content-Type", "application/json");

      String jsonData = "{"
                          "\"temperature\": " + String(temperature) + ","
                          "\"humidity\": " + String(humidity) +
                          "}";

      int httpResponseCode = http.POST(jsonData);

      if (httpResponseCode > 0) {
        String response = http.getString();
        Serial.println("Resposta do servidor: " + response);
      } else {
        Serial.println("Erro na requisição POST: " + String(httpResponseCode));
      }

      http.end();
    } else {
      Serial.println("Erro de conexão WiFi");
    }
  }
}
