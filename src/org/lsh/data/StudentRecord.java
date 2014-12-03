package org.lsh.data;

import javax.persistence.*;

/**
 * Created by lsh on 14/12/3.
 */
@Entity
@Table(name = "student_record", schema = "", catalog = "attendance")
public class StudentRecord {
    private int srid;
    private int scid;
    private String recordTime;
    private int rid;

    @Id
    @Column(name = "srid", nullable = false, insertable = true, updatable = true)
    public int getSrid() {
        return srid;
    }

    public void setSrid(int srid) {
        this.srid = srid;
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
    @Column(name = "record_time", nullable = false, insertable = true, updatable = true, length = 45)
    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentRecord that = (StudentRecord) o;

        if (scid != that.scid) return false;
        if (srid != that.srid) return false;
        if (recordTime != null ? !recordTime.equals(that.recordTime) : that.recordTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = srid;
        result = 31 * result + scid;
        result = 31 * result + (recordTime != null ? recordTime.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "rid", nullable = false, insertable = true, updatable = true)
    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }
}
