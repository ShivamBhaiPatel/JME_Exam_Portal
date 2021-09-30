package com.app.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
// here we will implement the userDetails security... and all userdetails methods will be imported and overridden
public class User implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length=30)
	private Long id;
	@Column(length=30)
	private String userName;
	@Column(length=30) 
	private String password;
	@Column(length=30)
	private String firstName;
	@Column(length=30)
	private String lastName;
	@Column(length=30)
	private String email;
	@Column(length=30)
	private String phone;
	private boolean enabled=true;
	private String profile;
	
	//user many roles
	//one to many
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy ="user" )
	@JsonIgnore
	private Set<UserRole> userRoles=new HashSet<UserRole>();

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 
		Set<Authority> set=new HashSet<>();
		// here we will return a set containing all the authorities
		this.userRoles.forEach(userRole -> {
			set.add(new Authority(userRole.getRole().getRoleName()));
		});
		
		return set;
	}
	

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
}
