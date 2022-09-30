package com.spring.handler.datahandlerspring11.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "The username cannot be empty!")
    private String userName;
    /**
     * Password
     */
    @NotBlank(message = "The password cannot be empty!")
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
     * Use number
     * 1 - super admin; 2 - admin; 3 - normal user
     */
    private int userPermission;
}
