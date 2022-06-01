package bilkent.dmgtv.db;

import bilkent.dmgtv.db.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "buy", uniqueConstraints = {
//		@UniqueConstraint(
//				columnNames = {"user_id", "movie_id"})
//})
public class Buy extends BaseEntity
{
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	private User user;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "movie_id", referencedColumnName = "id", nullable = false)
	private Movie movie;

	@Column(name = "purchase_date", columnDefinition = "date")
	private Date purchaseDate;
}
