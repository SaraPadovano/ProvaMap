/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package di.uniba.map.b.adventure;

import di.uniba.map.b.adventure.games.EnchantedForest;
import di.uniba.map.b.adventure.parser.DatabaseTime;
import di.uniba.map.b.adventure.parser.GameTimer;
import di.uniba.map.b.adventure.parser.Parser;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.rest.RestWeather;
import di.uniba.map.b.adventure.rest.TimeService;
import di.uniba.map.b.adventure.type.CommandType;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

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

    public void execute() throws SQLException {
        GameTimer gameTimer = new GameTimer();
        Thread timerThread = new Thread(gameTimer);
        timerThread.start();
        DatabaseTime.Time();
        System.out.println("================================");
        System.out.println("* ENCHANTED FOREST *"); 
        System.out.println("================================");
        System.out.println("\n\n-- INTRODUZIONE --\n");
        RestWeather.clientWeather();
        System.out.println("Ti trovi in macchina con la tua famiglia. Sei costretto ad andare all'ennesima vacanza con i tuoi genitori.\n"
                + "Sei molto contrario, avresti preferito rimanere nella tua città con i tuoi amici, invece ti ritrovi in un luogo\n"
                + "sperduto dove nessun oggetto tecnologico funziona. Con questi pensieri in mente, ti rendi conto che siete arrivati\n"
                + "al luogo della villeggiatura. Intravedi in lontananza la cassettina dove passerete questo mese di vacanza. Arrivato,\n"
                + "sistemi la valigia e, spinto dai tuoi genitori, decidi di inoltrarti nel bosco per esplorarlo. Entrato nel bosco vedi\n"
                + "la fitta vegetazione e osservi gli scoiattolini che gironzolano tra gli alberi. Ti stai annoiando a morte.\n"
                + "All'improvviso senti uno strano rumore provenire dalla tua sinistra, voltandoti noti una strana crepa insita in alcune rocce.\n"
                + "Ti ci avvicini e con sorpresa noti che la crepa è sufficientemente grande per poterci passare attraverso.\n"
                + "Non ci pensi due volte. Ti ritrovi all'interno di una caverna. Purtroppo è un lungo completamento oscuro quindi\n"
                + "non riesce a intravedere niente. Cerchi di fare qualche passo ma come una \"pera cotta\" cadi a terra... Sei svenuto...\n"
                + "Dopo un po' di tempo ti risvegli e sei completamente indolenzito dalla caduta. Preso dal dolore non noti che non ti trovi\n"
                + "più all'interno della caverna ma sei stato trasportato all'interno di un bosco. Dopo un po' noti il cambio di paesaggio,\n"
                + "non capendo come tu ci sia finito, decidi comunque di dare un'occhiata intorno...\n\n");
        System.out.println(game.getCurrentRoom().getName());
        System.out.println("================================================");
        System.out.println(game.getCurrentRoom().getDescription());
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            ParserOutput p = parser.parse(command, game.getCommands(), game.getCurrentRoom().getObjects(), game.getInventory());
            if (p == null || p.getCommand() == null) {
                System.out.println("Non capisco quello che mi vuoi dire.");
            } else if (p.getCommand() != null && p.getCommand().getType() == CommandType.END) {
                System.out.println("Addio!");
                break;
            } else {
                game.nextMove(p, System.out);
                System.out.println();
            }
        }
    }

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(4321).build();
        ResourceConfig config = new ResourceConfig(TimeService.class);
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config);
        try {
            server.start();
            Engine engine = new Engine(new EnchantedForest());
            engine.execute();
            server.shutdown();
        } catch (IOException ex) {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
