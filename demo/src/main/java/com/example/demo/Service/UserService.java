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

    public int checkUser(String username, String password) {
        User user= userRepository.findUserByUsernameAndPassword(username,password);
        if (user!=null){
            ModelMapper modelMapper=new ModelMapper();
            UserDto userDto=modelMapper.map(user, UserDto.class);
            return userDto.getId();
        }
        return 0;
    }
}
