/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.client.rpc;

import net.easysmarthouse.provider.device.trigger.Trigger;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.List;

/**
 *
 * @author mirash
 */
@RemoteServiceRelativePath("trigger")
public interface TriggerService extends RemoteService{

    public List<Trigger> getTriggers();

    public void setEnabled(String name, boolean enabled);
}
