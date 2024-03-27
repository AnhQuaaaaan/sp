package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@SessionAttributes({"userdto", "productList"})
@Controller
public class MyController {
    @GetMapping(value = {"/hello", "/home","index"})
    public String helloworld() {
        return "index" ;
    }
    @GetMapping(value = {"/thongtinsp"})
    public String ttsm() {
    return"thong-tin-sp" ;
}
    @GetMapping(value = {"/dangnhap"})
    public String dangnhap() {
        return "dang-nhap" ;
    }
    @GetMapping(value = {"/dangky"})
    public String dangky() {
        return "dang-ky" ;
    }
    @GetMapping(value = {"/giohang"})
    public String giohang() {
        return "gio-hang" ;
    }
    @GetMapping(value = {"/gioithieu"})
    public String gioithieu() {
        return "gioi-thieu" ;
    }
    @GetMapping(value = {"/lienhe"})
    public String lienhe() {
        return "lien-he" ;
    }
    @GetMapping(value = {"/laymatkhau"})
    public String laymatkhau() {
        return "lay-lai-mat-khau" ;
    }
    @GetMapping(value = {"/indexindex"})
    public String indexindex() {
        return "index-index" ;
    }
    @GetMapping(value = {"/dienthoai"})
    public String dienthoai() {
        return "product";
    }
    @GetMapping(value = {"/maytinhbang"})
    public String maytinhbang() {
        return "may_tinh_bang" ;
    }
    @GetMapping(value = {"/phukien"})
    public String phukien() {
        return "phu_kien" ;
    }
    @GetMapping(value = {"/dienlanh"})
    public String dienlanh() {
        return "dien_lanh" ;
    }
    @GetMapping(value = {"/laptop"})
    public String laptop() {
        return "laptop" ;
    }
    @GetMapping(value = {"/mayanh"})
    public String mayanh() {
        return "may_anh" ;
    }
}
