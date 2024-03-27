package com.example.demo.Service;

import com.example.demo.DTO.UserDto;
import com.example.demo.Entity.Cart;
import com.example.demo.Entity.CartItem;
import com.example.demo.Entity.Product;
import com.example.demo.Entity.User;
import com.example.demo.Repository.CartItemRepository;
import com.example.demo.Repository.CartRepository;
import com.example.demo.Repository.ProductRepository;
import com.example.demo.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    public int loadcart(int userid){
        Cart cart=cartRepository.getCartByUserId(userid);
        User user= userRepository.findUserById(userid);
        if (cart==null){
            cart = new Cart();
            cart.setUser(user);
            user.setCart(cart);
            cart.setCartItems(new ArrayList<>());
        }
        cartRepository.save(cart);
        return cart.getId();
    }
    @Transactional
    public void addtocart(int userId,int productId){
        int cartId=loadcart(userId);
        boolean check=false;
        CartItem cartItem1;
        List<CartItem> cartItemList = cartItemRepository.getCartItemByCart_Id(cartId);
        Product product=productRepository.findProductById(productId);
        Cart cart=cartRepository.getCartByUserId(userId);
        for (CartItem cartItem:cartItemList){
            if(cartItem.getProduct().getId()==product.getId()){
                check=true;
                cartItem.setQuantity(cartItem.getQuantity()+1);
                cartItemRepository.save(cartItem);
                break;
            }
        }
        if (check==false){
            cartItem1 = new CartItem();
            cartItem1.setProduct(product);
            cartItem1.setQuantity(1);
            cartItem1.setCart(cart);
            cartItemRepository.save(cartItem1);
        }
    }

    public void deleteproductcart(int cartItemID){
        cartItemRepository.deleteById(cartItemID);
    }
    @Transactional
    public void tangcart(int cartItemId){
        CartItem cartItem= cartItemRepository.getCartItemById(cartItemId);
        cartItem.setQuantity(cartItem.getQuantity()+1);
        System.out.println(cartItem.getQuantity());
        cartItemRepository.save(cartItem);
    }
    @Transactional
    public void giamcart(int cartItemId){
        System.out.println(cartItemId);
        CartItem cartItem= cartItemRepository.getCartItemById(cartItemId);
        if (cartItem.getQuantity()>=2) {
            cartItem.setQuantity(cartItem.getQuantity() - 1);
            cartItemRepository.save(cartItem);
        }
        else{
            deleteproductcart(cartItemId);
        }
    }
    public int getCartQuantity(int cartItemId){
        CartItem cartItem=cartItemRepository.getCartItemById(cartItemId);
        return cartItem.getQuantity();
    }
}
