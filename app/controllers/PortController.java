package controllers;

import models.Port;
import models.PortRepository;

import play.data.FormFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import play.mvc.Controller;
import play.mvc.Result;

import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;

import static play.libs.Json.toJson;


// Um Pegel einzulesen:
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 *
 *
 */
@Singleton
public class PortController extends Controller {

    // fuer DB-Abfragen
    private final FormFactory formFactory;
    private final PortRepository portRepository;
    private final HttpExecutionContext ec;


    private final List<Port> portsTest = new ArrayList<>();
    private final Double heightkonstanz = 450.0;
    private final Double pegelkonstanz = 4.5;
    private double pegelRomanshorn;

    @Inject
    public PortController(FormFactory formFactory, PortRepository portRepository, HttpExecutionContext ec) {
        this.formFactory = formFactory;
        this.portRepository = portRepository;
        this.ec = ec;
    }


    // DB abfragen und alle Häfen als Liste anzeigen
    public CompletionStage<Result> getPorts() {
        return portRepository.list().thenApplyAsync(ports -> {
            for (Port port : ports) {
            System.out.println(port.getDescription());
            }
            return ok(views.html.list.render(ports));
        });
    }



    public CompletionStage<Result> addPort() {
        Port port = formFactory.form(Port.class).bindFromRequest().get();
        return portRepository.add(port).thenApplyAsync(
                p -> redirect(routes.PortController.index()
                ), ec.current());
    }






    public Result index() {
        return ok(views.html.index.render());
    }

    public Result list() {
        return ok(views.html.list.render(portsTest));
    }

    public Result map() { return ok(views.html.map.render(portsTest));}

    public Result ports(){ return ok(Json.toJson(portsTest));}




    public Result add() {
        portsTest.add(new Port(1, "KreuzlingenYachthafen", 47.654409, 9.183276, pegelkonstanz + heightkonstanz - 450.0, "CH", "Kreuzlingen"));
        portsTest.add(new Port(2, "Bregenz", 47.5024, 9.7362, pegelkonstanz + heightkonstanz - 453.0, "AU", "Bregenz"));
        portsTest.add(new Port(3, "Romanshorn", 47.5667, 9.3833, pegelkonstanz + heightkonstanz - 447.0, "CH", "Romanshorn"));
        return ok();
    }


/*    public Result layout() {
        return ok(views.html.layout.render());
    }*/

    // Pegel Romanshorn von Mess-Station laden
    public Result getPegel() {
        try {
            Document doc = Jsoup.connect("https://www.hydrodaten.admin.ch/de/tabelle-der-aktuellen-situation-der-abflusse-und-wasserstande.html").get();
            Elements values = doc.select("tr[data-station-id=2032] td:nth-of-type(4)");
            pegelRomanshorn = Double.parseDouble(values.text());
            System.out.println(pegelRomanshorn);
            /*for (Element elem : values) {
                System.out.println(elem.text());
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ok(views.html.index.render());
        //return pegelRomanshorn;
    }



}
