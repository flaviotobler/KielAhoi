package controllers;

/**
 * Created by flavi on 14.12.2017.
 */
import org.junit.Test;
import play.Application;
import play.libs.F;
import play.mvc.Http.RequestBuilder;
import play.mvc.Result;
import play.test.*;
import static play.test.Helpers.*;
import play.test.TestBrowser;
import static org.junit.Assert.*;



public class KielAhoiTestApplication {
    public static Application fakeApp = fakeApplication();

    @Test
    public void testMap(){
        RequestBuilder request = Helpers.fakeRequest()
                .method(GET)
                .uri("/map");

        Result result = route(fakeApp, request);
        assertEquals(OK, result.status());


    }

    @Test
    public void testList(){
        RequestBuilder request = Helpers.fakeRequest()
                .method(GET)
                .uri("/list");

        Result result = route(fakeApp, request);
        assertEquals(OK, result.status());

    }


}

