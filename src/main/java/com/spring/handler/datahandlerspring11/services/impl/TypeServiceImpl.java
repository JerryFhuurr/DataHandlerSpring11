package com.spring.handler.datahandlerspring11.services.impl;

import com.spring.handler.datahandlerspring11.model.Type;
import com.spring.handler.datahandlerspring11.services.TypeService;
import com.spring.handler.datahandlerspring11.sqlmapper.TypeMapper;
import com.spring.handler.datahandlerspring11.sqlmapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("typeServices")
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
    public String removeType(int id, int currentPermission) {
        if (currentPermission < 3) {
            typeMapper.removeType(id);
            return "Type id " + id + " removed";
        } else {
            return "Insufficient permission";
        }
    }

    @Override
    public String removeTypes(List<Integer> ids, int currentPermission) {
        if (currentPermission < 3) {
            typeMapper.removeTypes(ids);
            String idString = "Id ";
            for (var id :
                    ids) {
                idString += id;
            }
            idString += " removed";
            return idString;
        } else {
            return "Insufficient permission";
        }
    }

    @Override
    public String updateType(Type type, int currentPermission) {
        Type typeGet = typeMapper.getSingleType(type);
        if (typeGet == null) {
            return "ERROR: Cannot find the type";
        } else {
            if (currentPermission < 3) {
                typeMapper.updateType(type);
                return type.getTypeId() + " updated";
            } else {
                return "Insufficient permission";
            }
        }
    }

    @Override
    public Type getSingleType(Type type) {
        return typeMapper.getSingleType(type);
    }

    @Override
    public List<Type> getAllTypes() {
        return typeMapper.getAllTypes();
    }
}
