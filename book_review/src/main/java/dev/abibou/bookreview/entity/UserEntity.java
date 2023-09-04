package dev.abibou.bookreview.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})} )
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long user_id;
	@Column(unique = true)
	private String username;
	@Column
	private String password;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable( name = "user_roles",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn( name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "role_id", referencedColumnName = "role_id")
//	@Setter(AccessLevel.NONE)
//	private Role role = new Role("USER");
	
	public UserEntity(String username, String password) {
		this.username=username;
		this.password=password;
	}
}
