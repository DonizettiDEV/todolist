package br.com.donizetti.todolist.Utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeanUtils;


public class Utils {

    public static void copyNonNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }
    
    public static String[] getNullPropertyNames(Object source){
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);

        PropertyDescriptor[] pds = wrappedSource.getPropertyDescriptors();
        Set<String> nullPropertyNames = new HashSet<>();

        for (PropertyDescriptor pd : pds) {
            Object propertyValue = wrappedSource.getPropertyValue(pd.getName());
            if (propertyValue == null) {
                nullPropertyNames.add(pd.getName());
            }
        }

        String[] result = new String[nullPropertyNames.size()];
        return nullPropertyNames.toArray(result);
    }
}

