package bilkent.dmgtv.controller;

import bilkent.dmgtv.controller.base.BaseController;
import bilkent.dmgtv.dto.FriendDto;
import bilkent.dmgtv.dto.UserDto;
import bilkent.dmgtv.dto.base.RestResponse;
import bilkent.dmgtv.service.FriendService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("friend")
public class FriendController extends BaseController<FriendDto>
{
	private final FriendService friendService;

	public FriendController(FriendService friendService)
	{
		super(friendService);
		this.friendService = friendService;
	}

	@PostMapping(value = "create/{firstusername}/{secondusername}")
	public ResponseEntity<RestResponse<FriendDto>> createFriend(@PathVariable String firstusername, @PathVariable String secondusername)
	{
		try
		{
			return new ResponseEntity<>(new RestResponse<>(friendService.addFriend(firstusername, secondusername),
					"Create Friend Relationship",
					"Friend relationship creation was successful"),
					HttpStatus.OK);
		}
		catch (EntityNotFoundException e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Friend creation",
					"Either one or both of the users are not found"),
					HttpStatus.NOT_FOUND);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Friend Create","Unexpected error"),
					HttpStatus.EXPECTATION_FAILED);
		}
	}

	@PostMapping(value = "delete/{firstUsername}/{secondUsername}")
	public ResponseEntity<RestResponse<List<UserDto>>> deleteFriend(@PathVariable String firstUsername, @PathVariable String secondUsername)
	{
		try
		{
			return new ResponseEntity<>(new RestResponse<>(friendService.deleteFriend(firstUsername, secondUsername),
					"Remove Friend Relationship",
					"Friend relationship deletion was successful"),
					HttpStatus.OK);
		}
		catch (EntityNotFoundException e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Remove Friend Relationship",
					"Either one or both of the users are not found"),
					HttpStatus.NOT_FOUND);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Remove Friend Relationship","Unexpected error"),
					HttpStatus.EXPECTATION_FAILED);
		}
	}

	@GetMapping(value = "getFriends/{username}")
	public ResponseEntity<RestResponse<List<UserDto>>> getFriends(@PathVariable String username)
	{
		try
		{
			return new ResponseEntity<>(new RestResponse<>(friendService.getFriends(username),
					"Get Friends",
					"Get friends was successful"),
					HttpStatus.OK);
		}
		catch (EntityNotFoundException e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Get Friends",
					"Either one or both of the users are not found"),
					HttpStatus.NOT_FOUND);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Get friends",e.getMessage()),
					HttpStatus.EXPECTATION_FAILED);
		}
	}
}
