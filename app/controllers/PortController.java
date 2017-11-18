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

import play.mvc.Controller;
import play.mvc.Result;

import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;

import static play.libs.Json.toJson;


/**
 *
 *
 *
 */
@Singleton
public class PortController extends Controller {


    private final FormFactory formFactory;
    private final PortRepository portRepository;
    private final HttpExecutionContext ec;

    @Inject
    public PortController(FormFactory formFactory, PortRepository portRepository, HttpExecutionContext ec) {
        this.formFactory = formFactory;
        this.portRepository = portRepository;
        this.ec = ec;
    }


    public CompletionStage<Result> getPorts() {
        return portRepository.list().thenApplyAsync(
                portStream -> ok(toJson(portStream.collect(Collectors.toList()))
                ), ec.current());
    }

    public CompletionStage<Result> addPort() {
        Port port = formFactory.form(Port.class).bindFromRequest().get();
        return portRepository.add(port).thenApplyAsync(
                p -> redirect(routes.PortController.index()
                ), ec.current());
    }




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
