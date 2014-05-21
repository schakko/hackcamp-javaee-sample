package de.schakko.hackcamp.business.user.boundary;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import de.schakko.hackcamp.business.user.entity.User;

@RunWith(BlockJUnit4ClassRunner.class)
public class UserAuthenticationTest {
	UserAuthentication sut;

	@Before
	public void setUp() {
		sut = new UserAuthentication();
		sut.userManagement = mock(UserManagement.class);
	}

	@Test(expected = AuthenticationException.class)
	public void Exception_if_user_is_not_found() throws AuthenticationException {
		when(sut.userManagement.findUserByUsername(anyString())).thenReturn(null);

		sut.authenticate("username", "password");
	}

	@Test(expected = AuthenticationException.class)
	public void Exception_if_user_has_no_password() throws AuthenticationException {
		User u = mock(User.class);
		when(sut.userManagement.findUserByUsername(anyString())).thenReturn(u);

		sut.authenticate("username", "password");
	}

	@Test(expected = AuthenticationException.class)
	public void Exception_if_user_has_invalid_password() throws AuthenticationException {
		User u = mock(User.class);
		when(u.getPassword()).thenReturn("wrong_password");

		when(sut.userManagement.findUserByUsername(anyString())).thenReturn(u);

		sut.authenticate("username", "password");
	}

	@Test
	public void User_can_be_authenticated() throws AuthenticationException {
		User u = mock(User.class);
		when(u.getPassword()).thenReturn("password");

		when(sut.userManagement.findUserByUsername("username")).thenReturn(u);

		User r = sut.authenticate("username", "password");

		assertEquals(u, r);
	}
}
