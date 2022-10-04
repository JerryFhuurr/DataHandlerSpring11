package com.spring.handler.datahandlerspring11.controller;

import com.spring.handler.datahandlerspring11.model.Type;
import com.spring.handler.datahandlerspring11.services.TypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/type/controller")
public class TypeController {
    @Autowired
    TypeService typeServices;

    @GetMapping("get/single")
    public Type getSingleType(Type type) {
        return typeServices.getSingleType(type);
    }

    @GetMapping("get/all")
    public List<Type> getAllTypes() {
        return typeServices.getAllTypes();
    }

    @PostMapping("add/single")
    public String addSingle(Type type) {
        return typeServices.addSingleType(type);
    }
}
