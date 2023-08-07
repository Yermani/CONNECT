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
public class EventEntreprise extends Event {
    private String partenaires;

    public EventEntreprise() {
    }
    

    public EventEntreprise(String partenaires) {
        this.partenaires = partenaires;
    }

    public EventEntreprise(String partenaires, String paiement, int idevent, String nomEvent, String dateDeb, String dateFin, String description, String lieu, String organisateur, String type, String domaine) {
        super(paiement, idevent, nomEvent, dateDeb, dateFin, description, lieu, organisateur, type, domaine);
        this.partenaires = partenaires;
    }

  

    

   

    

    public String getPartenaires() {
        return partenaires;
    }

    public void setPartenaires(String partenaires) {
        this.partenaires = partenaires;
    }
    
}
