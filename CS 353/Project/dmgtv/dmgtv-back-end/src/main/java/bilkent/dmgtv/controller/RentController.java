package bilkent.dmgtv.controller;

import bilkent.dmgtv.controller.base.BaseController;
import bilkent.dmgtv.dto.MovieDto;
import bilkent.dmgtv.dto.RentDto;
import bilkent.dmgtv.dto.base.RestResponse;
import bilkent.dmgtv.service.RentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("rent")
public class RentController extends BaseController<RentDto>
{
	private final RentService rentService;

	public RentController(RentService rentService)
	{
		super(rentService);
		this.rentService = rentService;
	}

	@PostMapping(value = "rentMovie/{movieTitle}/{username}")
	public ResponseEntity<RestResponse<RentDto>> rentMovie(@PathVariable String movieTitle, @PathVariable String username)
	{
		try
		{
			return new ResponseEntity<>(new RestResponse<>(rentService.rentMovie(movieTitle,username), "Rent a movie",
					"Renting a movie was successful"),
					HttpStatus.OK);
		}
		catch (EntityNotFoundException e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Rent a movie",
					"Renting a movie was unsuccessful due to an error"),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Rent a movie","Unexpected error"),
					HttpStatus.EXPECTATION_FAILED);
		}
	}

	@GetMapping(value = "getMovies/{username}")
	public ResponseEntity<RestResponse<List<MovieDto>>> getMovies(@PathVariable String username)
	{
		try
		{
			return new ResponseEntity<>(new RestResponse<>(rentService.getRentedMovies(username), "Get rented movies",
					"Getting rented movies list was successful"),
					HttpStatus.OK);
		}
		catch (EntityNotFoundException e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Get rented movies",
					"Getting rented movies list was unsuccessful due to an error"),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Get rented movies","Unexpected error"),
					HttpStatus.EXPECTATION_FAILED);
		}
	}

	@PutMapping(value = "stopRent/{movieTitle}/{username}")
	public ResponseEntity<RestResponse<RentDto>> stopRent(@PathVariable String movieTitle, @PathVariable String username)
	{
		try
		{
			return new ResponseEntity<>(new RestResponse<>(rentService.stopRenting(movieTitle,username), "Stop renting a movie",
					"Stop renting a movie was successful"),
					HttpStatus.OK);
		}
		catch (EntityNotFoundException e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Stop renting a movie",
					"Stop renting a movie was unsuccessful due to an error"),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Stop renting a movie","Unexpected error"),
					HttpStatus.EXPECTATION_FAILED);
		}
	}
}
