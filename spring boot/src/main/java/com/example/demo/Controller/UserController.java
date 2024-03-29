    package com.example.demo.Controller;

    import com.example.demo.DTO.UserDto;
    import com.example.demo.Entity.User;
    import com.example.demo.Service.UserService;
    import jakarta.servlet.http.HttpSession;
    import lombok.AllArgsConstructor;
    import org.modelmapper.ModelMapper;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.bind.support.SessionStatus;
    import org.springframework.web.context.request.WebRequest;

    @RestController
    @RequestMapping("/api/auth")
    public class UserController {
        @Autowired
        private UserService userService;

        @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody UserDto userDto){
            boolean k=userService.checkUser(userDto.getUsername(), userDto.getPassword());
            if (k){
                User user=userService.getUser(userDto.getUsername(), userDto.getPassword());
                ModelMapper modelMapper=new ModelMapper();
                UserDto userDto1=modelMapper.map(user, UserDto.class);
                return ResponseEntity.ok(userDto1);
            }
            else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Tên đăng nhập hoặc mật khẩu không đúng");
            }
        }
        @PostMapping("/register")
        public ResponseEntity<?> Register(@RequestBody UserDto userDto){
            System.out.println(userDto);
            if (userService.checkUser(userDto.getUsername(), userDto.getPassword())){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Tài khoản đã tồn tại");
            }
            userService.save(userDto);
            User user=userService.getUser(userDto.getUsername(), userDto.getPassword());
            ModelMapper modelMapper=new ModelMapper();
            UserDto userDto1=modelMapper.map(user, UserDto.class);
            return ResponseEntity.ok(userDto1);
        }
        @GetMapping("/logout")
        public String Logout(@ModelAttribute("userdto")UserDto userDto, WebRequest webRequest, SessionStatus status ){
            status.setComplete();
            webRequest.removeAttribute("userdto",webRequest.SCOPE_SESSION);
            webRequest.removeAttribute("productlist",webRequest.SCOPE_SESSION);
            return "dang-nhap";
        }

    }
