package com.example.organsharing.service;



import jakarta.persistence.*;

@Entity
public class Receiver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
   
	private String name;
    private String bloodGroup;
    private String requiredOrgan;
    private String city;
    private String number;
    
	private String status = "WAITING";

//    @OneToOne
//    private UserService user;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }

    public String getRequiredOrgan() { return requiredOrgan; }
    public void setRequiredOrgan(String requiredOrgan) { this.requiredOrgan = requiredOrgan; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}

//    public UserService getUser() { return user; }
//    public void setUser(UserService user) { this.user = user; }
}
