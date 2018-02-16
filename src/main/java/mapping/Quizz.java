package mapping;

import mapping.*;
import org.bson.types.ObjectId;
import org.json.*;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 *
 * @author Mahatoky Jonathan
 */
@Entity
public class Quizz{
    @Id
    ObjectId iD;
    double numero;
    String question;
    JSONArray reponse;
    String categorie;

    public Quizz(ObjectId iD,double numero,String question,JSONArray reponse,String categorie){
        setID(iD);
        setNumero(numero);
        setQuestion(question);
        setReponse(reponse);
        setCategorie(categorie);
    }
    public Quizz(double numero,String question,JSONArray reponse,String categorie){
        setNumero(numero);
        setQuestion(question);
        setReponse(reponse);
        setCategorie(categorie);
    }

    public ObjectId getID(){
        return iD;
    }
    public double getNumero(){
        return numero;
    }
    public String getQuestion(){
        return question;
    }
    public JSONArray getReponse(){
        return reponse;
    }

    public String getCategorie(){
        return categorie;
    }
    public void setID(ObjectId iD){
        this.iD = iD; 
    }
    public void setNumero(double numero){
        this.numero = numero;
    }
    public void setQuestion(String question){
        this.question = question;
    }
    public void setReponse(JSONArray reponse){
        this.reponse = reponse;
    }
    public void setCategorie(String categorie){
        this.categorie = categorie;
    }
}