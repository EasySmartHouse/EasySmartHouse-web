/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.client.rpc;

import net.easysmarthouse.ui.webui.client.rpc.mock.ActuatorsAsyncMock;
import net.easysmarthouse.ui.webui.client.rpc.mock.MonitoringAsyncMock;
import com.google.gwt.core.client.GWT;

/**
 *
 * @author rusakovich
 */
class LocatorFactoryImpl implements LocatorFactory {

    private LocatorFactoryImpl() {
    }

    @Override
    public ServiceLocator getLocator() {
        if (GWT.isProdMode()) {
            return new ServerServiceLocator();
        } else {
            return new MockServiceLocator();
        }
    }

    private static class MockServiceLocator extends ServiceLocator {

        private final MonitoringServiceAsync monitorigMock;
        private final ActuatorsServiceAsync actuatorsMock;

        private MockServiceLocator() {
            monitorigMock = new MonitoringAsyncMock();
            actuatorsMock = new ActuatorsAsyncMock();
        }

        @Override
        public MonitoringServiceAsync getMonitoring() {
            return monitorigMock;
        }

        @Override
        public ActuatorsServiceAsync getActuators() {
            return actuatorsMock;
        }

        @Override
        public SignalingServiceAsync getSignalings() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public TriggerServiceAsync getTriggers() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    private static class ServerServiceLocator extends ServiceLocator {

        private final MonitoringServiceAsync monitoringService;
        private final ActuatorsServiceAsync actuatorsService;
        private final SignalingServiceAsync signalingService;
        private final TriggerServiceAsync triggerService;

        private ServerServiceLocator() {
            monitoringService = GWT.create(MonitoringService.class);
            actuatorsService = GWT.create(ActuatorsService.class);
            signalingService = GWT.create(SignalingService.class);
            triggerService = GWT.create(TriggerService.class);
        }

        @Override
        public MonitoringServiceAsync getMonitoring() {
            return monitoringService;
        }

        @Override
        public ActuatorsServiceAsync getActuators() {
            return actuatorsService;
        }

        @Override
        public SignalingServiceAsync getSignalings() {
            return signalingService;
        }

        @Override
        public TriggerServiceAsync getTriggers() {
            return triggerService;
        }
    }

    private static class LocatorFactoryHolder {

        private static final LocatorFactory INSTANCE = new LocatorFactoryImpl();
    }

    public static LocatorFactory getInstance() {
        return LocatorFactoryHolder.INSTANCE;
    }
}
