package bilkent.dmgtv.db;

import bilkent.dmgtv.db.base.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "rent", uniqueConstraints = {
//		@UniqueConstraint(
//				columnNames = {"user_id", "movie_id", "start_date"})
//})
public class Rent extends BaseEntity
{
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	private User user;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "movie_id", referencedColumnName = "id", nullable = false)
	private Movie movie;

	@Column(name = "start_date", columnDefinition = "date")
	private Date startDate;

	@Column(name = "end_date", columnDefinition = "date")
	private Date endDate;

}
