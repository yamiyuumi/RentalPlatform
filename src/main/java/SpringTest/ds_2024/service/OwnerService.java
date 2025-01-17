package SpringTest.ds_2024.service;

import SpringTest.ds_2024.entity.Owner;
import SpringTest.ds_2024.entity.Roles;
import SpringTest.ds_2024.repository.OwnerRepository;
import SpringTest.ds_2024.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;
    private RolesRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public OwnerService(OwnerRepository ownerRepository,RolesRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.ownerRepository = ownerRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    public Owner getOwnerById(Long id) {
        return ownerRepository.findById(id).orElse(null);
    }

    public Owner saveOwner(Owner owner) {

        System.out.println("saving tenant");
        String passwd= owner.getPassword();
        String encodedPassword = passwordEncoder.encode(passwd);
        owner.setPassword(encodedPassword);

        Roles role = roleRepository.findByRoleName("ROLE_OWNER")
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        Set<Roles> roles = new HashSet<>();
        roles.add(role);
        owner.setRoles(roles);

        System.out.println("Saved user with ID: " + owner.getId());
        return ownerRepository.save(owner);
    }

    public void deleteOwner(Long id) {
        ownerRepository.deleteById(id);
    }

}
