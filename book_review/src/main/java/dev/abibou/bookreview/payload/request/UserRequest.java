package dev.abibou.bookreview.payload.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
	@Size(min=4, max=20, message="Username must be between 4 and 20 characters")
	private String username;
	@Min(value=7, message="password must be minimum 7 characters")
	private String password;
	@NotBlank(message = "Role is mandatory")
	private String role;
}
