package bilkent.dmgtv.controller;

import bilkent.dmgtv.controller.base.BaseController;
import bilkent.dmgtv.dto.WishDto;
import bilkent.dmgtv.dto.base.RestResponse;
import bilkent.dmgtv.service.WishService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("wish")
public class WishController extends BaseController<WishDto>
{
	private final WishService wishService;

	public WishController(WishService wishService)
	{
		super(wishService);
		this.wishService = wishService;
	}

	@PostMapping("createWish/{movieTitle}/{username}")
	public ResponseEntity<RestResponse<WishDto>> createWish(@PathVariable String movieTitle, @PathVariable String username)
	{
		try
		{
			return new ResponseEntity<>(new RestResponse<>(wishService.createWish(movieTitle, username), "Create Wish",
					"Creating wish was successful"),
					HttpStatus.OK);
		}
		catch (EntityNotFoundException e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Create Wish",
					e.getMessage()),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Create Wish","Unexpected error"),
					HttpStatus.EXPECTATION_FAILED);
		}
	}

	@DeleteMapping("delete/{movieTitle}/{username}")
	public ResponseEntity<RestResponse<WishDto>> delete(@PathVariable String movieTitle, @PathVariable String username)
	{
		try
		{
			return new ResponseEntity<>(new RestResponse<>(wishService.deleteWish(username,movieTitle), "Delete Wish",
					"Deleting wish was successful"),
					HttpStatus.OK);
		}
		catch (EntityNotFoundException e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Delete Wish",
					e.getMessage()),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Delete Wish","Unexpected error"),
					HttpStatus.EXPECTATION_FAILED);
		}
	}

	@GetMapping("readWish/{username}")
	public ResponseEntity<RestResponse<List<WishDto>>> readWishes(@PathVariable String username)
	{
		try
		{
			return new ResponseEntity<>(new RestResponse<>(wishService.findAllByUsername(username), "Read Wish",
					"Read wish was successful"),
					HttpStatus.OK);
		}
		catch (EntityNotFoundException e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Read Wish",
					e.getMessage()),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Read Wish","Unexpected error"),
					HttpStatus.EXPECTATION_FAILED);
		}
	}
}
