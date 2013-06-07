/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv243.mymaps.entities;

/**
 *
 * @author Kuba
 */
public enum Role {

    User("User"),
    Admin("Admin");
    
    private final String label;

    private Role(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
