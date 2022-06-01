package bilkent.dmgtv.repository;


import bilkent.dmgtv.db.Request;
import bilkent.dmgtv.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Transactional
@Repository
public interface RequestRepository extends BaseRepository<Request, UUID>
{
	@Modifying
	@Query(value = "insert into request values (?1, ?2, ?3)", nativeQuery = true)
	void insert(UUID uuid, String movieName, Integer productionYear);

	@Query(value = "select * from request where movie_name = ?1 and production_year = ?2", nativeQuery = true)
	Request findByMovieNameAndProductionYear(String movieName, Integer productionYear);
}
