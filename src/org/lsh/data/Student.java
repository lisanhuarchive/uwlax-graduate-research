package org.lsh.data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by lsh on 14/11/12.
 */
@Entity
public class Student {
	private String studentId;
	private String studentName;
	private String studentPassword;

	@Id
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Student student = (Student) o;

		if (studentId != null ? !studentId.equals(student.studentId) : student.studentId != null) return false;
		if (studentName != null ? !studentName.equals(student.studentName) : student.studentName != null) return false;
		if (studentPassword != null ? !studentPassword.equals(student.studentPassword) : student.studentPassword != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = studentId != null ? studentId.hashCode() : 0;
		result = 31 * result + (studentName != null ? studentName.hashCode() : 0);
		result = 31 * result + (studentPassword != null ? studentPassword.hashCode() : 0);
		return result;
	}
}
