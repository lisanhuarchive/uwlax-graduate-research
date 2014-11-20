package org.lsh.data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by lsh on 14/11/20.
 */
@Entity
public class Course {
	private int cid;
	private String cNo;
	private String section;
	private String cTitle;
	private String teacher;
	private String cTime;
	private Integer capacity;
	private Integer weekdays;
	private int term;
	private boolean activate;

	@Id
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Course course = (Course) o;

		if (cid != course.cid) return false;
		if (term != course.term) return false;
		if (cNo != null ? !cNo.equals(course.cNo) : course.cNo != null) return false;
		if (cTime != null ? !cTime.equals(course.cTime) : course.cTime != null) return false;
		if (cTitle != null ? !cTitle.equals(course.cTitle) : course.cTitle != null) return false;
		if (capacity != null ? !capacity.equals(course.capacity) : course.capacity != null) return false;
		if (section != null ? !section.equals(course.section) : course.section != null) return false;
		if (teacher != null ? !teacher.equals(course.teacher) : course.teacher != null) return false;
		if (weekdays != null ? !weekdays.equals(course.weekdays) : course.weekdays != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = cid;
		result = 31 * result + (cNo != null ? cNo.hashCode() : 0);
		result = 31 * result + (section != null ? section.hashCode() : 0);
		result = 31 * result + (cTitle != null ? cTitle.hashCode() : 0);
		result = 31 * result + (teacher != null ? teacher.hashCode() : 0);
		result = 31 * result + (cTime != null ? cTime.hashCode() : 0);
		result = 31 * result + (capacity != null ? capacity.hashCode() : 0);
		result = 31 * result + (weekdays != null ? weekdays.hashCode() : 0);
		result = 31 * result + term;
		return result;
	}

	@Basic
	@Column(name = "activate", nullable = false, insertable = true, updatable = true)
	public boolean isActivate() {
		return activate;
	}

	public void setActivate(boolean activate) {
		this.activate = activate;
	}
}
