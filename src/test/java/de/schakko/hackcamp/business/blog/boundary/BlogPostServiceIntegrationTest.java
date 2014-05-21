package de.schakko.hackcamp.business.blog.boundary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import de.schakko.hackcamp.business.blog.entity.Post;

@RunWith(BlockJUnit4ClassRunner.class)
public class BlogPostServiceIntegrationTest {
	EntityManager em;
	BlogPostService sut;

	@Before
	public void setUp() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("integrationTestPU");
		em = emf.createEntityManager();

		sut = new BlogPostService();
		sut.em = em;
	}

	@After
	public void tearDown() {
		em.close();
	}

	@Test(expected = NoResultException.class)
	public void Smoke_test_findById() {
		sut.find(1);
	}
	
	@Test
	public void Smoke_test_findAll() {
		sut.findAll();
	}

	@Test
	public void Post_is_persistable() {
		sut.em.getTransaction().begin();
		Post r = sut.create("subject", "body");
		sut.em.getTransaction().commit();

		assertNotNull(r);
		assertTrue(r.getId() > 0);
		assertEquals(1, sut.findAll().size());
	}
	
	@Test
	public void Post_is_deleteable() {
		sut.em.getTransaction().begin();
		Post r = sut.create("subject", "body");
		sut.em.getTransaction().commit();
		
		sut.em.getTransaction().begin();
		sut.delete(r.getId());
		sut.em.getTransaction().commit();

		assertEquals(0, sut.findAll().size());
	}
	
	@Test
	public void Post_is_updateable() {
		sut.em.getTransaction().begin();
		Post r = sut.create("subject", "body");
		sut.em.getTransaction().commit();
		
		sut.em.getTransaction().begin();
		sut.update(r.getId(), "S", "B");
		sut.em.getTransaction().commit();
		
		List<Post> all = sut.findAll();
		assertEquals(1, all.size());
		assertEquals("S", all.get(0).getSubject());
		assertEquals("B", all.get(0).getBody());
	}
}
