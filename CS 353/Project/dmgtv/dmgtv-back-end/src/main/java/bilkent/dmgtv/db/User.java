package bilkent.dmgtv.db;

import bilkent.dmgtv.db.base.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity
{
	//Attributes
	@Column(name = "username", nullable = false, columnDefinition = "varchar(20)", unique = true)
	private String username;

	@Column(name = "password", nullable = false, columnDefinition = "varchar(16)")
	private String password;

	@Column(name = "full_name", nullable = false, columnDefinition = "varchar(50)")
	private String fullName;

	@Column(name ="birth_date", nullable = false, columnDefinition = "date")
	private Date birthDate;
}
