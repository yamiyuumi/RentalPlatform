package SpringTest.ds_2024.service;

import SpringTest.ds_2024.entity.Roles;
import SpringTest.ds_2024.entity.Tenant;
import SpringTest.ds_2024.entity.User;
import SpringTest.ds_2024.repository.TenantRepository;
import SpringTest.ds_2024.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TenantService {

    private  TenantRepository tenantRepository;
    private RolesRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;


    public TenantService(TenantRepository tenantRepository ,RolesRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.tenantRepository = tenantRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Tenant> getAllTenants() {
        return tenantRepository.findAll();
    }

    public Tenant getTenantById(Long id) {
        return tenantRepository.findById(id).orElse(null);
    }

    public Tenant saveTenant(Tenant tenant) {
        System.out.println("saving tenant");
        String passwd= tenant.getPassword();
        String encodedPassword = passwordEncoder.encode(passwd);
        tenant.setPassword(encodedPassword);

        Roles role = roleRepository.findByRoleName("ROLE_TENANT")
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        Set<Roles> roles = new HashSet<>();
        roles.add(role);
        tenant.setRoles(roles);

        System.out.println("Saved user with ID: " + tenant.getId());
        return tenantRepository.save(tenant);
    }

    public void deleteTenant(Long id) {
        tenantRepository.deleteById(id);
    }
}