package com.spring.handler.datahandlerspring11.sqlmapper;

import com.spring.handler.datahandlerspring11.model.Company;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BangumiCompanyMapper {
    void addCompany(Company company);

    void addMoreCompanies(List<Company> companies);

    void removeCompany(int id);

    int removeCompanies(List<Integer> ids);

    Company getCompanyById(int id);

    List<Company> getAllCompany();

    void updateCompany(Company company);
}
