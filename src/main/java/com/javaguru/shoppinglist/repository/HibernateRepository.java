package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
@Profile("hibernate")
public class HibernateRepository implements ProductRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public  HibernateRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Product insert(Product product) {
        sessionFactory.getCurrentSession().save(product);
        return product;
    }

    @Override
    public Product findProductById(Long id) {
        Product product = (Product) sessionFactory.getCurrentSession().createCriteria(Profile.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
        return product;
    }

    @Override
    public void deleteProductById(Long id) {
        sessionFactory.getCurrentSession().delete(findProductById(id));
    }

    public boolean isUnique(Product product){
        String query = "select case when count(*)>0 then false else true end " +
                "from Product p where p.name=:name" ;
        return (boolean) sessionFactory.getCurrentSession().createQuery(query)
                .setString("name", product.getName())
                .setMaxResults(1)
                .uniqueResult();
    }
}
