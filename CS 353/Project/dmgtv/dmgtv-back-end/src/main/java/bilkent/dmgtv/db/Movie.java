package bilkent.dmgtv.db;

import bilkent.dmgtv.db.base.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Movie extends BaseEntity
{
	@Column(name = "title", columnDefinition = "varchar(64)", nullable = false)
	private String title;

	@Column(name = "production_year", columnDefinition = "integer", nullable = false)
	private int productionYear;

	@Column(name = "rating", columnDefinition = "numeric(2,1)")
	private double rating;

	@Column(name = "price_per_month", columnDefinition = "integer",nullable = false)
	private int pricePerMonth;

	@Column(name = "price_to_buy", columnDefinition = "integer", nullable = false)
	private int priceToBuy;

	@Column(name = "age_restricted" , columnDefinition = "boolean", nullable = false)
	private boolean ageRestricted;

	@Column(name = "imdb_rating", columnDefinition = "numeric(2,1)", nullable = false)
	private double imdbRating;

	@Column(name = "like_count", columnDefinition = "integer")
	private int likeCount;
}
