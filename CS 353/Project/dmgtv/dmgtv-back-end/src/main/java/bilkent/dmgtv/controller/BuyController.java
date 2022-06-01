package bilkent.dmgtv.controller;

import bilkent.dmgtv.controller.base.BaseController;
import bilkent.dmgtv.dto.BuyDto;
import bilkent.dmgtv.dto.MovieDto;
import bilkent.dmgtv.dto.base.RestResponse;
import bilkent.dmgtv.service.BuyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("buy")
public class BuyController extends BaseController<BuyDto>
{
	private final BuyService buyService;

	public BuyController(BuyService buyService)
	{
		super(buyService);
		this.buyService = buyService;
	}

	@PostMapping(value = "buyMovie/{movieTitle}/{username}")
	public ResponseEntity<RestResponse<BuyDto>> buyMovie(@PathVariable String movieTitle, @PathVariable String username)
	{
		try
		{
			return new ResponseEntity<>(new RestResponse<>(buyService.buyMovie(movieTitle,username), "Rent a movie",
					"Renting a movie was successful"),
					HttpStatus.OK);
		}
		catch (EntityNotFoundException e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Rent a movie",
					e.getMessage()),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Rent a movie","Unexpected error"),
					HttpStatus.EXPECTATION_FAILED);
		}
	}

	@GetMapping(value = "getAllMovies/{username}")
	public ResponseEntity<RestResponse<List<MovieDto>>> getAllMovies(@PathVariable String username)
	{
		try
		{
			return new ResponseEntity<>(new RestResponse<>(buyService.getAllMovies(username), "Get all buys",
					"Getting all buys was successful"),
					HttpStatus.OK);
		}
		catch (EntityNotFoundException e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Get all buys",
					e.getMessage()),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Get all buys","Unexpected error"),
					HttpStatus.EXPECTATION_FAILED);
		}
	}
}
