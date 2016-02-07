/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.client;

import com.google.gwt.user.client.Window;

/**
 *
 * @author mirash
 */
public class PageDispatcher {

    private PageDispatcher() {
    }

    private static class InstanceHolder {

        static final PageDispatcher INSTANCE = new PageDispatcher();
    }

    public static native String getAnchorHash() /*-{
     return $wnd.net.easysmarthouse.gwt.anchor.getHash();
     }-*/;

    private String getNewLocation(String currentLocation, Page currentPage, Page newPage) {
        return currentLocation.replaceFirst(currentPage.getLocation(), newPage.getLocation());

    }

    public void assign(String currentLocation, Page currentPage, Page newPage) {
        String newLocation = getNewLocation(currentLocation, currentPage, newPage);
        Window.Location.assign(newLocation);
    }

    public static PageDispatcher getInstance() {
        return InstanceHolder.INSTANCE;
    }
}
