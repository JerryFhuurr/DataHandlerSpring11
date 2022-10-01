package com.spring.handler.datahandlerspring11.services;

import com.spring.handler.datahandlerspring11.model.Company;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface CompanyService {
    String addCompany(Company company);

    @Transactional
    String addMoreCompanies(List<Company> companies);

    String removeCompany(int id);

    @Transactional
    String removeCompanies(List<Integer> ids);

    Company getCompanyById(int id);

    List<Company> getAllCompany();

    String updateCompany(Company company);
}
