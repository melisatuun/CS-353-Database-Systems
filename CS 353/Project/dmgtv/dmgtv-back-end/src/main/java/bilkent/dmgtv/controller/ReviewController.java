package bilkent.dmgtv.controller;

import bilkent.dmgtv.controller.base.BaseController;
import bilkent.dmgtv.db.LoginRequest;
import bilkent.dmgtv.db.ReviewRequest;
import bilkent.dmgtv.dto.FriendDto;
import bilkent.dmgtv.dto.ReviewDto;
import bilkent.dmgtv.dto.UserDto;
import bilkent.dmgtv.dto.base.RestResponse;
import bilkent.dmgtv.service.FriendService;
import bilkent.dmgtv.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("review")
public class ReviewController extends BaseController<ReviewDto>
{
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService)
    {
        super(reviewService);
        this.reviewService = reviewService;
    }

    @GetMapping(value = "user/{username}")
    public ResponseEntity<RestResponse<List<ReviewDto>>> getUserReviews(@PathVariable String username)
    {
        try
        {
            return new ResponseEntity<>(new RestResponse<>(reviewService.getUserReviews(username), "Get User Reviews",
                    "Entity retrieval was successful"),
                    HttpStatus.OK);
        }
        catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>(new RestResponse<>(null, "Get User Reviews",
                    "Entity retrieval was unsuccessful due to an error"),
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new RestResponse<>(null, "Get User Reviews",
                    "Unexpected error"),
                    HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping(value = "movie/{movieId}")
    public ResponseEntity<RestResponse<List<ReviewDto>>> getMovieReviews(@PathVariable String movieId)
    {
        try
        {
            return new ResponseEntity<>(new RestResponse<>(reviewService.getMovieReviews(movieId), "Get Movie Reviews",
                    "Entity retrieval was successful"),
                    HttpStatus.OK);
        }
        catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>(new RestResponse<>(null, "Get Movie Reviews",
                    "Entity retrieval was unsuccessful due to an error"),
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new RestResponse<>(null, "Get Movie Reviews",
                    "Unexpected error"),
                    HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping(value = "add")
    public ResponseEntity<RestResponse<ReviewDto>> add(@RequestBody ReviewRequest reviewRequest)
    {
        try
        {
            return new ResponseEntity<>(new RestResponse<>(reviewService.add(reviewRequest),
                    "Add Review",
                    "Review submission was successful"),
                    HttpStatus.OK);
        }
        catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>(new RestResponse<>(null, "Add Review",
                    "User or movie not found"),
                    HttpStatus.NOT_FOUND);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new RestResponse<>(null, "Add Review",
                    "Unexpected error"),
                    HttpStatus.EXPECTATION_FAILED);
        }
    }
}
