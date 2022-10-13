package com.spring.handler.datahandlerspring11.model;

import com.spring.handler.datahandlerspring11.services.validateGroup.TypeValidate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Type {
    private int typeId;
    @NotNull(message = "The name cannot be empty!", groups = TypeValidate.class)
    @NotBlank(message = "The name cannot be empty!", groups = TypeValidate.class)
    private String typeName;
}
