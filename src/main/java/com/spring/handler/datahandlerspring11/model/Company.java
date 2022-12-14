package com.spring.handler.datahandlerspring11.model;

import com.spring.handler.datahandlerspring11.services.validateGroup.CompanyValidate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company implements Serializable {
    private int companyId;
    @NotNull(message = "The company name cannot be null", groups = CompanyValidate.class)
    @NotBlank(message = "The company name cannot be null", groups = CompanyValidate.class)
    private String companyName;
}
