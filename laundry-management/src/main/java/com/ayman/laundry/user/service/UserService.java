package com.ayman.laundry.user.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayman.laundry.user.entity.User;
import com.ayman.laundry.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Transactional
public class UserService {


    private final UserRepository userRepository;



    // ===========================
    // Create User
    // ===========================

    public User createUser(User user){

        return userRepository.save(user);

    }



    // ===========================
    // Get All Users
    // ===========================

    @Transactional(readOnly = true)
    public List<User> getAllUsers(){

        return userRepository.findAll();

    }



    // ===========================
    // Get User By Id
    // ===========================

    @Transactional(readOnly = true)
    public User getUserById(Long id){

        return userRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("User not found with id: " + id)
                );

    }



    // ===========================
    // Update User
    // ===========================

    public User updateUser(Long id, User updatedUser){

        User user = getUserById(id);


        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setEmail(updatedUser.getEmail());
        user.setPhoneNumber(updatedUser.getPhoneNumber());
        user.setAddress(updatedUser.getAddress());
        user.setCity(updatedUser.getCity());
        user.setRole(updatedUser.getRole());
        user.setStatus(updatedUser.getStatus());


        return userRepository.save(user);

    }



    // ===========================
    // Delete User
    // ===========================

    public void deleteUser(Long id){

        User user = getUserById(id);

        userRepository.delete(user);

    }



    // ===========================
    // Find By Username
    // ===========================

    @Transactional(readOnly = true)
    public User getByUsername(String username){

        return userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new RuntimeException("Username not found")
                );

    }



    // ===========================
    // Find By Email
    // ===========================

    @Transactional(readOnly = true)
    public User getByEmail(String email){

        return userRepository.findByEmail(email)
                .orElseThrow(
                        () -> new RuntimeException("Email not found")
                );

    }



    // ===========================
    // Activate / Disable Account
    // ===========================

    public void disableUser(Long id){

        User user = getUserById(id);

        user.setEnabled(false);

        userRepository.save(user);

    }



    public void enableUser(Long id){

        User user = getUserById(id);

        user.setEnabled(true);

        userRepository.save(user);

    }

}