package bilkent.dmgtv.serviceimpl.mapper;

import bilkent.dmgtv.db.Friend;
import bilkent.dmgtv.db.Review;
import bilkent.dmgtv.dto.FriendDto;
import bilkent.dmgtv.dto.ReviewDto;
import bilkent.dmgtv.serviceimpl.mapper.base.BaseMapper;
import bilkent.dmgtv.serviceimpl.mapper.base.MapConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapConfig.class, componentModel = "spring")
public interface ReviewMapper extends BaseMapper<Review, ReviewDto>
{
   ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);
}
