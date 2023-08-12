/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.parser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author acer
 */

public class DatabaseTime {
   public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS time (playId INT PRIMARY KEY, playTime INTEGER)";
 
    public static void Time() throws SQLException{
        try{
            Connection conn = DriverManager.getConnection("jdbc:h2:./db/db");
            Statement stm = conn.createStatement();
            stm.executeUpdate(CREATE_TABLE);
            stm.close();
            stm = conn.createStatement();
            stm.executeUpdate("TRUNCATE TABLE time");
            stm.close();
            PreparedStatement pstm = conn.prepareStatement("INSERT INTO time VALUES (?,?)");
            pstm.setInt(1, 0);
            pstm.setInt(2, 0);
            pstm.executeUpdate();
             pstm.close();
            conn.close();
        }catch (SQLException ex){
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }
    }
    
    public static void writeTime(int gameTime) throws SQLException {
        try{
            Connection conn = DriverManager.getConnection("jdbc:h2:./db/db");
            Statement som = conn.createStatement();
            ResultSet rs = som.executeQuery("SELECT playId, playTime FROM time WHERE (SELECT MAX(playId) FROM time)=playId");
            rs.next();
            int gameId=rs.getInt(1)+1;
            som.close();
            rs.close();
            PreparedStatement pstm = conn.prepareStatement("INSERT INTO time VALUES (?,?)");
            pstm.setInt(1, gameId);
            pstm.setInt(2, gameTime);
            pstm.executeUpdate();
             pstm.close();
            conn.close();
        }catch (SQLException ex){
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
            
        }
    }
    
    public static void readTime() throws SQLException {
        try{
        Connection conn = DriverManager.getConnection("jdbc:h2:./db/db");
        Statement som = conn.createStatement();
            System.out.println("=======================");
            ResultSet rs = som.executeQuery("SELECT playId, playTime FROM time WHERE (SELECT MAX(playId) FROM time)=playId");
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
