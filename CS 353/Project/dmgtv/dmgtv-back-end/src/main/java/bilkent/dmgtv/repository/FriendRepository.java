package bilkent.dmgtv.repository;

import bilkent.dmgtv.db.Friend;
import bilkent.dmgtv.db.Movie;
import bilkent.dmgtv.dto.FriendDto;
import bilkent.dmgtv.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Repository
public interface FriendRepository extends BaseRepository<Friend, UUID> {
	// find all friends by the username of the first user
	@Query(value = "select * from friend f where f.first_username= ?1", nativeQuery = true)
	List<Friend> findByFirstUserUsername(String username);

	@Modifying
	@Query(value = "insert into friend values (?1, ?2, ?3)",nativeQuery = true)
	void insert(UUID id, String firstUsername, String secondUsername);

	@Query(value = "select * from friend f where f.first_username= ?1 and f.second_username = ?2", nativeQuery = true)
	Optional<Friend> findByFirstUserUsernameAndSecondUserUsername(String firstUsername, String secondUsername);
}

