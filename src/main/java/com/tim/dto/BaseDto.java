package com.tim.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

/**
 * 
 * @appName the_internal_media
 *
 */

@Data
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
