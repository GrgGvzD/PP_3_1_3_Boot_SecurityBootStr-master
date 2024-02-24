package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepo;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    @Transactional
    public void addRole(Role role) {
        roleRepo.save(role);
    }

    @Override
    public Role findRole(Long id) {
        return roleRepo.getReferenceById(id);
    }

    @Override
    @Transactional
    public void editRoleById(Role role) {
        roleRepo.save(role);
    }

    @Override
    @Transactional
    public void removeRoleById(Long id) {
        roleRepo.deleteById(id);
    }

    @Override
    public List<Role> getListOfRoles() {
        return roleRepo.findAll();
    }
}
