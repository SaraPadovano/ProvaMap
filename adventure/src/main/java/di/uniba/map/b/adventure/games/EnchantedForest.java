/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package di.uniba.map.b.adventure.games;

import di.uniba.map.b.adventure.GameDescription;
import di.uniba.map.b.adventure.parser.DataFile;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.AdvObject;
import di.uniba.map.b.adventure.type.AdvObjectContainer;
import di.uniba.map.b.adventure.type.Command;
import di.uniba.map.b.adventure.type.CommandType;
import di.uniba.map.b.adventure.type.Monster;
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
        Command give = new Command(CommandType.GIVE, "dare");
        give.setAlias(new String[]{"porgi", "porre", "do", "lancia"});
        getCommands().add(give);
        Command monster = new Command(CommandType.MONSTER, "mostro");
        monster.setAlias(new String[]{"creatura", "osserva mostro", "osserva creatura"}); //da provare se funzionano gli alias
        getCommands().add(monster);
        Command use = new Command(CommandType.USE, "usa");
        use.setAlias(new String[]{"utilizza", "lancia", "butta", "getta"});
        getCommands().add(use);

        //obejcts
        DataFile objFile = new DataFile();
        objFile.writeObjFile();
        
        AdvObject sword = new AdvObject(1, "spada", "");
        getInventory().add(sword);
        sword.setAlias(new String[]{"spadone", "lama", "arma"});
        AdvObjectContainer chest = new AdvObjectContainer(5, "forziere", "Il forziere divino nascondeva al suo interno segreti e tesori sconosciuti, doni degli dei e legami con il mondo della natura. Si diceva che solo coloro che avessero dimostrato una connessione profonda con il mare e una purezza d'animo avrebbero potuto aprirlo e accedere ai suoi tesori.");
        chest.setAlias(new String[]{"scrigno", "tesoro"});
        chest.setOpenable(true);
        chest.setPickupable(false);
        chest.setOpen(false);
        AdvObject poison = new AdvObject(2, "veleno", "La fiala di veleno per uccidere un'antica creatura era un oggetto oscuro e inquietante, carico di un potere mortale. La sua piccola e delicata forma contenitore nascondeva un liquido nero come l'ebano, denso e viscoso."
                + "Le pareti di vetro erano intarsiate con simboli sinistri, tracciati con precisione e dettaglio.");
        poison.setAlias(new String[]{"fiala di veleno", "veleno mortale"});
        chest.add(poison);
        poison.setPickupable(true);
        AdvObject acorn = new AdvObject(3, "ghianda", "La ghianda antica e mistica appartenente all'antica creatura era un tesoro prezioso e straordinario, che portava con sé una profonda connessione con il potere primordiale della natura. La sua forma era delicata e armoniosa, come se fosse stata scolpita con cura da una mano divina.");
        acorn.setAlias(new String[]{"ghiandosa"});
        chest.add(acorn);
        acorn.setPickupable(true);
        AdvObject fire = new AdvObject(4, "fiala del fuoco", "La fiala antica era molto più di un semplice contenitore. Rappresentava un canale per l'energia primordiale del fuoco, un dono delle divinità o degli antichi maestri che avrebbero saputo manipolarlo con cautela e rispetto."
                + "Era un oggetto riservato a coloro che avevano la conoscenza e il coraggio di maneggiare l'essenza del fuoco, pronti ad utilizzarla per scopi nobili o distruttivi.");
        fire.setAlias(new String[]{"fuoco", "fiamma", "fiala di fiamme"});
        AdvObject thunder = new AdvObject(5, "fiala del fulmine", "La fiala antica non era solo un semplice contenitore, ma un conduttore dell'essenza del fulmine stessa."
                + "Chiunque la possedesse avrebbe potuto canalizzare l'energia elettrica e utilizzarla per scopi creativi o distruttivi. Era un oggetto riservato a coloro che avevano la conoscenza e il coraggio di manipolare l'essenza del fulmine, pronti ad affrontare le sfide e i pericoli che essa comportava.");
        thunder.setAlias(new String[]{"fulmine", "fulmini", "fiala di fulmini", "saette"});
        AdvObject coin = new AdvObject(6, "moneta", "La moneta sorprendentemente luccicante donata dal Treant non sembra una di quelle solite monete viste nel mondo normale."
                + "Essa infatti non ha inciso alcun prezzo di riferimento ma è finemente decorata da entrambi i lati con decorazioni che richiamano paesaggi marini da un lato e boschivi dall'altro.");
        coin.setOpenable(false);
        coin.setPickupable(false);
        AdvObject radio = new AdvObject(7, "radio", "Quest'oggetto presenta le stesse caratteristiche di una radiolina, ma non sembrano esserci stazioni attive nella zona."
                + "Ha una forma piccola, che la rende tascabile. E' forse l'oggetto più adorabile che tu abbia mai visto. La sua utilità è ancora sconosciuta.");
        radio.setAlias(new String[]{"radiolina"});
//Monster

        Monster topoMannaro = new Monster(1, fire, "Topo Mannaro", "I topi mannari sono scaltri licantropi dall'indole avida e subdola. In forma umana sono snelli e nervosi, hanno i capelli radi e gli occhi sfuggenti.\n"
                + "Solitamente usano armi leggere e prediligono le imboscate piuttosto che muoversi in branco. Usa la sua forza principalmente per muoversi furtivamente e per scappare.\nI topi mannari, in forma ibrida o umanoide,"
                + "sono esteticamente caratterizzati dal loro spirito licantropo, ossia hanno fattezze molto simili ai topi. Gli occhi sono di un rosso scarlatto, privi di qualsiasi cenno di ragione. I denti soono molto"
                + "lunghi e affilati, e le mani hanno lunghe dita e unghie aguzze.\nDi certo non è una creatura che ti augureresti di incontrare la notte...\n\n");
        Monster cumuloStrisciante = new Monster(2, null, "Cumulo Strisciante", "Un cumulo strisciante, anche chiamato striciante, avanza a passo lento e pesante attraverso gli acquitrini, le paludi e le foreste più oscure,"
                + "assimilando ogni forma di materia organica che trova lungo il cammino.\nQuesto cumulo animato di vegetazione marcescente è una massa alta una volta e mezzo un umano, sormontata da una \"testa\" priva di volto\n\n");
        Monster treant = new Monster(3, coin, "Treant", "I Treant sono alberi risvegliati che vegliano sulle foreste più antiche. Sebbene preferiscano trascorrere i giorni, i mesi e gli anni in serena contemplazione, "
                + "proteggono con ferocia le loro dimore boschive dalle minaccie esterne.\nI Treant continuano a crescere come qualsiasi albero e possono raggiungere dimensioni smisurate e possono sviluppare poteri magici in grado di"
                + " influenzare animali e vegetali.\n Anche una volta risvegliato, un Treant passa buona parte del suo tempo vivendo come un albero. I Treant, rispetto ai cumuli striscianti, presentano un volto, molto spesso grinzoso"
                + " a causa delle striature del loro tronco.\n\n");
        Monster driade = new Monster(4, null, "Driade", "Le driadi sono spiriti della natura che abitano le foreste più antiche e incontaminate. Sono creature di bellezza e grazia sovrannaturali, che si muovono tra gli alberi"
                + " con la stessa agilità di una folata di vento.\nLe driadi sono creature di natura benevola, ma sono anche molto schive e non amano essere disturbate. Sebbene siano creature pacifiche, le driadi non esitano a difendere"
                + " la loro dimora con ferocia.\nL'origine delle driadi ha una natura drammatica ma allo stesso tempo romantica, infatti esse non sono altro che uno spirito fatato che è stato punito per essersi innamorato di un mortale "
                + "(in quanto reputato un amore proibito).\nUna driade può emergere da un albero e spostarsi nel territorio circostante, ma l'albero resta la dimora e il fulcro che la ancora al mondo.\nFintanto che l'albero rimane illeso e"
                + "in salute, la driade rimane esternamente giovane e seducente. Se l'albero viene danneggiato, anche la driade ne soffre tanto che potrebbe arrivare a perdere il senno.\nPotrebbe essere questo il nostro caso...\n\n");
        Monster fataleAcqua = new Monster(5, null, "Fatale dell' Acqua", "Un fatale dell'acqua é un guardiano elementale vincolato a uno specifico luogo pieno d'acqua, come una pozza o una fontana. Finché è immerso nell'acqua è invisibile,"
                + " ma la sua forma serpentina diventa evidente quando la creatura emerge.\nI fatali dell'acqua malvagi possono attaccare e uccidere con estrema facilità e per puro piacere chi si avvicina alla fonte che proteggono, mentre quelli buoni sono"
                + " più propensi a spaventare semplicemente gli avventurieri.\nSolitamente quando ci si avvicina a una fonte custodita da questa creatura, bisogna dare qualcosa in cambio.\n\n");
        Monster diavoloMarino = new Monster(6, thunder, "Diavolo Marino", "Lungo le coste immerse nella nebbia o nei tratti sconfinati\n"
                + "di oceano, il sinistro squillo di una conchiglia usata come corno di guerra fa pelare il sangue nelle vene di tutti coloro che lo sentono. È il suono del corno da caccia dei sahuagin, una chiamata alle armi che precede"
                + " una razzia e una battaglia. I coloni chiamano i sahuagin \"diavoli marini\", in quanto queste creature non mostrano alcuna compassione:\n"
                + "massacrano le ciurme delle navi e decimano i villaggi costieri indiscriminatamente.\nI diavoli marini che decidono di non partecipare alle razzie sono solitari e decidono di vivere in laghi e fiumi isolati, dove si nutrono di pesci e"
                + " dei malcapitati che si avvicinano alla loro tana.\nFisicamente, questi diavoli marini assomigliano a delle lucertole marine, con la faccia di uno scorfano, i denti aguzzi e taglienti e gli occhi che nelle oscurità marine sembrano"
                + " i fanali gialli di un auto.\nUna vera e propria bellezza marina insomma...\n\n");
        Monster merrow = new Monster(7, radio, "Merrow", "I merrow infestano le acque, dove aggrediscono i pescatori, i marinidi e qualsiasi altra creatura commestibile\n"
                + "De incontrino sul loro cammino. Questi mostri selvaggi ghermiscono e divorano le prede più imprudenti per trascinare i loro cadaveri annegati nelle loro caverne sottomarine e cibarsene in tranquillità.\n"
                + "La loro vicinanza agli abissi li ha resi malvagi e crudeli con il tempo.\nI merrow sono caratterizzati da una lunga coda a sirena molto squamosa che gli consente di muoversi velocemente e di difendersi dagli attacchi.\n\n");
        Monster mindFlayer = new Monster(8, null, "Mind Flayer", "I mind flayer, noti anche come illithid, sono l'anatema delle creature senzienti di innumerevoli mondi. Questi tiranni psionici, schiavisti e viaggiatori dimensionali ordiscono ciaborate trame per piegare intere razze ai loro fini nefasti.\n"
                + "Dalla testa simile a una piovra si protendono quattro tentacoli, che si agitano con impazienza ogni volta che una creatura senziente si avvicina.\n"
                + "Negli eoni passati, gli illithid regnavano su un impero che abbracciava numerosi mondi. Soggiogarono e alterarono intere razze di schiavi umanoidi. Uniti da una coscienza collettiva, gli illithid continuano ancora oggi a tessere trame tanto malvagie quanto estese, concepite dalle loro menti imperscrutabili.\n"
                + "Dopo la caduta del loro impero, i collettivi degli illithid presenti sul Piano Materiale si sono rifugiati nell'Underdark.\nI mind flayer sono dotati di poteri psionici che gli consentono di controllare le menti delle creature inferiori, a causa dei loro poteri preferiscono comunicare telepaticamente.\n"
                + "Un mind flayer solitario è in genere un rinnegato o un reietto. Infatti, la  maggior parte degli illithid fa parte di una colonia di fratelli fedeli ad un cervello antico, un gigantesco essere simile a un cervello immerso in una pozza salmastra al centro della comunità.\nGli illithid si cibano dei cervelli degli umanoidi,"
                + " poichè contengono tutti gli enzimi, gli ormoni e l'energia psichica di cui hanno bisogno.\nUn illithid in piena salute è ricoperto da un velo di muco color malva. I cervelli sono preziosi anche per i loro esperimenti quali la trasformazione in divoracervelli.\n"
                + "Con tutti quei tentacoli chissà cos'è in grado di fare...\n\n");
//Rooms

        Room entrataBosco = new Room(1, null, "Entrata bosco.", "Ti trovi all'entrata del bosco.\n"
                + "Questo bosco era pieno di alberi e di altissime piante con delle bellissime e grandi foglie e fiori che emanavano un profumo così dolce"
                + " e intenso e in sottofondo il rumore dell'acqua che scorreva.\nDavanti a te c'è un sentiero che si inoltra al suo interno e ti senti osservato."
                + "All'improvviso, uno strano personaggio appare tra i cespugli. Ha una lunga tunica grigia, un cappello a punta e un sorriso misterioso.\n"
                + "Si avvicina con passo sicuro e ti porge una spada, splendente come l'argento alla luce della luna.\n"
                + "\"Mio valoroso avventuriero, so che hai bisogno di una lama potente per affrontare le sfide che ti attendono per poter tornare a casa\", dice l'aiutante. "
                + "\"Prendi questa spada, forgiata dall'acciaio di una stella caduta, e diventerai invincibile contro le forze oscure che minacciano il bosco.\""
                + "\nL'aiutante ti porge la spada e scompare tra i cespugli. In cuor tuo speri di poterlo rivedere un giorno.\n\n");
        entrataBosco.getObjects().add(sword);
        Room tanaTopoMannaro = new Room(2, topoMannaro, "Tana dei topi mannari.", "Hai intrapreso il sentiero, ma improvvisamente comincia a piovere molto forte.\n"
                + "Sei riuscito a stento a scorgere l'entrata di una caverna e ti ci sei fiondato dentro.\n"
                + "Non è un posto molto accogliente, ma almeno è al riparo dalla pioggia.\n"
                + "C'è un letto, un tavolo e una sedia. Sembra che qualcuno ci viva.\n"
                + "Senti dei rumori provenire da un'altra stanza. Sei troppo curioso per non andare a vedere.\n"
                + "Ti avvicini alla porta e la apri lentamente. Dentro c'è un topo mannaro che sta mangiando.\n"
                + "Rimani sul ciglio della porta, forse è meglio osservare cosa stia effettivamente facendo quel maledetto topo mannaro.\n\n");
        tanaTopoMannaro.setLook("Osservando noti che il topo mannaro sta mangiando un braccio umano. Non è il tuo, ma è comunque disgustoso.\n"
                + "L'odore di sangue pervade per tutta la stanza."
                + "Il topo mannaro si accorge della tua presenza e ti attacca. Non hai scelta, devi difenderti.\nCosa pensi di fare?\n\n");
        tanaTopoMannaro.getObjects().add(fire);
        Room biforcazioneBosco = new Room(3, null, "Biforcazione.", "Sei riuscito ad uscire dalla tana del topo mannaro, l'atmosfera rispetto all'inizio sembra essere completamente cambiata,"
                + " è più cupa e ti da quasi un senso di inospitalità. Persino i fiori sembrano essere ostili.\n"
                + "Dove vuoi andare?\n\n");
        biforcazioneBosco.setLook("Il sentiero si divide in tre. A ovest c'è un sentiero dove però la vegetazione si infittisce, a est c'è un sentiero in cui la foresta sembra"
                + " più oscura e l'atmosfera diventa sempre più opprimente e a sud percepisci il rumore dell'acuqa provenire proprio da li.\n");
        Room fittaVegetazione = new Room(4, cumuloStrisciante, "Fitta vegetazione.", "Hai deciso di intraprendere il sentiero a ovest, ma la vegetazione è così fitta che non riesci a passare.\n"
                + "All'improvviso le radici degli alberi cominciano a muoversi e ti si attorcigliano alle caviglie, tentando di afferrarti."
                + "Di fronte a te appare un cumulo di radici ed erbacce dal pessimo odore. Cerchi di liberarti tagliando le radici attorno le tue caviglie, ma è tutto inutile."
                + "Continuano a rigenerarsi troppo velocemente!\n\n");
        fittaVegetazione.setLook("Improvvisamente appare nuovamente la figura incontrata in precedenza, che chissà puoi definirlo come un aiutante.\n"
                + "Si rivolge verso di te dicendo: \"Sono accorso percependo la tua disperazione! Sei proprio un caso perso. "
                + "Oltre la spada non hai altro nell'inventario? Controlla!\"\n"
                + "Così l'aiutante sparisce esattamente com'era comparso lasciandoti anche un pò imbarazzato.\n\n");
        Room cuoreBosco = new Room(5, treant, "Cuore del bosco.", "Sei riuscito a liberarti dal cumulo strisciante e a proseguire il tuo cammino.\n"
                + "Il sentiero ti ha portato in una radura, dove c'è un albero enorme, così grande che non riesci a vedere la cima.\n"
                + "L'albero ha un volto, è un treant! Ti guarda e sembra voglia dirti qualcosa...\n");
        cuoreBosco.setLook("Ti avvicini all'albero. Finalmente riesci a comprendere le sue parole: \"Sono la divinità di questo bosco, "
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
        cuoreBosco.getObjects().add(coin);
        Room alfheim = new Room(6, driade, "Alfheim.", "Hai deciso di proseguire il tuo cammino verso est, ma la foresta è sempre più oscura e l'atmosfera diventa sempre più opprimente.\n"
                + "All'improvviso appare una figura femminile, è una Driade! Ti guarda con occhi ignettati di sangue e prima ancora che tu possa accorgerti della sua presenza"
                + "si avventa su di te, per fortuna hai i riflessi pronti e riesci ad uscirne illeso, ma la furia della Driade non si placa così facilmente...\n\n");
        alfheim.setLook("Ti rendi conto che alcun attacco scalfisce la Driade, ma non abbastanza da farla desistere dal suo intento.\n"
                + "A volte scappare è la migliore opzione...\n\n");
        Room lago = new Room(7, fataleAcqua, "Lago.", "Hai deciso di proseguire il tuo cammino verso sud, ma il rumore dell'acqua è sempre più forte.\n"
                + "All'improvviso appare un lago circondato da una fitta nebbia e la sua superficie è completamente immobile e cristallina.\n"
                + "La nebbia si dirada e appare una mistica creatura, tanto elegante quanto spaventosa...\n"
                + "Non riesci a muoverti per la paura, la sua presenza ti incute terrore, come riflesso inizi ad attaccarlo con ogni tuo mezzo... ma è tutto inutile!\n"
                + "I colpi gli passano attraverso. Si avvicina sempre di più e senti che sussurra sempre una sola parola:\"Moneta!\"\n"
                + "Sarà il caso di dargli questa moneta, che tanto desidera?\n\n");
        Room biforcazioneLago = new Room(8, null, "Biforcazione.", "Sei finalmente nel lago e sei circondato da una fitta vegetazione marina.\n"
                + "Ti sembra tutto così strano anche grazie ai nuovi poteri che ti ha donato il fatale dell'acqua che ti consentono di respirare e muoverti.\n"
                + "Esplorando sei arrivato ad una biforcazione...\n\n");
        biforcazioneLago.setLook("La biforcazione si divide in due. A est la vegetazione marina continua ed essere rigogliosa ma non ci sono più le piccole"
                + "e adorate creaturine del lago che hai potuto ammirare in precedenza. Forse c'è qualcosa che non va, però sembra essere più rassicurante del sentiero ad ovest"
                + "dove persino la vegetazione non si può più definire tale. Il sentiero ad est è forse più sicuro da esplorare?\n\n");
        Room abissi = new Room(9, diavoloMarino, "Abissi.", "Hai deciso di proseguire per il sentiero ad est.\n Ti ritrovi nelle profondità degli abissi del lago"
                + "dove tutto ha iniziato a perdere colore. Nemmeno le creaturine marine sembra vogliano starci in un luogo così cupo.\n Continui ad esplorare"
                + "spinto dalla curiosità quando ad una certa intravedi una strana sagoma muoversi per i tronchi spogli del fondale del lago. Inizi a seguirlo, perchè i film horror non ti hanno mai insegnato niente"
                + "quando all'improvviso la figura si volta inaspettatamente nella tua direzione. Probabilmente avrà percepito il tuo sguardo su di sè.\n"
                + "Riesci così finalmente a scorgere meglio la sua figura. A primo impatto potrebbe essere scambiato tranquillamente per un serpente marino gigante ma in verità"
                + "sembra più una lucertola umanoide. Ti fissa con dei profondi occhi gialli, unico colore in quell'oscurità. E ti fissa così intensamente che"
                + "riesci a percepire il tuo essere preda. All'improvviso la creatura con uno scatto fulmineo si scaglia contro di te.\n Ti conviene iniziare ad impugnare un'arma per il combattimento.\n\n");
        abissi.getObjects().add(thunder);
        Room calipso = new Room(10, merrow, "Calipso.", "Proseguendo per il sentiero ad ovest continui ad inoltrarti negli abissi del lago.\n"
                + "Rispetto al sentiero ad est si percepisce fin da subito l'atmosfera cupa e tesa del luogo dove, nonostante i poteri concessi dal fatale dell'acqua,"
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
        calipso.setLook("La creatura è molto potente. Con la sua coda da sirena riesce a muoversi velocemente e ti sferra attacchi inaspettati. Inoltre, il corpo è ricoperto da squame così dure"
                + "che formano un'ottima difesa mentre ti attacca incessantemente con un arpione.\n Sei allo stremo e i tuoi attacchi non sembrano funzionare.\n Possibile che non ti ricordi cos'hai nell'inventario?\n"
                + "Dovresti vedere un medico per mancanza di invettiva.\n\n");
        Room tesoro = new Room(11, null, "Stanza del tesoro.", "Sei riuscito finalmente a sconfiggere quella sirena uscita male e non con poche difficoltà.\n"
                + "Per fortuna le tue fatiche potrebbero essere ripagate visto che dietro al trono della creatura sembra esserci una porta finemente decorata. Potrebbe essere una trappola, ma la curiosità è troppo forte.\n"
                + "Entri così in una stanza.\n Al centro della stanza, si erge un grande piedistallo di corallo e perle, su cui riposa il tesoro più prezioso. Montagne di monete d'oro, gioielli scintillanti e reliquie antiche brillano in tutta la loro magnificenza."
                + "Le gemme e i diamanti riflettono la luce, creando riflessi sfavillanti che danzano sulle pareti.\n\n");
        tesoro.getObjects().add(chest);
        Room underDark = new Room(12, mindFlayer, "Under Dark.", "Dopo aver superato finalmente la Driade, ti si prospetta davanti un portale immenso e oscuro.\n"
                + "Speri che ti possa ricondurre a casa ma ne dubiti vista l'atmosfera agghiacciante che lo circonda. A dar conferma ai tuoi pensieri compare all'improvviso"
                + "il \"tuo aiutante\" che ti dice:\"Congratulazioni avventuriero, sei risucito ad arrivare al boss finale di questo mondo. Se riuscirai a sconfiggerlo potrai finalmente tornare a casa.\n+"
                + "Ma attenzione! Questo è un nemico molto potente, più di tutti quelli che hai dovuto affrontare finora.\n Spero tu possa farcela. In ogni caso io ti saluto, è stato bello aiutarti nel tuo viaggio in questo mondo\""
                + "Così dicendo l'aiutante scompare. Adesso, con un impeto di coraggio e disperazione, decidi di attraversare il portale. Ti ritrovi immediatamente in un mondo completamente diverso da quello osservato finora.\n"
                + "Ti ritrovi nell'Under Dark.\n Un posto dove la luce del sole non batte mai e le cui uniche forme di vita sembrano essere delle piccole gocce d'acqua che battono sulla fredda e dura pietra.\n"
                + "Non hai il tempo di esplorare il luogo, non che avresti una gran voglia di farlo, che subito ti approccia con un ghigno di superiorità una creatura oribilante.\n"
                + "La sua testa è quella di un polipo ed indossa i tipici abiti da cattivi nei film che vedi sempre con tuo padre. Insomma si riesce a capire perfettamente il suo ruolo.\n"
                + "Appena ti approccia inizia a beffeggiarti dicendo \"L'unico modo per te di uscire da questo mondo è uccidermi. Peccato che un essere così inferiore non possa farmi niente. Se decidi di non sfidarmi e di accettare il tuo destino in qualità di creatura inferiore potrei anche pesnare di non ucciderti e di tenerti come mio schiavo\"\n"
                + "La creatura sembra parlare attraverso la telepatia perchè non vedi alcun movimento della sua bocca ricoperta di tentacoli.\n"
                + "Sei preso da una profonda paura perchè riesci a percepire chiaramente la sua potenza rispetto agli altri nemici, proprio come aveva detto l'aiutante, però allo stesso tempo sei pervaso da rabbia per le parole che ti ha rivolto.\n"
                + "Decidi così di accettare la sua sfida. Sei già pronto a combattere quando il Mind Flayer ti dice:\"Prima di iniziare, sappi che se mai riuscissi a sconfiggermi potrò pensare di lasciarti andare, altrimenti ti ucciderò vista la tua arroganza. Parola di Mind Flayer.\"\n"
                + "Sei un po' incerto ma alla fine decidi di accettare. La vostra sfida ha inizio...\n\n");
        underDark.setLook("L'Under Dark è un luogo conosciuto anche come Buio Profondo, chissà perchè.\n Nessuna luce è riuscita e riuscirà a illuminare questo posto, questo perchè stiamo parlando di un mondo segreto che si trova sotto la superficie.\n"
                + "Il suo cielo non è altro che una volta arida di pietra, come le pareti che, gelide e dure, si mostrano indifferenti nei confronti del destino di morte e sofferenza che attendono i malcapitati che finiscono per sbaglio in questo mondo.\n"
                + "La maggior parte delle persone infatti una volta qui, non torneranno mai indietro. Quelli che invece riescono a far ritorno, non saranno più gli stessi ma resteranno profondamente turbati dall'esperienza traumatica vissuta.\n"
                + "Nell'Under Dark regna un silenzio tombale, si sente solo il rieccheggiare di alcune gocce d'acqua che hanno l'audacia di cadere sul freddo e inospitale suolo.\n"
                + "Di cos'altro si nasconda in questo mondo sotterraneo tetro e silenzioso nessuno lo sa, o meglio nessuno è tornato abbastanza sano o vivo per raccontarlo...\n\n");
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
        underDark.setVisible(false);
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
            } else if (p.getCommand().getType() == CommandType.MONSTER) {
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

    private void endGood(PrintStream out) {
        out.println("Congratulazioni, sei riuscito ad uccidere il Mind Flayer.\nAll'improvviso ti senti stordito e perdi i sensi. Quando ti risvegli, ti ritrovi nella grotta in cui eri entrato poche ore fa. Il sole è ormai calato e intorno a te si sta facendo sempre più buio.\n"
                + "Esci dalla grotta e senti qualcuno chiamare il tuo nome in lontananza. Ti senti ancora stordito dal sogno che hai fatto, però senti qualcosa nella tasca dei tuoi jeans. Controlli e tiri fuori un'adorabile radiolina. Forse non è stato proprio un sogno...\nCon questi pensieri,"
                + " ritorni a casa per goderti la tua vacanza.\nStasera, tua madre ha deciso di preparare un bel piatto a base di polpo, ma tu non hai tanta fame.");
        System.exit(0);
    }

    private void endBad(PrintStream out) {
        out.println("Hai deciso di attaccare il Mind Flayer, ma, nonostante l'oggetto potente che hai utilizzato, sei riuscito soltanto a stordirlo. Continui ad attaccarlo, ma sembra tutto vano.\nAll'improvviso il Mind Flayer decide di contrattaccare e tu sei troppo debole per resistere.\n"
                + "Il Mind Flayer prima di darti il colpo di grazia ti dice:\"Posso essere sconfitto solo da qualcuno che ha compiuto buone azioni. Gli attacchi vili non hanno alcun effetto su una creatura altrettanto vile quanto me. Avresti potuto dare quella ghianda...\""
                + "Dopo aver detto ciò, il Mind Flayer ti fa perdere i sensi con uno dei suoi attacchi psichici. Quando ti risvegli, ti ritrovi in una cella fredda e oscura. Sei incatenato e non hai alcuna possibilità di scappare.\nInizi a disperarti, quando ad una certa senti dei passi che si avvicinano sempre più."
                + "Riesci, nell'oscurità, a intravedere la figura del Mind Flayer che sogghignando ti dice:\"Volevo ucciderti, però ho pensato di essere magnanimo e renderti mio schiavo per l'eternità. In fondo chi non vorrebbe essere mio schiavo! D'ora in avanti, vivrai in questa cella e sarai costretto ad eseguire tutti i miei ordini."
                + "Sarà inutile per te scappare o chiedere pietà, perchè avendo compiuto quell'azione tanto crudele, ti sei escluso ogni possibilità di salvezza. Resterai qui con me per l'eternità.\"\nMagari in un'altra vita ci penserai due volte a compiere determinate azioni...");
        System.exit(0);
    }
}
