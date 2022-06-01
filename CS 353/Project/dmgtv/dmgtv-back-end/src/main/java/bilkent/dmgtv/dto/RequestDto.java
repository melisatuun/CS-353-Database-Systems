package bilkent.dmgtv.dto;

import bilkent.dmgtv.dto.base.BaseDto;
import lombok.*;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto extends BaseDto<UUID>
{
	private String movieName;
	private int productionYear;
}
