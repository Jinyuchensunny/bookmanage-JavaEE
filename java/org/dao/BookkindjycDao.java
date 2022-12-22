package org.dao;

import org.model.Bookkindjyc;

import java.util.List;

public interface BookkindjycDao {
    //获得所有图书分类对象的列表
    List<Bookkindjyc> findAllBookClass();

    //新增图书分类
    void addBookClass(Bookkindjyc bookkindjyc);

    //删除图书分类
    void deletBookClass(Bookkindjyc bookkindjyc);

    //更新图书分类
    void updateBookClass(BookkindjycDao bookkindDao);

    //根据Name查询类型
    Bookkindjyc findBookClassbyName(String bookKindName);

    //根据id查询类型
    Bookkindjyc findBookClassbyId(int bookKindId);

}
