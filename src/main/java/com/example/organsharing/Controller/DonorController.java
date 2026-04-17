package com.example.organsharing.Controller;



import com.example.organsharing.repository.*;
import com.example.organsharing.service.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/donor")
public class DonorController {

    private final DonorRepository donorRepo;
//    private final UserRepository userRepo;

	public DonorController(DonorRepository donorRepo/* , UserRepository userRepo */) {
        this.donorRepo = donorRepo;
//        this.userRepo = userRepo;
    }
	
	@GetMapping("/list")
	public String donorlist(Model model) {
		  model.addAttribute("donors", donorRepo.findAll());
		return "donor-crud";
	}

    @GetMapping("/form")
    public String donorForm() {
        return "donor-form";
    }
//
    @PostMapping("/save")
	public String saveDonor(DonorService donor, Model model/* , Long userId */) {
//        UserService user = userRepo.findById(userId).orElse(null);
//        donor.setUser(user);
        donorRepo.save(donor);
        model.addAttribute("donors", donorRepo.findAll());
        return "donor-crud";
    }
    
    @GetMapping("/edit/{id}")
    public String editDonor(@PathVariable Long id, Model model) {

        DonorService donor = donorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Donor not found"));

        model.addAttribute("donor", donor);
        return "edit-donor";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteDonor(@PathVariable Long id, Model model) {
        donorRepo.deleteById(id);
        model.addAttribute("donors", donorRepo.findAll());
        return "donor-crud";
    }
    
    
    
   

}