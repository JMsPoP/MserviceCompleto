package com.jmtsu.ms.core.model;


import com.jmtsu.ms.core.repository.UserModelRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserModel implements AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    /*@NotNull(message = "The field 'username' is mandatory")
    @Column(nullable = false)
    private String username;*/
    @NotNull(message = "The field 'name' is mandatory")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "The field 'email' is mandatory")
    @Column(nullable = false)
    @Email
    private String email;


    @NotNull(message = "The field 'password' is mandatory")
    @Column(nullable = false)
    private String password;
<<<<<<< HEAD

    private UUID verificationCode;

    private Boolean enable;



=======
>>>>>>> 341f85a972fbfa08f7b4d111b48cf548c69fc9cf
}
