package bilkent.dmgtv.db;

import bilkent.dmgtv.db.base.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Table(name = "request")
public class Request extends BaseEntity
{
	@Column(name = "movie_name", columnDefinition = "varchar(64)", nullable = false)
	private String movieName;

	@Column(name = "production_year", columnDefinition = "numeric(4,0)", nullable = false)
	private int productionYear;
}
