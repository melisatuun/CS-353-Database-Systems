package bilkent.dmgtv.repository;

import bilkent.dmgtv.db.Friend;
import bilkent.dmgtv.db.Review;
import bilkent.dmgtv.db.User;
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
public interface ReviewRepository extends BaseRepository<Review, UUID> {
    @Query(value = "select * from review r where r.user_id = ?1 and r.movie_id = ?2", nativeQuery = true)
    Optional<Review> findByUserIdAndMovieId(UUID userId, UUID movieId);
    @Query(value = "select * from review r where r.movie_id = ?1", nativeQuery = true)
    List<Review> findAllByMovieId(UUID movieId);
    @Query(value = "select * from review r where r.user_id = ?1", nativeQuery = true)
    List<Review> findAllByUserId(UUID userId);
    @Modifying
    @Query(value = "insert into review values (?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
    void saveReview(UUID uuid, UUID userId, UUID movieId, Double rating, String review);
}

