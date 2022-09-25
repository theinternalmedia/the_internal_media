package com.tim.utils;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.tim.security.services.UserDetailsImpl;

public final class PrincipalUtils {

	/**
	 * @author minhtuanitk43
	 * @return null | authenticated user's userId (String)
	 */
	public static String getAuthenticatedUsersUserId() {
		UserDetailsImpl userDetailsImpl = getAuthenticatedUserDetail();
		// username: userId
		return userDetailsImpl != null ? userDetailsImpl.getUsername() : null;
	}
	
	/**
	 * @author minhtuanitk43
	 * @return authenticated user's Id (Long)
	 */
	public static Long getAuthenticatedUserId() {
		UserDetailsImpl userDetailsImpl = getAuthenticatedUserDetail();
		// username: userId
		return userDetailsImpl != null ? userDetailsImpl.getId() : null;
	}

	/**
	 * @author minhtuanitk43
	 * @return authenticated user's fullName 
	 */
	public static String getAuthenticatedName() {
		UserDetailsImpl userDetailsImpl = getAuthenticatedUserDetail();
		return userDetailsImpl != null ? userDetailsImpl.getName() : null;
	}

	/**
	 * @author minhtuanitk43
	 * @return authenticated UserDetailsImpl
	 */
	public static UserDetailsImpl getAuthenticatedUserDetail() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null | auth instanceof AnonymousAuthenticationToken) {
			return null;
		}
		UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
		return userDetails;
	}
	
	/**
	 * @author minhtuanitk43
	 * @return authenticated UserDetailsImpl
	 */
	public static boolean authenticatedUserIsTeacher() {
		UserDetailsImpl userDetailsImpl = getAuthenticatedUserDetail();
		return userDetailsImpl != null ? userDetailsImpl.isTeacher() : null;
	}
}
