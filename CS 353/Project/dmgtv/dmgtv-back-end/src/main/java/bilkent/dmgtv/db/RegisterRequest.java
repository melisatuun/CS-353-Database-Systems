package bilkent.dmgtv.db;

import lombok.*;

import javax.persistence.Column;
import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * Data class for credentials in logging in
 */
public class RegisterRequest {
    private String username;
    private String password;
    private String fullName;
    private Integer year;
    private Integer month;
    private Integer date;
}
