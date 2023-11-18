package api.pojos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Login {
    private String userEmail;
    private String userPassword;
}
