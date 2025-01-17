package SpringTest.ds_2024.controller;

import SpringTest.ds_2024.entity.Rental;
import SpringTest.ds_2024.entity.RentalApplication;
import SpringTest.ds_2024.entity.Property;
import SpringTest.ds_2024.service.RentalApplicationService;
import SpringTest.ds_2024.service.RentalService;
import SpringTest.ds_2024.service.PropertyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("rental-application")
public class RentalApplicationController {

    private final RentalApplicationService rentalApplicationService;
    private final PropertyService propertyService;
    private final RentalService rentalService;

    public RentalApplicationController(RentalApplicationService rentalApplicationService, PropertyService propertyService, RentalService rentalService) {
        this.rentalApplicationService = rentalApplicationService;
        this.propertyService = propertyService;
        this.rentalService = rentalService;
    }


    @GetMapping("/new/{propertyId}")
    public String showApplicationForm(@PathVariable Long propertyId, Model model) {
        Property property = propertyService.getPropertyById(propertyId);
        if (property == null) {
            model.addAttribute("error", "Property not found");
            return "error";
        }
        RentalApplication rentalApplication = new RentalApplication();
        rentalApplication.setProperty(property);
        model.addAttribute("rentalApplication", rentalApplication);
        return "rental/application";
    }

    // Submit the rental application
    @PostMapping("/new")
    public String submitRentalApplication(@ModelAttribute RentalApplication rentalApplication) {
        if (rentalApplication.getProperty() != null && rentalApplication.getProperty().getPropertyId() != 0) {
            Property property = propertyService.getPropertyById(rentalApplication.getProperty().getPropertyId());
            rentalApplication.setProperty(property);
        }

        rentalApplicationService.saveRentalApplication(rentalApplication);
        return "redirect:/rental";
    }

    @GetMapping("/all")
    public String showAllApplications(Model model) {
        List<RentalApplication> applications = rentalService.getAllRentalApplications();
        model.addAttribute("applications", applications);
        return "rental/application-list";
    }


    @PostMapping("/accept/{id}")
    public String acceptApplication(@PathVariable int id) {
        RentalApplication application = rentalService.getRentalApplicationById(id);
        if (application != null) {
            Rental rental = new Rental();
            rental.setProperty(application.getProperty());
            rental.setRentalAmount(application.getRentalAmount());

            rentalService.saveRental(rental);
            rentalService.deleteRentalApplication(application);
        }
        return "redirect:/rental-application/all";
    }


    @PostMapping("/reject/{id}")
    public String rejectApplication(@PathVariable int id) {
        RentalApplication application = rentalService.getRentalApplicationById(id);
        if (application != null) {

            rentalService.deleteRentalApplication(application);
        }
        return "redirect:/rental-application/all";
    }
}
