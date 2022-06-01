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
public class UpdateUserRequest {
    private String password;
    private String fullName;
    private Integer year;
    private Integer month;
    private Integer date;
}
