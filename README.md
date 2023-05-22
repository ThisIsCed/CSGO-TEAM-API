# CS:GO-Teamviewer

Als Pos-Projket hab ich einen "Teamviewer" für [CS:GO](https://www.counter-strike.net/news) programmiert, hierbei kann man diese Teams selber erstellen, editieren, löschen und anschauen. Jedes Team hat die selben Attribute. Für die technische Umsetzung habe ich  [Spring-Boot](https://spring.io/projects/spring-boot) für die API gewählt da wir schon mit dieser in DBI gearbeitet haben, für den Webclient nutze ich [Blazor](https://dotnet.microsoft.com/en-us/apps/aspnet/web-apps/blazor), die WPF-App wurde fest vorgegeben und um die Daten persistent zu speichert habe ich [MongoDB](https://www.mongodb.com/) ausgesucht. Zur Umsetzung habe ich folgende Programme genutzt: 
- [Intelij IDE](https://www.jetbrains.com/de-de/idea/) v.2023.1.2
- [Visuel Studio](https://visualstudio.microsoft.com/de/) v.2022 17.6
- [Postman](https://www.postman.com/) v.10.14
- [MongoDB Compass](https://www.mongodb.com/products/compass) v. 1.36.4


##	Spring-API
Die API besteht aus drei Klassen und einem Interface. 
Die Klassen sind:
- Team
- TeamService
- TeamController

Das Interface ist:
-	TeamRepository

###	Team
Das Team bildet die Grundklasse in der die Attribute meiner Teams festgelegt sind. Jedes Team besitzt somit folgente Attribute: eine ID, Namen, Location, Region, Founders, Players und eine URL für das Logo. **Bild** Man muss die Annotationen über Team und den Attributen hervorheben, diese dienen der Mongodb. **@Document(collection = "Teams")** ist dazu da, damit die Datenbank später weis wo die Einträge gespeichert werden sollen. **@Id** signalisiert der DB dass das darunter liegente Attribute die Id ist. **@Field("xxx")** zeigt auf dass das darunterliegente Feld ein Attribute ist das auch so später gespeichert wird. Dies schaut dann wie folgt in MongoDB Compass aus:
**Bild**

###	TeamService
TeamService bildet die grundlegenden Funktionen für die API, dort werden diese Vorgegeben. Mit der Annotation **@Autowired** stellt man eine Verbindung mit dem Repository her. Das **@Service** signalisiert das diese Klasse einen Service abbildet.
**Bild**

###	TeamController	
Im TeamController werden nun die Routen gestalltet. Diese Routen werden später zum für die CRUD-Funktionen genutzt. Als Beispiel die Route **/add/team** wenn man diese später in Postman anspricht werden alle existierenden Teams aus der Datenbank zurückgegeben. Hierbei ist die Annotation **@RequestMapping**, **@PostMapping**,**@PutMapping** und **@DeleteMapping** wichtig denn diese Regeln wie HTTP-Request gehandhabt wird.
> **Note:** Die Oben gelisteten Annotationen sind eine verkürzte Form.

**Bild**
Die Annotation **@RestController** zeigt nur das diese Klasse als Controller behandelt werden soll und die Andere **@CrossOrigin** dient später für die Web App damit man nicht in einen Error hineinläuft.

### TeamRepository
Dies ist ein Interface das durch MongoRepository extendet wird. Dieses Interface dient dazu für die grundlegenden CRUD-Funktionen. In meinem Fall ist es das MongoRepository, da ich die MongoDB als Datenbank nutze.
**Bild**

##	WPF	
Die WPF-App besteht aus einer Klasse, der Teams.cs und einer xaml Datei, der MainWindow.xaml, diese verfügt auch über eine C# Datei die die funktionalität hinter der .xaml Datei darstellt.
