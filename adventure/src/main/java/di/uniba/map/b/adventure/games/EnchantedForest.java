/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package di.uniba.map.b.adventure.games;

import di.uniba.map.b.adventure.GameDescription;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.AdvObject;
import di.uniba.map.b.adventure.type.AdvObjectContainer;
import di.uniba.map.b.adventure.type.Command;
import di.uniba.map.b.adventure.type.CommandType;
import di.uniba.map.b.adventure.type.Room;
import java.io.PrintStream;
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
        Command push = new Command(CommandType.PUSH, "premi");
        push.setAlias(new String[]{"spingi", "attiva"});
        getCommands().add(push);
        Command attack = new Command(CommandType.ATTACK, "attacca");
        attack.setAlias(new String[]{"colpisci", "uccidi", "ferisci", "distruggi"});
        getCommands().add(attack);
        Command give = new Command(CommandType.GIVE, "dare");
        give.setAlias(new String[]{"dai", "usa", "utilizza", "do", "lanciare", "lancia", "porgi", "porre"});
//Rooms
        Room entrataBosco = new Room(0, "Entrata bosco.", "Ti trovi all'entrata del bosco.\n"
                + "Questo bosco era pieno di alberi e di altissime piante con delle bellissime e grandi foglie e fiori che emanavano un profumo così dolce"
                + " e intenso e in sottofondo il rumore dell'acqua che scorreva.\nDavanti a te c'è un sentiero che si inoltra al suo interno e ti senti osservato.\n\n");
        entrataBosco.setLook("All'improvviso, uno strano personaggio appare tra i cespugli. Ha una lunga tunica grigia, un cappello a punta e un sorriso misterioso.\n"
                + "Si avvicina con passo sicuro e ti porge una spada, splendente come l'argento alla luce della luna.\n"
                + "\"Mio valoroso avventuriero, so che hai bisogno di una lama potente per affrontare le sfide che ti attendono per poter tornare a casa\", dice l'aiutante. "
                + "\"Prendi questa spada, forgiata dall'acciaio di una stella caduta, e diventerai invincibile contro le forze oscure che minacciano il bosco.\""
                + "\nL'aiutante ti porge la spada e scompare tra i cespugli. In cuor tuo speri di poterlo rivedere un giorno.\n\n");
        Room topoMannaro = new Room(1, "Tana dei topi mannari.", "Hai intrapreso il sentiero, ma improvvisamente comincia a piovere molto forte.\n"
                + "Sei riuscito a stento a scorgere l'entrata di una caverna e ti ci sei fiondato dentro.\n"
                + "Non è un posto molto accogliente, ma almeno è al riparo dalla pioggia.\n"
                + "C'è un letto, un tavolo e una sedia. Sembra che qualcuno ci viva.\n"
                + "Senti dei rumori provenire da un'altra stanza. Sei troppo curioso per non andare a vedere.\n"
                + "Ti avvicini alla porta e la apri lentamente. Dentro c'è un topo mannaro che sta mangiando.\n"
                + "Rimani sul ciglio della porta, forse è meglio osservare cosa stia effettivamente facendo quel maledetto topo mannaro.\n\n");
        topoMannaro.setLook("Osservando noti che il topo mannaro sta mangiando un braccio umano. Non è il tuo, ma è comunque disgustoso.\n"
                + "L'odore di sangue pervade per tutta la stanza."
                + "Il topo mannaro si accorge della tua presenza e ti attacca. Non hai scelta, devi difenderti.\nCosa pensi di fare?\n\n");

//la continueremo quando lui attacca in un altro punto.
        Room biforcazioneBosco = new Room(2, "Biforcazione.", "Sei riuscito ad uscire dalla tana del topo mannaro, l'atmosfera rispetto all'inizio sembra essere completamente cambiata,"
                + " è più cupa e ti da quasi un senso di inospitalità. Persino i fiori sembrano essere ostili.\n"
                + "Dove vuoi andare?\n\n");
        biforcazioneBosco.setLook("Il sentiero si divide in tre. A ovest c'è un sentiero dove però la vegetazione si infittisce, a est c'è un sentiero in cui la foresta sembra"
                + " più oscura e l'atmosfera diventa sempre più opprimente e a sud percepisci il rumore dell'acuqa provenire proprio da li.\n");
        Room cumuloStrisciante = new Room(3, "Fitta vegetazione.", "Hai deciso di intraprendere il sentiero a ovest, ma la vegetazione è così fitta che non riesci a passare.\n"
                + "All'improvviso le radici degli alberi cominciano a muoversi e ti si attorcigliano alle caviglie, tentando di afferrarti."
                + "Di fronte a te appare un cumulo di radici ed erbacce dal pessimo odore. Cerchi di liberarti tagliando le radici attorno le tue caviglie, ma è tutto inutile."
                + "Continuano a rigenerarsi troppo velocemente!\n\n");

// attendiamo comandi
        cumuloStrisciante.setLook("Improvvisamente appare nuovamente la figura incontrata in precedenza, che chissà puoi definirlo come un aiutante.\n"
                + "Si rivolge verso di te dicendo: \"Sono accorso percependo la tua disperazione! Sei proprio un caso perso. "
                + "Oltre la spada non hai altro nell'inventario? Controlla!\"\n"
                + "Così l'aiutante sparisce esattamente com'era comparso lasciandoti anche un pò imbarazzato.\n\n");
        Room treant = new Room(4, "Cuore del bosco.", "Sei riuscito a liberarti dal cumulo strisciante e a proseguire il tuo cammino.\n"
                + "Il sentiero ti ha portato in una radura, dove c'è un albero enorme, così grande che non riesci a vedere la cima.\n"
                + "L'albero ha un volto, è un treant! Ti guarda e sembra voglia dirti qualcosa...\n");
        treant.setLook("Ti avvicini all'albero. Finalmente riesci a comprendere le sue parole: \"Sono la divinità di questo bosco, "
                + "sono qui da migliaia di anni e non ho mai visto un essere umano. "
                + "Non so come tu sia arrivato qui, ma non puoi rimanere. Questo bosco è pericoloso per te, devi andartene!\"\n"
                + "Il treant sembra essere preoccupato per te.\nSe vuoi tornare da dove sei venuto, per quanto mi è possibile, posso darti una mano.\n"
                + "Come hai potuto ben vedere il bosco è circondato da una presenza oscura, la Driade è impazzite a causa della malattia che mi affligge ed ha trasformato questo posto in una valle oscura.\n"
                + "Tempo fa iniziarono delle strane piogge, che hanno iniziato a corrodermi dall'interno, rendendomi vulnerabile.\n"
                + "Giovane avventuriero, il tuo compito sarà quello di ritrovare la ghianda da me perduta, per ristabilire l'equilibrio.\n"
                + "Tale ghianda dovrebbe trovarsi da qualche parte all'interno del bosco.\n Se non dovessi riuscire a trovarla vorrei che che tu ponga fine alle mie sofferenze,"
                + "tramite un apposito veleno... Sono disposto a tutto per ristabilire l'equilibrio del bosco! In ogni caso riusciresti a superare"
                + "la Driade.\n Buona fortuna avventuriero... Rimarrò qui in attesa che tu faccia ritorno...\""
                + "Prima di allontanarti il Treant ti dona una moneta, da qui a breve riuscirai a capire a cosa ti sarà utile...\n\n");
        Room driade = new Room(5, "Driade.", "Hai deciso di proseguire il tuo cammino verso est, ma la foresta è sempre più oscura e l'atmosfera diventa sempre più opprimente.\n"
                + "All'improvviso appare una figura femminile, è una Driade! Ti guarda con occhi ignettati di sangue e prima ancora che tu possa accorgerti della sua presenza"
                + "si avventa su di te, per fortuna hai i riflessi pronti e riesci ad uscirne illeso, ma la furia della Driade non si placa così facilmente...\n\n");
        driade.setLook("Ti rendi conto che alcun attacco scalfisce la Driade, ma non abbastanza da farla desistere dal suo intento.\n"
                + "A volte scappare è la migliore opzione...\n\n");
        Room lago = new Room(6, "Lago.", "Hai deciso di proseguire il tuo cammino verso sud, ma il rumore dell'acqua è sempre più forte.\n"
                + "All'improvviso appare un lago, ma non è un lago qualsiasi, è un lago di sangue! Il lago è circondato da una nebbia rossastra e la sua superficie è completamente immobile.\n"
                + "La nebbia si dirada e appare una mistica creatura, tanto elegante quanto spaventosa...\n"
                + "Non riesci a muoverti per la paura, la sua presenza ti incute terrore, come riflesso inizi ad attaccarlo con ogni tuo mezzo... ma è tutto inutile!\n"
                + "I colpi gli passano attraverso. Si avvicina sempre di più e senti che sussurra sempre una sola parola:\"Moneta!\"\n"
                + "Sarà il caso di dargli questa moneta, che tanto desidera?\n\n");
        lago.setLook("Non c'è nulla da osservare...\n\n");
        Room biforcazioneLago = new Room(7, "Biforcazione", "Sei finalmente nel lago e sei circondato da una fitta vegetazione marina.\n"
                + "Ti sembra tutto così strano anche grazie ai nuovi poteri che ti ha donato il fatale dell'acqua che ti consentono di respirare e muoverti.\n"
                + "Esplorando sei arrivato ad una biforcazione...\n\n");
        biforcazioneLago.setLook("La biforcazione si divide in due. A est la vegetazione marina continua ed essere rigogliosa ma non ci sono più le piccole"
                + "e adorate creaturine del lago che hai potuto ammirare in precedenza. Forse c'è qualcosa che non va, però sembra essere più rassicurante del sentiero ad ovest"
                + "dove persino la vegetazione non si può più definire tale. Il sentiero ad est è forse più sicuro da esplorare?\n\n");
        Room diavoloMarino = new Room(8, "Abissi", "Hai deciso di proseguire per il sentiero ad est.\n Ti ritrovi nelle profondità degli abissi del lago"
                + "dove tutto ha iniziato a perdere colore. Nemmeno le creaturine marine sembra vogliano starci in un luogo così cupo.\n Continui ad esplorare sperando"
                + "spinto dalla curiosità quando ad una certa intravedi una strana sagoma muoversi per i tronchi spogli del fondale del lago. Inizi a seguirlo, perchè i film horror non ti hanno mai insegnato niente"
                + "quando all'improvviso la figura si volta inaspettatamente nella tua direzione. Probabilmente avrà percepito il tuo sguardo su di sè.\n"
                + "Riesci così finalmente a scorgere meglio la sua figura. A primo impatto potrebbe essere scambiato tranquillamente per un serpente marino gigante ma in verità"
                + "sembra più una lucertola umanoide. Ti fissa con dei profondi occhi gialli, unico colore in quell'oscurità. E ti fissa così intensamente che"
                + "riesci a percepire il tuo essere preda. All'improvviso la creatura con uno scatto fulmineo si scaglia contro di te.\n Ti conviene iniziare ad impugnare un'arma per il combattimento.\n\n");
        diavoloMarino.setLook("Non c'è nulla da osservare...\n\n");
        Room merrow = new Room(9, "Calipso", "Proseguendo per il sentiero ad ovest continui ad inoltrarti negli abissi del lago.\n"
                + "Rispetto al sentiero ad est si percepisce fin da subito l'atmosfera cupa e tesa del luogo dove, nonostante i poteri concessi dal fatale dell'acqua"
                + "respirare inizia ad essere veramente difficile.\n All'improvviso in quell'immenso deserto marino noti un cartello con inciso \"Abisso Calipso\".\n"
                + "Il fatto che un abisso abbia un nome ti fa pensare che la creatura che ne risiede sia abbastana potente, ma al momento riesci solo a percepirne l'aura soffocante.\n"
                + "Esplorando e girovagando per Calipso noti in lontananza una strana luce, molto strana vista l'atmosfera del luogo e la lontananza dalla superficie.\n"
                + "Curioso come non mai di quella luce sfavillante ti avvicini al luogo di provenienza e noti un bellissimo castello adornato di perle, oro e tesori preziosi.\n"
                + "Ti addentri nel castello senza pensarci due volte, preso da cotanta meraviglia.\n Appena entrato nel castello ti si prospetta direttamente quella che si potrebbe definire come una stanza del tesoro.\n"
                + "Sei circondato da oro e pietre preziose, armi e una quantità innumerevole di resti scheletrici appartenenti sia ad esseri umani sia ad altre creature mai viste prima.\n"
                + "Continuando a camminare per l'immensa stanza, inizi a sentire dei rumori. Ti giri nella loro direzione e noti una creatura, assomigliante ad una sirena uscita male, che dorme appisolata su un gigantesco trono.\n"
                + "Cerchi di non fare rumore perchè finalmente intuisci che la creatura di fronte a te è il boss degli abissi e magari affrontarlo senza un piano non porterà a niente di buono.\n"
                + "Il tuo tentativo fallisce miseramente poichè, assorto nei pensieri, hai per sbaglio calpestato il cranio scheletrico di un povero malcapitato."
                + "La creatura si sveglia immediatamente e tu ti metti in posizione di difesa pronto a combattere.\n\n");
        merrow.setLook("La creatura è molto potente. Con la sua coda da sirena riesce a muoversi velocemente e ti sferra attacchi inaspettati. Inoltre, il corpo è ricoperto da squame così dure"
                + "che formano un'ottima difesa mentre ti attacca incessantemente con un arpione.\n Sei allo stremo e i tuoi attacchi non sembrano funzionare.\n Possibile che non ti ricordi cos'hai nell'inventario?\n"
                + "Dovresti vedere un medico per mancanza di invettiva.\n\n");
        Room tesoro = new Room(10, "Stanza del tesoro", "Sei riuscito finalmente a sconfiggere quella sirena uscita male e non con poche difficoltà.\n"
                + "Per fortuna le tue fatiche potrebbero essere ripagate visto che dietro al trono della creatura sembra esserci una porta finemente decorata. Potrebbe essere una trappola, ma la curiosità è troppo forte.\n"
                + "Entri così in una stanza un po' spoglia rispetto al resto del castello però al suo interno, esplorando, trovi un forziere. Con un po' di ingegno riesci ad aprirlo e trovi con tus grande sorpresa la ghianda e"
                + "una fiala di veleno. Ti vengono in mente le parole del Treant e capisci che è arrivato il momento di prendere una decisione. Però prima biosgna uscire dal lago.\n"
                + "Sembra che i poteri concessi dal fatale stiano svanendo.\n\n");
        tesoro.setLook("Non c'è nulla da osservare qui...Anche perchè si potrebbe rimanere accecati...\n\n");
        Room underDark = new Room(11, "Under Dark", "Dopo aver superato finalmente la Driade, ti si prospetta davanti un portale immenso e oscuro.\n"
                + "Speri che ti possa ricondurre a casa ma ne dubiti vista l'atmosfera agghiacciante che lo circonda. A dar conferma ai yuoi pensieri compare all'improvviso"
                + "il \"tuo aiutante\" che ti dice:\"Congratulazioni avventuriero, sei risucito ad arrivare al boss finale di questo mondo. Se riuscirai a sconfiggerlo potrai finalmente tornare a casa.\n+"
                + "Ma attenzione! Questo è un nemico molto potente, più di tutti quelli che hai dovuto affrontare finora.\n Spero tu possa farcela. In ogni caso io ti saluto, è stato bello aiutarti nel tuo viaggio in questo mondo\""
                + "Così dicendo l'aiutante scomparve. Adesso, con un impeto di coraggio e disperazione, decidi di attraversare il portale. Ti ritrovi immediatamente in un mondo completamente diverso da quello osservato finora.\n"
                + "Ti ritrovi nell'Under Dark.\n Un posto dove la luce del sole non batte mai e le cui uniche forme di vita sembrano essere delle piccole gocce d'acqua che battono sulla fredda e dura pietra.\n"
                + "Non hai il tempo di esplorare il luogo, non che avresti una gran voglia di farlo, che subito ti approccia con un ghigno di superiorità una creatura oribilante.\n"
                + "La sua testa è quella di un polipo ed indossa i tipici abiti da cattivi nei film che vedi sempre con tuo padre. Insomma si riesce a capire perfettamente il suo ruolo.\n"
                + "Appena ti approccia inizia a beffeggiarti dicendo \"L'unico modo per te di uscire da questo mondo è uccidermi. Peccato che un essere così inferiore non possa farmi niente. Se decidi di non sfidarmi e di accettare il tuo destino in qaulità di creatura inferiore potrei anche pesnare di non ucciderti e di tenerti come mio schiavo\"\n"
                + "La creatura sembra parlare attraverso la telepatia perchè non vedi alcun movimento della sua bocca ricoperta di tentacoli.\n"
                + "Sei preso da una profonda paura perchè riesci a percepire chiaramente la sua potenza rispetto agli altri nemici, proprio come aveva detto l'aiutante, però allo stesso tempo sei pervaso da rabbia per le parole che ti ha rivolto.\n"
                + "Decidi così di accettare la sua sfida. Sei già pronto a combattere quando il Mind Flayer ti dice:\"Ti risparmio la fatica di combattere visto che sono un essere immortale. Ti dico già che la nostra sfida sarà ben diversa da quelle che hai affrontato finora. La nostra sarà una sfida d'intelligenza, infatti se riuscirai a risolvere l'indovinello che ti proporrò, potrò pensare di lasciarti andare, altrimenti ti ucciderò vista la tua arroganza. Parola di Mind Flayer.\"\n"
                + "Sei un po' incerto ma alla fine decidi di accettare. La vostra sfida ha inizio.\n\n");
        underDark.setLook("L'Under Dark è un luogo conosciuto anche come Buio Profondo, chissà perchè.\n Nessuna luce è riuscita e riuscirà a illuminare questo posto, questo perchè stiamo parlando di un mondo segreto che si trova sotto la superficie.\n"
                + "Il suo cielo non è altro che una volta arida di pietra, come le pareti che, gelide e dure, si mostrano indifferenti nei confronti del destino di morte e sofferenza che attendono i malcapitati che finiscono per sbaglio in questo mondo.\n"
                + "La maggior parte delle persone infatti una volta qui, non torneranno mai indietro. Quelli che invece riescono a far ritorno, non saranno più gli stessi ma resteranno profondamente turbati dall'esperienza traumatica vissuta.\n"
                + "Nell'Under Dark regna un silenzio tombale, si sente solo il rieccheggiare di alcune gocce d'acqua che hanno l'audacia di cadere sul freddo e inospitale suolo.\n"
                + "Di cos'altro si nasconda in questo mondo sotterraneo tetro e silenzioso nessuno lo sa, o meglio nessuno è tornato abbastanza sano o vivo per raccontarlo...\n\n");
//map
        kitchen.setEast(livingRoom);
        livingRoom.setNorth(hall);
        livingRoom.setWest(kitchen);
        hall.setSouth(livingRoom);
        hall.setWest(yourRoom);
        hall.setNorth(bathroom);
        bathroom.setSouth(hall);
        yourRoom.setEast(hall);
        getRooms().add(kitchen);
        getRooms().add(livingRoom);
        getRooms().add(hall);
        getRooms().add(bathroom);
        getRooms().add(yourRoom);
//obejcts
        AdvObject battery = new AdvObject(1, "batteria", "Un pacco di batterie, chissà se sono cariche.");
        battery.setAlias(new String[]{"batterie", "pile", "pila"});
        bathroom.getObjects().add(battery);
        AdvObjectContainer wardrobe = new AdvObjectContainer(2, "armadio", "Un semplice armadio.");
        wardrobe.setAlias(new String[]{"guardaroba", "vestiario"});
        wardrobe.setOpenable(true);
        wardrobe.setPickupable(false);
        wardrobe.setOpen(false);
        yourRoom.getObjects().add(wardrobe);
        AdvObject toy = new AdvObject(3, "giocattolo", "Il gioco che ti ha regalato zia Lina.");
        toy.setAlias(new String[]{"gioco", "robot"});
        toy.setPushable(true);
        toy.setPush(false);
        wardrobe.add(toy);
        AdvObject kkey = new AdvObject(4, "chiave", "Usa semplice chiave come tante altre.");
        toy.setAlias(new String[]{"key"});
        toy.setPushable(false);
        toy.setPush(false);
        kitchen.getObjects().add(kkey);
//set starting room
        setCurrentRoom(hall);
    }

    @Override
    public void nextMove(ParserOutput p, PrintStream out) {
        if (p.getCommand() == null) {
            out.println("Non ho capito cosa devo fare! Prova con un altro comando.");
        } else {
//move
            boolean noroom = false;
            boolean move = false;
            if (p.getCommand().getType() == CommandType.NORD) {
                if (getCurrentRoom().getNorth() != null) {
                    setCurrentRoom(getCurrentRoom().getNorth());
                    move = true;
                } else {
                    noroom = true;
                }
            } else if (p.getCommand().getType() == CommandType.SOUTH) {
                if (getCurrentRoom().getSouth() != null) {
                    setCurrentRoom(getCurrentRoom().getSouth());
                    move = true;
                } else {
                    noroom = true;
                }
            } else if (p.getCommand().getType() == CommandType.EAST) {
                if (getCurrentRoom().getEast() != null) {
                    setCurrentRoom(getCurrentRoom().getEast());
                    move = true;
                } else {
                    noroom = true;
                }
            } else if (p.getCommand().getType() == CommandType.WEST) {
                if (getCurrentRoom().getWest() != null) {
                    setCurrentRoom(getCurrentRoom().getWest());
                    move = true;
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
                } else {
                    out.println("Non c'è niente di interessante qui.");
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
            } else if (p.getCommand().getType() == CommandType.PUSH) {
//ricerca oggetti pushabili
                if (p.getObject() != null && p.getObject().isPushable()) {
                    out.println("Hai premuto: " + p.getObject().getName());
                    if (p.getObject().getId() == 3) {
                        end(out);
                    }
                } else if (p.getInvObject() != null && p.getInvObject().isPushable()) {
                    out.println("Hai premuto: " + p.getInvObject().getName());
                    if (p.getInvObject().getId() == 3) {
                        end(out);
                    }
                } else {
                    out.println("Non ci sono oggetti che puoi premere qui.");
                }
            }
            if (noroom) {
                out.println("Da quella parte non si può andare c'è un muro!\nNon hai ancora acquisito i poteri per oltrepassare i muri...");
            } else if (move) {
                out.println(getCurrentRoom().getName());
                out.println("================================================");
                out.println(getCurrentRoom().getDescription());
            }
        }
    }

    private void end(PrintStream out) {
        out.println("Premi il pulsante del giocattolo e in seguito ad una forte esplosione la tua casa prende fuoco...\ntu e tuoi famigliari cercate invano di salvarvi e venite avvolti dalle fiamme...\nè stata una morte CALOROSA...addio!");
        System.exit(0);
    }
}
