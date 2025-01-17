package SpringTest.ds_2024.controller;

import SpringTest.ds_2024.entity.Property;
import SpringTest.ds_2024.service.PropertyService;
import SpringTest.ds_2024.service.PropertyApplicationService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("property")
public class PropertyController {

    private final PropertyService propertyService;
    private final PropertyApplicationService propertyApplicationService;

    public PropertyController(PropertyService propertyService, PropertyApplicationService propertyApplicationService) {
        this.propertyService = propertyService;
        this.propertyApplicationService = propertyApplicationService;
    }

    @GetMapping("")
    public String showProperties(Model model) {
        List<Property> properties = propertyService.getProperties();
        model.addAttribute("properties", properties);
        return "property/properties";
    }

    @GetMapping("/new")
    public String addProperty(Model model) {
        Property property = new Property();
        model.addAttribute("property", property);
        return "property/property";
    }

    @PostMapping("/new")
    public String saveProperty(Property property) {
        propertyApplicationService.createAndSavePropertyApplication(property);
        return "redirect:/property";
    }

    @GetMapping("/{propertyId}")
    public String viewProperty(@PathVariable Long propertyId, Model model) {
        Property property = propertyService.getPropertyById(propertyId);
        model.addAttribute("property", property);
        return "property/view";
    }

//    @PostMapping("/{propertyId}/delete")
//    public String deleteProperty(@PathVariable int propertyId) {
//        propertyService.deleteProperty(propertyId);
//        return "redirect:/property";
//    }

    @GetMapping("/delete/{propertyId}")
    @Secured("ROLE_ADMIN")
    public String deleteProperty(@PathVariable Long propertyId, Model model) {
        try {
//            PropertyApplicationService.deletePropertyApplication(propertyId);
            propertyService.deleteProperty(propertyId);
            model.addAttribute("msg", "Tenant deleted successfully!");
        } catch (Exception e) {
            model.addAttribute("msg", "Error deleting tenant: " + e.getMessage());
        }
        return "redirect:/property";
    }
}

