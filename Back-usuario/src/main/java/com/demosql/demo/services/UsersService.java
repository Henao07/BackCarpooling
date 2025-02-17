package com.demosql.demo.services;


import com.demosql.demo.dtos.UserDto;
import com.demosql.demo.models.User;
import com.demosql.demo.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demosql.demo.Exception.WalterException;
import java.util.NoSuchElementException;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public Iterable<User> getAllUsers(){
        return usersRepository.findAll();
    }
    public User getUserById(Long userId) {
        return usersRepository.findById(userId).orElse(null);
    }
    public User insertUser(User user) {
        return usersRepository.save(user);
    }
    public void deleteUserById(Long userId) {
        usersRepository.deleteById(userId);
    }
    public User updateUser(Long userId, User updatedUser) {
        User existingUser = usersRepository.findById(userId).orElse(null);

        if (existingUser != null) {
            if(updatedUser.getNombre() != null){
                existingUser.setNombre(updatedUser.getNombre());
            }
            if(updatedUser.getPassword() != null){
                existingUser.setPassword(updatedUser.getPassword());
            }

            return usersRepository.save(existingUser);
        } else {
            throw new WalterException("No existe la Id: " + userId,null);

        }

    }
    public User login(String email, String password) {
        User usuario = usersRepository.findByemail(email);
        if (usuario.getPassword().equals(password)) {
            return usuario;
        }
        return null;
    }

}
