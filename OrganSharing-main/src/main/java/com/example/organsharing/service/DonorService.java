package com.example.organsharing.service;


	import jakarta.persistence.*;

	@Entity
	public class DonorService {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    private String name;
	    private int age;

	    private String bloodGroup;
	    private String organ;
	    private String city;
	    
		private String number;

//	    @OneToOne
//	    private UserService user;

	    // Getters & Setters
	    public Long getId() { return id; }
	    public void setId(Long id) { this.id = id; }

	    public String getBloodGroup() { return bloodGroup; }
	    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }

	    public String getOrgan() { return organ; }
	    public void setOrgan(String organ) { this.organ = organ; }

	    public String getCity() { return city; }
	    public void setCity(String city) { this.city = city; }
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
		public String getNumber() {
			return number;
		}
		public void setNumber(String number) {
			this.number = number;
		}

//	    public UserService getUser() { return user; }
//	    public void setUser(UserService user) { this.user = user; }
	    
	    
	}

