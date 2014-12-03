package org.lsh.data;

import javax.persistence.*;

/**
 * Created by lsh on 14/12/3.
 */
@Entity
@Table(name = "grade_item", schema = "", catalog = "attendance")
public class GradeItem {
    private int itemId;
    private String itemName;
    private int total;
    private int cid;
    private int isValid;

    @Id
    @Column(name = "item_id", nullable = false, insertable = true, updatable = true)
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Basic
    @Column(name = "item_name", nullable = false, insertable = true, updatable = true, length = 45)
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Basic
    @Column(name = "total", nullable = false, insertable = true, updatable = true)
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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
    @Column(name = "is_valid", nullable = false, insertable = true, updatable = true)
    public int getIsValid() {
        return isValid;
    }

    public void setIsValid(int isValid) {
        this.isValid = isValid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GradeItem gradeItem = (GradeItem) o;

        if (cid != gradeItem.cid) return false;
        if (isValid != gradeItem.isValid) return false;
        if (itemId != gradeItem.itemId) return false;
        if (total != gradeItem.total) return false;
        if (itemName != null ? !itemName.equals(gradeItem.itemName) : gradeItem.itemName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = itemId;
        result = 31 * result + (itemName != null ? itemName.hashCode() : 0);
        result = 31 * result + total;
        result = 31 * result + cid;
        result = 31 * result + isValid;
        return result;
    }
}
