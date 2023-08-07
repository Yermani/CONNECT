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
public class EventClub extends Event {
    private String sponsoring;

    public EventClub(String sponsoring) {
        this.sponsoring = sponsoring;
    }

    public EventClub(String sponsoring, String paiement, int idevent, String nomEvent, String dateDeb, String dateFin, String description, String lieu, String organisateur, String type, String domaine) {
        super(paiement, idevent, nomEvent, dateDeb, dateFin, description, lieu, organisateur, type, domaine);
        this.sponsoring = sponsoring;
    }

   

    

    public EventClub() {
    }

   

   

    public String isSponsoring() {
        return sponsoring;
    }

    public void setSponsoring(String sponsoring) {
        this.sponsoring = sponsoring;
    }
    
    
    
}
