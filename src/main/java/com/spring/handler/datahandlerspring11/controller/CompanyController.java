package com.spring.handler.datahandlerspring11.controller;

import com.spring.handler.datahandlerspring11.model.Company;
import com.spring.handler.datahandlerspring11.services.CompanyService;
import com.spring.handler.datahandlerspring11.services.validateGroup.CompanyValidate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/company/controller")
public class CompanyController {
    @Autowired
    CompanyService companyServices;

    @PostMapping("add/one")
    public String addOneCompany(@Validated(CompanyValidate.class) @RequestBody Company company) {
        return companyServices.addCompany(company);
    }

    @PostMapping("add/more")
    public String addMoreCompany(@Validated(CompanyValidate.class) @RequestBody List<Company> companies) {
        return companyServices.addMoreCompanies(companies);
    }

    @DeleteMapping("remove/single")
    public String removeCompany(int id) {
        return companyServices.removeCompany(id);
    }

    @DeleteMapping("remove/more")
    public String removeCompanies(@RequestBody List<Integer> ids) {
        return companyServices.removeCompanies(ids);
    }

    @GetMapping("get/single")
    public Company getCompanyById(int id) {
        return companyServices.getCompanyById(id);
    }

    @GetMapping("get/more")
    public List<Company> getAllCompany() {
        return companyServices.getAllCompany();
    }

    @PutMapping("update/single")
    public String updateCompany(@Validated(CompanyValidate.class) @RequestBody Company company) {
        return companyServices.updateCompany(company);
    }
}
