package bilkent.dmgtv.service;

import bilkent.dmgtv.db.ReviewRequest;
import bilkent.dmgtv.dto.FriendDto;
import bilkent.dmgtv.dto.MovieDto;
import bilkent.dmgtv.dto.ReviewDto;
import bilkent.dmgtv.service.base.BaseCrudService;

import java.util.List;

public interface ReviewService extends BaseCrudService<ReviewDto>
{
    List<ReviewDto> getUserReviews(String username);
    List<ReviewDto> getMovieReviews(String movieId);
    ReviewDto add(ReviewRequest reviewRequest);
    void calculateRating(String movieId);
}
