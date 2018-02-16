/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import mapping.Utilisateur; 

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.QueryBuilder;

import java.util.ArrayList;
import java.util.regex.Pattern;
import org.bson.types.ObjectId;

/**
 *
 * @author Mahatoky Jonathan
 */
public class UtilisateurDAO {
    
     public Utilisateur find(String pseudo,String motdepasse){
        Utilisateur utilisateur;
        Utilisateur[] resultat = null;
        MongoClient mongoClient = null;
        DB base = null;
        DBCursor cursor = null;
        DBCollection collection = null;
        DBObject requete = null;
        ArrayList find = null;
        try{
           mongoClient = new MongoClient(new MongoClientURI("mahatoky_jo:ramanoelina09070312@ds237808.mlab.com:37808/cloud"));
           base = mongoClient.getDB("cloud");
           collection = base.getCollection("utilisateur");
           requete =
                QueryBuilder.start().and(
                QueryBuilder.start("Pseudo").regex(Pattern.compile(pseudo)).get(),  
                QueryBuilder.start("MotDePasse").regex(Pattern.compile(motdepasse)).get()
                ).get();
            cursor = collection.find(requete);
            System.out.println(requete);
            find = new ArrayList();
            while(cursor.hasNext()){
                DBObject object = cursor.next();
                ObjectId iD = (ObjectId)(object.get("_id"));
                String pseudonyme = String.valueOf(object.get("Pseudo"));
                String motdePasse = String.valueOf(object.get("MotDePasse"));
                String email = String.valueOf(object.get("Email"));
                double classe = new Double(String.valueOf(object.get("Classe")));
                double score = new Double(String.valueOf(object.get("Score")));
                utilisateur = new Utilisateur(iD, pseudonyme, motdePasse, email,classe,score);
                find.add(utilisateur);
            }
            int i = find.size();
            resultat = new Utilisateur[i];
            System.out.println(i);
            find.toArray(resultat); 
        }
        catch(MongoException e){
            e.printStackTrace();
        }
        return resultat[0];
    }

    public Utilisateur[] find(String pseudo){
        Utilisateur utilisateur;
        Utilisateur[] resultat = null;
        MongoClient mongoClient = null;
        DB base = null;
        DBCursor cursor = null;
        DBCollection collection = null;
        DBObject requete = null;
        ArrayList find = null;
        try{
            mongoClient = new MongoClient(new MongoClientURI("mahatoky_jo:ramanoelina09070312@ds237808.mlab.com:37808/cloud"));
            base = mongoClient.getDB("cloud");
            collection = base.getCollection("utilisateur");
            cursor = collection.find(new BasicDBObject("Pseudo",pseudo));
            //System.out.println(requete);
            find = new ArrayList();
            while(cursor.hasNext()){
                DBObject object = cursor.next();
                ObjectId iD = (ObjectId)(object.get("_id"));
                String pseudonyme = String.valueOf(object.get("Pseudo"));
                String motdePasse = String.valueOf(object.get("MotDePasse"));
                String email = String.valueOf(object.get("Email"));
                double classe = new Double(String.valueOf(object.get("Classe")));
                double score = new Double(String.valueOf(object.get("Score")));
                utilisateur = new Utilisateur(iD, pseudonyme, motdePasse, email,classe,score);
                find.add(utilisateur);
            }
            int i = find.size();
            resultat = new Utilisateur[i];
            System.out.println(i);
            find.toArray(resultat); 
        }
        catch(MongoException e){
            e.printStackTrace();
        }
        return resultat;
    }

    public Utilisateur[] findByID(String iD){
        Utilisateur utilisateur;
        Utilisateur[] resultat = null;
        MongoClient mongoClient = null;
        DB base = null;
        DBCursor cursor = null;
        DBCollection collection = null;
        DBObject requete = null;
        ArrayList find = null;
        try{
            mongoClient = new MongoClient(new MongoClientURI("mahatoky_jo:ramanoelina09070312@ds237808.mlab.com:37808/cloud"));
            base = mongoClient.getDB("cloud");
            collection = base.getCollection("utilisateur");
            requete =
                QueryBuilder.start().and(
                QueryBuilder.start("_id").regex(Pattern.compile(iD)).get()  
                ).get();
            cursor = collection.find(requete);
            //System.out.println(requete);
            find = new ArrayList();
            while(cursor.hasNext()){
                DBObject object = cursor.next();
                ObjectId _iD = (ObjectId)(object.get("_id"));
                String pseudonyme = String.valueOf(object.get("Pseudo"));
                String motdePasse = String.valueOf(object.get("MotDePasse"));
                String email = String.valueOf(object.get("Email"));
                double classe = new Double(String.valueOf(object.get("Classe")));
                double score = new Double(String.valueOf(object.get("Score")));
                utilisateur = new Utilisateur(_iD, pseudonyme, motdePasse, email,classe,score);
                find.add(utilisateur);
            }
            int i = find.size();
            resultat = new Utilisateur[i];
            System.out.println(i);
            find.toArray(resultat); 
        }
        catch(MongoException e){
            e.printStackTrace();
        }
        return resultat;
    }

    public String insert(Utilisateur utilisateur){
        String pseudo = utilisateur.getPseudo();
        String password = utilisateur.getMotDePasse();
        String email = utilisateur.getMail();
        double classe = utilisateur.getClasse();
        double score = utilisateur.getScore();

        MongoClient mongoClient = null;
        DB base = null;
        DBCollection collection = null;
        BasicDBObject document = null;

        try{
            mongoClient = new MongoClient(new MongoClientURI("mahatoky_jo:ramanoelina09070312@ds237808.mlab.com:37808/cloud"));
            base = mongoClient.getDB("cloud");
            collection = base.getCollection("utilisateur");
            document = new BasicDBObject();
            document.put("Pseudo", pseudo);
            document.put("MotDePasse",password);
            document.put("Email",email);
            document.put("Classe",classe);
            document.put("Score",score);
            collection.insert(document);
        }
        catch(MongoException e){
            e.printStackTrace();
        }
        return "INSCRIPTION REUSSIE";
    }

    public String update(Utilisateur utilisateur){

        MongoClient mongoClient = null;
        DB base = null;
        DBCollection collection = null;
        BasicDBObject document = null;

        try{
            mongoClient = new MongoClient(new MongoClientURI("mahatoky_jo:ramanoelina09070312@ds237808.mlab.com:37808/cloud"));
            base = mongoClient.getDB("cloud");
            collection = base.getCollection("utilisateur");
            BasicDBObject newDocument =
                    new BasicDBObject().append("$inc",new BasicDBObject().append("Score",utilisateur.getScore()));
            collection.update(new BasicDBObject().append("Pseudo",utilisateur.getPseudo()),newDocument);

        }
        catch(MongoException e){
            e.printStackTrace();
        }
        return "UPDATE SCORE REUSSIE";
    }
    
}
