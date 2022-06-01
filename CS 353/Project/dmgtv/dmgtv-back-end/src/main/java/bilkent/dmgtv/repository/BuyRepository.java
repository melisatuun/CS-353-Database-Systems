package bilkent.dmgtv.repository;

import bilkent.dmgtv.db.Buy;
import bilkent.dmgtv.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Date;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Transactional
@Repository
public interface BuyRepository extends BaseRepository<Buy, UUID>
{
	@Query(value = "select case when count(*) > 0 then true else false end from buy b where b.movie_id = (select m.id from movie m where m.title = ?1) and b.user_id = (select u.id from users u where u.username = ?2)", nativeQuery = true)
	Boolean existsByMovieTitleAndUserUsername(String movieTitle, String userUsername);
	@Query(value = "select * from buy b where b.movie_id = (select m.id from movie m where m.title = ?1) and b.user_id = (select u.id from users u where u.username = ?2)", nativeQuery = true)
	Buy getByMovieTitleAndUserUsername(String movieTitle, String userUsername);
	@Query(value = "select * from buy b where b.user_id = (select u.id from users u where u.username = ?1)", nativeQuery = true)
	List<Buy> getByUserUsername(String userUsername);
	@Modifying
	@Query(value = "insert into buy values (?1, ?2, ?3, ?4)", nativeQuery = true)
	void insert(UUID id, UUID userId, UUID movieId, Date purchaseDate);
}
