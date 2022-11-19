package com.example.userservice.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "mycheck")
@Entity
public class Check {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String checkToken;
    @CreationTimestamp
    private LocalDateTime dateCreate;
    @UpdateTimestamp
    private LocalDateTime dateUpdate;
    private Integer count;
    private String currencyName;
    private String currencyEngName;
    private String currencyId;
    private String currencyCharCode;
    private boolean enabled;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "user_and_check",joinColumns = @JoinColumn(name = "check_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private User user;
}
