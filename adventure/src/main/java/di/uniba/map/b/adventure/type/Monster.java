/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.type;

/**
 *
 * @author 39388
 */
public class Monster {

    private final int id;

    private String descr;

    private String name;

    private boolean isAlive = true;

    private boolean dropSomething = false;

    private final int idDrop;

    public Monster(int id, int idDrop, String name, String descr) {
        
        this.id = id;
        this.name = name;
        this.descr = descr;
        this.idDrop = idDrop;
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return descr;
    }

    public void setDescription(String description) {
        descr = description;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean getDropSomething() {
        return dropSomething;
    }

    public void setDropSomething(boolean dropSomething) {
        this.dropSomething = dropSomething;
    }
    
    public int getId() {
    
        return id;
    
    }
    
    public int getIdDrop() {
    
        return idDrop;
    
    }
    
}

