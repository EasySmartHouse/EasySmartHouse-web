/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.client;

/**
 *
 * @author mirash
 */
public enum Page {

    LOGIN("login.html"),
    WEBUI("webui.html");
    private final String location;

    private Page(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
}
