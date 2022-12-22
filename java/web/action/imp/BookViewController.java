package web.action.imp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.dao.BookjycDao;
import org.dao.BookkindjycDao;
import org.model.Bookjyc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class BookViewController extends ActionSupport implements ServletResponseAware, ServletRequestAware {
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

    public void findBookById() throws IOException {
        String bookId = request.getParameter("bookId");
        Bookjyc bookjyc = bookjycDao.findBookById(new Integer(bookId));
        List<Bookjyc> list = new ArrayList<Bookjyc>();
        list.add(bookjyc);
        makeJson(list);
    }

    public void makeJson(List<Bookjyc> list)throws IOException{
        response.setHeader("Content-Type", "text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        // 构造json输出字符串
        JSONArray jsonArray = new JSONArray();
        for (Bookjyc bookjyc:list){
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("BookId",bookjyc.getBookId());
            jsonObj.put("BookName",bookjyc.getBookName());
            jsonObj.put("BookPrice",bookjyc.getBookPrice());
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
