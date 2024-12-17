package com.jmtsu.ms.core.model;


import com.jmtsu.ms.core.repository.UserModelRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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
    @NotNull(message = "The field 'username' is mandatory")
    @Column(nullable = false)
    private String username;
    @NotNull(message = "The field 'password' is mandatory")
    @Column(nullable = false)
    private String password;
}
