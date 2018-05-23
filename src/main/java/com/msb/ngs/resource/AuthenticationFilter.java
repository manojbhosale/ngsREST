package com.msb.ngs.resource;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.msb.ngs.model.user.User;
import com.msb.ngs.utils.NgsUtils;

@Provider
public class AuthenticationFilter implements ContainerRequestFilter {

	@Context
	private ResourceInfo resourceInfo;

	private static final String AUTHORIZATION_PROPERTY = "Authorization";
	private static final String AUTHORIZATION_SCHEME = "Basic";
	private static final Response ACCESS_DENIED = Response.status(Response.Status.FORBIDDEN)
			.entity("You can not access this resource !!").build();
	private static final Response ACCESS_FORBIDDEN = Response.status(Response.Status.FORBIDDEN)
			.entity("Access blocked for all users !!").build();

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		Method method = resourceInfo.getResourceMethod();

	/*	if (!method.isAnnotationPresent(PermitAll.class)) {
			// Access denoed for all users
			if (method.isAnnotationPresent(DenyAll.class)) {
				requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).entity("You can not access this resource !!").build());
				return;
			}
			// Get request headers
			final MultivaluedMap<String, String> headers = requestContext.getHeaders();

			// Fetch Authorization header
			final List<String> authoriaztion = headers.get(AUTHORIZATION_PROPERTY);

			// if not authoriation then block access
			if (authoriaztion == null || authoriaztion.isEmpty()) {
				requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).entity("Access blocked for all users !!").build());
				return;
			}

			//

			// get encoded username and password
			final String encodedUserPassword = authoriaztion.get(0).replaceFirst(AUTHORIZATION_SCHEME + " ", "");

			// decode username and password
			final String usernameAndPasword = new String(Base64.decode(encodedUserPassword.getBytes()));

			// split username and password tokens
			final StringTokenizer tokanizer = new StringTokenizer(usernameAndPasword, ":");

			final String username = tokanizer.nextToken();
			final String password = tokanizer.nextToken();

			// verifying username and password
			System.out.println("UserName: "+username);
			System.out.println("Password: "+password);

			// verify user access
			if (method.isAnnotationPresent(RolesAllowed.class)) {
				System.out.println("One");
				RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
				Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));

				// is user valid ?
				if (!isUserAllowed(username, password, rolesSet)) {
					requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).entity("Access blocked for all users !!").build());
				}

			}

		}
*/
	}

	private boolean isUserAllowed(final String username, final String password, final Set<String> rolesSet) {
		
		SessionFactory sf = NgsUtils.getSessionFactoryInstance();
		
		Session sess  = sf.openSession();
		System.out.println(username+" "+password);
		//String nativeQ = "select * from user_table where name = \'"+username+"\'";
		Query q = sess.createQuery("from user_table where name = :uname");
		q.setParameter("uname", username);
		//Query q = sess.createNativeQuery(nativeQ);
		
		List li = q.list();
		
		li.forEach(System.out::println);
		
		boolean isAllowed = false;
		
		for(Object o : li) {
			User u = (User)o;
			if (username.equals(u.getName()) && password.equals(u.getPassword())) {
				String userRole = u.getRole();
				System.out.println("userRole : "+userRole);
				if (rolesSet.contains(userRole)) {
					isAllowed = true;
				}
			}

		}
		/*if (username.equals("manoj") && password.equals("password")) {
			String userRole = "ADMIN";

			if (rolesSet.contains(userRole)) {
				isAllowed = true;
			}
		}
*/		
		
		return isAllowed;
	}

}
