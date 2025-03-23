package Service;

import Model.Role;
import Repository.RoleRepository;
import java.util.List;

@SuppressWarnings("unused")
public class RoleService {
    private RoleRepository roleRepository = new RoleRepository();
    public List<Role> getRoles() {
        return roleRepository.getRoles();
    }

    public boolean deleteRolesById(int id){
        return roleRepository.deleteRolesById(id);
    }

    public boolean insertRoles(Role role){
        return roleRepository.insertRoles(role);
    }
}
