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
public class invites {
     private int iinvites;
    private String evente ;
    private String user;

    public invites() {
    }

    public invites(int iinvites, String evente, String user) {
        this.iinvites = iinvites;
        this.evente = evente;
        this.user = user;
    }

    public int getIinvites() {
        return iinvites;
    }

    public String getEvente() {
        return evente;
    }

    public String getUser() {
        return user;
    }

    public void setIinvites(int iinvites) {
        this.iinvites = iinvites;
    }

    public void setEvente(String evente) {
        this.evente = evente;
    }

    public void setUser(String user) {
        this.user = user;
    }
    
    
}
