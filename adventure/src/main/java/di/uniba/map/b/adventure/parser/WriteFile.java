/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.parser;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author g.navolio
 */
public class WriteFile {

    private static final String DATA_OBJECT = "oggettidata";
    private static final int[] ID_OBJ = {1, 2, 3, 4, 5, 6, 7};
    private static final String[] DESC_OBJ = {
        "La spada donata dall'aiutante era un'opera d'arte incredibile, che incantava chiunque posasse gli occhi su di essa. Lunga e affilata, emanava un'aura di potere e maestosità."
        + "La lama era realizzata in acciaio lucido e rifinita con cura, riflettendo la luce con una brillantezza accecante. La sua forma sinuosa era adornata da motivi intricati e intagli dettagliati lungo tutto il filo, raffiguranti simboli misteriosi e immagini leggendarie.",
        "La fiala di veleno per uccidere un'antica creatura era un oggetto oscuro e inquietante, carico di un potere mortale. La sua piccola e delicata forma contenitore nascondeva un liquido nero come l'ebano, denso e viscoso."
        + "Le pareti di vetro erano intarsiate con simboli sinistri, tracciati con precisione e dettaglio.",
        "La ghianda antica e mistica appartenente all'antica creatura era un tesoro prezioso e straordinario, che portava con sé una profonda connessione con il potere primordiale della natura. La sua forma era delicata e armoniosa, come se fosse stata scolpita con cura da una mano divina.",
        "La fiala antica era molto più di un semplice contenitore. Rappresentava un canale per l'energia primordiale del fuoco, un dono delle divinità o degli antichi maestri che avrebbero saputo manipolarlo con cautela e rispetto."
        + "Era un oggetto riservato a coloro che avevano la conoscenza e il coraggio di maneggiare l'essenza del fuoco, pronti ad utilizzarla per scopi nobili o distruttivi.",
        "La fiala antica non era solo un semplice contenitore, ma un conduttore dell'essenza del fulmine stessa."
        + "Chiunque la possedesse avrebbe potuto canalizzare l'energia elettrica e utilizzarla per scopi creativi o distruttivi. Era un oggetto riservato a coloro che avevano la conoscenza e il coraggio di manipolare l'essenza del fulmine, pronti ad affrontare le sfide e i pericoli che essa comportava.",
        "La moneta sorprendentemente luccicante donata dalla Driade non sembra una di quelle solite monete viste nel mondo normale."
        + "Essa infatti non ha inciso alcun prezzo di riferimento ma è finemente decorata da entrambi i lati con decorazioni che richiamano paesaggi marini da un lato e boschivi dall'altro.",
        "Quest'oggetto presenta le stesse caratteristiche di una radiolina, ma non sembrano esserci stazioni attive nella zona."
        + "Ha una forma piccola, che la rende tascabile. E' forse l'oggetto più adorabile che tu abbia mai visto. La sua utilità è ancora sconosciuta."
    };

    private static final String DATA_ROOM_DESC = "stanzedescdata";
    private static final int[] ID_ROOM = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    private static final String[] DESC_ROOM = {
        "Ti trovi all'entrata del bosco.\n"
        + "Questo bosco era pieno di alberi e di altissime piante con delle bellissime e grandi foglie e fiori che emanavano un profumo così dolce"
        + " e intenso e in sottofondo il rumore dell'acqua che scorreva.\nDavanti a te c'è un sentiero che si inoltra al suo interno e ti senti osservato."
        + "All'improvviso, uno strano personaggio appare tra i cespugli. Ha una lunga tunica grigia, un cappello a punta e un sorriso misterioso.\n"
        + "Si avvicina con passo sicuro e ti porge una spada, splendente come l'argento alla luce della luna.\n"
        + "\"Mio valoroso avventuriero, so che hai bisogno di una lama potente per affrontare le sfide che ti attendono per poter tornare a casa\", dice l'aiutante. "
        + "\"Prendi questa spada, forgiata dall'acciaio di una stella caduta, e diventerai invincibile contro le forze oscure che minacciano il bosco.\""
        + "\nL'aiutante ti porge la spada e scompare tra i cespugli. In cuor tuo speri di poterlo rivedere un giorno.\n\n",
        "Hai intrapreso il sentiero, ma improvvisamente comincia a piovere molto forte.\n"
        + "Sei riuscito a stento a scorgere l'entrata di una caverna e ti ci sei fiondato dentro.\n"
        + "Non è un posto molto accogliente, ma almeno è al riparo dalla pioggia.\n"
        + "C'è un letto, un tavolo e una sedia. Sembra che qualcuno ci viva.\n"
        + "Senti dei rumori provenire da un'altra stanza. Sei troppo curioso per non andare a vedere.\n"
        + "Ti avvicini alla porta e la apri lentamente. Dentro c'è un topo mannaro che sta mangiando.\n"
        + "Rimani sul ciglio della porta, forse è meglio osservare cosa stia effettivamente facendo quel maledetto topo mannaro.\n\n",
        "Sei riuscito ad uscire dalla tana del topo mannaro, l'atmosfera rispetto all'inizio sembra essere completamente cambiata,"
        + " è più cupa e ti da quasi un senso di inospitalità. Persino i fiori sembrano essere ostili.\n"
        + "Dove vuoi andare?\n\n",
        "Hai deciso di intraprendere il sentiero a est, ma la vegetazione è così fitta che non riesci a passare.\n"
        + "All'improvviso le radici degli alberi cominciano a muoversi e ti si attorcigliano alle caviglie, tentando di afferrarti."
        + "Di fronte a te appare un cumulo di radici ed erbacce dal pessimo odore. Cerchi di liberarti tagliando le radici attorno le tue caviglie, ma è tutto inutile."
        + "Continuano a rigenerarsi troppo velocemente!\n\n",
        "Sei giunto in un'altra misteriosa stanza. L'atmosfera, rispetto a quella del castello, è molto più scura e sacra. Di fronte a te, si stagliano le radici di un grande albero. Probabilmente sono le radici del Treant di cui ti ha parlato l'aiutante.\n"
            + "In mezzo alla stanza, nascosta dalle lunghe radici, è presente un altare sacrificale. E' il momento di compiere la tua scelta: "
            + "usare il veleno o dare la ghianda?\n\n",
        "Hai deciso di proseguire il tuo cammino verso ovest, ma la foresta è sempre più oscura e l'atmosfera diventa sempre più opprimente.\n"
        + "All'improvviso appare una figura femminile, è una Driade! Ti guarda con occhi ignettati di sangue e prima ancora che tu possa accorgerti della sua presenza"
        + "si avventa su di te, per fortuna hai i riflessi pronti e riesci ad uscirne illeso, ma la furia della Driade non si placa così facilmente...\n\n",
        "Hai deciso di proseguire il tuo cammino verso nord, ma il rumore dell'acqua è sempre più forte.\n"
        + "All'improvviso appare un lago circondato da una fitta nebbia e la sua superficie è completamente immobile e cristallina.\n"
        + "La nebbia si dirada e appare una mistica creatura, tanto elegante quanto spaventosa...\n"
        + "Non riesci a muoverti per la paura, la sua presenza ti incute terrore, come riflesso inizi ad attaccarlo con ogni tuo mezzo... ma è tutto inutile!\n"
        + "I colpi gli passano attraverso. Si avvicina sempre di più e senti che sussurra sempre una sola parola:\"Moneta!\"\n"
        + "Sarà il caso di dargli questa moneta, che tanto desidera?\n\n",
        "Sei finalmente nel lago e sei circondato da una fitta vegetazione marina.\n"
        + "Ti sembra tutto così strano anche grazie ai nuovi poteri che ti ha donato il fatale dell'acqua che ti consentono di respirare e muoverti.\n"
        + "Esplorando sei arrivato ad una biforcazione...\n\n",
        "Hai deciso di proseguire per il sentiero ad est.\n Ti ritrovi nelle profondità degli abissi del lago"
        + "dove tutto ha iniziato a perdere colore. Nemmeno le creaturine marine sembra vogliano starci in un luogo così cupo.\n Continui ad esplorare"
        + "spinto dalla curiosità quando ad una certa intravedi una strana sagoma muoversi per i tronchi spogli del fondale del lago. Inizi a seguirlo, perchè i film horror non ti hanno mai insegnato niente"
        + "quando all'improvviso la figura si volta inaspettatamente nella tua direzione. Probabilmente avrà percepito il tuo sguardo su di sè.\n"
        + "Riesci così finalmente a scorgere meglio la sua figura. A primo impatto potrebbe essere scambiato tranquillamente per un serpente marino gigante ma in verità"
        + "sembra più una lucertola umanoide. Ti fissa con dei profondi occhi gialli, unico colore in quell'oscurità. E ti fissa così intensamente che"
        + "riesci a percepire il tuo essere preda. All'improvviso la creatura con uno scatto fulmineo si scaglia contro di te.\n Ti conviene iniziare ad impugnare un'arma per il combattimento.\n\n",
        "Proseguendo per il sentiero ad ovest continui ad inoltrarti negli abissi del lago.\n"
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
        + "La creatura si sveglia immediatamente e tu ti metti in posizione di difesa pronto a combattere.\n\n",
        "Sei riuscito finalmente a sconfiggere quella sirena uscita male e non con poche difficoltà.\n"
        + "Per fortuna le tue fatiche potrebbero essere ripagate visto che dietro al trono della creatura sembra esserci una porta finemente decorata. Potrebbe essere una trappola, ma la curiosità è troppo forte.\n"
        + "Entri così in una stanza.\n Al centro della stanza, si erge un grande piedistallo di corallo e perle, su cui riposa il tesoro più prezioso. Montagne di monete d'oro, gioielli scintillanti e reliquie antiche brillano in tutta la loro magnificenza."
        + "Le gemme e i diamanti riflettono la luce, creando riflessi sfavillanti che danzano sulle pareti.\n\n",
        "Dopo aver superato finalmente la Driade, ti si prospetta davanti un portale immenso e oscuro.\n"
        + "Speri che ti possa ricondurre a casa ma ne dubiti vista l'atmosfera agghiacciante che lo circonda. A dar conferma ai tuoi pensieri compare all'improvviso"
        + "il \"tuo aiutante\" che ti dice:\"Congratulazioni avventuriero, sei risucito ad arrivare al boss finale di questo mondo. Se riuscirai a sconfiggerlo potrai finalmente tornare a casa.\n"
        + "Ma attenzione! Questo è un nemico molto potente, più di tutti quelli che hai dovuto affrontare finora.\n Spero tu possa farcela. In ogni caso io ti saluto, è stato bello aiutarti nel tuo viaggio in questo mondo\""
        + "Così dicendo l'aiutante scompare. Adesso, con un impeto di coraggio e disperazione, decidi di attraversare il portale. Ti ritrovi immediatamente in un mondo completamente diverso da quello osservato finora.\n"
        + "Ti ritrovi nell'Under Dark.\n Un posto dove la luce del sole non batte mai e le cui uniche forme di vita sembrano essere delle piccole gocce d'acqua che battono sulla fredda e dura pietra.\n"
        + "Non hai il tempo di esplorare il luogo, non che avresti una gran voglia di farlo, che subito ti approccia con un ghigno di superiorità una creatura oribilante.\n"
        + "La sua testa è quella di un polipo ed indossa i tipici abiti da cattivi nei film che vedi sempre con tuo padre. Insomma si riesce a capire perfettamente il suo ruolo.\n"
        + "Appena ti approccia inizia a beffeggiarti dicendo \"L'unico modo per te di uscire da questo mondo è uccidermi. Peccato che un essere così inferiore non possa farmi niente. Se decidi di non sfidarmi e di accettare il tuo destino in qualità di creatura inferiore potrei anche pesnare di non ucciderti e di tenerti come mio schiavo\"\n"
        + "La creatura sembra parlare attraverso la telepatia perchè non vedi alcun movimento della sua bocca ricoperta di tentacoli.\n"
        + "Sei preso da una profonda paura perchè riesci a percepire chiaramente la sua potenza rispetto agli altri nemici, proprio come aveva detto l'aiutante, però allo stesso tempo sei pervaso da rabbia per le parole che ti ha rivolto.\n"
        + "Decidi così di accettare la sua sfida. Sei già pronto a combattere quando il Mind Flayer ti dice:\"Prima di iniziare, sappi che se mai riuscissi a sconfiggermi potrò pensare di lasciarti andare, altrimenti ti ucciderò vista la tua arroganza. Parola di Mind Flayer.\"\n"
        + "Sei un po' incerto ma alla fine decidi di accettare. La vostra sfida ha inizio...\n\n"
    };

    private static final String DATA_ROOM_LOOK = "stanzelookdata";
    private static final String[] LOOK_ROOM = {
        "",
        "Osservando noti che il topo mannaro sta mangiando un braccio umano. Non è il tuo, ma è comunque disgustoso.\n"
        + "L'odore di sangue pervade per tutta la stanza."
        + "Il topo mannaro si accorge della tua presenza e ti attacca. Non hai scelta, devi difenderti.\nCosa pensi di fare?\n\n",
        "Il sentiero si divide in tre. A ovest c'è un sentiero dove però la vegetazione si infittisce, a est c'è un sentiero in cui la foresta sembra"
        + " più oscura e l'atmosfera diventa sempre più opprimente e a sud percepisci il rumore dell'acuqa provenire proprio da li.\n",
        "Improvvisamente appare nuovamente la figura incontrata in precedenza, che chissà puoi definirlo come un aiutante.\n"
        + "Si rivolge verso di te dicendo: \"Sono accorso percependo la tua disperazione! Sei proprio un caso perso. "
        + "Oltre la spada non hai altro nell'inventario? Controlla!\"\n"
        + "Così l'aiutante sparisce esattamente com'era comparso lasciandoti anche un pò imbarazzato.\n\n",
        "",
        "Ti rendi conto che alcun attacco scalfisce la Driade, così decidi momentaneamente di ritirarti dietro un cespuglio e osservarla.\n"
        + "Compare, improvvisamente al tuo fianco, l'aiutante. Ha pensato che sia giusto farti capire cosa sta succedendo nel bosco: \"Nel bosco risiede una divinità, il Treant. "
        + "Risiede qui da migliaia di anni e non ha mai visto un essere umano. "
        + "Il treant sembra essere preoccupato per te.\nSe vuoi tornare da dove sei venuto, per quanto mi è possibile, posso darti una mano.\n"
        + "Come hai potuto ben vedere il bosco è circondato da una presenza oscura, la Driade è impazzita a causa della malattia che affligge il Treant e che ha trasformato questo posto in una valle oscura.\n"
        + "Tempo fa iniziarono delle strane piogge, che hanno iniziato a corrodere l'albero dall'interno, rendendolo vulnerabile.\n"
        + "Giovane avventuriero, il tuo compito sarà quello di ritrovare la ghianda perduta dall'albero, per ristabilire l'equilibrio.\n"
        + "Tale ghianda dovrebbe trovarsi da qualche parte all'interno del bosco.\n Se non dovessi riuscire a trovarla vorrei che che tu ponga fine alle sofferenze del Treant,"
        + "tramite un apposito veleno... Sia io che il Treant siamo disposti a tutto per ristabilire l'equilibrio del bosco! In ogni caso riusciresti a superare"
        + "la Driade.\n Buona fortuna avventuriero... Rimarrò qui in attesa che tu faccia ritorno...\""
        + "Prima di allontanarti l'aiutante ti dona una moneta, da qui a breve riuscirai a capire a cosa ti sarà utile...\n\n",
        "",
        "La biforcazione si divide in due. A est la vegetazione marina continua ed essere rigogliosa ma non ci sono più le piccole"
        + "e adorate creaturine del lago che hai potuto ammirare in precedenza. Forse c'è qualcosa che non va, però sembra essere più rassicurante del sentiero ad ovest"
        + "dove persino la vegetazione non si può più definire tale. Il sentiero ad est è forse più sicuro da esplorare?\n\n",
        "",
        "La creatura è molto potente. Con la sua coda da sirena riesce a muoversi velocemente e ti sferra attacchi inaspettati. Inoltre, il corpo è ricoperto da squame così dure"
        + "che formano un'ottima difesa mentre ti attacca incessantemente con un arpione.\n Sei allo stremo e i tuoi attacchi non sembrano funzionare.\n Possibile che non ti ricordi cos'hai nell'inventario?\n"
        + "Dovresti vedere un medico per mancanza di invettiva.\n\n",
        "",
        "L'Under Dark è un luogo conosciuto anche come Buio Profondo, chissà perchè.\n Nessuna luce è riuscita e riuscirà a illuminare questo posto, questo perchè stiamo parlando di un mondo segreto che si trova sotto la superficie.\n"
        + "Il suo cielo non è altro che una volta arida di pietra, come le pareti che, gelide e dure, si mostrano indifferenti nei confronti del destino di morte e sofferenza che attendono i malcapitati che finiscono per sbaglio in questo mondo.\n"
        + "La maggior parte delle persone infatti una volta qui, non torneranno mai indietro. Quelli che invece riescono a far ritorno, non saranno più gli stessi ma resteranno profondamente turbati dall'esperienza traumatica vissuta.\n"
        + "Nell'Under Dark regna un silenzio tombale, si sente solo il rieccheggiare di alcune gocce d'acqua che hanno l'audacia di cadere sul freddo e inospitale suolo.\n"
        + "Di cos'altro si nasconda in questo mondo sotterraneo tetro e silenzioso nessuno lo sa, o meglio nessuno è tornato abbastanza sano o vivo per raccontarlo...\n\n"
    };

    private static final String DATA_MONSTER = "mostridata";
    private static final int[] ID_MON = {1, 2, 3, 4, 5, 6, 7, 8};
    private static final String[] DESC_MON = {
        "I topi mannari sono scaltri licantropi dall'indole avida e subdola. In forma umana sono snelli e nervosi, hanno i capelli radi e gli occhi sfuggenti.\n"
        + "Solitamente usano armi leggere e prediligono le imboscate piuttosto che muoversi in branco. Usa la sua forza principalmente per muoversi furtivamente e per scappare.\nI topi mannari, in forma ibrida o umanoide,"
        + "sono esteticamente caratterizzati dal loro spirito licantropo, ossia hanno fattezze molto simili ai topi. Gli occhi sono di un rosso scarlatto, privi di qualsiasi cenno di ragione. I denti soono molto"
        + "lunghi e affilati, e le mani hanno lunghe dita e unghie aguzze.\nDi certo non è una creatura che ti augureresti di incontrare la notte...\n\n",
        "Un cumulo strisciante, anche chiamato striciante, avanza a passo lento e pesante attraverso gli acquitrini, le paludi e le foreste più oscure,"
        + "assimilando ogni forma di materia organica che trova lungo il cammino.\nQuesto cumulo animato di vegetazione marcescente è una massa alta una volta e mezzo un umano, sormontata da una \"testa\" priva di volto\n\n",
        "I Treant sono alberi risvegliati che vegliano sulle foreste più antiche. Sebbene preferiscano trascorrere i giorni, i mesi e gli anni in serena contemplazione, "
        + "proteggono con ferocia le loro dimore boschive dalle minaccie esterne.\nI Treant continuano a crescere come qualsiasi albero e possono raggiungere dimensioni smisurate e possono sviluppare poteri magici in grado di"
        + " influenzare animali e vegetali.\n Anche una volta risvegliato, un Treant passa buona parte del suo tempo vivendo come un albero. I Treant, rispetto ai cumuli striscianti, presentano un volto, molto spesso grinzoso"
        + " a causa delle striature del loro tronco.\n\n",
        "Le driadi sono spiriti della natura che abitano le foreste più antiche e incontaminate. Sono creature di bellezza e grazia sovrannaturali, che si muovono tra gli alberi"
        + " con la stessa agilità di una folata di vento.\nLe driadi sono creature di natura benevola, ma sono anche molto schive e non amano essere disturbate. Sebbene siano creature pacifiche, le driadi non esitano a difendere"
        + " la loro dimora con ferocia.\nL'origine delle driadi ha una natura drammatica ma allo stesso tempo romantica, infatti esse non sono altro che uno spirito fatato che è stato punito per essersi innamorato di un mortale "
        + "(in quanto reputato un amore proibito).\nUna driade può emergere da un albero e spostarsi nel territorio circostante, ma l'albero resta la dimora e il fulcro che la ancora al mondo.\nFintanto che l'albero rimane illeso e"
        + "in salute, la driade rimane esternamente giovane e seducente. Se l'albero viene danneggiato, anche la driade ne soffre tanto che potrebbe arrivare a perdere il senno.\nPotrebbe essere questo il nostro caso...\n\n",
        "Un fatale dell'acqua é un guardiano elementale vincolato a uno specifico luogo pieno d'acqua, come una pozza o una fontana. Finché è immerso nell'acqua è invisibile,"
        + " ma la sua forma serpentina diventa evidente quando la creatura emerge.\nI fatali dell'acqua malvagi possono attaccare e uccidere con estrema facilità e per puro piacere chi si avvicina alla fonte che proteggono, mentre quelli buoni sono"
        + " più propensi a spaventare semplicemente gli avventurieri.\nSolitamente quando ci si avvicina a una fonte custodita da questa creatura, bisogna dare qualcosa in cambio.\n\n",
        "Lungo le coste immerse nella nebbia o nei tratti sconfinati\n"
        + "di oceano, il sinistro squillo di una conchiglia usata come corno di guerra fa pelare il sangue nelle vene di tutti coloro che lo sentono. È il suono del corno da caccia dei sahuagin, una chiamata alle armi che precede"
        + " una razzia e una battaglia. I coloni chiamano i sahuagin \"diavoli marini\", in quanto queste creature non mostrano alcuna compassione:\n"
        + "massacrano le ciurme delle navi e decimano i villaggi costieri indiscriminatamente.\nI diavoli marini che decidono di non partecipare alle razzie sono solitari e decidono di vivere in laghi e fiumi isolati, dove si nutrono di pesci e"
        + " dei malcapitati che si avvicinano alla loro tana.\nFisicamente, questi diavoli marini assomigliano a delle lucertole marine, con la faccia di uno scorfano, i denti aguzzi e taglienti e gli occhi che nelle oscurità marine sembrano"
        + " i fanali gialli di un auto.\nUna vera e propria bellezza marina insomma...\n\n",
        "I merrow infestano le acque, dove aggrediscono i pescatori, i marinidi e qualsiasi altra creatura commestibile\n"
        + "De incontrino sul loro cammino. Questi mostri selvaggi ghermiscono e divorano le prede più imprudenti per trascinare i loro cadaveri annegati nelle loro caverne sottomarine e cibarsene in tranquillità.\n"
        + "La loro vicinanza agli abissi li ha resi malvagi e crudeli con il tempo.\nI merrow sono caratterizzati da una lunga coda a sirena molto squamosa che gli consente di muoversi velocemente e di difendersi dagli attacchi.\n\n",
        "I mind flayer, noti anche come illithid, sono l'anatema delle creature senzienti di innumerevoli mondi. Questi tiranni psionici, schiavisti e viaggiatori dimensionali ordiscono ciaborate trame per piegare intere razze ai loro fini nefasti.\n"
        + "Dalla testa simile a una piovra si protendono quattro tentacoli, che si agitano con impazienza ogni volta che una creatura senziente si avvicina.\n"
        + "Negli eoni passati, gli illithid regnavano su un impero che abbracciava numerosi mondi. Soggiogarono e alterarono intere razze di schiavi umanoidi. Uniti da una coscienza collettiva, gli illithid continuano ancora oggi a tessere trame tanto malvagie quanto estese, concepite dalle loro menti imperscrutabili.\n"
        + "Dopo la caduta del loro impero, i collettivi degli illithid presenti sul Piano Materiale si sono rifugiati nell'Underdark.\nI mind flayer sono dotati di poteri psionici che gli consentono di controllare le menti delle creature inferiori, a causa dei loro poteri preferiscono comunicare telepaticamente.\n"
        + "Un mind flayer solitario è in genere un rinnegato o un reietto. Infatti, la  maggior parte degli illithid fa parte di una colonia di fratelli fedeli ad un cervello antico, un gigantesco essere simile a un cervello immerso in una pozza salmastra al centro della comunità.\nGli illithid si cibano dei cervelli degli umanoidi,"
        + " poichè contengono tutti gli enzimi, gli ormoni e l'energia psichica di cui hanno bisogno.\nUn illithid in piena salute è ricoperto da un velo di muco color malva. I cervelli sono preziosi anche per i loro esperimenti quali la trasformazione in divoracervelli.\n"
        + "Con tutti quei tentacoli chissà cos'è in grado di fare...\n\n"
    };

    public static void writeObjFile() throws IOException {

        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("target/generated-sources/" + DATA_OBJECT)));
        for (int i = 0; i < ID_OBJ.length; i++) {
            out.writeInt(ID_OBJ[i]);
            out.writeUTF(DESC_OBJ[i]);
        }

        out.close();
    }

    public static void writeRoomDescFile() throws IOException {
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("target/generated-sources/" + DATA_ROOM_DESC)));
        for (int i = 0; i < ID_ROOM.length; i++) {
            out.writeInt(ID_ROOM[i]);
            out.writeUTF(DESC_ROOM[i]);
        }
        out.close();
    }
    
    public static void writeRoomLookFile() throws IOException {
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("target/generated-sources/" + DATA_ROOM_LOOK)));
        for (int i = 0; i < ID_ROOM.length; i++) {
            out.writeInt(ID_ROOM[i]);
            out.writeUTF(LOOK_ROOM[i]);
        }
        out.close();
    }

    
    public static void writeMonsterFile() throws IOException {
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("target/generated-sources/" + DATA_MONSTER)));
        for (int i = 0; i < ID_MON.length; i++) {
            out.writeInt(ID_MON[i]);
            out.writeUTF(DESC_MON[i]);
        }
        out.close();

    }
}
