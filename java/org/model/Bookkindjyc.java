package org.model;

import java.util.Set;

public class Bookkindjyc {
    private Integer bookKindId;
    private String bookKindName;
    private Set<Bookjyc> bookjycs;

    public Integer getBookKindId() {
        return bookKindId;
    }

    public void setBookKindId(Integer bookKindId) {
        this.bookKindId = bookKindId;
    }

    public String getBookKindName() {
        return bookKindName;
    }

    public void setBookKindName(String bookKindName) {
        this.bookKindName = bookKindName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bookkindjyc that = (Bookkindjyc) o;

        if (bookKindId != null ? !bookKindId.equals(that.bookKindId) : that.bookKindId != null) return false;
        if (bookKindName != null ? !bookKindName.equals(that.bookKindName) : that.bookKindName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bookKindId != null ? bookKindId.hashCode() : 0;
        result = 31 * result + (bookKindName != null ? bookKindName.hashCode() : 0);
        return result;
    }

    public Set<Bookjyc> getBookjycs() {
        return bookjycs;
    }

    public void setBookjycs(Set<Bookjyc> bookjycs) {
        this.bookjycs = bookjycs;
    }
}
