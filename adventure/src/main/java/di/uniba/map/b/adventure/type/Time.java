/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.type;

/**
 *
 * @author acer
 */
public class Time {
    private int time;
    
    public Time() {
          time=0;
    }
    
    public Time(int tempo){
        time=tempo;
    }

    public void setTime(int time){
        this.time=time;
    }
    
    public int getTime(){
        return time;
    }
   
}
