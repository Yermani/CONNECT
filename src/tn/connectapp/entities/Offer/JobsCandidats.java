/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.entities.Offer;

/**
 *
 * @author Thinkpad
 */
public class JobsCandidats {

    private String name;
    private String familyName;
    private String email;
    private String cv;

    public void setName(String name) {
        this.name = name;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getName() {
        return name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getEmail() {
        return email;
    }

    public String getCv() {
        return cv;
    }

    public JobsCandidats(String name, String familyName, String email) {
        this.name = name;
        this.familyName = familyName;
        this.email = email;
    }

    public JobsCandidats(String name, String familyName, String email, String cv) {
        this.name = name;
        this.familyName = familyName;
        this.email = email;
        this.cv = cv;
    }

}
