/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.client.view.widget;

import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Text;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;

/**
 *
 * @author mirash
 */
public class ErrorMessage extends Composite {

    private HTML htmlWidget;
    
    private Element createElement(String header, String message){
        Element divElement = DOM.createDiv();
        DivElement alertDiv = DivElement.as(divElement);
        
        alertDiv.addClassName("alert");
        alertDiv.addClassName("alert-danger");
        alertDiv.addClassName("fade in");
        
        Element anchorElement = DOM.createAnchor();
        AnchorElement errorAnchor = AnchorElement.as(anchorElement);
        errorAnchor.setHref("#");
        errorAnchor.addClassName("close");
        errorAnchor.setAttribute("data-dismiss", "alert");
        errorAnchor.setInnerHTML("&times;");
        
        alertDiv.appendChild(anchorElement);
        
        SafeHtmlBuilder contentBuiler = new SafeHtmlBuilder();
        contentBuiler.appendHtmlConstant("<strong>")
                .appendEscaped(header)
                .appendHtmlConstant("</strong> ")
                .appendEscaped(message);
        
        HTML content = new HTML(contentBuiler.toSafeHtml());
        alertDiv.appendChild(content.getElement());
        
        return alertDiv;
    }

    public ErrorMessage(String header, String message) {
        Element elem = createElement(header, message);
        this.htmlWidget = HTML.wrap(elem);
        this.initWidget(htmlWidget);
    }
    
}
