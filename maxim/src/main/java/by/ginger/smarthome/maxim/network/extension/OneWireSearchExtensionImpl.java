/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.maxim.network.extension;

import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.DSPortAdapter;
import com.dalsemi.onewire.adapter.OneWireIOException;
import com.dalsemi.onewire.application.monitor.AbstractDeviceMonitor;
import com.dalsemi.onewire.application.monitor.NetworkDeviceMonitor;
import com.dalsemi.onewire.container.OneWireContainer;
import com.dalsemi.onewire.utils.OWPath;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author rusakovich
 */
public class OneWireSearchExtensionImpl extends AdapterProviderBaseExtension implements OneWireSearchExtension {

    //The monitor will generate events for device arrivals, device departures, and 
    //exceptions from the DSPortAdapter.
    //The device monitor keeps a "missing state count" for each device on the network. 
    //Each search cycle that passes where the device is missing causes it's 
    //"missing state count"  to be incremented.
    //To receive events, an object must implement the <code>DeviceMonitorEventListener</code> interface
    protected AbstractDeviceMonitor devicesMonitor;
    //<code>OWPath</code> - 1-Wire® Network path. Large 1-Wire networks can be sub-divided into branches for load, 
    //location, or organizational reasons. Once 1-Wire devices are placed on this branches 
    //there needs to be a mechanism to reach these devices. The OWPath class was designed to 
    //provide a convenient method to open and close 1-Wire paths to reach remote devices.
    protected Map<Long, OWPath> oneWireDevices;

    public OneWireSearchExtensionImpl() {
        oneWireDevices = new HashMap<Long, OWPath>();
    }

    @Override
    public OneWireContainer getDevice(long address) {
        final OneWireContainer container = AbstractDeviceMonitor.getDeviceContainer(adapter, address);
        return container;
    }

    @Override
    public boolean isDevicePresent(long address) {
        return oneWireDevices.containsKey(address);
    }

    @Override
    public List<OneWireContainer> getDevices() {
        final List<OneWireContainer> result = new LinkedList<OneWireContainer>();

        for (Long address : oneWireDevices.keySet()) {
            final OneWireContainer container = AbstractDeviceMonitor.getDeviceContainer(adapter, address);
            result.add(container);
        }

        return result;
    }

    public void initExtension() throws OneWireIOException, OneWireException {
        refresh();
    }

    @Override
    public void refresh() throws OneWireIOException, OneWireException {
        //A vector of Long objects, represent new arrival addresses.
        final Vector<Long> arrivals = new Vector<Long>();
        //A vector of Long objects, represent departed addresses.
        final Vector<Long> departures = new Vector<Long>();
        //Performs a search of the 1-Wire network
        devicesMonitor.search(arrivals, departures);

        for (Long address : arrivals) {
            final OWPath path = devicesMonitor.getDevicePath(address);
            oneWireDevices.put(address, path);
        }
        for (Long address : departures) {
            oneWireDevices.remove(address);
        }
    }

    @Override
    public List<Long> getDevicesAddresses() {
        final List<Long> result = new LinkedList<Long>(oneWireDevices.keySet());
        return result;
    }

    @Override
    public void setAdapter(DSPortAdapter adapter) {
        super.setAdapter(adapter);

        //NetworkDeviceMonitor represents the monitor that searches the
        // 1-Wire net, including the traversal of branches, looing for new arrivals
        // and departures.
        final NetworkDeviceMonitor networkDeviceMonitor = new NetworkDeviceMonitor(adapter);
        //Indicates whether or not branches are automatically traversed.  If false,
        // new branches must be indicated using the "addBranch" method.
        networkDeviceMonitor.setBranchAutoSearching(false);

        devicesMonitor = networkDeviceMonitor;
        //Number of searches that an error occurs before listener's are notified
        devicesMonitor.setMaxErrorCount(1);
    }
}
