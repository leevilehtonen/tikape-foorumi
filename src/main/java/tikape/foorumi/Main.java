package tikape.foorumi;

import java.util.HashMap;
import spark.ModelAndView;
import spark.Spark;
import static spark.Spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.foorumi.database.Database;
import tikape.foorumi.palvelin.Domain;

public class Main {

    public static void main(String[] args) throws Exception {
        Spark.staticFileLocation("/public");
        Database database = new Database("jdbc:sqlite:foorumi.db");
        database.init();
        Domain domain = new Domain(database);

        get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("alueet", domain.haeAlueet());
            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());
        
        post("/", (req, res) -> {
            String nimi = req.queryParams("nimi");
            String kuvaus = req.queryParams("kuvaus");
            domain.lisaaAlue(nimi,kuvaus);
            res.redirect("/");
            return "";
        });
        
        get("/alue/:id", (req, res) -> {
            HashMap map = new HashMap<>();
            int alueId = Integer.parseInt(req.params(":id"));
            map.put("alueId", alueId);
            map.put("keskustelut", domain.haeKeskustelutAlueella(alueId));
            return new ModelAndView(map, "alue");
        }, new ThymeleafTemplateEngine());
        
        post("/alue/:alue", (req, res) -> {
            int alueId = Integer.parseInt(req.params(":alue"));
 
            String nimi = req.queryParams("nimi");
            String nimimerkki = req.queryParams("nimimerkki");
            String viesti = req.queryParams("viesti");
            
            domain.lisaaKeskustelu(alueId, nimi);
            int keskusteluId = domain.haeKeskusteluNimellä(nimi).getId();
            domain.lisaaViesti(alueId,keskusteluId,nimimerkki,viesti);
            res.redirect("/alue/"+alueId+"/keskustelu/"+keskusteluId);
            return "";
        });
        
        get("/alue/:alue/keskustelu/:id", (req, res) -> {
            HashMap map = new HashMap<>();
            int alueId = Integer.parseInt(req.params(":alue"));
            int keskusteluId = Integer.parseInt(req.params(":id"));
            map.put("viestit", domain.haeViestitKeskustelulla(keskusteluId));
            map.put("alueId", alueId);
            map.put("keskusteluId", keskusteluId);
            return new ModelAndView(map, "keskustelu");
        }, new ThymeleafTemplateEngine());
        
        post("/alue/:alue/keskustelu/:id", (req, res) -> {
            int keskusteluId = Integer.parseInt(req.params(":id"));
            int alueId = Integer.parseInt(req.params(":alue"));
            String nimimerkki = req.queryParams("nimimerkki");
            String sisalto = req.queryParams("sisalto");
            domain.lisaaViesti(alueId,keskusteluId,nimimerkki,sisalto);
            res.redirect("/alue/"+alueId+"/keskustelu/"+keskusteluId);
            return "";
        });
    }
}
