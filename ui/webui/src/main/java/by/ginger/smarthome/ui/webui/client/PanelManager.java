/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.client;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Panel;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author rusakovich
 */
public class PanelManager {

    public static final PanelKey PANEL_KEY_DEFAULT = PanelKey.SIGNALING;

    public static enum PanelKey {

        SIGNALING("signaling"),
        MONITORING("monitoring"),
        SWITCHING("switching"),
        CAMERAS("cameras"),
        TRIGGER("trigger");
        private final String token;

        private PanelKey(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }
    }
    protected static final Map<PanelKey, Panel> panels = new HashMap<PanelKey, Panel>();

    public static Panel addPanel(PanelKey key) {
        final FlowPanel panel = new FlowPanel();
        panel.setVisible(true);

        panels.put(key, panel);
        return panel;
    }

    public static Panel getPanel(PanelKey key) {
        return panels.get(key);
    }

    public static PanelKey getPanelKeyByToken(String token) {
        if (token == null || token.trim().isEmpty()) {
            return null;
        }

        PanelKey[] keys = PanelKey.values();

        for (PanelKey panelKey : keys) {
            if (panelKey.getToken().equalsIgnoreCase(token)) {
                return panelKey;
            }
        }

        return null;
    }
}
