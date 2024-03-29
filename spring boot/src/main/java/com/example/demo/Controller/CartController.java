package com.example.demo.Controller;

import com.example.demo.DTO.UserDto;
import com.example.demo.Entity.CartItem;
import com.example.demo.Entity.Product;
import com.example.demo.Service.CartItemService;
import com.example.demo.Service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@SessionAttributes({"userdto", "productList"})
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private CartItemService cartItemService;
    private void load(Model model, UserDto userDto) {
        int cartId = cartService.loadcart(userDto.getId());
        List<CartItem> cartItems = cartItemService.findAllByCartId(cartId);
        List<Product> productList = cartItemService.getAllProduct(cartItems);
        int totalQuantity = 0;
        int totalPrice = 0;

        for (CartItem c : cartItems) {
            totalQuantity += c.getQuantity();
            totalPrice += c.getQuantity() * c.getProduct().getGia_moi();
        }

        model.addAttribute("productcart", productList);
        model.addAttribute("cartItemList", cartItems);
        model.addAttribute("allquantity", totalQuantity);
        model.addAttribute("allprice", totalPrice);
    }
    @GetMapping("loadcart")
    public String loadcart(Model model,@ModelAttribute("userdto") UserDto userDto){
        load(model,userDto);
        return "gio-hang";
    }
    @PostMapping("addtocart")
    public String addToCart(Model model, @ModelAttribute("userdto") UserDto userDto, @RequestParam("productId") int productId){
        cartService.addtocart(userDto.getId(),productId);
        load(model,userDto);
        return "gio-hang";
    }
    @PostMapping("deleteproductcart")
    public String deleteprcart(Model model, @ModelAttribute("userdto") UserDto userDto, @RequestParam("cartItemId") int cartItemId){
        cartService.deleteproductcart(cartItemId);
        load(model,userDto);
        return "gio-hang";
    }
    @PostMapping ("tangcart")
    public String tangcart(Model model, @ModelAttribute("userdto") UserDto userDto, @RequestParam("cartItemId") int cartItemId){
        cartService.tangcart(cartItemId);
        load(model,userDto);
        return "gio-hang";
    }
    @PostMapping ("giamcart")
    public String giamcart(Model model, @ModelAttribute("userdto") UserDto userDto, @RequestParam("cartItemId") int cartItemId){
        cartService.giamcart(cartItemId);
        load(model,userDto);
        return "gio-hang";
    }
}
