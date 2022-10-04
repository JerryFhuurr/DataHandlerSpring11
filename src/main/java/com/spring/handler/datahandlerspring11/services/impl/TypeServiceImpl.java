package com.spring.handler.datahandlerspring11.services.impl;

import com.spring.handler.datahandlerspring11.model.Type;
import com.spring.handler.datahandlerspring11.services.TypeService;
import com.spring.handler.datahandlerspring11.sqlmapper.TypeMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TypeServiceImpl implements TypeService {
    @Autowired
    TypeMapper typeMapper;

    @Override
    public String addSingleType(Type type) {
        Type typeGet = typeMapper.getSingleType(type);
        String result = "";
        result = (typeGet == null) ? "ERROR: The type is already existed." : addType(type);
        return result;
    }

    private String addType(Type type) {
        typeMapper.addSingleType(type);
        return type.getTypeId() + " is added.";
    }

    @Override
    public String addMoreType(List<Type> types) {
        String verifyResult = verifyAddMore(types);
        if (verifyResult.equals(null)) {
            typeMapper.addMoreType(types);
            return types.size() + " types added.";
        }
        return verifyResult;
    }

    private String verifyAddMore(List<Type> types) {
        List<Type> typesGet = typeMapper.getAllTypes();
        String errorFront = "ERROR: The type id ";
        int errorCount = 0;
        for (var type :
                types) {
            for (var typeGet :
                    typesGet) {
                if (type == typeGet) {
                    errorFront += type.getTypeId();
                    errorCount++;
                }
            }
        }
        if (errorCount == 0) {
            errorFront = null;
        } else {
            errorFront += "(is) are duplicated";
        }
        return errorFront;
    }

    @Override
    public void removeType(int id, int currentPermission) {

    }

    @Override
    public void removeTypes(List<Integer> ids, int currentPermission) {

    }

    @Override
    public void updateType(Type type, int currentPermission) {

    }

    @Override
    public Type getSingleType(Type type) {
        return null;
    }

    @Override
    public List<Type> getAllTypes() {
        return null;
    }
}
