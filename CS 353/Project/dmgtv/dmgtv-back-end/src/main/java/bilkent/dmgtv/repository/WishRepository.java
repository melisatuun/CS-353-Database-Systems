package bilkent.dmgtv.repository;

import bilkent.dmgtv.db.Wish;
import bilkent.dmgtv.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Transactional
@Repository
public interface WishRepository extends BaseRepository<Wish, UUID>
{
	@Modifying
	@Query(value = "insert into wish values (?1, ?2, ?3, ?4)", nativeQuery = true)
	void insert(UUID id, UUID userId, UUID movieId, Date wishDate);

	@Query(value = "select * from wish where user_id = ?1 and movie_id = ?2", nativeQuery = true)
	Wish findByUserIdAndMovieId(UUID userId, UUID movieId);

	@Modifying
	@Query(value = "delete from wish where user_id = (select u.id from users u where u.username = ?1) and movie_id = (select m.id from movie m where m.title = ?2)", nativeQuery = true)
	void deleteByUserUsernameAndMovieTitle(String username, String title);

	@Query(value = "select * from wish where user_id = (select u.id from users u where u.username = ?1) and movie_id = (select m.id from movie m where m.title = ?2)", nativeQuery = true)
	Wish findByUserUsernameAndMovieTitle(String username, String title);

	@Query(value = "select * from wish where user_id = (select u.id from users u where u.username = ?1)", nativeQuery = true)
	List<Wish> findAllByUserUsername(String username);
}
