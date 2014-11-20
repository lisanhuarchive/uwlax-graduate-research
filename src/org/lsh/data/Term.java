package org.lsh.data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by lsh on 14/11/20.
 */
@Entity
public class Term {
	private int termId;
	private String termName;
	private String termStart;
	private String termEnd;
	private boolean isActivated;
	private boolean activated;

	@Id
	@Column(name = "term_id", nullable = false, insertable = true, updatable = true)
	public int getTermId() {
		return termId;
	}

	public void setTermId(int termId) {
		this.termId = termId;
	}

	@Basic
	@Column(name = "term_name", nullable = false, insertable = true, updatable = true, length = 45)
	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	@Basic
	@Column(name = "term_start", nullable = false, insertable = true, updatable = true, length = 45)
	public String getTermStart() {
		return termStart;
	}

	public void setTermStart(String termStart) {
		this.termStart = termStart;
	}

	@Basic
	@Column(name = "term_end", nullable = false, insertable = true, updatable = true, length = 45)
	public String getTermEnd() {
		return termEnd;
	}

	public void setTermEnd(String termEnd) {
		this.termEnd = termEnd;
	}

	@Basic
	@Column(name = "activated", nullable = false, insertable = true, updatable = true)
	public boolean isActivated() {
		return isActivated;
	}

	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Term term = (Term) o;

		if (termId != term.termId) return false;
		if (termEnd != null ? !termEnd.equals(term.termEnd) : term.termEnd != null) return false;
		if (termName != null ? !termName.equals(term.termName) : term.termName != null) return false;
		if (termStart != null ? !termStart.equals(term.termStart) : term.termStart != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = termId;
		result = 31 * result + (termName != null ? termName.hashCode() : 0);
		result = 31 * result + (termStart != null ? termStart.hashCode() : 0);
		result = 31 * result + (termEnd != null ? termEnd.hashCode() : 0);
		return result;
	}
}
