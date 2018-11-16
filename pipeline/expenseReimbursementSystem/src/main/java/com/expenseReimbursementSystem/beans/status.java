package com.expenseReimbursementSystem.beans;

public class status {
	private int st_id;
	private String  st_descr;
	private String st_type;
	public status() {
		super();
		// TODO Auto-generated constructor stub
	}
	public status(int st_id, String st_descr, String st_type) {
		super();
		this.st_id = st_id;
		this.st_descr = st_descr;
		this.st_type = st_type;
	}
	@Override
	public String toString() {
		return "status [st_id=" + st_id + ", st_descr=" + st_descr + ", st_type=" + st_type + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((st_descr == null) ? 0 : st_descr.hashCode());
		result = prime * result + st_id;
		result = prime * result + ((st_type == null) ? 0 : st_type.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		status other = (status) obj;
		if (st_descr == null) {
			if (other.st_descr != null)
				return false;
		} else if (!st_descr.equals(other.st_descr))
			return false;
		if (st_id != other.st_id)
			return false;
		if (st_type == null) {
			if (other.st_type != null)
				return false;
		} else if (!st_type.equals(other.st_type))
			return false;
		return true;
	}
	public int getSt_id() {
		return st_id;
	}
	public void setSt_id(int st_id) {
		this.st_id = st_id;
	}
	public String getSt_descr() {
		return st_descr;
	}
	public void setSt_descr(String st_descr) {
		this.st_descr = st_descr;
	}
	public String getSt_type() {
		return st_type;
	}
	public void setSt_type(String st_type) {
		this.st_type = st_type;
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}
	
}
