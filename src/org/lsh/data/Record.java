package org.lsh.data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by lsh on 14/12/3.
 */
@Entity
public class Record {
    private int rid;
    private int cid;
    private int isOpen;

    @Id
    @Column(name = "rid", nullable = false, insertable = true, updatable = true)
    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    @Basic
    @Column(name = "cid", nullable = false, insertable = true, updatable = true)
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    @Basic
    @Column(name = "is_open", nullable = false, insertable = true, updatable = true)
    public int getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(int isOpen) {
        this.isOpen = isOpen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Record record = (Record) o;

        if (cid != record.cid) return false;
        if (isOpen != record.isOpen) return false;
        if (rid != record.rid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rid;
        result = 31 * result + cid;
        result = 31 * result + isOpen;
        return result;
    }
}
