package com.zx.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class User {
    private Integer id;
    private String name;
    private String sex;
    private String work;
    private String address;
}
