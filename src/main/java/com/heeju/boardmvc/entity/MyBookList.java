package com.heeju.boardmvc.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MyBooks")
public class MyBookList {

    @Id
    private Long id;
    @Column
    private String name;
    @Column
    private String author;
    @Column
    private String price;
}
