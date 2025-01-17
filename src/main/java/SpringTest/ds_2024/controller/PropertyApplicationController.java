package SpringTest.ds_2024.controller;


import SpringTest.ds_2024.service.RentalApplicationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;
import SpringTest.ds_2024.entity.Rental;
import SpringTest.ds_2024.entity.PropertyApplication;
import SpringTest.ds_2024.entity.Property;
import SpringTest.ds_2024.service.PropertyApplicationService;
import SpringTest.ds_2024.service.RentalService;
import SpringTest.ds_2024.service.PropertyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("property-application")
public class PropertyApplicationController {

    private final PropertyApplicationService propertyApplicationService;
    private final PropertyService propertyService;


    public PropertyApplicationController(PropertyApplicationService propertyApplicationService, PropertyService propertyService) {
        this.propertyApplicationService = propertyApplicationService;
        this.propertyService = propertyService;
    }


    @GetMapping("/new")
    public String showPropertyForm(Model model) {
        Property property = new Property();
        model.addAttribute("property", property);
        return "property/property";
    }


    @PostMapping("/new")
    public String submitPropertyForm(@ModelAttribute Property property) {
        propertyApplicationService.createAndSavePropertyApplication(property);
        return "redirect:/property-application/all";
    }


    @GetMapping("/all")
    public String showAllApplications(Model model) {
        List<PropertyApplication> applications = propertyApplicationService.getAllPendingApplications();
        model.addAttribute("applications", applications);
        return "property/property-application-list";
    }


    @PostMapping("/approve/{id}")
    public String approveApplication(@PathVariable int id) {
        propertyApplicationService.approveApplication(id);
        return "redirect:/property";
    }

    @PostMapping("/reject/{id}")
    public String rejectApplication(@PathVariable int id) {
        propertyApplicationService.rejectApplication(id);
        return "redirect:/property-application/all";
    }

}