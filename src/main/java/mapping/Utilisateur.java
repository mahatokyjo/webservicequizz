/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapping;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 *
 * @author Mahatoky Jonathan
 */

@Entity
public class Utilisateur {
    @Id
    ObjectId iD;
    String pseudo;
    String motdepasse;
    String email;
    double classe;
    double score;

    public Utilisateur(ObjectId iD,String pseudo,String motdepasse,String email,double classe,double score){
        setID(iD);
        setPseudo(pseudo);
        setMotDePasse(motdepasse);
        setMail(email);
        setClasse(classe);
        setScore(score);
    }
    public Utilisateur(String pseudo,String motdepasse,String email,double classe,double score){
        setPseudo(pseudo);
        setMotDePasse(motdepasse);
        setMail(email);
        setClasse(classe);
        setScore(score);
    }

    public ObjectId getID(){
        return iD;
    }
    public String getPseudo(){
        return pseudo;
    }
    public String getMotDePasse(){
        return motdepasse;
    }
    public String getMail(){
        return email;
    }
    public double getClasse(){
        return classe;
    }
    public double getScore(){
        return score;
    }

    public void setID(ObjectId iD){
        this.iD = iD;
    }
    public void setPseudo(String pseudo){
        this.pseudo = pseudo;
    }
    public void setMotDePasse(String motdepasse){
        this.motdepasse = motdepasse;
    }
    public void setMail(String email){
        this.email = email;
    }
    public void setClasse(double classe){
        this.classe = classe;
    }
    public void setScore(double score){
        this.score = score;
    }
    
}
