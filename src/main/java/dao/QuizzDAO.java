/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import com.mongodb.*;
import mapping.*;
import java.util.*;
import java.util.regex.Pattern;

import org.bson.types.*;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *
 * @author Mahatoky Jonathan
 */

public class QuizzDAO{

    public Quizz[] find(){
        Quizz quizz;
        Quizz[] resultat = null;
        MongoClient mongoClient = null;
        DB base = null;
        DBCursor cursor = null;
        DBCollection collection = null;
        DBObject requete = null;
        ArrayList find = null;
        try{
            mongoClient = new MongoClient(new MongoClientURI("mahatoky_jo:ramanoelina09070312@ds237808.mlab.com:37808/cloud"));
            base = mongoClient.getDB("cloud");
            collection = base.getCollection("quizz");
            //requete = QueryBuilder.start().get();
            cursor = collection.find();
            //System.out.print(requete);
            find = new ArrayList();
            while(cursor.hasNext()){
                DBObject object = cursor.next();
                ObjectId iD = (ObjectId)(object.get("_id"));
                double numero = new Double(String.valueOf(object.get("Numero")));
                String question = String.valueOf(object.get("Question"));
                JSONArray reponse = new JSONArray(String.valueOf(object.get("Reponse")));
                String categorie = String.valueOf((object.get("Categorie")));
                quizz = new Quizz(iD,numero, question, reponse, categorie);
                find.add(quizz);
            }
            int i = find.size();
            resultat = new Quizz[i];
            System.out.println(i);
            find.toArray(resultat); 
        }
        catch(MongoException e){
            e.printStackTrace();
        }
        catch(JSONException e){
            e.printStackTrace();
        }
        return resultat;
    }
    public Quizz[] find(String q){
        Quizz quizz;
        Quizz[] resultat = null;
        MongoClient mongoClient = null;
        DB base = null;
        DBCursor cursor = null;
        DBCollection collection = null;
        DBObject requete = null;
        ArrayList find = null;
        try{
            mongoClient = new MongoClient(new MongoClientURI("mahatoky_jo:ramanoelina09070312@ds237808.mlab.com:37808/cloud"));
            base = mongoClient.getDB("cloud");
            collection = base.getCollection("quizz");
            //requete = QueryBuilder.start("Question").regex(Pattern.compile(q)).get();
            cursor = collection.find(new BasicDBObject("Question", q));
            System.out.print(requete);
            find = new ArrayList();
            while(cursor.hasNext()){
                DBObject object = cursor.next();
                ObjectId iD = (ObjectId)(object.get("_id"));
                double numero = new Double(String.valueOf(object.get("Numero")));
                String question = String.valueOf(object.get("Question"));
                JSONArray reponse = new JSONArray(String.valueOf(object.get("Reponse")));
                String categorie = String.valueOf((object.get("Categorie")));
                quizz = new Quizz(iD,numero, question, reponse, categorie);
                find.add(quizz);
            }
            int i = find.size();
            resultat = new Quizz[i];
            System.out.println(i);
            find.toArray(resultat); 
        }
        catch(MongoException e){
            e.printStackTrace();
        }
        catch(JSONException e){
            e.printStackTrace();
        }
        return resultat;
    }

}