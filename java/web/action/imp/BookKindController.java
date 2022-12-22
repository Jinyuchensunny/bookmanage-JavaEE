/**
 * Copyright (C), 2019-2020.
 * FileName: BookKindController
 * Author:   asus
 * Date:     2020/6/29 19:36
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
import org.dao.BookkindjycDao;
import org.model.Bookkindjyc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class BookKindController extends ActionSupport implements ServletResponseAware, ServletRequestAware {


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

    private String BookKindName;

    public String getBookKindName() {
        return BookKindName;
    }

    public void setBookKindName(String bookKindName) {
        BookKindName = bookKindName;
    }

    public void addBookKind(){
        Bookkindjyc bookkindjyc = new Bookkindjyc();
        bookkindjyc.setBookKindName(BookKindName);
        bookkindjycDao.addBookClass(bookkindjyc);
    }


    private BookkindjycDao bookkindjycDao;

    public BookkindjycDao getBookkindjycDao() {
        return bookkindjycDao;
    }

    public void setBookkindjycDao(BookkindjycDao bookkindjycDao) {
        this.bookkindjycDao = bookkindjycDao;
    }

    public void deleteBookKind(){
        String bookId = request.getParameter("bookClassId");
        Bookkindjyc bookkindjyc = bookkindjycDao.findBookClassbyId(new Integer(bookId));
        bookkindjycDao.deletBookClass(bookkindjyc);
    }

    public void findAllBookKind()throws IOException{
        List<Bookkindjyc> list = bookkindjycDao.findAllBookClass();
        makeJson(list);
    }

    public String updateBookKind(){
        return null;
    }


    public void makeJson(List<Bookkindjyc> list)throws IOException {
        response.setHeader("Content-Type", "text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        // 构造json输出字符串
        JSONArray jsonArray = new JSONArray();
        for (Bookkindjyc bookkindjyc:list){
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("BookkindId",bookkindjyc.getBookKindId());
            jsonObj.put("BookkindName",bookkindjyc.getBookKindName());
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
