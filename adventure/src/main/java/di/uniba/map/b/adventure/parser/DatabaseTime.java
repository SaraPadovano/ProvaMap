/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.parser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author acer
 */
public class DatabaseTime {
   public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS time (playId INT AUTO_INCREMENT PRIMARY KEY, playTime INTEGER)";
    
    public static void Time() throws SQLException{
        try{
            Connection conn = DriverManager.getConnection("jdbc:h2:./target/generated-sources/db");
            Statement stm = conn.createStatement();
            stm.executeUpdate(CREATE_TABLE);
            stm.close();
            stm = conn.createStatement();
            stm.executeUpdate("TRUNCATE TABLE time");
            stm.close();
            stm = conn.createStatement();
            stm.executeUpdate("INSERT INTO time VALUES (1, 20)");
            stm.close();
            conn.close();
            
        }catch (SQLException ex){
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }
    }
    
    public static void readTime() throws SQLException {
        try{
        Connection conn = DriverManager.getConnection("jdbc:h2:./target/generated-sources/db");
        Statement som = conn.createStatement();
            System.out.println("=======================");
            ResultSet rs = som.executeQuery("SELECT playId, playTime FROM time WHERE (SELECT MAX(playId) FROM time)");
            rs.next();
            System.out.println("Il tuo tempo di gioco è stato: "+ rs.getInt(2));
            rs.close();
            som.close();
            conn.close();
        }catch (SQLException ex){
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }
    }
}
