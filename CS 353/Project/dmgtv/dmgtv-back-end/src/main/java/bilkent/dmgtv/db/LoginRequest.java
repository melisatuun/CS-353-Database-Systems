package bilkent.dmgtv.db;

import lombok.*;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * Data class for credentials in logging in
 */
public class LoginRequest {
    private String username;
    private String password;
}
