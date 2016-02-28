/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.mocks.device.sensor;

import net.easysmarthouse.provider.device.sensor.SensorType;

/**
 *
 * @author mirash
 */
public final class MockSensorAbstractFactory {

    private static SensorDecoratorFactory decoratorFactory = new SensorDecoratorFactoryConcrete();
    private static SensorPrototypeFactory prototypeFactory = new SensorPrototypeFactoryConcrete();

    private MockSensorAbstractFactory() {
    }

    private static SensorType getType(String sensorType) {
        SensorType[] types = SensorType.values();
        for (SensorType currtype : types) {
            String currName = currtype.name();
            if (currName.equalsIgnoreCase(sensorType)) {
                return currtype;
            }

            String sensorTypeLower = sensorType.toLowerCase();
            String currTypeLower = currName.toLowerCase();

            if (currTypeLower.startsWith(sensorTypeLower)) {
                return currtype;
            }
        }

        return null;
    }

    public static MockSensor createMock(SensorType type, double value) {
        MockSensor prototype = prototypeFactory.createPrototype(type);
        return decoratorFactory.createStatic(prototype, value);
    }

    public static MockSensor createMock(String sensor, double value) {
        SensorType type = getType(sensor);
        return createMock(type, value);
    }

    public static MockSensor createMock(SensorType type, double minEdge, double maxEdge) {
        MockSensor prototype = prototypeFactory.createPrototype(type);
        return decoratorFactory.createDynamic(prototype, minEdge, maxEdge);
    }

    public static MockSensor createMock(String sensor, double minEdge, double maxEdge) {
        SensorType type = getType(sensor);
        return createMock(type, minEdge, maxEdge);
    }

    public static MockSensor createMock(SensorType type, double minEdge, double maxEdge, long changeDelay) {
        MockSensor prototype = prototypeFactory.createPrototype(type);
        return decoratorFactory.createDynamic(prototype, minEdge, maxEdge, changeDelay);
    }

    public static MockSensor createMock(String sensor, double minEdge, double maxEdge, long changeDelay) {
        SensorType type = getType(sensor);
        return createMock(type, minEdge, maxEdge, changeDelay);
    }
}
