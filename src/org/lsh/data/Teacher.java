package org.lsh.data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by lsh on 14/11/12.
 */
@Entity
public class Teacher {
	private String teacherId;
	private String teacherName;
	private String teacherPassword;

	@Id
	@Column(name = "teacher_id", nullable = false, insertable = true, updatable = true, length = 50)
	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	@Basic
	@Column(name = "teacher_name", nullable = false, insertable = true, updatable = true, length = 50)
	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	@Basic
	@Column(name = "teacher_password", nullable = false, insertable = true, updatable = true, length = 64)
	public String getTeacherPassword() {
		return teacherPassword;
	}

	public void setTeacherPassword(String teacherPassword) {
		this.teacherPassword = teacherPassword;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Teacher teacher = (Teacher) o;

		if (teacherId != null ? !teacherId.equals(teacher.teacherId) : teacher.teacherId != null) return false;
		if (teacherName != null ? !teacherName.equals(teacher.teacherName) : teacher.teacherName != null) return false;
		if (teacherPassword != null ? !teacherPassword.equals(teacher.teacherPassword) : teacher.teacherPassword != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = teacherId != null ? teacherId.hashCode() : 0;
		result = 31 * result + (teacherName != null ? teacherName.hashCode() : 0);
		result = 31 * result + (teacherPassword != null ? teacherPassword.hashCode() : 0);
		return result;
	}
}
