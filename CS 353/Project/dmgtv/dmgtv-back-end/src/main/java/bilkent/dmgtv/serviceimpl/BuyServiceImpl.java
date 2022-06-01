package bilkent.dmgtv.serviceimpl;

import bilkent.dmgtv.db.Buy;
import bilkent.dmgtv.db.Movie;
import bilkent.dmgtv.db.User;
import bilkent.dmgtv.dto.BuyDto;
import bilkent.dmgtv.dto.MovieDto;
import bilkent.dmgtv.repository.BuyRepository;
import bilkent.dmgtv.repository.MovieRepository;
import bilkent.dmgtv.repository.UserRepository;
import bilkent.dmgtv.service.BuyService;
import bilkent.dmgtv.serviceimpl.base.BaseServiceImpl;
import bilkent.dmgtv.serviceimpl.mapper.BuyMapper;
import bilkent.dmgtv.serviceimpl.mapper.MovieMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BuyServiceImpl extends BaseServiceImpl<Buy, BuyDto> implements BuyService
{
	private static final Logger LOGGER = LoggerFactory.getLogger(BuyServiceImpl.class);

	private final BuyMapper buyMapper;
	private final BuyRepository buyRepository;
	private final UserRepository userRepository;
	private final MovieRepository movieRepository;

	public BuyServiceImpl(BuyRepository buyRepository, BuyMapper buyMapper, UserRepository userRepository, MovieRepository movieRepository)
	{
		super(buyRepository, buyMapper);
		this.buyMapper = buyMapper;
		this.buyRepository = buyRepository;
		this.userRepository = userRepository;
		this.movieRepository = movieRepository;
	}

	@Override
	public BuyDto buyMovie(String movieTitle, String username)
	{
		// check if user exists
		Optional<User> user = userRepository.findByUsername(username);
		if (!user.isPresent())
		{
			LOGGER.error("User with username {} does not exist", username);
			throw new EntityNotFoundException("User with username " + username + " does not exist");
		}

		// check if movie exists
		Optional<Movie> movie = movieRepository.findByTitle(movieTitle);
		if (!movie.isPresent())
		{
			LOGGER.error("Movie with title {} does not exist", movieTitle);
			throw new EntityNotFoundException("Movie with title " + movieTitle + " does not exist");
		}

		// check if movie is already bought
		if (buyRepository.existsByMovieTitleAndUserUsername(movieTitle, username))
		{
			LOGGER.error("Movie with title {} is already bought by user {}", movieTitle, username);
			throw new EntityNotFoundException("Movie with title " + movieTitle + " is already bought by user " + username);
		}

		// save buy
		buyRepository.insert(UUID.randomUUID(), user.get().getId(), movie.get().getId(), new java.sql.Date(new java.util.Date().getTime()));

		// return buy
		return buyMapper.entityToDto(buyRepository.getByMovieTitleAndUserUsername(movieTitle, username));
	}

	@Override
	public List<MovieDto> getAllMovies(String username)
	{
		// check if user exists
		Optional<User> user = userRepository.findByUsername(username);
		if (!user.isPresent())
		{
			LOGGER.error("User with username {} does not exist", username);
			throw new EntityNotFoundException("User with username " + username + " does not exist");
		}

		// get all buys
		List<Buy> buys = buyRepository.getByUserUsername(username);

		// return movies
		List<Movie> movieList = new ArrayList<>();
		for (Buy buy : buys)
		{
			movieList.add(buy.getMovie());
		}
		// return buys
		return MovieMapper.INSTANCE.entityListToDtoList(movieList);
	}
}
