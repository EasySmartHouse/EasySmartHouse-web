/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.mocks.device.sensor;

/**
 *
 * @author mirash
 */
public interface SensorDecoratorFactory {

    MockSensor createDynamic(MockSensor delegated, double minEdge, double maxEdge);

    MockSensor createDynamic(MockSensor delegated, double minEdge, double maxEdge, long changeDelay);

    MockSensor createStatic(MockSensor delegated, double value);
}
