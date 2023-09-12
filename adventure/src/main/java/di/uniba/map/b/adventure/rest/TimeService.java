/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.rest;

/**
 *
 * @author acer
 */
import com.google.gson.Gson;
import di.uniba.map.b.adventure.parser.DatabaseTime;
import di.uniba.map.b.adventure.type.Time;
import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author acer
 */
@Path("time")
public class TimeService {

    /**
     *
     * @return
     * @throws java.sql.SQLException
     */
    @GET
    @Produces("application/json")
    public Response getTime() throws SQLException {
        int tempo = DatabaseTime.readTime();
        Time time = new Time(tempo);
        Gson gson = new Gson();
        //Object -> JSON
        String jsonString = gson.toJson(time);
        return Response.ok(jsonString, MediaType.APPLICATION_JSON).build();
    }


    /**
     *
     * @param json
     * @param time
     * @return
     * @throws java.sql.SQLException
     */
    @PUT
    @Path("/add")
    @Consumes("application/json")
    public Response add(String json, @QueryParam("gameP") String time) throws SQLException {
        int gameTime=Integer.parseInt(time);
        DatabaseTime.writeTime(gameTime);
        Gson gson = new Gson();
        Time tempo = gson.fromJson(json, Time.class);
        return Response.ok().build();
    }
}