/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.helpers;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.junit.runners.model.InitializationError;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;

/**
 *
 * @author mirash
 */
public class OrderedRunner extends BlockJUnit4ClassRunner {

    public OrderedRunner(Class<?> targetClass)
            throws InitializationError {
        super(targetClass);
    }

    @Override
    protected List<FrameworkMethod> computeTestMethods() {
        List<FrameworkMethod> list = super.computeTestMethods();

        Collections.sort(list,
                new Comparator<FrameworkMethod>() {
            @Override
            public int compare(FrameworkMethod one, FrameworkMethod another) {
                Order oneOrder = one.getAnnotation(Order.class);
                Order anotherOrder = another.getAnnotation(Order.class);

                if (oneOrder == null || anotherOrder == null) {
                    return -1;
                }

                return oneOrder.order() - anotherOrder.order();
            }
        });
        return list;
    }
}
