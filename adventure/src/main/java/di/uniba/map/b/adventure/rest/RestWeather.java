/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author acer
 */
public class RestWeather {
    
    private static final String API_KEY = "e54e9fbb53bb2dbad7f33833d019b0e0";
    
    private static String getWeatherByCity(String city) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://api.openweathermap.org/data/2.5");
        Response resp = target.path("weather")
                .queryParam("appid", API_KEY)
                .queryParam("q", city)
                .request(MediaType.APPLICATION_JSON).get();
         String  risposta = resp.readEntity(String.class);
         return risposta;
   }
    
    private static void getMessaggioMeteo(String weather){
        if(weather.contains("Thunderstorm")){
            System.out.println("La tua avventura inizia in una giornata tempestosa!\n");
        } else if(weather.contains("Drizzle")){
            System.out.println("La tua avventura inizia in una giornata un po' piovosa!\n");
        } else if(weather.contains("Rain")){
            System.out.println("La tua avventura inizia in una giornata piovosa!\n");
        } else if(weather.contains("Snow")){
            System.out.println("La tua avventura inizia in una giornata nevosa!\n");
        } else if(weather.contains("Mist") || weather.contains("Haze") || weather.contains("Fog")){
            System.out.println("La tua avventura inizia in una giornata piena di nebbia!\n");
        } else if(weather.contains("Smoke") || weather.contains("Dust") || weather.contains("Sand") || weather.contains("Ash")){
            System.out.println("La tua avventura inizia in una giornata in cui, un po' per il troppo inquinamento,\n"+
               "un po' per la presenza di polveri naturali nell'aria, il sole non riesce più a splendere come faceva qualche giorno fa!\n");
        } else if(weather.contains("Squall")){
            System.out.println("La tua avventura inizia in una giornta burrascosa!\n");
        } else if(weather.contains("Tornado")){
            System.out.println("La tua avventura inizia in una giornata non proprio soleggiata, vista l'allerta per possibili tornado"+
                    "emanata qualche ora fa!\n");
        } else if(weather.contains("Clear")){
            System.out.println("La tua avventura inizia in una bella giornata soleggiata!\n");
        }else if(weather.contains("Clouds")){
            System.out.println("La tua avventura inizia in una giornata grigia e nuvolosa!\n");
        }
    }
    
    public static void clientWeather(){
        String weather = getWeatherByCity("Bari");
        getMessaggioMeteo(weather);
    }
}
