package com.tim.restful;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.tim.security.services.UserDetailsImpl;

/**
 * 
 * @appName the_internal_media
 *
 */
public abstract class AbstractResource {

	protected Long getAuthenticatedUserId() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
			return user != null ? user.getId() : -1;
		}
		return null;
	}

	protected String getAuthenticatedName() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
			return user != null ? user.getName() : null;
		}
		return null;
	}

	protected String getAuthenticatedUsername() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth != null ? auth.getName() : null;
	}

	protected UserDetailsImpl getAuthenticatedUserDetail() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
			return userDetails;
		}
		return null;
	}

}
