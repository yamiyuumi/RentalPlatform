package SpringTest.ds_2024.controller;

import SpringTest.ds_2024.entity.Owner;
import SpringTest.ds_2024.repository.OwnerRepository;
import SpringTest.ds_2024.service.OwnerService;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("owner")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("")
    @Secured("ROLE_ADMIN")
    public String ShowOwners(Model model){
        model.addAttribute("owns", ownerService.getAllOwners());
        return "owner/owners";
    }

    @GetMapping("{id}")
    @Secured("ROLE_ADMIN")
    public String ShowOwner(@PathVariable Long id, Model model) {
        Owner owner = ownerService.getOwnerById(id);
        if (owner == null) {
            model.addAttribute("msg", "Owner not found!");
            return "redirect:/owner";
        }
        model.addAttribute("owner", owner); // Use `owner` instead of `owns`
        return "owner/details";
    }


    @GetMapping("/delete/{id}")
    @Secured("ROLE_ADMIN")
    public String deleteOwner(@PathVariable Long id, Model model) {
        try {
            ownerService.deleteOwner(id);
            model.addAttribute("msg", "Tenant deleted successfully!");
        } catch (Exception e) {
            model.addAttribute("msg", "Error deleting tenant: " + e.getMessage());
        }
        return "redirect:/owner";
    }
    @GetMapping("/new")
    @Secured("ROLE_ADMIN")
    public String addOwner(Model model){
        model.addAttribute("owner", new Owner());
        return "owner/owner";
    }

    @PostMapping("/new")
    @Secured("ROLE_ADMIN")
    public String saveOwner(@ModelAttribute("owner") Owner owner, Model model) {
        ownerService.saveOwner(owner);
        model.addAttribute("owns", ownerService.getAllOwners());
        return "owner/owners";
    }
    @GetMapping("/register")
    public String OwnerRegister(Model model){
        model.addAttribute("owner", new Owner());
        System.out.println("new empty owner created");
        return "owner/register";
    }

    @PostMapping("/register")
    public String saveOwnerRegister(@ModelAttribute("owner") Owner owner, Model model) {
        ownerService.saveOwner(owner);
        model.addAttribute("owns", ownerService.getAllOwners());
        return "redirect:/login";
    }

    @GetMapping("/edit/{id}")
    @Secured("ROLE_ADMIN")
    public String editOwner(@PathVariable Long id, Model model) {
        Owner owner = ownerService.getOwnerById(id);
        if (owner == null) {
            model.addAttribute("msg", "Owner not found!");
            return "redirect:/owner";
        }
        model.addAttribute("owner", owner);
        return "owner/edit"; // Return the edit form view
    }

    @PostMapping("/edit/{id}")
    @Secured("ROLE_ADMIN")
    public String updateOwner(@PathVariable Long id, @ModelAttribute("owner") Owner updatedOwner, Model model) {
        Owner existingOwner = ownerService.getOwnerById(id);
        if (existingOwner == null) {
            model.addAttribute("msg", "Owner not found!");
            return "redirect:/owner";
        }

        existingOwner.setUsername(updatedOwner.getUsername());
        existingOwner.setPassword(updatedOwner.getPassword());
        existingOwner.setFirstName(updatedOwner.getFirstName());
        existingOwner.setLastName(updatedOwner.getLastName());
        existingOwner.setEmail(updatedOwner.getEmail());
        existingOwner.setPhoneNumber(updatedOwner.getPhoneNumber());

        ownerService.saveOwner(existingOwner);
        model.addAttribute("msg", "Owner updated successfully!");
        return "redirect:/owner";
    }
}