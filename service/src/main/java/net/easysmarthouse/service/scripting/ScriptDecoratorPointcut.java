/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.service.scripting;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import net.easysmarthouse.network.NetworkManager;
import net.easysmarthouse.provider.device.Device;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

/**
 *
 * @author rusakovich
 */
public class ScriptDecoratorPointcut extends StaticMethodMatcherPointcut {

    @Override
    public boolean matches(Method method, Class<?> type) {
        Class<?> returnType = method.getReturnType();
        if (returnType == Device.class) {
            return true;
        }

        if (Collection.class.isAssignableFrom(returnType)) {
            Type genericReturnType = method.getGenericReturnType();
            if (genericReturnType instanceof ParameterizedType) {
                ParameterizedType listType = (ParameterizedType) genericReturnType;
                Type[] actualTypeArgs = listType.getActualTypeArguments();

                if (actualTypeArgs != null && actualTypeArgs.length != 0) {
                    Class<?> listClass = (Class<?>) actualTypeArgs[0];
                    if (listClass == Device.class) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public ClassFilter getClassFilter() {
        return new ClassFilter() {
            @Override
            public boolean matches(Class targetCls) {
                return (NetworkManager.class.isAssignableFrom(targetCls));
            }
        };
    }

}
