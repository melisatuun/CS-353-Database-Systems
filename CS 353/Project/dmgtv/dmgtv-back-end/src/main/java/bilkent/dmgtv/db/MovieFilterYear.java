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
public class MovieFilterYear {
    private Integer lowerYear;
    private Integer upperYear;
}
