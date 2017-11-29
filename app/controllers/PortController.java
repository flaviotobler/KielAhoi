package controllers;

import models.PortRepository;


import play.data.DynamicForm;
import play.data.FormFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

import java.util.concurrent.CompletionStage;

import play.mvc.Controller;
import play.mvc.Result;

import play.libs.concurrent.HttpExecutionContext;


// Um Pegel einzulesen:
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import scala.Long;


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




    //private static List<Port> portsTest = new ArrayList<>();
//    private final Double heightkonstanz = 450.0;
//    private final Double pegelkonstanz = 4.5;
    private static double pegelRomanshorn;
    private static double tiefgang;

    @Inject
    public PortController(FormFactory formFactory, PortRepository portRepository, HttpExecutionContext ec) {
        this.formFactory = formFactory;
        this.portRepository = portRepository;
        this.ec = ec;
    }


    // DB abfragen und alle Häfen als Liste anzeigen
    public CompletionStage<Result> getList() {
        return portRepository.list().thenApplyAsync(ports -> {
/*            for (Port port : ports) {
            System.out.println(port.getDescription());
            }*/
            return ok(views.html.list.render(ports, getPegel()));
        });
    }


    public CompletionStage<Result> getMap() {
        return portRepository.list().thenApplyAsync(ports -> {
/*            for (Port port : ports) {
            System.out.println(port.getDescription());
            }*/
            return ok(views.html.map.render(ports, getPegel()));
        });
    }



    // *****************************************************************************************





    public Result readTiefgang() {
        DynamicForm requestData = formFactory.form().bindFromRequest();
        String tiefgang = requestData.get("tiefgang");

        System.out.println("Das wurde eingelesen: "+tiefgang);
        //tiefgang = 2.5;
        return ok("Hello " + tiefgang);
    }








    // *****************************************************************************************



/*    public CompletionStage<Result> addPort() {
        Port port = formFactory.form(Port.class).bindFromRequest().get();
        return portRepository.add(port).thenApplyAsync(
                p -> redirect(routes.PortController.index()
                ), ec.current());
    }*/








/*    public Result add() {
        portsTest.add(new Port(1, "KreuzlingenYachthafen", 47.654409, 9.183276, pegelkonstanz + heightkonstanz - 450.0, "CH", "Kreuzlingen"));
        portsTest.add(new Port(2, "Bregenz", 47.5024, 9.7362, pegelkonstanz + heightkonstanz - 453.0, "AU", "Bregenz"));
        portsTest.add(new Port(3, "Romanshorn", 47.5667, 9.3833, pegelkonstanz + heightkonstanz - 447.0, "CH", "Romanshorn"));
        return ok();
    }*/


    // Pegel Romanshorn von Mess-Station laden
    public Double getPegel() {
        try {
            Document doc = Jsoup.connect("https://www.hydrodaten.admin.ch/de/tabelle-der-aktuellen-situation-der-abflusse-und-wasserstande.html").get();
            Elements values = doc.select("tr[data-station-id=2032] td:nth-of-type(4)");
            pegelRomanshorn = Double.parseDouble(values.text());
            //System.out.println(pegelRomanshorn);
            /*for (Element elem : values) {
                System.out.println(elem.text());
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pegelRomanshorn;
    }



}
