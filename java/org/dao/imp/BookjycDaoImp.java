package org.dao.imp;

import org.dao.BookjycDao;
import org.model.Bookjyc;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class BookjycDaoImp extends HibernateDaoSupport implements BookjycDao {
    @Override
    public Bookjyc findBookById(int bookId) {
        List<Bookjyc>list = getHibernateTemplate().find("from org.model.Bookjyc");
        return list.get(0);
    }

    @Override
    public List<Bookjyc> findAllBookByBookClassId(int bookKindId) {
        List<Bookjyc>list = getHibernateTemplate().find("from Bookjyc where bookkindjyc.bookKindId=?",bookKindId);
        return list;
    }

    @Override
    public void updateBookById(Bookjyc bookjyc) {
        getHibernateTemplate().saveOrUpdate(bookjyc);
    }

    @Override
    public List<Bookjyc> findAllBook() {
        List<Bookjyc> list = getHibernateTemplate().find("from org.model.Bookjyc");
        return list;
    }

    @Override
    public void deleteBookById(Bookjyc bookjyc) {
        getHibernateTemplate().delete(bookjyc);
    }

    @Override
    public void addBook(Bookjyc bookjyc) {
        getHibernateTemplate().saveOrUpdate(bookjyc);
    }
}
