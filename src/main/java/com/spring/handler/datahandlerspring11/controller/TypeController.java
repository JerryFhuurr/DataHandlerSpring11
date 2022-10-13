package com.spring.handler.datahandlerspring11.controller;

import com.spring.handler.datahandlerspring11.model.Type;
import com.spring.handler.datahandlerspring11.services.TypeService;
import com.spring.handler.datahandlerspring11.services.validateGroup.TypeValidate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/type/controller")
public class TypeController {
    @Autowired
    TypeService typeServices;

    @GetMapping("get/single")
    public Type getSingleType(int id) {
        return typeServices.getSingleType(id);
    }

    @GetMapping("get/all")
    public List<Type> getAllTypes() {
        return typeServices.getAllTypes();
    }

    @PostMapping("add/single")
    public String addSingle(@RequestBody @Validated(TypeValidate.class) Type type) {
        return typeServices.addSingleType(type);
    }

    @PostMapping("add/more")
    public String addMore(@RequestBody @Validated(TypeValidate.class) List<Type> types) {
        return typeServices.addMoreType(types);
    }

    @DeleteMapping("delete/single")
    public String deleteType(int id, int currentPermission) {
        return typeServices.removeType(id, currentPermission);
    }

    @DeleteMapping("delete/more")
    public String deleteTypes(@RequestBody List<Integer> ids, int currentPermission) {
        return typeServices.removeTypes(ids, currentPermission);
    }

    @PutMapping("update")
    public String updateType(@RequestBody @Validated(TypeValidate.class) Type type, int currentPermission) {
        return typeServices.updateType(type, currentPermission);
    }
}
