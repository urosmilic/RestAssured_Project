package api.pojos.login;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Login_Input {
    private String userEmail;
    private String userPassword;
}
