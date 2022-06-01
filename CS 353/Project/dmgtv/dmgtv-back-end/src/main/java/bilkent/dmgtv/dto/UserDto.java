package bilkent.dmgtv.dto;

import bilkent.dmgtv.dto.base.BaseDto;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends BaseDto<UUID>
{
	private String username;
	private String password;
	private String fullName;
	private Date birthDate;
}
