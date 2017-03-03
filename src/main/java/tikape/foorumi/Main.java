package tikape.foorumi;

import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import spark.Spark;
import static spark.Spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.foorumi.database.Database;
import tikape.foorumi.domain.Alue;
import tikape.foorumi.domain.Keskustelu;
import tikape.foorumi.palvelin.Domain;

public class Main {

    public static void main(String[] args) throws Exception {
        Spark.staticFileLocation("/public");
        Database database = new Database("jdbc:sqlite:foorumi.db");
        database.init();
        Domain domain = new Domain(database);
        
        if (System.getenv("PORT") != null) {
            port(Integer.valueOf(System.getenv("PORT")));
        }

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
            Alue a = domain.haeAlue(alueId);
            map.put("alueId", alueId);
            int sivu = 1;
            try{
                sivu = Integer.parseInt(req.queryParams("sivu"));
            }catch(Exception e){
            }
            List<Keskustelu> keskustelut = domain.haeKeskustelutAlueella(alueId);
            boolean ohitus = false;
            if(keskustelut.size()<=(sivu-1)*10){
                if(keskustelut.isEmpty()){
                    ohitus = true;
                }else{
                    sivu = 1;
                }
            }
            if(!ohitus){
                if(keskustelut.size()>=sivu*10){
                    keskustelut = keskustelut.subList((sivu-1)*10, sivu*10);
                }else{
                    keskustelut = keskustelut.subList((sivu-1)*10, keskustelut.size());
                }
            }
            map.put("keskustelut", keskustelut);
            map.put("alueOtsikko", a.getOtsikko());
            map.put("alueKuvaus", a.getKuvaus());
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
            Keskustelu k = domain.haeKeskustelu(keskusteluId);
            Alue a = domain.haeAlue(alueId);
            map.put("viestit", domain.haeViestitKeskustelulla(keskusteluId));
            map.put("alueId", alueId);
            map.put("keskusteluId", keskusteluId);
            map.put("keskusteluOtsikko", k.getOtsikko());
            map.put("alueOtsikko", a.getOtsikko());
            
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
