    package com.example.demo.Controller;

    import com.example.demo.DTO.UserDto;
    import com.example.demo.Service.UserService;
    import jakarta.servlet.http.HttpSession;
    import lombok.AllArgsConstructor;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.bind.support.SessionStatus;
    import org.springframework.web.context.request.WebRequest;

    @Controller
    @AllArgsConstructor
    @SessionAttributes("userdto")
    public class UserController {
        @Autowired
        private UserService userService;
        @ModelAttribute("userdto")
        public UserDto userDto(){
            return new UserDto();
        }
        @PostMapping("/index")
        public String login(@ModelAttribute("userdto")UserDto userDto){
            int k=userService.checkUser(userDto.getUsername(), userDto.getPassword());
            if (k!=0){
                userDto.setId(k);
                return "index";
            }
            else {
                return "redirect:/dangnhap?userwrong";
            }
        }
        @PostMapping("/register")
        public String Register(@ModelAttribute("userdto") UserDto userDto,@RequestParam("repassword") String repassword){
            if(!userDto.getPassword().equals(repassword)){
                return "redirect:/dangky?repasswrong";
            }
            if (userService.checkUser(userDto.getUsername(), userDto.getPassword())!=0){
                return "redirect:/dangky?userwrong";
            }
            userService.save(userDto);
            return "redirect:/dangnhap";
        }
        @GetMapping("/logout")
        public String Logout(@ModelAttribute("userdto")UserDto userDto, WebRequest webRequest, SessionStatus status ){
            status.setComplete();
            webRequest.removeAttribute("userdto",webRequest.SCOPE_SESSION);
            webRequest.removeAttribute("productlist",webRequest.SCOPE_SESSION);
            return "dang-nhap";
        }

    }
