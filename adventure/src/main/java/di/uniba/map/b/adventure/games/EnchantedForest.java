/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package di.uniba.map.b.adventure.games;

import di.uniba.map.b.adventure.GameDescription;
import di.uniba.map.b.adventure.parser.GameTimer;
import di.uniba.map.b.adventure.parser.LoadFile;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.parser.WriteFile;
import di.uniba.map.b.adventure.rest.RestClientTime;
import di.uniba.map.b.adventure.type.AdvObject;
import di.uniba.map.b.adventure.type.AdvObjectContainer;
import di.uniba.map.b.adventure.type.Command;
import di.uniba.map.b.adventure.type.CommandType;
import di.uniba.map.b.adventure.type.Monster;
import di.uniba.map.b.adventure.type.Room;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.Iterator;

/**
 * ATTENZIONE: La descrizione del gioco è fatta in modo che qualsiasi gioco
 * debba estendere la classe GameDescription. L'Engine è fatto in modo che possa
 * eseguire qualsiasi gioco che estende GameDescription, in questo modo si
 * possono creare più gioci utilizzando lo stesso Engine.
 *
 * Diverse migliorie possono essere applicate: - la descrizione del gioco
 * potrebbe essere caricate da file o da DBMS in modo da non modificare il
 * codice sorgente - l'utilizzo di file e DBMS non è semplice poiché all'interno
 * del file o del DBMS dovrebbe anche essere codificata la logica del gioco
 * (nextMove) oltre alla descrizione di stanze, oggetti, ecc...
 *
 * @author pierpaolo
 */
public class EnchantedForest extends GameDescription {

    private boolean looked = false;

    @Override
    public void init() throws Exception {
//Commands
        Command nord = new Command(CommandType.NORD, "nord");
        nord.setAlias(new String[]{"n", "N", "Nord", "NORD"});
        getCommands().add(nord);
        Command iventory = new Command(CommandType.INVENTORY, "inventario");
        iventory.setAlias(new String[]{"inv"});
        getCommands().add(iventory);
        Command sud = new Command(CommandType.SOUTH, "sud");
        sud.setAlias(new String[]{"s", "S", "Sud", "SUD"});
        getCommands().add(sud);
        Command est = new Command(CommandType.EAST, "est");
        est.setAlias(new String[]{"e", "E", "Est", "EST"});
        getCommands().add(est);
        Command ovest = new Command(CommandType.WEST, "ovest");
        ovest.setAlias(new String[]{"o", "O", "Ovest", "OVEST"});
        getCommands().add(ovest);
        Command end = new Command(CommandType.END, "end");
        end.setAlias(new String[]{"end", "fine", "esci", "muori", "ammazzati", "ucciditi", "suicidati", "exit"});
        getCommands().add(end);
        Command look = new Command(CommandType.LOOK_AT, "osserva");
        look.setAlias(new String[]{"guarda", "vedi", "trova", "cerca", "descrivi"});
        getCommands().add(look);
        Command pickup = new Command(CommandType.PICK_UP, "raccogli");
        pickup.setAlias(new String[]{"prendi"});
        getCommands().add(pickup);
        Command open = new Command(CommandType.OPEN, "apri");
        open.setAlias(new String[]{});
        getCommands().add(open);
        Command attack = new Command(CommandType.ATTACK, "attacca");
        attack.setAlias(new String[]{"colpisci", "uccidi", "ferisci", "distruggi"});
        getCommands().add(attack);
        Command give = new Command(CommandType.GIVE, "dai");
        give.setAlias(new String[]{"dare", "porgi", "porre", "do", "lancia"});
        getCommands().add(give);
        Command monster = new Command(CommandType.MONSTER, "mostro");
        monster.setAlias(new String[]{"creatura"});
        getCommands().add(monster);
        Command use = new Command(CommandType.USE, "usa");
        use.setAlias(new String[]{"utilizza", "lancia", "butta", "getta"});
        getCommands().add(use);
        Command time = new Command(CommandType.TIME, "tempo");
        time.setAlias(new String[]{"time"});
        getCommands().add(time);

        //obejcts
        WriteFile.writeObjFile();
        
        AdvObject sword = new AdvObject(1, "spada", LoadFile.readObjectFile(1));
        getInventory().add(sword);
        sword.setAlias(new String[]{"spadone", "lama", "arma"});
        AdvObjectContainer chest = new AdvObjectContainer(8, "forziere", LoadFile.readObjectFile(8));
        chest.setAlias(new String[]{"scrigno", "tesoro"});
        chest.setOpenable(true);
        chest.setPickupable(false);
        chest.setOpen(false);
        AdvObject poison = new AdvObject(2, "veleno", LoadFile.readObjectFile(2));
        poison.setAlias(new String[]{"fiala di veleno", "veleno mortale"});
        chest.add(poison);
        poison.setPickupable(true);
        AdvObject acorn = new AdvObject(3, "ghianda", LoadFile.readObjectFile(3));
        acorn.setAlias(new String[]{"ghiandosa"});
        chest.add(acorn);
        acorn.setPickupable(true);
        AdvObject fire = new AdvObject(4, "fiala del fuoco", LoadFile.readObjectFile(4));
        fire.setAlias(new String[]{"fuoco", "fiamma", "fiala di fiamme"});
        AdvObject thunder = new AdvObject(5, "fiala del fulmine", LoadFile.readObjectFile(5));
        thunder.setAlias(new String[]{"fulmine", "fulmini", "fiala di fulmini", "saette"});
        AdvObject coin = new AdvObject(6, "moneta", LoadFile.readObjectFile(6));
        coin.setOpenable(false);
        coin.setPickupable(false);
        AdvObject radio = new AdvObject(7, "radio", LoadFile.readObjectFile(7));
        radio.setAlias(new String[]{"radiolina"});
//Monster
        WriteFile.writeMonsterFile();

        Monster topoMannaro = new Monster(1, fire, "Topo Mannaro", LoadFile.readMonsterFile(1));
        Monster cumuloStrisciante = new Monster(2, null, "Cumulo Strisciante", LoadFile.readMonsterFile(2));
        Monster treant = new Monster(3, coin, "Treant", LoadFile.readMonsterFile(3));
        Monster driade = new Monster(4, null, "Driade", LoadFile.readMonsterFile(4));
        Monster fataleAcqua = new Monster(5, null, "Fatale dell' Acqua", LoadFile.readMonsterFile(5));
        Monster diavoloMarino = new Monster(6, thunder, "Diavolo Marino", LoadFile.readMonsterFile(6));
        Monster merrow = new Monster(7, radio, "Merrow", LoadFile.readMonsterFile(7));
        Monster mindFlayer = new Monster(8, null, "Mind Flayer", LoadFile.readMonsterFile(8));
//Rooms
        WriteFile.writeRoomDescFile();
        WriteFile.writeRoomLookFile();

        Room entrataBosco = new Room(1, null, "Entrata bosco.", LoadFile.readRoomDescFile(1));
        entrataBosco.getObjects().add(sword);
        Room tanaTopoMannaro = new Room(2, topoMannaro, "Tana dei topi mannari.", LoadFile.readRoomDescFile(2));
        tanaTopoMannaro.setLook(LoadFile.readRoomLookFile(2));
        tanaTopoMannaro.getObjects().add(fire);
        Room biforcazioneBosco = new Room(3, null, "Biforcazione.", LoadFile.readRoomDescFile(3));
        biforcazioneBosco.setLook(LoadFile.readRoomLookFile(3));
        Room fittaVegetazione = new Room(4, cumuloStrisciante, "Fitta vegetazione.", LoadFile.readRoomDescFile(4));
        fittaVegetazione.setLook(LoadFile.readRoomLookFile(4));
        Room cuoreBosco = new Room(5, treant, "Cuore del bosco.", LoadFile.readRoomDescFile(5));
        cuoreBosco.setLook(LoadFile.readRoomLookFile(5));
        cuoreBosco.getObjects().add(coin);
        Room alfheim = new Room(6, driade, "Alfheim.", LoadFile.readRoomDescFile(6));
        alfheim.setLook(LoadFile.readRoomLookFile(6));
        Room lago = new Room(7, fataleAcqua, "Lago.", LoadFile.readRoomDescFile(7));
        Room biforcazioneLago = new Room(8, null, "Biforcazione.", LoadFile.readRoomDescFile(8));
        biforcazioneLago.setLook(LoadFile.readRoomLookFile(8));
        Room abissi = new Room(9, diavoloMarino, "Abissi.", LoadFile.readRoomDescFile(9));
        abissi.getObjects().add(thunder);
        Room calipso = new Room(10, merrow, "Calipso.", LoadFile.readRoomDescFile(10));
        calipso.setLook(LoadFile.readRoomLookFile(10));
        Room tesoro = new Room(11, null, "Stanza del tesoro.", LoadFile.readRoomDescFile(11));
        tesoro.getObjects().add(chest);
        Room underDark = new Room(12, mindFlayer, "Under Dark.", LoadFile.readRoomDescFile(12));
        underDark.setLook(LoadFile.readRoomLookFile(12));
//map
        entrataBosco.setNorth(tanaTopoMannaro);
        tanaTopoMannaro.setNorth(biforcazioneBosco);
        tanaTopoMannaro.setSouth(entrataBosco);
        biforcazioneBosco.setEast(fittaVegetazione);
        biforcazioneBosco.setSouth(tanaTopoMannaro);
        fittaVegetazione.setEast(cuoreBosco);
        fittaVegetazione.setWest(biforcazioneBosco);
        biforcazioneBosco.setWest(alfheim);
        alfheim.setWest(underDark);
        underDark.setEast(alfheim);
        alfheim.setEast(biforcazioneBosco);
        biforcazioneBosco.setNorth(lago);
        cuoreBosco.setWest(fittaVegetazione);
        lago.setNorth(biforcazioneLago);
        lago.setSouth(biforcazioneBosco);
        biforcazioneLago.setEast(abissi);
        abissi.setWest(biforcazioneLago);
        biforcazioneLago.setSouth(lago);
        biforcazioneLago.setWest(calipso);
        calipso.setEast(biforcazioneLago);
        calipso.setWest(tesoro);
        tesoro.setEast(calipso);
        underDark.setVisible(true);
        biforcazioneBosco.setVisible(false);
        biforcazioneLago.setVisible(false);
        tesoro.setVisible(false);
        getRooms().add(entrataBosco);
        getRooms().add(tanaTopoMannaro);
        getRooms().add(biforcazioneBosco);
        getRooms().add(fittaVegetazione);
        getRooms().add(cuoreBosco);
        getRooms().add(alfheim);
        getRooms().add(underDark);
        getRooms().add(lago);
        getRooms().add(biforcazioneLago);
        getRooms().add(abissi);
        getRooms().add(calipso);
        getRooms().add(tesoro);

//set starting room
        setCurrentRoom(entrataBosco);
    }

    @Override
    public void nextMove(ParserOutput p, PrintStream out) {
        if (p.getCommand() == null) {
            out.println("Non ho capito cosa devo fare! Prova con un altro comando.");
        } else {
//move
            boolean noroom = false;
            boolean move = false;
            boolean visible = true;
            if (p.getCommand().getType() == CommandType.NORD) {
                if (getCurrentRoom().getNorth() != null) {
                    if (getCurrentRoom().getNorth().isVisible() == true) {
                        setCurrentRoom(getCurrentRoom().getNorth());
                        move = true;
                    } else {
                        visible = false;
                    }
                } else {
                    noroom = true;
                }
            } else if (p.getCommand().getType() == CommandType.SOUTH) {
                if (getCurrentRoom().getSouth() != null) {
                    if (getCurrentRoom().getSouth().isVisible() == true) {
                        setCurrentRoom(getCurrentRoom().getSouth());
                        move = true;
                    } else {
                        visible = false;
                    }
                } else {
                    noroom = true;
                }
            } else if (p.getCommand().getType() == CommandType.EAST) {
                if (getCurrentRoom().getEast() != null) {
                    if (getCurrentRoom().getEast().isVisible() == true) {
                        setCurrentRoom(getCurrentRoom().getEast());
                        move = true;
                    } else {
                        visible = false;
                    }
                } else {
                    noroom = true;
                }
            } else if (p.getCommand().getType() == CommandType.WEST) {
                if (getCurrentRoom().getWest() != null) {
                    if (getCurrentRoom().getWest().isVisible() == true) {
                        setCurrentRoom(getCurrentRoom().getWest());
                        move = true;
                    } else {
                        visible = false;
                    }
                } else {
                    noroom = true;
                }
            } else if (p.getCommand().getType() == CommandType.INVENTORY) {
                out.println("Nel tuo inventario ci sono:");
                for (AdvObject o : getInventory()) {
                    out.println(o.getName() + ": " + o.getDescription());
                }
            } else if (p.getCommand().getType() == CommandType.LOOK_AT) {
                if (getCurrentRoom().getLook() != null) {
                    out.println(getCurrentRoom().getLook());
                    if (getCurrentRoom().getName().equals("Cuore del bosco.")) {
                        if (looked != true) {
                            getInventory().add(getCurrentRoom().getMonster().getDropObject());
                            looked = true;
                        }
                    }
                } else {
                    out.println("Non c'è niente di interessante qui.");
                }
            }else if(p.getCommand().getType() == CommandType.TIME){
                     out.println( "Il tuo tempo di gioco corrente è : " +  GameTimer.getTotalGameTime()/1000);
            }else if (p.getCommand().getType() == CommandType.MONSTER) {
                if (getCurrentRoom().getMonster() != null) {
                    out.println(getCurrentRoom().getMonster().getDescription());
                }
            } else if (p.getCommand().getType() == CommandType.PICK_UP) {
                if (p.getObject() != null) {
                    if (p.getObject().isPickupable()) {
                        getInventory().add(p.getObject());
                        getCurrentRoom().getObjects().remove(p.getObject());
                        out.println("Hai raccolto: " + p.getObject().getDescription());
                    } else {
                        out.println("Non puoi raccogliere questo oggetto.");
                    }
                } else {
                    out.println("Non c'è niente da raccogliere qui.");
                }
            } else if (p.getCommand().getType() == CommandType.ATTACK) {
                try {
                    if (p.getInvObject().getName().equals("spada")) {
                        if (getCurrentRoom().getMonster() != null && getCurrentRoom().getMonster().getIsAlive() == true) {
                            if (getCurrentRoom().getMonster().getId() == 1 || getCurrentRoom().getMonster().getId() == 6) {
                                out.println("Congratulazioni hai sconfitto il mostro!!! Non sei così incapace come pensavo!");
                                getCurrentRoom().getMonster().setAlive(false);
                                getInventory().add(getCurrentRoom().getMonster().getDropObject());
                                out.println("Hai conquistato un nuovo oggetto che ti potrà aiutare a sconfiggere i futuri mostri!");
                                if (getCurrentRoom().getMonster().getId() == 1) {
                                    getCurrentRoom().getNorth().setVisible(true);
                                }
                            } else {
                                out.println("La spada non è efficace in questo caso. Riprova!");
                            }
                        } else {
                            out.println("Non c'è niente da attaccare in questo posto... a parte te stesso!");
                        }
                    } else {
                        out.println("Non puoi attaccare con quest'oggetto!\n\n");
                    }
                } catch (Exception NullPointerException) {
                    if (p.getInvObject() == null) {
                        out.println("Non puoi attaccare con quest'oggetto!\n\n");
                    }
                }
            } else if (p.getCommand().getType() == CommandType.USE) {
                try {
                    if (p.getInvObject().getName().equals("fiala del fuoco") || p.getInvObject().getName().equals("fiala del fulmine") || p.getInvObject().getName().equals("veleno") || p.getInvObject().getName().equals("radio")) {
                        if (getCurrentRoom().getMonster() != null && getCurrentRoom().getMonster().getIsAlive() == true) {
                            Iterator<AdvObject> it = getInventory().iterator();
                            boolean findPoison = false;
                            boolean findThunder = false;
                            boolean findRadio = false;
                            while (it.hasNext()) {
                                AdvObject next = it.next();
                                if (next.getName().equals("veleno")) {
                                    out.println("veleno");
                                    findPoison = true;
                                }
                                if (next.getName().equals("fiala del fulmine")) {
                                    findThunder = true;
                                }
                                if (next.getName().equals("radio")) {
                                    findRadio = true;
                                }
                            }
                            if (getCurrentRoom().getMonster().getId() == 3 && findPoison == true) {
                                out.println("Hai deciso di uccidere il Treant: ricorda, le tue scelte avranno delle gravi consequenze.");
                                getCurrentRoom().getMonster().setAlive(false);
                                setCurrentRoom(getCurrentRoom().getWest().getWest().getWest());
                                getCurrentRoom().getMonster().setAlive(false);
                                getCurrentRoom().getWest().setVisible(true);
                                out.println("Sotto consiglio del Treant, ti dirigi verso la Driade per assistere alla sua morte. Una volta sul luogo, noti la Driade decomporsi in tante piccole foglie dorate e scomparire trasportata dal vento.\n\n");
                                Iterator<AdvObject> re = getInventory().iterator();

                                while (re.hasNext()) {
                                    AdvObject next = re.next();
                                    if (next.getName().equals("veleno")) {
                                        getInventory().remove(next);
                                    }

                                }
                            }
                            if (getCurrentRoom().getMonster().getId() == 7 && findThunder == true) {
                                out.println("Congratulazioni, hai ucciso il Merrow!");
                                getCurrentRoom().getMonster().setAlive(false);
                                getInventory().add(getCurrentRoom().getMonster().getDropObject());
                                out.println("Hai ottenuto un altro oggetto all'interno dell'inventario!");
                                getCurrentRoom().getWest().setVisible(true);
                                Iterator<AdvObject> re = getInventory().iterator();

                                while (re.hasNext()) {
                                    AdvObject next = re.next();
                                    if (next.getName().equals("fiala del fulmine")) {
                                        getInventory().remove(next);
                                    }

                                }
                            }
                            if (getCurrentRoom().getMonster().getId() == 2) {
                                out.println("Congratulazioni, hai ucciso il cumulo strisciante!");
                                getCurrentRoom().getMonster().setAlive(false);
                                Iterator<AdvObject> re = getInventory().iterator();

                                while (re.hasNext()) {
                                    AdvObject next = re.next();
                                    if (next.getName().equals("fiala del fuoco")) {
                                        getInventory().remove(next);
                                    }

                                }
                            }
                            if (getCurrentRoom().getMonster().getId() == 8 && findRadio == true) {
                                if(getCurrentRoom().getEast().getMonster().getIsAlive() == true) 
                                    endGood(out);
                                else
                                    endBad(out);
                            }
                        }
                    } else {
                        out.println("Non puoi usare questo oggetto!");
                    }
                } catch (Exception NullPointerException) {
                    if (p.getInvObject() == null) {
                        out.println("Non puoi attaccare con quest'oggetto!\n\n");
                    }
                }
            } else if (p.getCommand().getType() == CommandType.GIVE) {
                try {
                    if (p.getInvObject().getName().equals("moneta") || p.getInvObject().getName().equals("ghianda")) {
                        if (getCurrentRoom().getMonster() != null && getCurrentRoom().getMonster().getIsAlive() == true) {

                            Iterator<AdvObject> it = getInventory().iterator();
                            boolean findCoin = false;
                            boolean findAcorn = false;
                            while (it.hasNext()) {
                                AdvObject next = it.next();
                                if (next.getName().equals("ghianda")) {
                                    findAcorn = true;
                                }
                                if (next.getName().equals("moneta")) {
                                    findCoin = true;
                                }
                            }
                            if (getCurrentRoom().getMonster().getId() == 3 && findAcorn == true) {
                                out.println("Hai scelto di curare il Treant, le tue gesta saranno riconosciute in seguito.");
                                setCurrentRoom(getCurrentRoom().getWest().getWest().getWest());
                                getCurrentRoom().getMonster().setAlive(false);
                                out.println("Sotto consiglio del Treant, ti dirigi verso la Driade. Appena arrivato, ti si para di fronte una bellissima donna dall'aspetto fatato, che ti ringrazia per averla salvata.\n\n");
                                getCurrentRoom().getWest().setVisible(true);
                                Iterator<AdvObject> re = getInventory().iterator();

                                while (re.hasNext()) {
                                    AdvObject next = re.next();
                                    if (next.getName().equals("ghianda")) {
                                        getInventory().remove(next);
                                    }

                                }
                            }
                            if (getCurrentRoom().getMonster().getId() == 5 && findCoin == true) {
                                getCurrentRoom().getMonster().setAlive(false);
                                out.println("Davanti a te si apre un portale per gli abissi del lago.");
                                getCurrentRoom().getNorth().setVisible(true);
                                Iterator<AdvObject> re = getInventory().iterator();

                                while (re.hasNext()) {
                                    AdvObject next = re.next();
                                    if (next.getName().equals("moneta")) {
                                        getInventory().remove(next);
                                    }

                                }
                            }
                        }
                    }
                } catch (Exception NullPointerException) {
                    if (p.getInvObject() == null) {
                        out.println("Non puoi attaccare con quest'oggetto!\n\n");
                    }
                }
            } else if (p.getCommand().getType() == CommandType.OPEN) {
                /*ATTENZIONE: quando un oggetto contenitore viene aperto, tutti gli oggetti contenuti
                 * vengongo inseriti nella stanza o nell'inventario a seconda di dove si trova l'oggetto contenitore.
                 * Potrebbe non esssere la soluzione ottimale.
                 */
                if (p.getObject() == null && p.getInvObject() == null) {
                    out.println("Non c'è niente da aprire qui.");
                } else {
                    if (p.getObject() != null) {
                        if (p.getObject().isOpenable() && p.getObject().isOpen() == false) {
                            if (p.getObject() instanceof AdvObjectContainer) {
                                out.println("Hai aperto: " + p.getObject().getName());
                                AdvObjectContainer c = (AdvObjectContainer) p.getObject();
                                if (!c.getList().isEmpty()) {
                                    out.print(c.getName() + " contiene:");
                                    Iterator<AdvObject> it = c.getList().iterator();
                                    while (it.hasNext()) {
                                        AdvObject next = it.next();
                                        getCurrentRoom().getObjects().add(next);
                                        out.print(" " + next.getName());
                                        it.remove();
                                    }
                                    out.println();
                                }
                                p.getObject().setOpen(true);
                            } else {
                                out.println("Hai aperto: " + p.getObject().getName());
                                p.getObject().setOpen(true);
                            }
                        } else {
                            out.println("Non puoi aprire questo oggetto.");
                        }
                    }
                    if (p.getInvObject() != null) {
                        if (p.getInvObject().isOpenable() && p.getInvObject().isOpen() == false) {
                            if (p.getInvObject() instanceof AdvObjectContainer) {
                                AdvObjectContainer c = (AdvObjectContainer) p.getInvObject();
                                if (!c.getList().isEmpty()) {
                                    out.print(c.getName() + " contiene:");
                                    Iterator<AdvObject> it = c.getList().iterator();
                                    while (it.hasNext()) {
                                        AdvObject next = it.next();
                                        getInventory().add(next);
                                        out.print(" " + next.getName());
                                        it.remove();
                                    }
                                    out.println();
                                }
                                p.getInvObject().setOpen(true);
                            } else {
                                p.getInvObject().setOpen(true);
                            }
                            out.println("Hai aperto nel tuo inventario: " + p.getInvObject().getName());
                        } else {
                            out.println("Non puoi aprire questo oggetto.");
                        }
                    }
                }
            }
            if (noroom) {
                out.println("Da quella parte non si può andare c'è un muro!\nNon hai ancora acquisito i poteri per oltrepassare i muri...");
            } else if (visible == false) {
                out.println("Non hai ancora le giuste capacità per accedere a questa parte del mondo...");
            } else if (move) {
                out.println(getCurrentRoom().getName());
                out.println("================================================");
                out.println(getCurrentRoom().getDescription());
            }
        }
    }

    private void endGood(PrintStream out) throws SQLException {
        out.println("Congratulazioni, sei riuscito ad uccidere il Mind Flayer.\nAll'improvviso ti senti stordito e perdi i sensi. Quando ti risvegli, ti ritrovi nella grotta in cui eri entrato poche ore fa. Il sole è ormai calato e intorno a te si sta facendo sempre più buio.\n"
                + "Esci dalla grotta e senti qualcuno chiamare il tuo nome in lontananza. Ti senti ancora stordito dal sogno che hai fatto, però senti qualcosa nella tasca dei tuoi jeans. Controlli e tiri fuori un'adorabile radiolina. Forse non è stato proprio un sogno...\nCon questi pensieri,"
                + " ritorni a casa per goderti la tua vacanza.\nStasera, tua madre ha deciso di preparare un bel piatto a base di polpo, ma tu non hai tanta fame.");
        GameTimer.stopTimer(); 
        int elapsedGameTime =(int) GameTimer.getTotalGameTime();
        RestClientTime.clientTime(elapsedGameTime/1000);
        System.exit(0);
    }

    private void endBad(PrintStream out) throws SQLException {
        out.println("Hai deciso di attaccare il Mind Flayer, ma, nonostante l'oggetto potente che hai utilizzato, sei riuscito soltanto a stordirlo. Continui ad attaccarlo, ma sembra tutto vano.\nAll'improvviso il Mind Flayer decide di contrattaccare e tu sei troppo debole per resistere.\n"
                + "Il Mind Flayer prima di darti il colpo di grazia ti dice:\"Posso essere sconfitto solo da qualcuno che ha compiuto buone azioni. Gli attacchi vili non hanno alcun effetto su una creatura altrettanto vile quanto me. Avresti potuto dare quella ghianda...\""
                + "Dopo aver detto ciò, il Mind Flayer ti fa perdere i sensi con uno dei suoi attacchi psichici. Quando ti risvegli, ti ritrovi in una cella fredda e oscura. Sei incatenato e non hai alcuna possibilità di scappare.\nInizi a disperarti, quando ad una certa senti dei passi che si avvicinano sempre più."
                + "Riesci, nell'oscurità, a intravedere la figura del Mind Flayer che sogghignando ti dice:\"Volevo ucciderti, però ho pensato di essere magnanimo e renderti mio schiavo per l'eternità. In fondo chi non vorrebbe essere mio schiavo! D'ora in avanti, vivrai in questa cella e sarai costretto ad eseguire tutti i miei ordini."
                + "Sarà inutile per te scappare o chiedere pietà, perchè avendo compiuto quell'azione tanto crudele, ti sei escluso ogni possibilità di salvezza. Resterai qui con me per l'eternità.\"\nMagari in un'altra vita ci penserai due volte a compiere determinate azioni...");
        GameTimer.stopTimer(); 
        int elapsedGameTime =(int) GameTimer.getTotalGameTime();
       RestClientTime.clientTime(elapsedGameTime/1000);
        System.exit(0);
    }
}
