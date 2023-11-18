package api.pojos.Login;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Login_Output {
    private String token;
    private String userId;
    private String message;
}