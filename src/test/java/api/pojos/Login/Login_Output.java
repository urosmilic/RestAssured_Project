package api.pojos.Login;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Login_Output {
    private String token;
    private String userId;
    private String message;
}