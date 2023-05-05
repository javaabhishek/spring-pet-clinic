package com.asoft.springpetclinic.services.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractMapService <T,ID>{

    protected Map<ID,T> map=new HashMap<>();
    protected  T findById(ID id){
        return map.get(id);
    }

    protected T save(ID id,T t){
        map.put(id,t);
        return t;
    }

    protected Set<T> findAll(){
        return map.values().stream().collect(Collectors.toSet());
    }

    protected void delete(T model){
      boolean isRemoved=map.entrySet().removeIf(e->e.equals(model));
      if (isRemoved)
          System.out.println("removed");
      else
          System.out.println("not removed");
    }

    protected void deleteById(ID modelId){
        map.remove(modelId);
    }
}
