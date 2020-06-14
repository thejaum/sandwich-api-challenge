package com.thejaum.challenge.sandwich.repository.custom.implementations;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;

import com.thejaum.challenge.sandwich.models.Order;
import com.thejaum.challenge.sandwich.repository.custom.OrderRepositoryCustom;

@Service
public class OrderRepositoryCriteria implements OrderRepositoryCustom{

	@PersistenceContext
    private EntityManager em;

	public List<Order> getOrdersByQueryStringParameters(String status) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
		Root<Order> root = criteriaQuery.from(Order.class);
		criteriaQuery.select(root);
		/*
		 * For each query string parameters not null, add to set collection and then
		 * put the set on the where.
		 */
		Set<Predicate> predicates = new HashSet<>(1);
		if(null != status)
			predicates.add(criteriaBuilder.equal(root.get("status"), status));
		if(!predicates.isEmpty())
			criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));

		List<Order> order_list = em.createQuery(criteriaQuery).getResultList();
		return order_list;
	}
}
