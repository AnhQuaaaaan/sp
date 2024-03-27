package com.example.demo.DTO;

import jakarta.persistence.Column;
import lombok.*;
import org.hibernate.annotations.Formula;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto implements Serializable {
    private int id;
    private String ten;
    private String hinh_anh;
    private String anh1;
    private String anh2;
    private String anh3;
    private int gia_cu;
    private int sale;
    private int gia_moi;
    private String gioi_thieu_sp;
    private String unit;

}
