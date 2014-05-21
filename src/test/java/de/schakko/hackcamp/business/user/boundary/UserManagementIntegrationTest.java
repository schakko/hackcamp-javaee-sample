package de.schakko.hackcamp.business.user.boundary;

import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class UserManagementIntegrationTest {
	EntityManager em;
	UserManagement sut;

	@Before
	public void setUp() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("integrationTestPU");
		em = emf.createEntityManager();

		sut = new UserManagement();
		sut.em = em;
	}

	@After
	public void tearDown() {
		em.close();
	}

	@Test(expected=NoResultException.class)
	public void Smoke_test_findUserByUsername() {
		sut.findUserByUsername("some_username");
	}

	@Test
	public void User_is_persistable() {
		em.getTransaction().begin();
		sut.createUser("username", "password");
		em.getTransaction().commit();

		assertNotNull(sut.findUserByUsername("username"));
	}
}
