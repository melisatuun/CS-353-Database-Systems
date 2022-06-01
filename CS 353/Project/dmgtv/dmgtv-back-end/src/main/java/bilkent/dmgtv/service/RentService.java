package bilkent.dmgtv.service;

import bilkent.dmgtv.dto.MovieDto;
import bilkent.dmgtv.dto.RentDto;
import bilkent.dmgtv.service.base.BaseCrudService;

import java.util.List;

public interface RentService extends BaseCrudService<RentDto>
{
	RentDto rentMovie(String movieName, String username);
	List<MovieDto> getRentedMovies(String username);
	RentDto stopRenting(String movieName, String username);
}
