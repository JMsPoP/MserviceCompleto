package com.jmtsu.ms.auth.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO {
    private Long id;
    private String emailTo;
    private  String subject;
    private String text;
}