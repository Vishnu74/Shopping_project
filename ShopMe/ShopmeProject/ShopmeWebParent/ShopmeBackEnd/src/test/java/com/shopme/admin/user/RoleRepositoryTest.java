package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Role;

@DataJpaTest
@AutoConfigureTestDatabase(replace =Replace.NONE)
@Rollback(false)
public class RoleRepositoryTest {
	@Autowired
	private RoleRepository repo;
	
	@Test
	public void TestCreateFile()
	{
		Role roleadmin = new Role("manager","manager everything");
		Role savedRole =repo.save(roleadmin);
		assertThat(savedRole.getId()).isGreaterThan(0);
	}
	@Test
	public void TestCreateRestRoles()
	{
		Role rolesalesperson = new Role("Salesperson","manager product price, "+"customers,shipping,order and salesreport");
		Role roleEditor = new Role("Editor","manager categories ,brands, "+"products,article and menu");
		Role roleShip = new Role("Shippe","view products ,view order, "+"update order status");
		Role roleassistant = new Role("RoleAssisteant","manage questions and review");
	repo.saveAll(List.of(rolesalesperson,roleEditor,roleShip,roleassistant));
	}
}
