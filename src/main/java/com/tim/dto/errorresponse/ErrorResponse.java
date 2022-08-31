package com.tim.dto.errorresponse;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ErrorResponse response to client if having any exception
 *
 * @appName the_internal_media
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {
	private String message;
	private List<String> errDetails;
}
