package bilkent.dmgtv.serviceimpl.mapper;

import bilkent.dmgtv.db.Rent;
import bilkent.dmgtv.dto.RentDto;
import bilkent.dmgtv.serviceimpl.mapper.base.BaseMapper;
import bilkent.dmgtv.serviceimpl.mapper.base.MapConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapConfig.class, componentModel = "spring")
public interface RentMapper extends BaseMapper<Rent, RentDto>
{
	RentMapper INSTANCE = Mappers.getMapper(RentMapper.class);
}
