package dev.abibou.bookreview.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer review_id;
	private Integer user_id;
	@NotBlank(message = "comment is mandantory")
	private String comment;

}
