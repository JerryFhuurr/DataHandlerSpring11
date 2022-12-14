package com.spring.handler.datahandlerspring11.model;

import com.spring.handler.datahandlerspring11.services.validateGroup.UserValidate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {
    /**
     * User id (auto generated)
     */
    private String userId;
    /**
     * Username (displayed as profile)
     */
    @NotNull(message = "The username cannot be empty!", groups = UserValidate.class)
    @NotBlank(message = "The username cannot be empty!", groups = UserValidate.class)
    private String userName;
    /**
     * Password
     */
    @NotNull(message = "The password cannot be empty!", groups = UserValidate.class)
    @NotBlank(message = "The password cannot be empty!", groups = UserValidate.class)
    @Min(value = 6, message = "The length of password should larger than 6 !", groups = UserValidate.class)
    private String userPassword;
    /**
     * Email (used to send email to verify)
     */
    private String userEmail;
    /**
     * Phone number , can be empty
     */
    private String userPhone;
    /**
     * User permission code
     * 1 - super admin; 2 - admin; 3 - normal user
     */
    private int userPermission;
    /**
     * User description
     */
    private String userDesc;
}
