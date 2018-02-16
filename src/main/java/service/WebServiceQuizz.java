package service;

import com.google.gson.Gson;
import dao.*;
import mapping.*;
import static spark.Spark.*;

public class WebServiceQuizz {
    public void inscription()
    {
        Gson gson = new Gson();
        post("/add-utilisateur", (req,res)->{
            res.type("application/json");
            Utilisateur utilisateur = gson.fromJson(req.body(), Utilisateur.class);
            UtilisateurDAO dao = new UtilisateurDAO();
            System.out.println(utilisateur.getPseudo());
            return dao.insert(utilisateur);}, gson ::toJson);
    }
    public void verifier(){
        Gson gson = new Gson();
        post("/find-utilisateur", (req,res)->{
            res.type("application/json");
            Utilisateur utilisateur = gson.fromJson(req.body(), Utilisateur.class);
            //System.out.println(s.getPass());
            return new UtilisateurDAO().find(utilisateur.getPseudo(), utilisateur.getMotDePasse());
        }, gson ::toJson);
    }
    public void question(){
        Gson gson = new Gson();
        get("/find-question", (req,res)->{
            res.type("application/json");
            //System.out.println(s.getPass());
            return new QuizzDAO().find();
        }, gson ::toJson);
    }
    public void score(){
        Gson gson = new Gson();
        put("/update-score", (req,res)->{
            res.type("application/json");
            Utilisateur utilisateur = gson.fromJson(req.body(), Utilisateur.class);
            //System.out.println(s.getPass());

            return new UtilisateurDAO().update(utilisateur);
        }, gson ::toJson);
    }
}
