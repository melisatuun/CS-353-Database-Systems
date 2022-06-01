package bilkent.dmgtv.serviceimpl.mapper;

import bilkent.dmgtv.db.Movie;
import bilkent.dmgtv.dto.MovieDto;
import bilkent.dmgtv.serviceimpl.mapper.base.BaseMapper;
import bilkent.dmgtv.serviceimpl.mapper.base.MapConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapConfig.class, componentModel = "spring")
public interface MovieMapper extends BaseMapper<Movie, MovieDto>
{
	MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);
}
