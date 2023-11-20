package api.pojos.Registration;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Registration_Input {
    private String firstName;
    private String lastName;
    private String userEmail;
    private String userRole;
    private String occupation;
    private String gender;
    private String userMobile;
    private String userPassword;
    private String confirmPassword;
    private boolean required;
}
