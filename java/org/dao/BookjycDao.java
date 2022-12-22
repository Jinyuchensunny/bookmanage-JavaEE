package org.dao;

import org.model.Bookjyc;

import java.util.List;

public interface BookjycDao {
    //获得指定图书编号的图书
    Bookjyc findBookById(int bookId);

    //获得指定图书分类编号的所有图书对象
    List<Bookjyc> findAllBookByBookClassId(int bookKindId);

    //修改指定编号的图书名称和图书价格
    void updateBookById(Bookjyc bookjyc);

    //查询所有图书
    List<Bookjyc>findAllBook();

    //删除指定图书编号的图书
    void deleteBookById(Bookjyc bookjyc);

    //新增图书
    void addBook(Bookjyc bookjyc);
}
