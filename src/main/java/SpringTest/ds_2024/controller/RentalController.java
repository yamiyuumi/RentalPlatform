package SpringTest.ds_2024.controller;

import SpringTest.ds_2024.entity.Property;
import SpringTest.ds_2024.entity.PropertyStatus;
import SpringTest.ds_2024.entity.Rental;
import SpringTest.ds_2024.entity.RentalApplication;
import SpringTest.ds_2024.service.PropertyService;
import SpringTest.ds_2024.service.RentalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("rental")
public class RentalController {

    private final RentalService rentalService;
    private final PropertyService propertyService;

    public RentalController(RentalService rentalService, PropertyService propertyService) {
        this.rentalService = rentalService;
        this.propertyService = propertyService;
    }

    @GetMapping("")
    public String showAvailableProperties(Model model) {
        List<Property> availableProperties = propertyService.getAvailableProperties();
        model.addAttribute("properties", availableProperties);
        return "rental/rentals";
    }

    @GetMapping("/new")
    public String addRental(Model model) {
        List<Property> availableProperties = propertyService.getAvailableProperties();
        model.addAttribute("properties", availableProperties);
        model.addAttribute("rentalApplication", new Rental());
        return "rental/rental";
    }

    @PostMapping("/new")
    public String saveRental(Rental rental, Model model) {
        rentalService.saveRental(rental);
        model.addAttribute("rentals", rentalService.getRentals());
        return "rental/rentals";
    }

    @GetMapping("/new/{propertyId}")
    public String createRentalForm(@PathVariable Long propertyId, Model model) {
        RentalApplication rentalApplication = new RentalApplication();
        Property property = propertyService.getPropertyById(propertyId);

        rentalApplication.setRentalAmount(property.getPrice());

        model.addAttribute("rentalApplication", rentalApplication);
        model.addAttribute("property", property);
        return "rental/application";
    }
}