package com.spring.handler.datahandlerspring11.sqlmapper;

import com.spring.handler.datahandlerspring11.model.Type;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TypeMapper {
    void addSingleType(Type type);

    void addMoreType(List<Type> types);

    void removeType(int id);

    void removeTypes(List<Integer> ids);

    void updateType(Type type);

    Type getSingleType(int id);

    List<Type> getAllTypes();
}
