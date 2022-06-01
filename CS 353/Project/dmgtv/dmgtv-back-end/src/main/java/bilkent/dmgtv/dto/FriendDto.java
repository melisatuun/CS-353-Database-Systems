package bilkent.dmgtv.dto;

import bilkent.dmgtv.dto.base.BaseDto;
import lombok.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FriendDto extends BaseDto<UUID>
{
	private UserDto firstUser;
	private UserDto secondUser;
}
