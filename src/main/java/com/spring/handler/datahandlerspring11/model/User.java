package com.spring.handler.datahandlerspring11.model;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    /**
     * User id (auto generated)
     */
    private String userId;
    /**
     * Username (displayed as profile)
     */
    @NotBlank(message = "The username cannot be empty!")
    @Min(value = 5, message = "At least 5 letters!")
    @Max(value = 30, message = "No more than 30 letters!")
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
