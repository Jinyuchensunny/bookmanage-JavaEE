package org.model;

public class Bookjyc {
    private Integer bookId;
    private String bookName;
    private String bookPrice;
    private Bookkindjyc bookkindjyc;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(String bookPrice) {
        this.bookPrice = bookPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bookjyc bookjyc = (Bookjyc) o;

        if (bookId != null ? !bookId.equals(bookjyc.bookId) : bookjyc.bookId != null) return false;
        if (bookName != null ? !bookName.equals(bookjyc.bookName) : bookjyc.bookName != null) return false;
        if (bookPrice != null ? !bookPrice.equals(bookjyc.bookPrice) : bookjyc.bookPrice != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bookId != null ? bookId.hashCode() : 0;
        result = 31 * result + (bookName != null ? bookName.hashCode() : 0);
        result = 31 * result + (bookPrice != null ? bookPrice.hashCode() : 0);
        return result;
    }

    public Bookkindjyc getBookkindjyc() {
        return bookkindjyc;
    }

    public void setBookkindjyc(Bookkindjyc bookkindjyc) {
        this.bookkindjyc = bookkindjyc;
    }
}
