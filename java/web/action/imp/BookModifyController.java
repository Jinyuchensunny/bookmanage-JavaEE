package web.action.imp;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.dao.BookjycDao;
import org.model.Bookjyc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BookModifyController extends ActionSupport implements ServletResponseAware, ServletRequestAware {
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

    public void updateBookById(){
        String bookId = request.getParameter("bookId");
        String bookName = request.getParameter("bookName");
        String bookPrice = request.getParameter("bookPrice");
        Bookjyc bookjyc = new Bookjyc();
        bookjyc.setBookId(new Integer(bookId));
        bookjyc.setBookName(bookName);
        bookjyc.setBookPrice(bookPrice);
        bookjyc.setBookkindjyc(bookjycDao.findBookById(new Integer(bookId)).getBookkindjyc());
        bookjycDao.updateBookById(bookjyc);
    }
}
