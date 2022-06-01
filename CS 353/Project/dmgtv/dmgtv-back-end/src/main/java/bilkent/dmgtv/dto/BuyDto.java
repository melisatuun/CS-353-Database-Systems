package bilkent.dmgtv.dto;

import bilkent.dmgtv.db.Movie;
import bilkent.dmgtv.db.User;
import bilkent.dmgtv.dto.base.BaseDto;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuyDto extends BaseDto<UUID>
{
	private User user;
	private Movie movie;
	private Date purchaseDate;
}
