package api.pojos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Registration {
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
