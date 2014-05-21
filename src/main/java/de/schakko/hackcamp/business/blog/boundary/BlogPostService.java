package de.schakko.hackcamp.business.blog.boundary;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import de.schakko.hackcamp.business.blog.entity.Post;

@Stateless
public class BlogPostService {
	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Post> findAll() {
		return (List<Post>) em.createQuery("SELECT p FROM Post p").getResultList();
	}

	public Post find(long id) {
		Post r = em.find(Post.class, id);
		
		if (null == r) {
			throw new NoResultException();
		}
		
		return r;
	}

	public Post create(String subject, String body) {
		Post post = new Post();
		post.setSubject(subject);
		post.setBody(body);

		em.persist(post);
		em.flush();

		return post;
	}

	public Post update(long id, String subject, String body) {
		Post post = em.find(Post.class, id);

		post.setSubject(subject);
		post.setBody(body);

		em.persist(post);
		em.flush();

		return post;
	}

	public void delete(long id) {
		em.remove(em.find(Post.class, id));
	}

}
