package com.jmtsu.ms.auth.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthRequest
        {
            private String email;
            private String password;
}
