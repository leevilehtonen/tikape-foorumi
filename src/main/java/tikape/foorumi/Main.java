package tikape.foorumi;

import java.util.HashMap;
import spark.ModelAndView;
import spark.Spark;
import static spark.Spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.foorumi.database.Database;

public class Main {

    public static void main(String[] args) throws Exception {
        Spark.staticFileLocation("/public");
        Database database = new Database("jdbc:sqlite:foorumi.db");
        database.init();

        get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());
        
        get("/alue", (req, res) -> {
            HashMap map = new HashMap<>();
            return new ModelAndView(map, "alue");
        }, new ThymeleafTemplateEngine());
    }
}
