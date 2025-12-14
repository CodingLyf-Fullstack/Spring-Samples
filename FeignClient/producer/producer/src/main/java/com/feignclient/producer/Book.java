package com.feignclient.producer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor 
public class Book {

     private Integer bookId;
     private String bookName;
     private Double bookCost;
}