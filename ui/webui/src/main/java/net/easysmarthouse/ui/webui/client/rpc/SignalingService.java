/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.client.rpc;

import net.easysmarthouse.provider.device.alarm.SignalingElement;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.List;

/**
 *
 * @author mirash
 */
@RemoteServiceRelativePath("signaling")
public interface SignalingService extends RemoteService {

    public List<SignalingElement> getSignalingElements();

    public void setEnabled(String address, boolean enabled);
}
