package com.tim.exception;

import com.tim.data.ETimMessages;
import com.tim.utils.MessageUtils;

/**
 * 
 * @appName the_internal_media
 *
 */
public class TimNotFoundException extends TimException {

	private static final long serialVersionUID = 985793335839209871L;

	/**
	 * TimNotFoundException
	 * 
	 * @param value0 {0}
	 * @param value1 {1}
	 * @param value2 {2}
	 * @return Không tìm thấy {0} với {1} = {2}. 
	 */
	public TimNotFoundException(String value0, String value1, String value2) {
		this.message = MessageUtils.get(ETimMessages.ENTITY_NOT_FOUND, value0, value1, value2);
	}
}
