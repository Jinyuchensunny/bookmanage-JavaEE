/**
 * Copyright (C), 2019-2020.
 * FileName: BookController
 * Author:   asus
 * Date:     2020/6/29 19:37
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package web.action.imp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.dao.BookjycDao;
import org.dao.BookkindjycDao;
import org.model.Bookjyc;
import org.model.Bookkindjyc;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;

public class BookController extends ActionSupport implements ServletResponseAware, ServletRequestAware {

    private HttpServletRequest request;
    private HttpServletResponse response;
    // 回调函数，当tomcat服务器端发送响应给移动端时，自动调用
    public void setServletResponse(HttpServletResponse res) {
        this.response = res;
    }
    // 回调函数，当移动端向tomcat服务器端发送请求时，自动调用
    public void setServletRequest(HttpServletRequest req) {
        this.request = req;
    }

    private BookjycDao bookjycDao;

    public BookjycDao getBookjycDao() {
        return bookjycDao;
    }

    public void setBookjycDao(BookjycDao bookjycDao) {
        this.bookjycDao = bookjycDao;
    }

    private String BookClass;
    private String BookName;
    private String BookPrice;

    public String getBookClass() {
        return BookClass;
    }

    public void setBookClass(String bookClass) {
        BookClass = bookClass;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public String getBookPrice() {
        return BookPrice;
    }

    public void setBookPrice(String bookPrice) {
        BookPrice = bookPrice;
    }

    private BookkindjycDao bookkindjycDao;

    public BookkindjycDao getBookkindjycDao() {
        return bookkindjycDao;
    }

    public void setBookkindjycDao(BookkindjycDao bookkindjycDao) {
        this.bookkindjycDao = bookkindjycDao;
    }

    public void addBook(){
        Bookjyc bookjyc = new Bookjyc();
        bookjyc.setBookName(BookName);
        bookjyc.setBookPrice(BookPrice);

        Bookkindjyc bookkindjyc = bookkindjycDao.findBookClassbyName(BookClass);

        bookjyc.setBookkindjyc(bookkindjyc);

        bookjycDao.addBook(bookjyc);

    }

    public void deleteBook(){
        String bookId = request.getParameter("bookClassId");
        Bookjyc bookjyc = bookjycDao.findBookById(new  Integer(bookId));
        bookjycDao.deleteBookById(bookjyc);
    }

    public void findAllBook()throws IOException {
        List<Bookjyc> list = bookjycDao.findAllBook();
        makeJson(list);
    }

    public void updateBook(){

    }

    public void makeJson(List<Bookjyc> list)throws IOException {
        response.setHeader("Content-Type", "text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        // 构造json输出字符串
        JSONArray jsonArray = new JSONArray();
        for (Bookjyc bookjyc:list){
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("BookId",bookjyc.getBookId());
            jsonObj.put("BookName",bookjyc.getBookName());
            jsonObj.put("BookPrice",bookjyc.getBookPrice());
            jsonObj.put("BookClass",bookjyc.getBookkindjyc().getBookKindName());
            jsonArray.add(jsonObj);
        }
        System.out.println(jsonArray.toString());
        JSONObject root = new JSONObject();
        root.put("result", jsonArray);
        out.write(root.toString());
        out.flush();
        out.close();
    }
}
