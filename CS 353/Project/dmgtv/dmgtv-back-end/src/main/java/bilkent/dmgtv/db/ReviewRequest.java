package bilkent.dmgtv.db;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * Data class for review addition
 */
public class ReviewRequest {
    private String username;
    private String movieId;
    private double rating;
    private String comment;
}
