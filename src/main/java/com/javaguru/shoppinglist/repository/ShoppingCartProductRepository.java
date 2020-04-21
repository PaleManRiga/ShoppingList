package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.ShoppingCartProduct;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ShoppingCartProductRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public ShoppingCartProductRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void insert(ShoppingCartProduct shoppingCartProduct){
        sessionFactory.getCurrentSession().save(shoppingCartProduct);
    }
}