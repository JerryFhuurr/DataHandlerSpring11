package com.spring.handler.datahandlerspring11.services.impl;

import com.spring.handler.datahandlerspring11.model.Company;
import com.spring.handler.datahandlerspring11.model.Type;
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
        Company companyGet = mapper.getCompanyById(company.getCompanyId());
        if (companyGet != company) {
            mapper.addCompany(company);
            return "Company id : " + company.getCompanyId() + " added";
        } else {
            return "ERROR: The type is already existed.";
        }
    }

    @Override
    public String addMoreCompanies(List<Company> companies) {
        String companyVerify = verifyAddMore(companies);
        if (companyVerify.equals("null")) {
            mapper.addMoreCompanies(companies);
            return companies.size() + " companies added";
        } else {
            return companyVerify;
        }
    }

    private String verifyAddMore(List<Company> companies) {
        List<Company> companyList = mapper.getAllCompany();
        String errorFront = "ERROR: The type id ";
        int errorCount = 0;
        for (var company :
                companies) {
            for (var companyGet :
                    companyList) {
                if (company == companyGet) {
                    errorFront += company.getCompanyId();
                    errorCount++;
                }
            }
        }
        if (errorCount == 0) {
            errorFront = "null";
        } else {
            errorFront += "(is) are duplicated";
        }
        return errorFront;
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
