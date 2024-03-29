package com.example.demo.Service;

import com.example.demo.DTO.ProductDto;
import com.example.demo.Entity.Cart;
import com.example.demo.Entity.CartItem;
import com.example.demo.Entity.Product;
import com.example.demo.Entity.User;
import com.example.demo.Repository.CartItemRepository;
import com.example.demo.Repository.CartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;

    public List<CartItem> findAllByCartId(int cartId){
        return cartItemRepository.getCartItemByCart_Id(cartId);
    }

    public List<Product> getAllProduct(List<CartItem> cartitems){
        List<Product> productList= new ArrayList<>();
        for (CartItem c:cartitems){
            productList.add(c.getProduct());
        }
        return productList;
    }
}
