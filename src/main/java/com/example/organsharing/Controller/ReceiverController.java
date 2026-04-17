package com.example.organsharing.Controller;



import com.example.organsharing.repository.*;
import com.example.organsharing.service.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/receiver")
public class ReceiverController {

    private final ReceiverRepository receiverRepo;
    
    @Autowired
    private DonorRepository donorRepo;
//    private final UserRepository userRepo;

    public ReceiverController(ReceiverRepository receiverRepo ) {
        this.receiverRepo = receiverRepo;
//       this.userRepo = userRepo;
    }
 
    @GetMapping ("/list")
    public String receiverlist(Model model) {
    	model.addAttribute("recipients", receiverRepo.findAll());
    	return "receiver-crud";
    }
    @GetMapping("/form")
    public String receiverForm() {
        return "receiver-form";
    }
   
//    @GetMapping("/findMatch/{id}")
//    public String findMatch(@PathVariable Long id, Model model) {
//
//        // 1️⃣ Get receiver
//        Receiver receiver = receiverRepo.findById(id).orElse(null);
//
//        if (receiver == null) {
//            return "redirect:/receiver/list";
//        }
//
//        // 2️⃣ Find donors with same blood group
//        List< DonorService> matchedDonors =
//        		 donorRepo.findAllByBloodGroup(receiver.getBloodGroup());
//
//        System.out.println(matchedDonors);
//        // 3️⃣ Send to view
//        model.addAttribute("receiver", receiver);
//        model.addAttribute("matchedDonors", matchedDonors);
//
//        return "match-result";
//        
//        List< DonorService> matchedDonor =
//       		 donorRepo.findAllByBloodGroup(receiver.getBloodGroup());
//
//       System.out.println(matchedDonors);
//       // 3️⃣ Send to view
//       model.addAttribute("receiver", receiver);
//       model.addAttribute("matchedDonors", matchedDonor);
//
//       return "match-result";
//    }
    
    @GetMapping("/findMatch/{id}")
    public String findMatch(@PathVariable Long id, Model model) {

        // 1️⃣ Get receiver
        Receiver receiver = receiverRepo.findById(id).orElse(null);

        if (receiver == null) {
            return "redirect:/receiver/list";
        }

        // 2️⃣ Find donors with SAME blood group + organ
        List<DonorService> matchedDonors = donorRepo
            .findByBloodGroupAndOrgan(
                receiver.getBloodGroup(),
                receiver.getRequiredOrgan()
            );

        // 3️⃣ Send data to view
        model.addAttribute("receiver", receiver);
        model.addAttribute("matchedDonors", matchedDonors);

        return "match-result";
    }
    

    @PostMapping("/save")
    public String saveReceiver(Receiver receiver, Model model) {
//        UserService user = userRepo.findById(userId).orElse(null);
//        receiver.setUser(user);
        receiverRepo.save(receiver);
//        List<Receiver> recipients = receiverRepo.findAll();
//        model.addAttribute("recipients", recipients);
//        return "receiver-crud";
        return "redirect:/receiver/list";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteRecipient(@PathVariable Long id) {
        receiverRepo.deleteById(id);
        return "redirect:/receiver/list";
    }
    
    
}