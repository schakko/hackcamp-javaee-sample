package de.schakko.hackcamp.presentation.blog;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import de.schakko.hackcamp.business.blog.boundary.BlogPostService;
import de.schakko.hackcamp.business.blog.entity.Post;

@ApplicationScoped
@Named("blogView")
public class BlogView {
	private List<Post> posts;

	@EJB
	BlogPostService blogPostService;

	/**
	 * @return the posts
	 */
	public List<Post> getPosts() {

		if (posts == null) {
			posts = blogPostService.findAll();
		}

		return posts;
	}

	/**
	 * @param posts
	 *            the posts to set
	 */
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	private String subject;
	private String body;

	public String doInsertPost() {
		blogPostService.create(getSubject(), getBody());
		posts = blogPostService.findAll();

		return "/app/index";
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * @param body
	 *            the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}
}
