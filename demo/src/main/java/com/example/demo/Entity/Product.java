package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`product`")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ten", nullable = false)
    private String ten;

    @Column(name = "hinh_anh", nullable = false)
    private String hinh_anh;

    @Column(name = "anh1")
    private String anh1;

    @Column(name = "anh2")
    private String anh2;

    @Column(name = "anh3")
    private String anh3;

    @Column(name = "gia_cu", nullable = false)
    private int gia_cu;

    @Column(name = "sale", columnDefinition = "INT DEFAULT 0")
    private int sale;

    @Column(name = "gia_moi")
    @Formula(value = "gia_cu - gia_cu * COALESCE(sale, 0) / 100")
    private int gia_moi;

    @Column(name = "gioi_thieu_sp")
    private String gioi_thieu_sp;

    @Column(name = "unit")
    private String unit;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartItem> CartItem;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderItem> OrderItem;
}
