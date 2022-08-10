package com.tim.dto.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

/**
 * 
 * @appName the_internal_media
 *
 * @param <T> Entity
 */
public class TimSpecification<T> implements Specification<T> {

	private static final long serialVersionUID = -378544062055384017L;

	private List<SearchCriteria> list;

	public TimSpecification() {
		this.list = new ArrayList<>();
	}

	public void add(SearchCriteria criteria) {
		list.add(criteria);
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		
		List<Predicate> predicates = new ArrayList<>();

		for (SearchCriteria criteria : list) {
			switch (criteria.getOperation()) {
			case GREATER_THAN:
				predicates.add(builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString()));
				break;
			case LESS_THAN:
				predicates.add(builder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString()));
				break;
			case GREATER_THAN_EQUAL:
				predicates
						.add(builder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString()));
				break;
			case LESS_THAN_EQUAL:
				predicates.add(builder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString()));
				break;
			case NOT_EQUAL:
				predicates.add(builder.notEqual(root.get(criteria.getKey()), criteria.getValue()));
				break;
			case EQUAL:
				predicates.add(builder.equal(root.get(criteria.getKey()), criteria.getValue()));
				break;
			case LIKE:
				predicates.add(builder.like(builder.lower(root.get(criteria.getKey())),
						"%" + criteria.getValue().toString().toLowerCase() + "%"));
				break;
			case END_WITH:
				predicates.add(builder.like(builder.lower(root.get(criteria.getKey())),
						criteria.getValue().toString().toLowerCase() + "%"));
				break;
			case START_WITH:
				predicates.add(builder.like(builder.lower(root.get(criteria.getKey())),
						"%" + criteria.getValue().toString().toLowerCase()));
				break;
			case IN:
				predicates.add(builder.in(root.get(criteria.getKey())).value(criteria.getValue()));
				break;
			case NOT_IN:
				predicates.add(builder.not(root.get(criteria.getKey())).in(criteria.getValue()));
				break;
			default:
				break;
			}
		}
		return builder.and(predicates.toArray(new Predicate[0]));
	}

}
