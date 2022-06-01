package bilkent.dmgtv.dto;


import bilkent.dmgtv.dto.base.BaseDto;
import lombok.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WishDto extends BaseDto<UUID>
{
	private UserDto user;
	private MovieDto movie;
	private Date wishDate;
}
