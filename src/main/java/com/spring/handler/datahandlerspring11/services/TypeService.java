package com.spring.handler.datahandlerspring11.services;

import com.spring.handler.datahandlerspring11.model.Type;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TypeService {
    String addSingleType(Type type);

    String addMoreType(List<Type> types);

    String removeType(int id, int currentPermission);

    String removeTypes(List<Integer> ids, int currentPermission);

    String updateType(Type type, int currentPermission);

    Type getSingleType(int id);

    List<Type> getAllTypes();
}
