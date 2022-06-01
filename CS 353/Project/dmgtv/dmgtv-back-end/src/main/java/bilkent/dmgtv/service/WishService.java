package bilkent.dmgtv.service;

import bilkent.dmgtv.dto.WishDto;
import bilkent.dmgtv.service.base.BaseCrudService;

import java.util.List;

public interface WishService extends BaseCrudService<WishDto>
{
	WishDto deleteWish(String username, String movieTitle);
	List<WishDto> findAllByUsername(String username);
	WishDto createWish(String movieTitle, String username);
}
