package bilkent.dmgtv.db;

import bilkent.dmgtv.db.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Wish extends BaseEntity
{
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	private User user;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "movie_id", referencedColumnName = "id", nullable = false)
	private Movie movie;
	private Date wishDate;
}
