package com.asoft.springpetclinic.util;

import com.asoft.springpetclinic.model.BaseEntity;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Set;

public class DataUtil {
    public static <T extends BaseEntity> Set<T> findAllUtil(Iterable<T> iterable){
        Set<T> collectElements=new HashSet<>();
        iterable.forEach(collectElements::add);
        return collectElements;
    }
}
