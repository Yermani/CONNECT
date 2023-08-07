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
public class Model {
    private String email;
    private String idevent;
    private String pai;

    public Model() {
    }

    public String getPai() {
        return pai;
    }

    public void setPai(String pai) {
        this.pai = pai;
    }
    

    public String getEmail() {
        return email;
    }

    public String getIdevent() {
        return idevent;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIdevent(String idevent) {
        this.idevent = idevent;
    }
    
    
}
