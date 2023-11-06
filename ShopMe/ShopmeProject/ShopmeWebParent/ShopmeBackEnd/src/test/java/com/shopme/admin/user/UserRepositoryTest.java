package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
	@Autowired
	private UserRepository userrepo;
	@Autowired
	private TestEntityManager entitymanager;

	@Test
	public void testUserWithOneRole() {
		Role manager = entitymanager.find(Role.class, 1);
		User username = new User("karthikeyan@gmail.com", "Kartth@122", "karthikeyan", "KK");
		username.addRole(manager);
		User savedUser = userrepo.save(username);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}

	@Test
	public void testUserwithTwoRole() {
		User uservv = new User("vivekanannda@12gmail.com", "vivek@12", "Vivek", "Anandhan");
		Role roleEditor = new Role(3);
		Role roleAssistent = new Role(5);
		uservv.addRole(roleEditor);
		uservv.addRole(roleAssistent);
		User savedusered = userrepo.save(uservv);
		assertThat(savedusered.getId()).isGreaterThan(0);
	}

	@Test
	public void testAlluser() {
		Iterable<User> listuser = userrepo.findAll();

		listuser.forEach(user -> System.out.println(user));

	}

	@Test
	public void testUserId() {
		User username = userrepo.findById(1).get();
		assertThat(username).isNotNull();

	}

	@Test
	public void testUpdateUser() {
		User usernames = userrepo.findById(1).get();
		usernames.setEnabled(true);
		usernames.setEmail("zameer12@gmail.com");
		userrepo.save(usernames);
	}

//	@Test
//	public boolean isEmailUnique(String email) {
//		User userByEmail = userrepo.getUserByEmail(email);
//		return userByEmail == null;
//	}
	@Test
	public void testGetUserByEmail()
	{
		String email="zameer12@gmail.com";
		User user =userrepo.getUserByEmail(email);
		assertThat(user).isNotNull();
	}
}
