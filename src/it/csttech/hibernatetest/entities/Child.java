package it.csttech.hibernatetest.entities;

import javax.persistence.*;

@Entity //richiede un field con annotation @Id
@Table(name = "CHILDREN")
public class Child {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne
    @JoinColumn(name = "parent_id",
            foreignKey = @ForeignKey(name = "children_parent_id_fkey")
    )
	private Employee employee;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "age")
	private int age;
	
	public Child() {
		// TODO Auto-generated constructor stub
	}
	
	public Child(Employee employee, String name, int age) {
		super();
		this.name = name;
		this.age = age;
		this.employee = employee;
	}

	public Employee getEmployee() {
		return employee;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
