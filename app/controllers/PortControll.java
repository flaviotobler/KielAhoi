package controllers;

import models.Port;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;

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


    /*@Inject
    public PortControll(FormFactory formFactory) {
        this.form = formFactory.form(WidgetData.class);
        this.widgets = com.google.common.collect.Lists.newArrayList(
                new Widget("Data 1", 123),
                new Widget("Data 2", 456),
                new Widget("Data 3", 789)
        );
    }*/

    public Result index() {
        return ok(views.html.index.render());
    }

    /*public Result listWidgets() {
        return ok(views.html.listWidgets.render(asScala(widgets), form));
    }

    public Result createWidget() {
        final Form<WidgetData> boundForm = form.bindFromRequest();

        if (boundForm.hasErrors()) {
            play.Logger.ALogger logger = play.Logger.of(getClass());
            logger.error("errors = {}", boundForm.errors());
            return badRequest(views.html.listWidgets.render(asScala(widgets), boundForm));
        } else {
            WidgetData data = boundForm.get();
            widgets.add(new Widget(data.getName(), data.getPrice()));
            flash("info", "Widget added!");
            return redirect(routes.PortControll.listWidgets());
        }
    }*/
    public Result list() {
        return ok(views.html.list.render(ports));
    }

    public Result add() {
        ports.add(new Port(1, "KreuzlingenYachthafen", 47.654409, 9.183276, 450.0, "CH", "Kreuzlingen"));
        return ok();
    }

    public Result heightControll(Double height) {
        for(Port port: ports)
        {
            if(pegelkonstanz + heightkonstanz - port.height - height <= 0)
            {
                port.ok = false;
            }
            else
            {
                port.ok = true;
            }
        }

        return ok(views.html.list.render(ports));
    }

    public Result layout() {
        return ok(views.html.layout.render(null));
    }


}
