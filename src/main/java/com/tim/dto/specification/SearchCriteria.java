package com.tim.dto.specification;

import com.tim.data.SearchOperation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SearchCriteria {
	/**
	 * fieldName of Object
	 */
	private String key;
	/**
	 * Value 
	 */
    private Object value;
    /**
     * Method search
     */
    private SearchOperation operation;
}
