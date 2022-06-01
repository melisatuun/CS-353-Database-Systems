package bilkent.dmgtv.dto;

import bilkent.dmgtv.db.Movie;
import bilkent.dmgtv.db.User;
import bilkent.dmgtv.dto.base.BaseDto;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto extends BaseDto<UUID>
{
    private UserDto user;
    private MovieDto movie;
    private double rating;
    private String comment;
}
