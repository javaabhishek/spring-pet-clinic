package com.asoft.springpetclinic.services.map;

import com.asoft.springpetclinic.model.BaseEntity;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractMapService <T extends BaseEntity,ID extends Long>{

    protected Map<Long,T> map=new HashMap<>();
    protected  T findById(ID id){
        return map.get(id);
    }

    protected T save(T t){
        if(t !=null){
            if(t.getId()==null){
                t.setId(getMaxId());
            }
            map.put(t.getId(),t);
        }else {
            throw new RuntimeException("Object can not be null");
        }
        return t;
    }

    protected Set<T> findAll(){
        return map.values().stream().collect(Collectors.toSet());
    }

    protected void delete(T model){
      boolean isRemoved=map.entrySet().removeIf(e->e.getValue().getId().equals(model.getId()));
      if (isRemoved)
          System.out.println("removed");
      else
          System.out.println("not removed");
    }

    protected void deleteById(ID modelId){
        map.remove(modelId);
    }

    protected Long getMaxId(){
        Long maxId=null;
        try{
            maxId=Collections.max(map.keySet())+1;
        }catch (NoSuchElementException e){
            maxId=1l;
        }
        return maxId;
    }
}
