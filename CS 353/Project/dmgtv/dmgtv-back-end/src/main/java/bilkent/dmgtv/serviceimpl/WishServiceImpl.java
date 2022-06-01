package bilkent.dmgtv.serviceimpl;

import bilkent.dmgtv.db.Movie;
import bilkent.dmgtv.db.User;
import bilkent.dmgtv.db.Wish;
import bilkent.dmgtv.dto.WishDto;
import bilkent.dmgtv.repository.MovieRepository;
import bilkent.dmgtv.repository.UserRepository;
import bilkent.dmgtv.repository.WishRepository;
import bilkent.dmgtv.service.WishService;
import bilkent.dmgtv.serviceimpl.base.BaseServiceImpl;
import bilkent.dmgtv.serviceimpl.mapper.WishMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WishServiceImpl extends BaseServiceImpl<Wish, WishDto> implements WishService
{
	private static final Logger LOGGER = LoggerFactory.getLogger(WishServiceImpl.class);

	private final WishRepository wishRepository;
	private final WishMapper wishMapper;
	private final UserRepository userRepository;
	private final MovieRepository movieRepository;

	public WishServiceImpl(WishRepository wishRepository, WishMapper wishMapper, UserRepository userRepository, MovieRepository movieRepository)
	{
		super(wishRepository, wishMapper);
		this.wishRepository = wishRepository;
		this.wishMapper = wishMapper;
		this.userRepository = userRepository;
		this.movieRepository = movieRepository;
	}

	public WishDto createWish(String movieTitle, String username) throws EntityNotFoundException
	{
		//check if user exists
		Optional<User> user = userRepository.findByUsername(username);
		if (!user.isPresent())
		{
			throw new EntityNotFoundException("User not found");
		}
		// check if movie exists
		Optional<Movie> movie = movieRepository.findByTitle(movieTitle);
		if (!movie.isPresent())
		{
			throw new EntityNotFoundException("Movie not found");
		}
		// check if wish exists
		Wish wish = wishRepository.findByUserUsernameAndMovieTitle(username, movieTitle);
		if (wish != null)
		{
			throw new EntityNotFoundException("Wish already exists!");
		}
		wishRepository.insert(UUID.randomUUID(), user.get().getId(), movie.get().getId(), new Date(System.currentTimeMillis()));
		return wishMapper.entityToDto(wishRepository.findByUserIdAndMovieId(user.get().getId(), movie.get().getId()));
	}


	public WishDto deleteWish(String username, String movieTitle) throws EntityNotFoundException
	{
		Wish wish = wishRepository.findByUserUsernameAndMovieTitle(username, movieTitle);
		wishRepository.deleteByUserUsernameAndMovieTitle(username, movieTitle);
		return wishMapper.entityToDto(wish);
	}

	public List<WishDto> findAllByUsername(String username)
	{
		List<Wish> list = wishRepository.findAllByUserUsername(username);
		for (Wish wish : list)
		{
			LOGGER.info(wish.getUser().getFullName());
		}
		return WishMapper.INSTANCE.entityListToDtoList(list);
	}
}
