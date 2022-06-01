package bilkent.dmgtv.serviceimpl.mapper;

import bilkent.dmgtv.db.Friend;
import bilkent.dmgtv.dto.FriendDto;
import bilkent.dmgtv.serviceimpl.mapper.base.BaseMapper;
import bilkent.dmgtv.serviceimpl.mapper.base.MapConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapConfig.class, componentModel = "spring")
public interface FriendMapper extends BaseMapper<Friend, FriendDto>
{
	FriendMapper INSTANCE = Mappers.getMapper(FriendMapper.class);
}
