package com.spring.handler.datahandlerspring11.services;

import com.spring.handler.datahandlerspring11.model.Type;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TypeService {
    String addSingleType(Type type);

    String addMoreType(List<Type> types);

    void removeType(int id, int currentPermission);

    void removeTypes(List<Integer> ids, int currentPermission);

    void updateType(Type type, int currentPermission);

    Type getSingleType(Type type);

    List<Type> getAllTypes();
}
