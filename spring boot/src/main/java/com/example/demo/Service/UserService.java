package com.example.demo.Service;

import com.example.demo.DTO.ProductDto;
import com.example.demo.DTO.UserDto;
import com.example.demo.Entity.Product;
import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public void save(UserDto userDto) {
        ModelMapper modelMapper=new ModelMapper();
        User user=modelMapper.map(userDto, User.class);
        userRepository.save(user);
    }

    public boolean checkUser(String username, String password) {
        User user= userRepository.findUserByUsernameAndPassword(username,password);
        if (user!=null){
            return true;
        }
        return false;
    }
    public User getUser(String username, String password){
        return userRepository.findUserByUsernameAndPassword(username,password);
    }
}
