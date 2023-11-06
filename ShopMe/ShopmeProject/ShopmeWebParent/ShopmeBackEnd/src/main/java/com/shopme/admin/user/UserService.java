package com.shopme.admin.user;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@Service
public class UserService {
	@Autowired
	private UserRepository userrespo;
	@Autowired
	private RoleRepository rolerepo;
	
//	@Autowired
//	private PasswordEncoder passwordencoder;
	
	public List<User> listAll()
	{
		return (List<User>) userrespo.findAll();
	}
	public List<Role> listRoles()
	{
	return (List<Role>) rolerepo.findAll()	;
	}
	public void save(User user) {
		boolean isUpdatingUser=(user.getId()!= null);
		if(isUpdatingUser) {
			User userExisting=userrespo.findById(user.getId()).get();
			if(user.getPassword().isEmpty())
			{
				user.setPassword(userExisting.getPassword());
			}
			else
			{
				encodePassword(user);
			}
			
		}
		
//		
		
		userrespo.save(user);
		
	}

	private void encodePassword(User user) {
		// TODO Auto-generated method stub
		
	}
	public boolean isEmailUnique(String email) {
		User userByEmail = userrespo.getUserByEmail(email);
		return userByEmail == null;
		 
	}
	public User get(Integer id) throws UsernotFoundException {
		try {
		return userrespo.findById(id).get();
		}
		catch(NoSuchElementException ex)
		{
			throw new UsernotFoundException("Could not find any ID"+id);
		}
	}

}
