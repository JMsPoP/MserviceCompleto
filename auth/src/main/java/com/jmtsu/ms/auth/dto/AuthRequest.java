package com.jmtsu.ms.auth.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthRequest
        {
            private String username;
            private String password;
}
