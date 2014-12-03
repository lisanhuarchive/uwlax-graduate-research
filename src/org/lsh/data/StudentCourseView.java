package org.lsh.data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by lsh on 14/12/2.
 */
@Entity
@Table(name = "student_course_view", schema = "", catalog = "attendance")
public class StudentCourseView {
    private String studentId;
    private String studentName;
    private String studentPassword;
    private int cid;
    private String cNo;
    private String section;
    private String cTitle;
    private String teacher;
    private String cTime;
    private Integer capacity;
    private Integer weekdays;
    private int term;
    private byte activate;
    private int scid;

    @Basic
    @Column(name = "student_id", nullable = false, insertable = true, updatable = true, length = 50)
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Basic
    @Column(name = "student_name", nullable = false, insertable = true, updatable = true, length = 50)
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Basic
    @Column(name = "student_password", nullable = false, insertable = true, updatable = true, length = 64)
    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
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
    @Column(name = "c_no", nullable = false, insertable = true, updatable = true, length = 45)
    public String getcNo() {
        return cNo;
    }

    public void setcNo(String cNo) {
        this.cNo = cNo;
    }

    @Basic
    @Column(name = "section", nullable = false, insertable = true, updatable = true, length = 45)
    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    @Basic
    @Column(name = "c_title", nullable = false, insertable = true, updatable = true, length = 45)
    public String getcTitle() {
        return cTitle;
    }

    public void setcTitle(String cTitle) {
        this.cTitle = cTitle;
    }

    @Basic
    @Column(name = "teacher", nullable = false, insertable = true, updatable = true, length = 50)
    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    @Basic
    @Column(name = "c_time", nullable = true, insertable = true, updatable = true, length = 45)
    public String getcTime() {
        return cTime;
    }

    public void setcTime(String cTime) {
        this.cTime = cTime;
    }

    @Basic
    @Column(name = "capacity", nullable = true, insertable = true, updatable = true)
    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Basic
    @Column(name = "weekdays", nullable = true, insertable = true, updatable = true)
    public Integer getWeekdays() {
        return weekdays;
    }

    public void setWeekdays(Integer weekdays) {
        this.weekdays = weekdays;
    }

    @Basic
    @Column(name = "term", nullable = false, insertable = true, updatable = true)
    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    @Basic
    @Column(name = "activate", nullable = false, insertable = true, updatable = true)
    public byte getActivate() {
        return activate;
    }

    public void setActivate(byte activate) {
        this.activate = activate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentCourseView that = (StudentCourseView) o;

        if (activate != that.activate) return false;
        if (cid != that.cid) return false;
        if (term != that.term) return false;
        if (cNo != null ? !cNo.equals(that.cNo) : that.cNo != null) return false;
        if (cTime != null ? !cTime.equals(that.cTime) : that.cTime != null) return false;
        if (cTitle != null ? !cTitle.equals(that.cTitle) : that.cTitle != null) return false;
        if (capacity != null ? !capacity.equals(that.capacity) : that.capacity != null) return false;
        if (section != null ? !section.equals(that.section) : that.section != null) return false;
        if (studentId != null ? !studentId.equals(that.studentId) : that.studentId != null) return false;
        if (studentName != null ? !studentName.equals(that.studentName) : that.studentName != null) return false;
        if (studentPassword != null ? !studentPassword.equals(that.studentPassword) : that.studentPassword != null)
            return false;
        if (teacher != null ? !teacher.equals(that.teacher) : that.teacher != null) return false;
        if (weekdays != null ? !weekdays.equals(that.weekdays) : that.weekdays != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = studentId != null ? studentId.hashCode() : 0;
        result = 31 * result + (studentName != null ? studentName.hashCode() : 0);
        result = 31 * result + (studentPassword != null ? studentPassword.hashCode() : 0);
        result = 31 * result + cid;
        result = 31 * result + (cNo != null ? cNo.hashCode() : 0);
        result = 31 * result + (section != null ? section.hashCode() : 0);
        result = 31 * result + (cTitle != null ? cTitle.hashCode() : 0);
        result = 31 * result + (teacher != null ? teacher.hashCode() : 0);
        result = 31 * result + (cTime != null ? cTime.hashCode() : 0);
        result = 31 * result + (capacity != null ? capacity.hashCode() : 0);
        result = 31 * result + (weekdays != null ? weekdays.hashCode() : 0);
        result = 31 * result + term;
        result = 31 * result + (int) activate;
        return result;
    }

    @Basic
    @Column(name = "scid", nullable = false, insertable = true, updatable = true)
    public int getScid() {
        return scid;
    }

    public void setScid(int scid) {
        this.scid = scid;
    }
}
