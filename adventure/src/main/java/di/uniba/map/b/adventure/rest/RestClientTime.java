/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.rest;

import com.google.gson.Gson;
import di.uniba.map.b.adventure.parser.GameTimer;
import di.uniba.map.b.adventure.type.Time;
import java.sql.SQLException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author acer
 */
public class RestClientTime {
    public static void clientTime(int elapsedGameTime) throws SQLException{
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:4321");

        Gson gson = new Gson();
        Time tempo = new Time(elapsedGameTime);
        target.path("time/add").queryParam("gameP", elapsedGameTime).request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(gson.toJson(tempo), MediaType.APPLICATION_JSON));
        
        Response respGet = target.path("time").request(MediaType.APPLICATION_JSON).get();
        System.out.println("Il tuo tempo di gioco è stato: " + respGet.readEntity(String.class));
    }
}
