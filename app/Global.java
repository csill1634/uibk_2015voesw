import java.util.List;

import models.Person;
import play.Application;
import play.GlobalSettings;
import play.libs.Yaml;
import play.db.ebean.Model;

import play.*;
import play.mvc.*;



import com.avaje.ebean.Ebean;

public class Global extends GlobalSettings {

    @Override
    public void onStart(Application app) {
        //Hinweis: Diese Funktion wird beim (Neu-)Start der Applikation
        // ausgeführt
        if (new Model.Finder(String.class, Person.class).findRowCount() == 0) {
            //if no persons exist; load data model from initial-data.yml
            Ebean.save((List<?>) Yaml.load("initial-data.yml"));
        }
    }
}
