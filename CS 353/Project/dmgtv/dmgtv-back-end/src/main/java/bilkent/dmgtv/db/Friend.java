package bilkent.dmgtv.db;

import bilkent.dmgtv.db.base.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@Table(name = "friend", uniqueConstraints = {
//		@UniqueConstraint(
//				columnNames = {"first_username", "second_username"})
//})
public class Friend extends BaseEntity
{
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "first_username", referencedColumnName = "username", nullable = false)
	private User firstUser;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "second_username", referencedColumnName = "username", nullable = false)
	private User secondUser;
}