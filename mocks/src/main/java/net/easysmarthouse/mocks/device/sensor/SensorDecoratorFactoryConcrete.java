/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.mocks.device.sensor;

/**
 *
 * @author mirash
 */
class SensorDecoratorFactoryConcrete implements SensorDecoratorFactory {

    @Override
    public MockSensor createDynamic(MockSensor delegated, double minEdge, double maxEdge) {
        DynamicMockSensorDecorator decorator = new DynamicMockSensorDecorator(delegated);
        decorator.setRange(minEdge, maxEdge);
        return decorator;
    }

    @Override
    public MockSensor createDynamic(MockSensor delegated, double minEdge, double maxEdge, long changeDelay) {
        DynamicMockSensorDecorator decorator = (DynamicMockSensorDecorator) this.createDynamic(delegated, minEdge, maxEdge);
        decorator.setChangeDelay(changeDelay);
        return decorator;
    }

    @Override
    public MockSensor createStatic(MockSensor delegated, double value) {
        StaticMockSensorDecorator staticMockSensorDecorator = new StaticMockSensorDecorator(delegated);
        staticMockSensorDecorator.setValue(value);
        return staticMockSensorDecorator;
    }
}
