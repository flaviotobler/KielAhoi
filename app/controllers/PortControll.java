package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Port;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.*;
import scala.util.parsing.json.JSON;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

import static play.libs.Scala.asScala;

/**
 * An example of form processing.
 *
 * https://playframework.com/documentation/latest/JavaForms
 */
@Singleton
public class PortControll extends Controller {

    //private final Form<WidgetData> form;
    private final List<Port> ports = new ArrayList<>();
    private final Double heightkonstanz = 450.0;
    private final Double pegelkonstanz = 4.5;


    public Result index() {
        return ok(views.html.index.render());
    }

    public Result list() {
        return ok(views.html.list.render(ports));
    }

    public Result map() { return ok(views.html.map.render(ports));}

    public Result ports(){ return ok(Json.toJson(ports));}




    public Result add() {
        ports.add(new Port(1, "KreuzlingenYachthafen", 47.654409, 9.183276, pegelkonstanz + heightkonstanz - 450.0, "CH", "Kreuzlingen"));
        ports.add(new Port(2, "Bregenz", 47.5024, 9.7362, pegelkonstanz + heightkonstanz - 453.0, "AU", "Bregenz"));
        ports.add(new Port(3, "Romanshorn", 47.5667, 9.3833, pegelkonstanz + heightkonstanz - 447.0, "CH", "Romanshorn"));
        return ok();
    }


    public Result layout() {
        return ok(views.html.layout.render());
    }


}
