package bilkent.dmgtv.controller;

import bilkent.dmgtv.controller.base.BaseController;
import bilkent.dmgtv.db.MovieFilterYear;
import bilkent.dmgtv.db.UpdateUserRequest;
import bilkent.dmgtv.dto.MovieDto;
import bilkent.dmgtv.dto.UserDto;
import bilkent.dmgtv.dto.base.RestResponse;
import bilkent.dmgtv.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("movie")
public class MovieController extends BaseController<MovieDto>
{
	private final MovieService movieService;

	public MovieController(MovieService movieService)
	{
		super(movieService);
		this.movieService = movieService;
	}

	@GetMapping(value = "filter_year")
	public ResponseEntity<RestResponse<List<MovieDto>>> filterByYear(@RequestBody MovieFilterYear movieFilterYear)
	{
		try
		{
			return new ResponseEntity<>(new RestResponse<>(movieService.filterByYear(movieFilterYear), "Filter Movie By Year",
					"Entity retrieval was successful"),
					HttpStatus.OK);
		}
		catch (EntityNotFoundException e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Filter Movie By Year",
					"Entity retrieval was unsuccessful due to an error"),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Filter Movie By Year","Unexpected error"),
					HttpStatus.EXPECTATION_FAILED);
		}
	}
}
