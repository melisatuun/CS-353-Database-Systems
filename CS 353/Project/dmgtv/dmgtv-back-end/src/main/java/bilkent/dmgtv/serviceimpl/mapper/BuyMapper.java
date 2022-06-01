package bilkent.dmgtv.serviceimpl.mapper;

import bilkent.dmgtv.db.Buy;
import bilkent.dmgtv.dto.BuyDto;
import bilkent.dmgtv.serviceimpl.mapper.base.BaseMapper;
import bilkent.dmgtv.serviceimpl.mapper.base.MapConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapConfig.class, componentModel = "spring")
public interface BuyMapper extends BaseMapper<Buy, BuyDto>
{
	BuyMapper INSTANCE = Mappers.getMapper(BuyMapper.class);
}
