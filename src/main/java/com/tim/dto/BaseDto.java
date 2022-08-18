package com.tim.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @appName the_internal_media
 *
 */

@Getter
@Setter
public class BaseDto implements Serializable {

	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = 7702340293141714083L;

	private Long id;
	private LocalDateTime createdDate = LocalDateTime.now();
	private String createdBy;
	private LocalDateTime modifiedDate = LocalDateTime.now();
	private String modifiedBy;
	private Boolean status = true;

}
