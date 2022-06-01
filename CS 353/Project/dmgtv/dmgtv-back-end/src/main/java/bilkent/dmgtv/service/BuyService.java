package bilkent.dmgtv.service;

import bilkent.dmgtv.dto.BuyDto;
import bilkent.dmgtv.dto.MovieDto;
import bilkent.dmgtv.service.base.BaseCrudService;

import java.util.List;

public interface BuyService extends BaseCrudService<BuyDto>
{
	BuyDto buyMovie(String movieTitle, String username);
	List<MovieDto> getAllMovies(String username);
}
