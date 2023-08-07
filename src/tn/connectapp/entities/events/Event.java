/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.entities.events;

import java.sql.Date;

/**
 *
 * @author Administrator
 */
public class Event {
    private int idevent;
    private String nomEvent;
    private String dateDeb;
    private String dateFin;
    private String description;
    private String lieu;
   private String organisateur;
    private String type;
    
    private String domaine;
    private String paiement;
    
   
    public Event() {
    }

    public Event(String paiement ,int idevent, String nomEvent, String dateDeb, String dateFin, String description, String lieu, String organisateur, String type,  String domaine) {
        this.idevent = idevent;
        this.nomEvent = nomEvent;
        this.dateDeb = dateDeb;
        this.dateFin = dateFin;
        this.description = description;
        this.lieu = lieu;
        this.organisateur = organisateur;
        this.type = type;
        
        this.domaine = domaine;
        this.paiement=paiement;
    }

    public void setPaiement(String paiement) {
        this.paiement = paiement;
    }

    public String getPaiement() {
        return paiement;
    }

    public int getIdevent() {
        return idevent;
    }

    public String getNomEvent() {
        return nomEvent;
    }

    public String getDateDeb() {
        return dateDeb;
    }

    public String getDateFin() {
        return dateFin;
    }

    public String getDescription() {
        return description;
    }

    public String getLieu() {
        return lieu;
    }

    public String getOrganisateur() {
        return organisateur;
    }

    public String getType() {
        return type;
    }

    
    public String getDomaine() {
        return domaine;
    }

    public void setIdevent(int idevent) {
        this.idevent = idevent;
    }

    public void setNomEvent(String nomEvent) {
        this.nomEvent = nomEvent;
    }

    public void setDateDeb(String dateDeb) {
        this.dateDeb = dateDeb;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setOrganisateur(String organisateur) {
        this.organisateur = organisateur;
    }

    public void setType(String type) {
        this.type = type;
    }

   

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }
    
    
    
}
