package com.expenseReimbursementSystem.beans;

public class employee {
	int emp_id;
	String emp_FirstName;
	String emp_LastName;
	String emp_UserName;
	String emp_Password;
	String emp_Email;
	int role_id;
	public employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public employee(int emp_id, String emp_FirstName, String emp_LastName, String emp_UserName, String emp_Password,
			String emp_Email, int role_id) {
		super();
		this.emp_id = emp_id;
		this.emp_FirstName = emp_FirstName;
		this.emp_LastName = emp_LastName;
		this.emp_UserName = emp_UserName;
		this.emp_Password = emp_Password;
		this.emp_Email = emp_Email;
		this.role_id = role_id;
	}
	@Override
	public String toString() {
		return "employee [emp_id=" + emp_id + ", emp_FirstName=" + emp_FirstName + ", emp_LastName=" + emp_LastName
				+ ", emp_UserName=" + emp_UserName + ", emp_Password=" + emp_Password + ", emp_Email=" + emp_Email
				+ ", role_id=" + role_id + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emp_Email == null) ? 0 : emp_Email.hashCode());
		result = prime * result + ((emp_FirstName == null) ? 0 : emp_FirstName.hashCode());
		result = prime * result + ((emp_LastName == null) ? 0 : emp_LastName.hashCode());
		result = prime * result + ((emp_Password == null) ? 0 : emp_Password.hashCode());
		result = prime * result + ((emp_UserName == null) ? 0 : emp_UserName.hashCode());
		result = prime * result + emp_id;
		result = prime * result + role_id;
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
		employee other = (employee) obj;
		if (emp_Email == null) {
			if (other.emp_Email != null)
				return false;
		} else if (!emp_Email.equals(other.emp_Email))
			return false;
		if (emp_FirstName == null) {
			if (other.emp_FirstName != null)
				return false;
		} else if (!emp_FirstName.equals(other.emp_FirstName))
			return false;
		if (emp_LastName == null) {
			if (other.emp_LastName != null)
				return false;
		} else if (!emp_LastName.equals(other.emp_LastName))
			return false;
		if (emp_Password == null) {
			if (other.emp_Password != null)
				return false;
		} else if (!emp_Password.equals(other.emp_Password))
			return false;
		if (emp_UserName == null) {
			if (other.emp_UserName != null)
				return false;
		} else if (!emp_UserName.equals(other.emp_UserName))
			return false;
		if (emp_id != other.emp_id)
			return false;
		if (role_id != other.role_id)
			return false;
		return true;
	}
	public int getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmp_FirstName() {
		return emp_FirstName;
	}
	public void setEmp_FirstName(String emp_FirstName) {
		this.emp_FirstName = emp_FirstName;
	}
	public String getEmp_LastName() {
		return emp_LastName;
	}
	public void setEmp_LastName(String emp_LastName) {
		this.emp_LastName = emp_LastName;
	}
	public String getEmp_UserName() {
		return emp_UserName;
	}
	public void setEmp_UserName(String emp_UserName) {
		this.emp_UserName = emp_UserName;
	}
	public String getEmp_Password() {
		return emp_Password;
	}
	public void setEmp_Password(String emp_Password) {
		this.emp_Password = emp_Password;
	}
	public String getEmp_Email() {
		return emp_Email;
	}
	public void setEmp_Email(String emp_Email) {
		this.emp_Email = emp_Email;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
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
