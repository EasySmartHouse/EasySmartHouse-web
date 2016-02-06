/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.client.view;

import by.ginger.smarthome.ui.webui.client.view.widget.WelcomeLabel;
import by.ginger.smarthome.ui.i18n.Root;
import java.util.HashMap;
import java.util.Map;

import by.ginger.smarthome.ui.webui.client.PanelManager;
import by.ginger.smarthome.ui.webui.client.PanelManager.PanelKey;
import by.ginger.smarthome.ui.webui.client.controller.RootController.RootControllerAction;
import by.ginger.smarthome.ui.webui.client.history.HistoryChangeListener;
import by.ginger.smarthome.ui.webui.client.messages.MessagesHolder;
import by.ginger.smarthome.ui.webui.client.view.handler.LogoutHandler;
import com.google.gwt.event.dom.client.ClickEvent;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwtmvc.client.Controller;
import com.googlecode.gwtmvc.client.ModelForView;
import com.googlecode.gwtmvc.client.MvcEvent;
import com.googlecode.gwtmvc.client.View;
import java.util.Set;

/**
 *
 * @author rusakovich
 */
public class RootView extends View implements HistoryChangeListener {

    private static final int DEFAULT_TAB_INDEX = 0;
    private Root rootMessages = MessagesHolder.getInstance().getRootMessages();
    private VerticalPanel rootPanel = new VerticalPanel();
    private DecoratedTabPanel tabPanel = new DecoratedTabPanel();
    private Map<Integer, PanelKey> tabsIndexes;

    public RootView(Controller controller) {
        super(ViewId.ROOT_VIEW_ID, controller);
        this.initRootPanel();
    }

    @Override
    public void onModelChange(ModelForView model) {
    }

    private void initRootPanel() {
        this.add(rootPanel);

        WelcomeLabel welcomeLabel = new WelcomeLabel(rootMessages.labelTextWelcome());
        RootPanel.get("welcome").add(welcomeLabel);

        Hyperlink logoutLink = new Hyperlink();
        logoutLink.setText(rootMessages.hyperlinkTextLogout());
        logoutLink.addDomHandler(new LogoutHandler(), ClickEvent.getType());
        RootPanel.get("logout").add(logoutLink);

        HorizontalPanel headerPanel = new HorizontalPanel();
        rootPanel.add(headerPanel);

        tabsIndexes = new HashMap<Integer, PanelKey>();

        final Panel signalingPanel = PanelManager.addPanel(PanelKey.SIGNALING);
        tabPanel.add(signalingPanel, rootMessages.panelTabsTitleSignaling());
        tabsIndexes.put(0, PanelKey.SIGNALING);

        final Panel monitoringPanel = PanelManager.addPanel(PanelKey.MONITORING);
        tabPanel.add(monitoringPanel, rootMessages.panelTabsTitleClimate());
        tabsIndexes.put(1, PanelKey.MONITORING);

        final Panel switchingPanel = PanelManager.addPanel(PanelKey.SWITCHING);
        tabPanel.add(switchingPanel, rootMessages.panelTabsTitleLight());
        tabsIndexes.put(2, PanelKey.SWITCHING);

        final Panel devicePanel = PanelManager.addPanel(PanelKey.CAMERAS);
        tabPanel.add(devicePanel, rootMessages.panelTabsTitleCameras());
        tabsIndexes.put(3, PanelKey.CAMERAS);

        final Panel triggerPanel = PanelManager.addPanel(PanelKey.TRIGGER);
        tabPanel.add(triggerPanel, rootMessages.panelTabsTitleTriggers());
        tabsIndexes.put(4, PanelKey.TRIGGER);

        fireHistoryChange(History.getToken());

        tabPanel.addSelectionHandler(new SelectionHandler<Integer>() {
            @Override
            public void onSelection(SelectionEvent<Integer> event) {
                final Integer item = event.getSelectedItem();
                final PanelKey key = tabsIndexes.get(item);

                controller.call(new MvcEvent<PanelKey>(RootControllerAction.TAB_SELECTED, key));
            }
        });

        rootPanel.add(tabPanel);
        rootPanel.setCellHorizontalAlignment(tabPanel, HasHorizontalAlignment.ALIGN_CENTER);
    }

    @Override
    public Widget createWidget() {
        return rootPanel;
    }

    @Override
    public void onRender() {
        gwtWrap();
    }

    @Override
    public void fireHistoryChange(String token) {
        PanelKey panelKey = PanelManager.getPanelKeyByToken(token);

        if (panelKey != null) {
            Set<Integer> tabNumbers = tabsIndexes.keySet();
            for (Integer index : tabNumbers) {
                if (panelKey == tabsIndexes.get(index)) {
                    tabPanel.selectTab(index);
                }
            }
        } else {
            tabPanel.selectTab(DEFAULT_TAB_INDEX);
        }

    }

    private static native void gwtWrap() /*-{
     $wnd.gwtWrap();
     }-*/;
}
