package com.example.secservice.sec.service;

import com.example.secservice.sec.entities.AppRole;
import com.example.secservice.sec.entities.AppUser;
import com.example.secservice.sec.repositories.AppRoleRepository;
import com.example.secservice.sec.repositories.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;
    public AccountServiceImpl(AppUserRepository appUserRepository,
                              AppRoleRepository appRoleRepository,
                              PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public AppUser addNewUser(AppUser appUser) {
        String password=appUser.getPassword();
        appUser.setPassword(passwordEncoder.encode(password));
        return appUserRepository.save(appUser);
    }
    @Override
    public AppRole addNewRole(AppRole appRole) {
        return appRoleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        AppUser appUser=appUserRepository.findByUsername(userName);
        AppRole appRole=appRoleRepository.findByRoleName(roleName);
        appUser.getAppRoles().add(appRole);
    }
    @Override
    public AppUser loadUserByUserName(String username) {
        return appUserRepository.findByUsername(username);
    }
    @Override
    public List<AppUser> listUsers() {
        return appUserRepository.findAll();
    }
    @Override
    public List<AppRole> listRoles() {
        return appRoleRepository.findAll();
    }
}
