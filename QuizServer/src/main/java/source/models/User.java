	package source.models;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(
	name = "users",
	indexes = { @Index(columnList = "email",name =  "user_email")}
)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
    private String id;
	
	@Email(message = "Invalid email")
	@Column(name = "email",nullable = false,unique = true)
	private String email;
	
	@NotBlank(message = "Name is required")
	private String name;
	
	@Enumerated(EnumType.STRING)
	@NotBlank(message = "Role is required")
	@Column(name = "role")
	private Role role = Role.getDefault();
	
	@Column(name = "profile",length = 400)
	private String profile;
	@NotBlank(message =  "Password is required")
	@Size(min = 4,max = 12,message = "Password must be between 4 to 12 characters")
	private String password;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(name = "created_at",nullable = false,updatable = false)
	private Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

	@OneToMany(
		fetch = FetchType.LAZY,
		cascade = CascadeType.ALL,
		mappedBy = "user"
	)
	private List<Attempt> attempts;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public List<Attempt> getAttempts() {
		return attempts;
	}

	public void setAttempts(List<Attempt> attempts) {
		this.attempts = attempts;
	}
	 
	
    
}
