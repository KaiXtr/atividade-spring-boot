package com.bramosewerton.todolist.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class Utils {

    public static void copiarPropriedadesNaoVazias(Object source,Object target){
        BeanUtils.copyProperties(source, target, getPropriedadesVazias(source));
    }

    public static String[] getPropriedadesVazias(Object source){
        final BeanWrapper src = new BeanWrapperImpl(source);

        PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> nomesVazios = new HashSet<>();

        for (PropertyDescriptor pd:pds){
            Object srcValor = src.getPropertyValue(pd.getName());
            if (srcValor == null){
                nomesVazios.add(pd.getName());
            }
        }
        String[] resultados = new String[nomesVazios.size()];
        return nomesVazios.toArray(resultados);
    }
}
