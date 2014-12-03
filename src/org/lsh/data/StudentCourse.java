package org.lsh.data;

import javax.persistence.*;

/**
 * Created by lsh on 14/12/2.
 */
@Entity
@Table(name = "student_course", schema = "", catalog = "attendance")
public class StudentCourse {
    private int scid;
    private String studentId;
    private int courseId;

    @Id
    @Column(name = "scid", nullable = false, insertable = true, updatable = true)
    public int getScid() {
        return scid;
    }

    public void setScid(int scid) {
        this.scid = scid;
    }

    @Basic
    @Column(name = "student_id", nullable = true, insertable = true, updatable = true, length = 50)
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Basic
    @Column(name = "course_id", nullable = true, insertable = true, updatable = true)
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentCourse that = (StudentCourse) o;

        if (courseId != that.courseId) return false;
        if (scid != that.scid) return false;
        if (studentId != null ? !studentId.equals(that.studentId) : that.studentId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = scid;
        result = 31 * result + (studentId != null ? studentId.hashCode() : 0);
        result = 31 * result + courseId;
        return result;
    }
}
