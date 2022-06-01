package bilkent.dmgtv.serviceimpl.mapper;

import bilkent.dmgtv.db.Request;
import bilkent.dmgtv.dto.RequestDto;
import bilkent.dmgtv.serviceimpl.mapper.base.BaseMapper;
import bilkent.dmgtv.serviceimpl.mapper.base.MapConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapConfig.class, componentModel = "spring")
public interface RequestMapper extends BaseMapper<Request, RequestDto>
{
	RequestMapper INSTANCE = Mappers.getMapper(RequestMapper.class);
}
