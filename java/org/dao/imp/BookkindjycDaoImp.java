package org.dao.imp;

import org.dao.BookkindjycDao;
import org.model.Bookkindjyc;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class BookkindjycDaoImp extends HibernateDaoSupport implements BookkindjycDao {
    @Override
    public List<Bookkindjyc> findAllBookClass() {
        List<Bookkindjyc>list = getHibernateTemplate().find("from org.model.Bookkindjyc");
        return list;
    }

    @Override
    public void addBookClass(Bookkindjyc bookkindjyc) {
        getHibernateTemplate().save(bookkindjyc);
    }

    @Override
    public void deletBookClass(Bookkindjyc bookkindjyc) {
        getHibernateTemplate().delete(bookkindjyc);
    }

    @Override
    public void updateBookClass(BookkindjycDao bookkindDao) {
        getHibernateTemplate().saveOrUpdate(bookkindDao);
    }

    @Override
    public Bookkindjyc findBookClassbyName(String bookKindName) {
        List<Bookkindjyc>list = getHibernateTemplate().find("from Bookkindjyc where bookKindName =?",bookKindName);
        return list.get(0);
    }

    @Override
    public Bookkindjyc findBookClassbyId(int bookKindId) {
        List<Bookkindjyc> list = getHibernateTemplate().find("from Bookkindjyc where bookKindId =?",bookKindId);
        return list.get(0);
    }
}
