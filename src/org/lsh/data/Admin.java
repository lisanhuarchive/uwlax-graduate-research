package org.lsh.data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by lsh on 14/11/12.
 */
@Entity
public class Admin {
	private String adminId;
	private String colPswd;
	private String colName;

	@Id
	@Column(name = "admin_id", nullable = false, insertable = true, updatable = true, length = 50)
	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	@Basic
	@Column(name = "col_pswd", nullable = false, insertable = true, updatable = true, length = 64)
	public String getColPswd() {
		return colPswd;
	}

	public void setColPswd(String colPswd) {
		this.colPswd = colPswd;
	}

	@Basic
	@Column(name = "col_name", nullable = false, insertable = true, updatable = true, length = 45)
	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Admin admin = (Admin) o;

		if (adminId != null ? !adminId.equals(admin.adminId) : admin.adminId != null) return false;
		if (colName != null ? !colName.equals(admin.colName) : admin.colName != null) return false;
		if (colPswd != null ? !colPswd.equals(admin.colPswd) : admin.colPswd != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = adminId != null ? adminId.hashCode() : 0;
		result = 31 * result + (colPswd != null ? colPswd.hashCode() : 0);
		result = 31 * result + (colName != null ? colName.hashCode() : 0);
		return result;
	}
}
