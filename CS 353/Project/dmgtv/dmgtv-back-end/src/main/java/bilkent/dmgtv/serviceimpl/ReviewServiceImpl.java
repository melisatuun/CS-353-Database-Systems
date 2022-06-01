package bilkent.dmgtv.serviceimpl;

import bilkent.dmgtv.db.*;
import bilkent.dmgtv.dto.FriendDto;
import bilkent.dmgtv.dto.ReviewDto;
import bilkent.dmgtv.dto.UserDto;
import bilkent.dmgtv.repository.FriendRepository;
import bilkent.dmgtv.repository.MovieRepository;
import bilkent.dmgtv.repository.ReviewRepository;
import bilkent.dmgtv.repository.UserRepository;
import bilkent.dmgtv.repository.base.BaseRepository;
import bilkent.dmgtv.service.FriendService;
import bilkent.dmgtv.service.ReviewService;
import bilkent.dmgtv.serviceimpl.base.BaseServiceImpl;
import bilkent.dmgtv.serviceimpl.mapper.FriendMapper;
import bilkent.dmgtv.serviceimpl.mapper.ReviewMapper;
import bilkent.dmgtv.serviceimpl.mapper.base.BaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReviewServiceImpl extends BaseServiceImpl<Review, ReviewDto> implements ReviewService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewServiceImpl.class);

    private final ReviewMapper reviewMapper;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ReviewMapper reviewMapper, UserRepository userRepository, MovieRepository movieRepository) {
        super(reviewRepository, reviewMapper);
        this.reviewMapper = reviewMapper;
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ReviewDto create(ReviewDto reviewDto) {
        Review entity = ReviewMapper.INSTANCE.dtoToEntity(reviewDto);
        if (entity == null) {
            LOGGER.warn("The entity to save cannot be empty!");
            throw new EntityNotFoundException();
        }
        Optional<User> userOptional = userRepository.findByUsername(reviewDto.getUser().getUsername());
        if (!userOptional.isPresent())
        {
            LOGGER.warn("User not found");
            throw new EntityNotFoundException();
        }
        entity.setUser(userOptional.get());
        Optional<Movie> movieOptional = movieRepository.findByTitleAndProductionYear(reviewDto.getMovie().getTitle(), reviewDto.getMovie().getProductionYear());
        if (!movieOptional.isPresent())
        {
            LOGGER.warn("Movie not found");
            throw new EntityNotFoundException();
        }
        entity.setUser(userOptional.get());
        entity.setMovie(movieOptional.get());
        entity.setComment(reviewDto.getComment());
        entity.setRating(reviewDto.getRating());
        ReviewDto newReviewDto = super.create(ReviewMapper.INSTANCE.entityToDto(entity));
        calculateRating(movieOptional.get().getId().toString());
        return newReviewDto;
    }

    public List<ReviewDto> getUserReviews(String username) throws EntityNotFoundException
    {
        if (username == null)
        {
            LOGGER.warn("Username cannot be empty");
            throw new EntityNotFoundException();
        }
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent()) {
            LOGGER.warn("No such user");
            throw new EntityNotFoundException();
        }
        List<ReviewDto> reviews = reviewMapper.entityListToDtoList(reviewRepository.findAllByUserId(userOptional.get().getId()));
        return reviews;
    }

    public List<ReviewDto> getMovieReviews(String movieId) throws EntityNotFoundException
    {
        if (movieId == null)
        {
            LOGGER.warn("Movie id cannot be empty");
            throw new EntityNotFoundException();
        }
        Optional<Movie> movieOptional = movieRepository.findById(UUID.fromString(movieId));
        if (!movieOptional.isPresent()) {
            LOGGER.warn("No such movie");
            throw new EntityNotFoundException();
        }
        List<ReviewDto> reviews = reviewMapper.entityListToDtoList(reviewRepository.findAllByMovieId(UUID.fromString(movieId)));
        return reviews;
    }

    public ReviewDto add(ReviewRequest reviewRequest) {
        Optional<User> user = userRepository.findByUsername(reviewRequest.getUsername());

        Optional<Review> reviewOptional = reviewRepository.findByUserIdAndMovieId(user.get().getId(), UUID.fromString(reviewRequest.getMovieId()));
        if (!reviewOptional.isPresent()) {
            if (user.isPresent()) {
                Optional<Movie> movie = movieRepository.findById(UUID.fromString(reviewRequest.getMovieId()));
                if (movie.isPresent()) {
                    Review review = new Review(user.get(), movie.get(), reviewRequest.getRating(), reviewRequest.getComment());
                    review.setId(UUID.randomUUID());
                    reviewRepository.saveReview(review.getId(),review.getUser().getId(), review.getMovie().getId(), review.getRating(), review.getComment());
                    ReviewDto reviewDto = ReviewMapper.INSTANCE.entityToDto( reviewRepository.findByUserIdAndMovieId(review.getUser().getId(), review.getMovie().getId()).get());
                    calculateRating(reviewRequest.getMovieId());
                    return reviewDto;
                }
                else {
                    throw new EntityNotFoundException("Movie not found");
                }
            }
            else {
                throw new EntityNotFoundException("User not found");
            }
        }
        else {
            throw new EntityNotFoundException("Such review exists");
        }
    }

    public void calculateRating(String movieId) {
        Optional<Movie> movie = movieRepository.findById(UUID.fromString(movieId));
        if (movie.isPresent()) {
            double average = 0;
            List<Review> reviews = reviewRepository.findAllByMovieId(UUID.fromString(movieId));
            for (int i = 0; i < reviews.size(); i++) {
                average = average + reviews.get(i).getRating();
            }
            average = average / reviews.size();
            movie.get().setRating(average);
            movieRepository.save(movie.get());
        }
        else {
            throw new EntityNotFoundException("Movie not found");
        }
    }
}

