package com.example.demo.Controller;

import com.example.demo.DTO.ProductDto;
import com.example.demo.Service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@AllArgsConstructor
@SessionAttributes({"userdto", "productList"})
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/get{unit}")
    public String getdienthoai(Model model, @RequestParam(name="pageNo", defaultValue = "1") Integer pageNo, @PathVariable String unit) {
        Page<ProductDto> productList = this.productService.getAllbyunit(pageNo,unit);
        model.addAttribute("unit", unit);
        model.addAttribute("productlist", productList);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", productList.getTotalPages());
        return "product";
    }
    @GetMapping("/thongtinsp/{id}")
    public String getthongtinsp(Model model,@PathVariable String id){
        ProductDto productDto=productService.getProductById(Integer.parseInt(id));
        model.addAttribute("ttproductdto",productDto);
        return "thong-tin-sp";
    }
}
