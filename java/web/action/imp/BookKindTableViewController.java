package web.action.imp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.dao.BookkindjycDao;
import org.model.Bookkindjyc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class BookKindTableViewController extends ActionSupport implements ServletRequestAware, ServletResponseAware {
    private HttpServletRequest request;
    private HttpServletResponse response;
    // 回调函数，当tomcat服务器端发送响应给移动端时，自动调用
    private BookkindjycDao bookkindjycDao;

    public void setServletResponse(HttpServletResponse res) {
        this.response = res;
    }

    public void setServletRequest(HttpServletRequest req) {
        this.request = req;
    }

    public BookkindjycDao getBookkindjycDao() {
        return bookkindjycDao;
    }

    public void setBookkindjycDao(BookkindjycDao bookkindjycDao) {
        this.bookkindjycDao = bookkindjycDao;
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

    public void findAllBookClass() throws IOException {
        List<Bookkindjyc>list = bookkindjycDao.findAllBookClass();
        makeJson(list);
    }
}
