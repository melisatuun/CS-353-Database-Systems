package bilkent.dmgtv.dto;

import java.util.Date;
import java.util.UUID;

import bilkent.dmgtv.db.Movie;
import bilkent.dmgtv.db.User;
import bilkent.dmgtv.dto.base.BaseDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentDto extends BaseDto<UUID>
{
	private User user;
	private Movie movie;
	private Date startDate;
	private Date endDate;
}
