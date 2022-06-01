package bilkent.dmgtv.serviceimpl;

import bilkent.dmgtv.db.Friend;
import bilkent.dmgtv.db.User;
import bilkent.dmgtv.dto.FriendDto;
import bilkent.dmgtv.dto.UserDto;
import bilkent.dmgtv.repository.FriendRepository;
import bilkent.dmgtv.repository.UserRepository;
import bilkent.dmgtv.service.FriendService;
import bilkent.dmgtv.serviceimpl.base.BaseServiceImpl;
import bilkent.dmgtv.serviceimpl.mapper.FriendMapper;
import bilkent.dmgtv.serviceimpl.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FriendServiceImpl extends BaseServiceImpl<Friend, FriendDto> implements FriendService
{
	private static final Logger LOGGER = LoggerFactory.getLogger(FriendServiceImpl.class);

	private final FriendMapper friendMapper;
	private final FriendRepository friendRepository;
	private final UserRepository userRepository;

	public FriendServiceImpl(FriendRepository friendRepository, FriendMapper friendMapper, UserRepository userRepository)
	{
		super(friendRepository, friendMapper);
		this.friendMapper = friendMapper;
		this.friendRepository = friendRepository;
		this.userRepository = userRepository;
	}

	public FriendDto addFriend(String firstUsername, String secondUsername)
	{
		Optional<User> firstUser = userRepository.findByUsername(firstUsername);
		Optional<User> secondUser = userRepository.findByUsername(secondUsername);
		if (firstUser.isPresent() && secondUser.isPresent()){
			// we need to add the friend to the first user
			friendRepository.insert(UUID.randomUUID(), firstUsername, secondUsername);
			// we need to add the friend to the second user
			friendRepository.insert(UUID.randomUUID(), secondUsername, firstUsername);
			return friendMapper.entityToDto(friendRepository.findByFirstUserUsernameAndSecondUserUsername(firstUsername, secondUsername).get());
		}
		else
		{
			throw new EntityNotFoundException("One/Both of the users not found");
		}
	}

	public List<UserDto> deleteFriend(String firstUsername, String secondUsername)
	{
		Optional<User> firstUser = userRepository.findByUsername(firstUsername);
		Optional<User> secondUser = userRepository.findByUsername(secondUsername);
		if (firstUser.isPresent() && secondUser.isPresent()) {
			List<UserDto> friends = new ArrayList<>();
			friends.add(UserMapper.INSTANCE.entityToDto(firstUser.get()));
			friends.add(UserMapper.INSTANCE.entityToDto(secondUser.get()));
			Optional<Friend> friendOptionalFirst = friendRepository.findByFirstUserUsernameAndSecondUserUsername(firstUsername, secondUsername);
			Optional<Friend> friendOptionalSecond = friendRepository.findByFirstUserUsernameAndSecondUserUsername(secondUsername, firstUsername);
			if (friendOptionalFirst.isPresent() && friendOptionalSecond.isPresent()) {
				friendRepository.delete(friendOptionalFirst.get());
				friendRepository.delete(friendOptionalSecond.get());
				return friends;
			}
			else
			{
				throw new EntityNotFoundException("Friend relation not found");
			}
		}
		else
		{
			throw new EntityNotFoundException("One/Both of the users not found");
		}
	}

	public List<UserDto> getFriends(String username)
	{
		List<UserDto> friends = new ArrayList<>();
		List<Friend> list = friendRepository.findByFirstUserUsername(username);
		for (Friend friend : list){
			friends.add(UserMapper.INSTANCE.entityToDto(friend.getSecondUser()));
		}
		return friends;
	}
}

