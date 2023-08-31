/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package di.uniba.map.b.adventure;

import di.uniba.map.b.adventure.parser.DatabaseTime;
import di.uniba.map.b.adventure.parser.GameTimer;
import di.uniba.map.b.adventure.parser.Parser;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.rest.RestWeather;
import di.uniba.map.b.adventure.type.CommandType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * ATTENZIONE: l'Engine è molto spartano, in realtà demanda la logica alla
 * classe che implementa GameDescription e si occupa di gestire I/O sul
 * terminale.
 *
 * @author pierpaolo
 */
public class Engine {

    private final GameDescription game;

    private Parser parser;

    public Engine(GameDescription game) {
        this.game = game;
        try {
            this.game.init();
        } catch (Exception ex) {
            System.err.println(ex);
        }
        try {
            Set<String> stopwords = Utils.loadFileListInSet(new File("./resources/stopwords"));
            parser = new Parser(stopwords);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public void execute(JTextField f1, JTextArea a1, JButton b1) throws SQLException {
        GameTimer gameTimer = new GameTimer();
        Thread timerThread = new Thread(gameTimer);
        timerThread.start();
        DatabaseTime.dbTime();
        a1.setText("======================\n"
                + "* ENCHANTED FOREST *\n"
                + "======================\n"
                + "------- INTRODUZIONE -------\n");
        a1.append("\n" + RestWeather.clientWeather());
        a1.append("\n" + "Ti trovi in macchina con la tua famiglia. Sei costretto ad andare all'ennesima vacanza con i tuoi genitori\n"
                + "Sei molto contrario, avresti preferito rimanere nella tua città con i tuoi amici, invece ti ritrovi in un luogo\n"
                + "sperduto dove nessun oggetto tecnologico funziona\n"
                + "Con questi pensieri in mente, ti rendi conto che siete arrivati al luogo della villeggiatura\n"
                + "Intravedi in lontananza la cassettina dove passerete questo mese di vacanza\n"
                + "Arrivato,sistemi la valigia e, spinto dai tuoi genitori, decidi di inoltrarti nel bosco per esplorarlo\n"
                + "Entrato nel bosco vedi la fitta vegetazione e osservi gli scoiattolini che gironzolano tra gli alberi\n"
                + "Ti stai annoiando a morte\n"
                + "All'improvviso senti uno strano rumore provenire dalla tua sinistra, voltandoti noti una strana crepa insita in alcune rocce\n"
                + "Ti ci avvicini e con sorpresa noti che la crepa è sufficientemente grande per poterci passare attraverso\n"
                + "Non ci pensi due volte\n"
                + "Ti ritrovi all'interno di una caverna. Purtroppo è un lungo completamento oscuro quindi non riesci a intravedere niente\n"
                + "Cerchi di fare qualche passo ma come una \"pera cotta\" cadi a terra!Sei svenuto!\n"
                + "Dopo un po' di tempo ti risvegli e sei completamente indolenzito dalla caduta\n"
                + "Preso dal dolore non noti che non ti trovi più all'interno della caverna ma sei stato trasportato all'interno di un bosco\n"
                + "Dopo un po' noti il cambio di paesaggio,non capendo come tu ci sia finito, decidi comunque di dare un'occhiata intorno!\n");
        a1.append("\n\n" + game.getCurrentRoom().getName());
        a1.append("\n" + "================================================");
        a1.append("\n" + game.getCurrentRoom().getDescription().trim());
        a1.append("\n" + "                                                          \n");
        
        KeyListener k;
        k = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
               
            }

            @Override
            public void keyPressed(KeyEvent ke) {
               
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                if(ke.getKeyCode() == KeyEvent.VK_ENTER){
                    b1.doClick();
                }
            }
        };

        f1.addKeyListener(k);


        ActionListener l;
        l = (ActionEvent e) -> {
            a1.append("\n\n" + f1.getText());
            String command = f1.getText();
            f1.setText("");
            ParserOutput p = parser.parse(command, game.getCommands(), game.getCurrentRoom().getObjects(), game.getInventory());
            if (p == null || p.getCommand() == null) {
                a1.append("\n" + "Non capisco quello che mi vuoi dire.");
            } else if (p.getCommand() != null && p.getCommand().getType() == CommandType.END) {
                a1.append("\n" + "Addio!");
                System.exit(0);
            } else {
                game.nextMove(p, a1);
            }
        };
        b1.addActionListener(l);
        
        

    }

}
