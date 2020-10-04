/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.popovichia.eshop.services;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.popovichia.eshop.entities.Customer;

@Service
public class TerminalDataServiceImpl implements TerminalDataService {
    
    @Autowired
    private EntityManager entityManager;
    
    public List<Customer> getAllCustomers() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> from = criteriaQuery.from(Customer.class);

        List<Predicate> listPredicates = new ArrayList<>();
        listPredicates.add(criteriaBuilder.like(from.get("firstName"), "%%"));

        CriteriaQuery<Customer> criteriaQuery1 = criteriaQuery
                .select(from)
                .where(listPredicates.toArray(new Predicate[0]));

        return entityManager.createQuery(criteriaQuery1).getResultList();
    }
    
    public List<Customer> getCustomersLikePattern(String stringPattern) {
        return null;
    }
    
}
