/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.entities.events;

/**
 *
 * @author Administrator
 */
public class Participant {
    private int idparticipant;
    public String Nom;
    public String Prenom;
      public String Email;
      public int Age;
      public String ecole;

    public Participant() {
    }

    public Participant(int idparticipant, String Prenom, String Nom,String Email, int Age,String ecole) {
        this.idparticipant = idparticipant;
        this.Prenom = Prenom;
        this.Email = Email;
        this.Age = Age;
        this.ecole= ecole;
        this.Nom=Nom;
    }

    public int getIdparticipant() {
        return idparticipant;
    }

    public String getEcole() {
        return ecole;
    }

    public void setEcole(String ecole) {
        this.ecole = ecole;
    }
    

    public String getNom() {
        return Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public String getEmail() {
        return Email;
    }

    public void setIdparticipant(int idparticipant) {
        this.idparticipant = idparticipant;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }
      
    
    
}
