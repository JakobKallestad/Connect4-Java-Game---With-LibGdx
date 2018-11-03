Man kjører spillet ved å kjøre Main.jar


# [Semesteroppgave 2: “Fire på rad”](https://retting.ii.uib.no/inf101.v18.sem2/blob/master/SEM-2.md)


* **README**
* [Oppgavetekst](SEM-2.md)

Dette prosjektet inneholder [Semesteroppgave 2](SEM-2.md). Du kan også [lese oppgaven online](https://retting.ii.uib.no/inf101.v18.oppgaver/inf101.v18.sem2/blob/master/SEM-2.md) (kan evt. ha små oppdateringer i oppgaveteksten som ikke er med i din private kopi).

**Innleveringsfrist:**
* Hele oppgaven skal være ferdig til **fredag 27. april kl. 2359** ([AoE](https://www.timeanddate.com/worldclock/fixedtime.html?msg=4&iso=20180427T2359&p1=3399))
* [Ekstra tips til innlevering](https://retting.ii.uib.no/inf101/inf101.v18/wikis/innlevering)

(Kryss av under her, i README.md, så kan vi følge med på om du anser deg som ferdig med ting eller ikke.)

**Utsettelse:** Hvis du trenger forlenget frist er det mulig å be om det (spør gruppeleder – evt. foreleser/assistenter hvis det er en spesiell situasjon). Hvis du ber om utsettelse bør du være i gang (ha gjort litt ting, og pushet) før fristen
   * En dag eller to går greit uten begrunnelse.
   * Eksamen er relativt tidlig i år, så vi vil helst unngå lange utsettelser.
   * Om det er spesielle grunner til at du vil trenge lengre tid, så er det bare å ta kontakt, så kan vi avtale noe. Ta også kontakt om du [trenger annen tilrettelegging](http://www.uib.no/student/49241/trenger-du-tilrettelegging-av-ditt-studiel%C3%B8p). 
   
# Fyll inn egne svar/beskrivelse/kommentarer til prosjektet under
* Levert av:   *Jakob Kallestad* (*jka042*)
* [Ja😃] hele semesteroppgaven er ferdig og klar til retting!
* Code review:
   * [ ] jeg har fått tilbakemelding underveis fra @brukernavn, ...
   * [ ] jeg har gitt tilbakemelding underveis til @brukernavn, ...
* Sjekkliste:
   * [100%] Kjørbart Fire på Rad-spill
   * [100%] Forklart designvalg, hvordan koden er organisert, abstraksjon, og andre ting 
   * [100%] Tester
   * [100%] Dokumentasjon (JavaDoc, kommentarer, diagrammer, README, etc.)
   * [100%] Fornuftige navn på klasser, interfaces, metoder og variabler
   * [100%] Fornuftige abstraksjoner og innkapsling (bruk av klasser, interface, metoder, etc.)

## Oversikt
Foreløpig er spillet kjørbart, men bare med tekstgrafikk. Mesteparten av tingene skjer fra GameScreen, og spillet fortsetter å kjøre som følge av GameScreen.loop() metoden. Det er også lagt vekt på å bruke mye interface og et Generisk board som tar inn et ruleSet. Derfor vil jeg si at det er bra modulert.

Ting som jobbes med:
- Ai spiller
- Penere brett
- Bedre input system, evt KeyListener
- Flere tester
- Kommentering
- Readme
- Med mer
Oppdatering 23/04:
- Jeg har gjennoppbygget spillet på nytt ved å bruke libGdx libraries samtidig som jeg har forsøkt å beholde en del av det jeg allerede har fra før.
- Jeg har laget denne libgdx GUI versjonen av spillet som et annet prosjekt og ingenting av det er derfor blitt registrert ved hjelp av git ennå.
- Denne oppdaterte libgdx versjonen vil bli addet her innen et par dager
- Jeg vil derimot ta kontakt med en gruppeleder før jeg sletter alt jeg har gjort fra den vanlige versjonen.
Oppdatering 27/3:
- Done! :)
- Fikk ikke tatt kontakt med gruppeleder, men regner med det går bra :)
- Oversikt står egentlig greit forklart i abstraksjon i starten. 
- I korte trekk: libgdx kjører LwjglApplication.
- Den kjører skjermene mine som implements Screen.
- I skjermene mine kjøres render() en gang per frame.
- NOTE: Into Deadlands.mp3 er antageligvis copyrightet :p. Det andre tror jeg stort sett er Royality free eller ingenting.

Spillet er først og fremst beregnet på å bli spilt av to spillere ettersom "ai" er stokk dum. Sorry for å ha misbrukt "ai" navnet haha :=P.
BTW: jeg har akkurat kopiert hele koden over til dette prosjektet. Hvis det er noe textures eller errors involvert som en følge av det så beklager jeg det. Får nok ikke kjørt det så mange ganger før jeg leverer, så kan forekomme bugs.

BTW: så tror jeg libgdx.jar -ene mangler dokumentasjon ettersom jeg bare kopierte de inn i prosjektet. Det er heldigvis veldig lett å finne dokumentasjonen på nettet hvis man googler det.


### Bruk
For å starte programmet kjør: Main

## Designvalg


### Bruk av abstraksjon
*(hvordan du har valgt objekter/klasser for å representere ting i spillet)*
Jeg har valgt klasser for alle deler som kan individuelt trekkes ut fra programmet for å få til god abstrahering og modelering
--
Oppdatering 23/04:
1. Main kaller en ny GameApp. Dette "tror jeg" er et av de øverste organene i koden utenom LwjglApplication som er høyest av alle.
2. GameApp extender fra libGdx sin Game, og kan interagere med MenuScreen og GameScreen gjennom Game.setScreen()
3. GameScreen og MenuScreen implementerer fra libGdx sin Screen og får derfor en god del metoder. De viktige som jeg bruker er render(float delta) og dispose(). Utenom det har jeg laget noen ekstra metoder (update(), changePlayer() og updateFallPiece()), men det viktige å ta med seg herfra er egentlig bare at render() blir kjørt én gang per frame. Derfor kan jeg ta inn PlayerInput hver frame og oppdatere FallPiece og Board. Når FallPiece har falt ned til dropPositionY, så vil FallPiece bli lagt til i Board og deretter satt til null. Så vil også changePlayer() bli kjørt en gang. Når så den neste Player  klikker på space vil det bli laget en ny FallPiece, repeat. Regler og sånn er seperat og er nær-identisk med reglene som jeg laget før grafikkoppgraderingen :).

4. GameScreen:
	- Den har en del Textures, Pieces, YellowBorder, Music og Sound som allerede er "pre-defined" av meg. Altså de bestemmes ikke av MenuScreen
	- YellowBorder extender Sprite, noe som gjør at den har en Texture og en Position. Jeg har også lagt inn en egen int xIndex og yIndex som brukes til å avgjøre hvilken kolonne som spilleren ønsker å slippe brikken i med space. (fungerer litt dårlig i fullskjerm).
	- Den har to Player-objekter. De vil bli satt til enten HumanPlayer() eller AIPlayer avhengig av parametre fra konstruktøren. (Fra MenuScreen).
	- Den har også et IBoard<Piece> objekt som er generisk. Her settes FallPiece inn når den når sin dropPositionY (bestemt fra board og rules).
	- Den har et IRules<Piece> objekt som også er generisk. IRules bruker ikke direkte av GameScreen, men blir brukt i Board til å bestemme hvordan 	brikker skal plasseres i grid og til å sjekke Win-conditions hver gang en ny brikke er addet til Board.
	- Piece nullPiece plasseres i alle de åpne cellene til Board sin cells. Den blir brukt for blant annet å sjekke setRule, Win-conditions og blir renderet til skjermen på lik linje med de andre brikkene. Eneste forskjellen er at den består av en Texture som er blank og derfor vil være usynlig på skjermen. I senere tid har jeg funnet ut at den strengt tatt ikke vil være nødvendig ettersom jeg istedenfor kan bruke vanlig null for å utrette det samme, og antageligvis vil spare masse minne og prosseseringstid. NullPiece vil derfor bli fjernet og erstattet med null når jeg har overskudd til dette.
	- int TimeRemaining brukes til å passe på at man ikke kan gi for mange inputs for raskt. Dette var et problem før jeg la den inn i og med at update() blir kalt veldig mange ganger per sekund. Ved å bruke TimeRemaining tar det altså 0.15 sekunder mellom hver mulige input.

5. MenuScreen:
	- Den skal jeg skrive om når jeg er helt ferdig med den
	- 27/4:
	- MenuScreen har masse Texture-buttons der tre av de vises om gangen avhengig av hvilken "layer" man er på. Layerene øker eller minker når man klikker på knappene, og blir layers lik 4 så skifter MenuScreen over til GameScreen med litt info om gameModes, playerTypes og musikkvalg.

6. Board:
	- Generisk.
	- Implementerer IBoard
	- Den har cells som er en Liste av Lister med T-objekter. getBoard() returnerer cells og det er cells som bestemmer hvor og hva som skal tegnes hvor på skjermen.
	- Har en egen kopi av et IRules-objekt rules. Rules blir sammen med cells brukt getNextY() for å finne neste y og i set() for å plassere en brikke inn i cells og deretter sjekke om det er 4 identiske brikker på rad, altså sjekke om noen har vunnet. set() returnerer true hvis noen har vunnet.
	- Opprinnelig var det en displayBoard metode også, men da jeg begynte å bruke grafikk valgte jeg heller å bruke getBoard() og tegne brikkene til skjermen i GameScreen sin render()
	- Bruker fortsatt den opprinnelige displayBoard til testene, for de må gjøres i consollen.
	
7. ConnectFourRules:
	- Generisk 
	- Implementerer IRules
	- setRule(): Den begynner på toppen av en gitt kolonne og sjekker nedover til den finner første brikke den støter på eller bunnen av brettet. Den returnerer så hvor mange indexer den måtte bevege seg nedover i cells før dette skjedde.
	- checkWin(): Jeg har valgt å sjekke bare mulige vinnerposisjoner fra den brikken som ble sist lagt ned sin posisjon. Dette har jeg gjort fordi det ville vært tungvint å måttet sjekke hele brettet etter ALLE mulige vinnerposisjoner hver gang. Måten jeg har gjort det på er at jeg sjekker om den brikken som ble lagt ned er identisk med de ved siden av seg. Da må jeg sjekke i horisontalt, diagonalt-1, diagonalt-2 og nedover. I hver av disse rettningene må jeg sjekke linjen som går begynner tre posisjoner før brikken og ender tre posisjoner etter brikken. Så har jeg en counter som teller opp når brikkene er like, men begynner på nytt når det er en annen. Jeg gjorde første dette litt feil, men rettet det (se erfaringer).
	- Grunnen til at jeg kan bruke identitet til å sjekke fire på rad er fordi alle Piece-objektene fra player1 er egentlig ett og samme objekt og alle Piece-objektene fra player2 er ett og samme objekt. 
	- Jeg mener derfor at min måte å sjekke fire på rad er en veldig effektiv og intuitiv måte og jeg slipper også å importere Piece for å sjekke noe sånn som: if (piece.getColor() == otherPiece.getColor().
	
8.  GameHelper:
	- Den har noen statiske metoder som brukes for å scale grid og textures til kordinatsystemet til Board sin cells.
	- Tanken bak er at det skulle være lett å endre for eksempel størrelsen på brettet eller grafikk-vinduet uten å måtte skrive om all koden.
	- Jeg er fan av tanken, men etter hvert som jeg har lagt til mer og mer hadde det nok blitt en del å endre på uansett, siden jeg ikke brukte GameHelper til alt.
	- Den kunne kanskje hatt navnet GameScaler eller noe og? men men. 

9. Player:
	- Det skal være mulig å at Player er både HumanPlayer eller en AIPlayer. Disse skal kalles for å få input fra bruker/datamaskinen. 
	- HumanPlayer bruker libGdx sin input metoder. 
	- AIPlayer bruker random tall og legger en piece i den kolonnen som tallet svarer til på x-kordinaten. 
	- OBS: AIPlayer er helt random og ble ikke prioritert denne gangen. Jeg vil helst lære mer om algoritmer og sånn før jeg setter meg inn i det.
	- Jeg så at MinMax algorithm var noe greier, men leste ikke nøye.
	
	


### Erfaring – hvilke valg viste seg å være gode / dårlige?
*(designerfaringer – er det noe du ville gjort annerledes?)*
Jeg vurderer å legge til en form for grafikk på spillet, men det ser litt vanskelig ut så langt. Jeg vurderer blant annet å imiplementere spillet på nytt gjennom ekstra library LibGdx.

Oppdatering 23/04:
Jeg gjorde nettopp dette. Jeg rekonstruerte spillet på nytt ved hjelp av libgdx. Dette var egentlig mye lettere enn jeg først trodde. Jeg trengte ikke endre noenting på Board, Rules, så det viser jo hvor bra et generisk Board er. Derimot måtte jeg legge til en god del ekstra ting, og gjøre om på GameScreen betraktelig for å bruke libGdx sin måte at designe spillet på. I ettertid er jeg veldig glad for at jeg gjorde dette. Det var ikke spesielt vanskelig og jeg merker hvordan inf101-begrepene hjelper meg og ser blant annet eksempler på hvordan de er tatt i bruk internt i libGdx.

Oppdatering 27/4:
Har jobbet mye med spillet nå og begynner å gå litt lei. Jeg har hatt en god erfaring og lært mye. Det er definnetivt mye som jeg kunne ha gjort bedre også, men jeg tror dette kommer etterhvert som jeg får mer erfaring. Jeg la merke til at libGdx er veldig "event-driven". Jeg vet ikke helt hva det vil si ennå, men jeg tror jeg må lære meg den måten å programmere på i tillegg til objektorientert for å få fult utbytte av libgdx. Likevell syns jeg libgdx var veldig enkelt og greit og på en god måte demonstrerer OOP-prinsippene i inf101. Kanskje noe som burde ha blitt anbefalt til oss av Anya?

Det med checkWin problemet jeg hadde i starten var forresten at jeg sjekket alle 8 retninger fra piecen og bortover 4 ganger for å se om alle var samme objekt. Jeg kom ikke på med en gang at dette ville ignorere mange potensielle vinnerposisjoner, men jeg fikk heldigvis fikset det ganske kort og greit like etterpå.

## Testing
*(hvordan du har testet ting)*
Jeg har testet Board ved blant annet å sjekke om brikkene kommer der de skal når de slippes, og jeg har sjekket om wincondition skjer når det er 4 rikker på rad. Testene viste seg å ikke være fult tilstrekkelige da de viste riktig på alt, men det ikke fungerte i spillet. Grunnen til det var et problem i ConnectFourRules der jeg ikke sjekket checkWin på riktig måte. Jeg fikset så på dette i Rules. Testene ga meg riktig uansett. Så jeg brude lage noen flere. Jeg merket også en gang ved hjelp av å la to AIPlayer legge tilfeldige brikker at jeg ikke hadde lagt inn en draw mulighet i spillet. Derfor fikset jeg dette. Takk til testing :)

Oppdatering 27/4:
Nå tester jeg Board med å bruke String objekter istedenfor Piece objecter. Det fungerer på samme måte og kan også visualiseres lett i konsollen. Jeg antar at libgdx har et eget teste-framework, men har ikke giddet å sette meg inn i det. Jeg har bare testet win-conditions, draw, initelement==null. Jeg ser ikke noen særlig grunn til å teste noe annet. Det har såklart oppstått noen problemer her og der, men jeg fant at det var relativt lett å debugge, og jeg kommer ikke på noe særlig mer lurt å teste. Jeg kunne såklart testet flere posisjoner assertTrue(win), men jeg har spilt spillet ganske mange ganger for å si det sånn :p. Hadde nok oppdaget en posijon som ga feil winCondition hvis den eksisterte.

Et problem jeg lenge hadde også var at AIPlayer spilte videre etter at spillet var ferdig :p. Så fikset det med å ikke kjøre update() hvis det var en winner eller draw.

## Funksjonalitet, bugs
*(hva virker / virker ikke)*
Spillet er fungerende, men man "kan" foreløpig legge til ekstra brikker på toppen av brettet og dermed bruke opp sin tur uten å legge en brikke. Dette er såklart ikke meningen. Jeg skal fikse det når jeg får tid. Ellers fungerer alt som det skal. MenuScreen er ikke ferdig implementert ennå da

Oppdatering 27/4:
Det meste ser ut til å fungere nå. jeg har ikke giddet å legge inn en måte der man kan ta av soundEffects ennå, men dette er jo superlett å gjøre egentlig ved å enten kjøre noen (sound != null)-tester i GameScreen et par ganger, eller eventuelt bare legge inn en .wav fil som er helt stille.
I MenuScreen står det også Music on/off, men egentlig så kan du bytte mellom flere sanger, der en av dem er null, så det er mulig å få off også. Labelen burde vært oppdatert, men det er finpirk.

Når man forsøker å kjøre MLG-mode så blir det litt lag ettersom alle animasjonene skal lages. Jeg vurderte å bare legge til en loadingScreen eller noe, for det ser litt ut som at spillet klikker, men det er altså OK. Det er også en liten bug der quickScope "lagger" litt den første gangen den skyter per match.


## Evt. erfaring fra code review
*(lærte du noe av å gå gjennom din eller andres kode?)*
Har foreløpig ikke gjort det

Oppdateringn 27/4:
Jeg har fortsatt ikke gjort det. Jeg har diskutert litt om med andre om hvordan man kan gjøre det med winConditions, og hørt om noen sine AI-planer. Men utenom det har ikke så mange sett på koden min. Jeg har piroritert å bruke mest tid på å lage den alene.

## Annet
*(er det noe du ville gjort annerledes?)*
Jeg ville ha skrevet mer kommentarer underveis.

Oppdatering 27/4:
Jeg ville nok lagt over libgdx prosjektet til git-repositoriet mye tidligere. Jeg var litt usikker i starten på om libgdx var lov, men skjønte etterhvert at det antageligvis måtte være det siden så mange andre gui-greier var lov. 

Det var fortsatt en veldig god idé å skrive tekstbasert versjon med genersik Grid og Rules først. De var jo fult brukbare i gui-versjonen også. Det liker jeg. 

Et par ting jeg vurderte å legge inn på slutten:
- En opening cutscene med titanic på blokkfløyte som såklart ville bygget forventningene i taket.
- Flere gameModes med litt forskjellige regler. Var litt redd for å begynne på det ettersom jeg var redd for å virkelig gjøre koden uleselig for retter.
- Flere mlg effekter og at de flytter seg rundt på skjermen, blir større og mindre. Og at de lager lyder random, som airhorn og hypescreams.
