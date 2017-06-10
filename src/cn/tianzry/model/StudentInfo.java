package cn.tianzry.model;

// ѧ����Ϣ����ģ��

public class StudentInfo {

	private String id;
	private String name;
	private String sex;
	private String birthday;
	private int grade;
	private String phone;
	private String address;
	
	
	// ѧ�ţ��������Ա����գ����֣��绰������
	public StudentInfo(String id, String name, String sex, String birthday, int grade, String phone, String address) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.birthday = birthday;
		this.grade = grade;
		this.phone = phone;
		this.address = address;
	}

	
	public StudentInfo() {
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
	
}
