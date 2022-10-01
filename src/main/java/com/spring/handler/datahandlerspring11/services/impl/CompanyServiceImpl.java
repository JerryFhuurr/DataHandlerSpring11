package com.spring.handler.datahandlerspring11.services.impl;

import com.spring.handler.datahandlerspring11.model.Company;
import com.spring.handler.datahandlerspring11.services.CompanyService;
import com.spring.handler.datahandlerspring11.sqlmapper.BangumiCompanyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("companyServices")
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    BangumiCompanyMapper mapper;

    @Override
    public String addCompany(Company company) {
        mapper.addCompany(company);
        return "Company id : " + company.getCompanyId() + " added";
    }

    @Override
    public String addMoreCompanies(List<Company> companies) {
        mapper.addMoreCompanies(companies);
        return companies.size() + " companies added";
    }

    @Override
    public String removeCompany(int id) {
        mapper.removeCompany(id);
        return "Company id : " + id + " removed";
    }

    @Override
    public String removeCompanies(List<Integer> ids) {
        return mapper.removeCompanies(ids) + " companies removed";
    }

    @Override
    public Company getCompanyById(int id) {
        return mapper.getCompanyById(id);
    }

    @Override
    public List<Company> getAllCompany() {
        return mapper.getAllCompany();
    }

    @Override
    public String updateCompany(Company company) {
        mapper.updateCompany(company);
        return "Company id : " + company.getCompanyId() + " updated";
    }
}
