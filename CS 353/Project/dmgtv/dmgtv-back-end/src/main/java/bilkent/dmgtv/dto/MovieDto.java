package bilkent.dmgtv.dto;

import bilkent.dmgtv.dto.base.BaseDto;
import lombok.*;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto extends BaseDto<UUID>
{
	private String title;
	private int productionYear;
	private double rating;
	private int pricePerMonth;
	private int priceToBuy;
	private boolean ageRestricted;
	private double imdbRating;
	private int likeCount;

	public boolean getAgeRestricted(){
		return ageRestricted;
	}
}
