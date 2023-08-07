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
public class Participant_Event {
    private int idparticipation;
    private Event event;
    private Participant participant;

    public Event getEvent() {
        return event;
    }

    public Participant getParticipant() {
        return participant;
    }

    public int getIdparticipation() {
        return idparticipation;
    }

    public void setIdparticipation(int idparticipation) {
        this.idparticipation = idparticipation;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public Participant_Event(int idparticipation, Event event, Participant participant) {
        this.idparticipation = idparticipation;
        this.event = event;
        this.participant = participant;
    }

    public Participant_Event() {
    }
    
    
}
