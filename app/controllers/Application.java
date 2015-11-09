package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

import models.Person;

import play.data.Form;

import java.util.List;

import play.db.ebean.Model;

import static play.libs.Json.*;

public class Application extends Controller {

    public static Result index() {
        /* String user = session("connected");
        if(user != null) {
            return ok("Hello " + user);
        } else {
            return unauthorized("Oops, you are not connected");
        }*/
        return ok(index.render());
    }

    public static Result addPerson() {
        Person person = Form.form(Person.class).bindFromRequest().get();
        person.save();
        return redirect(routes.Application.index());
    }

    public static Result getPersons() {
        List<Person> persons = new Model.Finder(String.class, Person.class).all();
        return ok(toJson(persons));
    }

    public static Result login(){
        session("connected", "user@gmail.com");
        return ok("Welcome!");
    }
    public static Result logout(){
        session().clear();
        return ok("Bye");
    }
}
