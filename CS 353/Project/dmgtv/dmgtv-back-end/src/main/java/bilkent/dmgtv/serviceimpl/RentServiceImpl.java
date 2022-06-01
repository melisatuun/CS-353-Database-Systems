package bilkent.dmgtv.serviceimpl;

import bilkent.dmgtv.db.Movie;
import bilkent.dmgtv.db.Rent;
import bilkent.dmgtv.db.User;
import bilkent.dmgtv.dto.MovieDto;
import bilkent.dmgtv.dto.RentDto;
import bilkent.dmgtv.repository.MovieRepository;
import bilkent.dmgtv.repository.RentRepository;
import bilkent.dmgtv.repository.UserRepository;
import bilkent.dmgtv.service.RentService;
import bilkent.dmgtv.serviceimpl.base.BaseServiceImpl;
import bilkent.dmgtv.serviceimpl.mapper.MovieMapper;
import bilkent.dmgtv.serviceimpl.mapper.RentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
public class RentServiceImpl extends BaseServiceImpl<Rent, RentDto> implements RentService
{
	private static final Logger LOGGER = LoggerFactory.getLogger(RentServiceImpl.class);

	private final RentRepository rentRepository;
	private final RentMapper rentMapper;
	private final MovieRepository movieRepository;
	private final UserRepository userRepository;

	public RentServiceImpl(RentRepository rentRepository, RentMapper rentMapper, MovieRepository movieRepository, UserRepository userRepository)
	{
		super(rentRepository, RentMapper.INSTANCE);
		this.rentRepository = rentRepository;
		this.rentMapper = rentMapper;
		this.movieRepository = movieRepository;
		this.userRepository = userRepository;
	}

	public RentDto rentMovie(String movieName, String username)
	{
		Optional<User> user = userRepository.findByUsername(username);
		// check if user exists
		if (!user.isPresent())
		{
			LOGGER.error("User with username {} does not exist", username);
			return null;
		}
		Optional<Movie> movie = movieRepository.findByTitle(movieName);
		// check if movie exists
		if (!movie.isPresent())
		{
			LOGGER.error("Movie with name {} does not exist", movieName);
			return null;
		}
		// check if movie is already rented by the user
		Rent rent = rentRepository.findByUserUsernameAndMovieTitleAndEndDateIsNull(user.get().getUsername(), movie.get().getTitle());
		if (rent != null)
		{
			throw new EntityNotFoundException("Movie is already rented by the user");
		}
		// rent movie
		rent = new Rent();
		rent.setMovie(movie.get());
		rent.setUser(user.get());
		rent.setStartDate(new Date(System.currentTimeMillis()));

		rentRepository.insert(UUID.randomUUID(), rent.getUser().getUsername(), rent.getMovie().getTitle(), rent.getStartDate());
		return rentMapper.entityToDto(rentRepository.findByUserUsernameAndMovieTitleAndEndDateIsNull(user.get().getUsername(), movie.get().getTitle()));
	}

	public List<MovieDto> getRentedMovies(String username)
	{
		Optional<User> user = userRepository.findByUsername(username);
		// check if user exists
		if (!user.isPresent())
		{
			LOGGER.error("User with username {} does not exist", username);
			return null;
		}
		List<Rent> rentList = rentRepository.findByUserUsernameAndEndDateIsNull(user.get().getUsername());
		List<Movie> movieList = new ArrayList<>();
		for (Rent rent : rentList)
		{
			movieList.add(rent.getMovie());
		}
		return MovieMapper.INSTANCE.entityListToDtoList(movieList);
	}

	@Override
	public RentDto stopRenting(String movieName, String username)
	{
		Optional<User> user = userRepository.findByUsername(username);
		// check if user exists
		if (!user.isPresent())
		{
			LOGGER.error("User with username {} does not exist", username);
			return null;
		}
		Optional<Movie> movie = movieRepository.findByTitle(movieName);
		// check if movie exists
		if (!movie.isPresent())
		{
			LOGGER.error("Movie with name {} does not exist", movieName);
			return null;
		}

		// stop renting movie
		Rent rent = rentRepository.findByUserUsernameAndMovieTitleAndEndDateIsNull(user.get().getUsername(), movie.get().getTitle());
		if (rent == null)
		{
			LOGGER.error("User {} is not renting movie {}", username, movieName);
			return null;
		}
		rentRepository.updateEndDateById(new Date(System.currentTimeMillis()), rent.getId());

		return rentMapper.entityToDto(rent);
	}
}