package bilkent.dmgtv.serviceimpl.mapper;

import bilkent.dmgtv.db.Wish;
import bilkent.dmgtv.dto.WishDto;
import bilkent.dmgtv.serviceimpl.mapper.base.BaseMapper;
import bilkent.dmgtv.serviceimpl.mapper.base.MapConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapConfig.class, componentModel = "spring")
public interface WishMapper extends BaseMapper<Wish, WishDto>
{
	WishMapper INSTANCE = Mappers.getMapper(WishMapper.class);
}
