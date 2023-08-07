/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Administrator
 */
public class Paiement {
    private int idp;
    private String email;
     private int  carte;
      private int tel;
       private String idevente;
        private String ideventc;
         private String iduser;

    public Paiement(int idp, String email, int carte, int tel, String idevente, String ideventc, String iduser) {
        this.idp = idp;
        this.email = email;
        this.carte = carte;
        this.tel = tel;
        this.idevente = idevente;
        this.ideventc = ideventc;
        this.iduser = iduser;
    }

    public Paiement() {
    }

    public int getIdp() {
        return idp;
    }

    public String getEmail() {
        return email;
    }

    public int getCarte() {
        return carte;
    }

    public int getTel() {
        return tel;
    }

    public String getIdevente() {
        return idevente;
    }

    public String getIdeventc() {
        return ideventc;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCarte(int carte) {
        this.carte = carte;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public void setIdevente(String idevente) {
        this.idevente = idevente;
    }

    public void setIdeventc(String ideventc) {
        this.ideventc = ideventc;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }
         
      
    
}
