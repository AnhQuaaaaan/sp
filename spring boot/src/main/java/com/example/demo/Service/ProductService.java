package com.example.demo.Service;

import com.example.demo.Entity.Product;
import com.example.demo.Repository.ProductRepository;
import com.example.demo.DTO.ProductDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    private ProductDto convertToDto(Product product) {
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(product, ProductDto.class);
    }

    public Page<ProductDto> getAllbyunit(Integer pageNo, String unit) {
        List<Product> productList = productRepository.findAllByUnit(unit);
        List<ProductDto> productDtoList = productList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        Pageable pageable = PageRequest.of(pageNo - 1, 16);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), productDtoList.size());
        return new PageImpl<>(productDtoList.subList(start, end), pageable, productDtoList.size());
    }
    public ProductDto getProductById(int id){
        Product product=productRepository.findProductById(id);
        ProductDto productDto=convertToDto(product);
        return productDto;
    }
}
