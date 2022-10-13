package com.spring.handler.datahandlerspring11.services.impl;

import com.spring.handler.datahandlerspring11.model.Type;
import com.spring.handler.datahandlerspring11.services.TypeService;
import com.spring.handler.datahandlerspring11.sqlmapper.TypeMapper;
import com.spring.handler.datahandlerspring11.utils.exceptions.ReqExceptions;
import com.spring.handler.datahandlerspring11.utils.exceptions.common.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("typeServices")
public class TypeServiceImpl implements TypeService {
    @Autowired
    TypeMapper typeMapper;

    @Override
    public String addSingleType(Type type) {
        System.out.println(type);
        Type typeGet = typeMapper.getSingleType(type.getTypeId());
        String result = "";
        result = (typeGet != null) ? "ERROR: The type is already existed." : addType(type);
        if (result.contains("ERROR")) {
            throw new ReqExceptions(ErrorCode.Type.TYPE_DUPLICATED, "The type is already existed");
        } else {
            return result;
        }
    }

    private String addType(Type type) {
        typeMapper.addSingleType(type);
        return type.getTypeId() + " is added.";
    }

    @Override
    public String addMoreType(List<Type> types) {
        String verifyResult = verifyAddMore(types);
        if (verifyResult.equals("null")) {
            typeMapper.addMoreType(types);
            return types.size() + " types added.";
        }
        return verifyResult;
    }

    private String verifyAddMore(List<Type> types) {
        List<Type> typesGet = typeMapper.getAllTypes();
        String errorFront = "The type id ";
        int errorCount = 0;
        for (var type :
                types) {
            for (var typeGet :
                    typesGet) {
                if (type == typeGet) {
                    errorFront += type.getTypeId();
                    errorFront += ",";
                    errorCount++;
                }
            }
        }
        if (errorCount == 0) {
            errorFront = "null";
        } else {
            errorFront += "(is) are duplicated";
        }
        throw new ReqExceptions(ErrorCode.Type.TYPE_DUPLICATED, errorFront);
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
                idString += ",";
            }
            idString += " removed";
            return idString;
        } else {
            throw new ReqExceptions(ErrorCode.Type.TYPE_PERMISSION_INVALID, "Insufficient permission");
        }
    }

    @Override
    public String updateType(Type type, int currentPermission) {
        Type typeGet = typeMapper.getSingleType(type.getTypeId());
        if (typeGet == null) {
            return "ERROR: Cannot find the type";
        } else {
            if (currentPermission < 3) {
                typeMapper.updateType(type);
                return type.getTypeId() + " updated";
            } else {
                throw new ReqExceptions(ErrorCode.Type.TYPE_PERMISSION_INVALID, "Insufficient permission");
            }
        }
    }

    @Override
    public Type getSingleType(int id) {
        return typeMapper.getSingleType(id);
    }

    @Override
    public List<Type> getAllTypes() {
        return typeMapper.getAllTypes();
    }
}
