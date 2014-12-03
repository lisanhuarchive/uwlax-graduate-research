package org.lsh.data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by lsh on 14/12/3.
 */
@Entity
public class Grade {
    private int gid;
    private int scid;
    private int itemId;
    private int grade;

    @Id
    @Column(name = "gid", nullable = false, insertable = true, updatable = true)
    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    @Basic
    @Column(name = "scid", nullable = false, insertable = true, updatable = true)
    public int getScid() {
        return scid;
    }

    public void setScid(int scid) {
        this.scid = scid;
    }

    @Basic
    @Column(name = "item_id", nullable = false, insertable = true, updatable = true)
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Basic
    @Column(name = "grade", nullable = false, insertable = true, updatable = true)
    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grade grade1 = (Grade) o;

        if (gid != grade1.gid) return false;
        if (grade != grade1.grade) return false;
        if (itemId != grade1.itemId) return false;
        if (scid != grade1.scid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = gid;
        result = 31 * result + scid;
        result = 31 * result + itemId;
        result = 31 * result + grade;
        return result;
    }
}
