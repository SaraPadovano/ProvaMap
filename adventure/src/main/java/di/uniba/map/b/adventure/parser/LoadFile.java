/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.parser;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author g.navolio
 */
public class LoadFile {
    private static final String DATA_OBJECT = "oggettidata";
    private static final String DATA_ROOM_DESC = "stanzedescdata";
    private static final String DATA_ROOM_LOOK = "stanzelookdata";
    private static final String DATA_MONSTER = "mostridata";
    
    public static String readObjectFile(int idInput) throws IOException {
        DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("target/generated-sources/" + DATA_OBJECT)));
        int idobj;
        String descobj;
        try {
            while (true) {
                idobj = in.readInt();
                descobj = in.readUTF();
                if(idInput == idobj){
                    return descobj;
                }
            }
        } catch (IOException e) {
        } finally {
          in.close();
        }
        return null;

    }
    
    public static String readRoomDescFile(int idInput) throws IOException {
        DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("target/generated-sources/" + DATA_ROOM_DESC)));
        int idRoom;
        String descRoom;
        try {
            while (true) {
                idRoom = in.readInt();
                descRoom = in.readUTF();
                if (idInput == idRoom) {
                    return descRoom;
                }
            }
        } catch (IOException e) {
        } finally {
          in.close();
        }
        return null;

    }
    
    public static String readRoomLookFile(int idInput) throws IOException {
        DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("target/generated-sources/" + DATA_ROOM_LOOK)));
        int idRoom;
        String lookRoom;
        try {
            while (true) {
                idRoom = in.readInt();
                lookRoom = in.readUTF();
                if (idInput == idRoom) {
                    return lookRoom;
                }
            }
        } catch (IOException e) {
        } finally {
          in.close();
        }
        return null;

    }
    
    public static String readMonsterFile(int idInput) throws IOException {
        DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("target/generated-sources/" + DATA_MONSTER)));
        int idMon;
        String descMon;
        try {
            while (true) {
                idMon = in.readInt();
                descMon = in.readUTF();
                if (idInput == idMon) {
                    return descMon;
                }
            }
        } catch (IOException e) {
        } finally {
          in.close();
        }
        return null;

    }
    
}
