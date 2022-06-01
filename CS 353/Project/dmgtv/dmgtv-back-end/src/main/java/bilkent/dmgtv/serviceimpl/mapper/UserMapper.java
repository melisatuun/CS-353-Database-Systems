package bilkent.dmgtv.serviceimpl.mapper;

import bilkent.dmgtv.db.User;
import bilkent.dmgtv.dto.UserDto;
import bilkent.dmgtv.serviceimpl.mapper.base.BaseMapper;
import bilkent.dmgtv.serviceimpl.mapper.base.MapConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapConfig.class, componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDto> {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
}

