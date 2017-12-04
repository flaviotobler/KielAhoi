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

    private static double pegelRomanshorn;
    private static double tiefgang = 1.5; // default = 1.5m
    private static String ansicht = "map";



    @Inject
    public PortController(FormFactory formFactory, PortRepository portRepository, HttpExecutionContext ec) {
        this.formFactory = formFactory;
        this.portRepository = portRepository;
        this.ec = ec;
    }


    // DB abfragen und alle Häfen als Liste anzeigen
    public CompletionStage<Result> getList() {
        return portRepository.list().thenApplyAsync(ports -> {
            ansicht = "list";
            return ok(views.html.list.render(ports, getPegel(), tiefgang, ansicht));
        });
    }

    // Kartendarstellung anzeigen
    public CompletionStage<Result> getMap() {
        return portRepository.list().thenApplyAsync(ports -> {
            ansicht = "map";
            return ok(views.html.map.render(ports, getPegel(), tiefgang, ansicht));
        });
    }

    // Tiefgang aus Webseite (POST) einlesen
    public Result readTiefgang() {
        DynamicForm requestData = formFactory.form().bindFromRequest();
        tiefgang = Double.parseDouble(requestData.get("tiefgang"));
        ansicht = requestData.get("ansicht");
        System.out.println("Eingelesener Tiefgang: "+tiefgang);

        switch (ansicht) {
            case "map":
                return redirect(routes.PortController.getMap());
            case "list":
                return redirect(routes.PortController.getList());
            default:
                return redirect(routes.PortController.getMap());
        }
    }

    // Pegel Romanshorn von Mess-Station laden
    public Double getPegel() {
        try {
            Document doc = Jsoup.connect("https://www.hydrodaten.admin.ch/de/tabelle-der-aktuellen-situation-der-abflusse-und-wasserstande.html").get();
            Elements values = doc.select("tr[data-station-id=2032] td:nth-of-type(4)");
            pegelRomanshorn = Double.parseDouble(values.text());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pegelRomanshorn;
    }


/*    public CompletionStage<Result> addPort() {
        Port port = formFactory.form(Port.class).bindFromRequest().get();
        return portRepository.add(port).thenApplyAsync(
                p -> redirect(routes.PortController.index()
                ), ec.current());
    }
*/

}
