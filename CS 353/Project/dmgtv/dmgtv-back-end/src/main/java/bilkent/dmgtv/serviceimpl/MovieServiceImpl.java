package bilkent.dmgtv.serviceimpl;

import bilkent.dmgtv.db.Movie;
import bilkent.dmgtv.db.MovieFilterYear;
import bilkent.dmgtv.dto.MovieDto;
import bilkent.dmgtv.dto.ReviewDto;
import bilkent.dmgtv.repository.MovieRepository;
import bilkent.dmgtv.service.MovieService;
import bilkent.dmgtv.serviceimpl.base.BaseServiceImpl;
import bilkent.dmgtv.serviceimpl.mapper.MovieMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MovieServiceImpl extends BaseServiceImpl<Movie, MovieDto> implements MovieService
{
	private static final Logger LOGGER = LoggerFactory.getLogger(MovieServiceImpl.class);

	private final MovieRepository movieRepository;
	private final MovieMapper movieMapper;

	public MovieServiceImpl(MovieRepository movieRepository, MovieMapper movieMapper)
	{
		super(movieRepository, MovieMapper.INSTANCE);
		this.movieRepository = movieRepository;
		this.movieMapper = movieMapper;
	}


	@Override
	public MovieDto create(MovieDto dto) throws EntityNotFoundException {
		movieRepository.insert(UUID.randomUUID(), dto.getTitle(), dto.getProductionYear(), dto.getRating(), dto.getPricePerMonth(), dto.getPriceToBuy(), dto.getAgeRestricted(), dto.getImdbRating(), 0);
		return movieMapper.entityToDto(movieRepository.findByTitle(dto.getTitle()).get());
	}

	public List<MovieDto> filterByYear(MovieFilterYear movieFilterYear) throws EntityNotFoundException
	{
		if (movieFilterYear.getLowerYear() == null || movieFilterYear.getUpperYear() == null)
		{
			LOGGER.warn("Filter information cannot be empty");
			throw new EntityNotFoundException();
		}
		List<MovieDto> movies = movieMapper.entityListToDtoList(
				movieRepository.findAllByProductionYearGreaterThanEqualAndProductionYearLessThanEqual(
						movieFilterYear.getLowerYear(), movieFilterYear.getUpperYear()
				)
		);
		return movies;
	}
}