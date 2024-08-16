package com.goylik.transactionservice.model.entity;

import com.goylik.transactionservice.model.DocumentType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "name")
    private String name;
    @Column(name = "doc_num")
    private String documentNumber;
    @Enumerated(EnumType.STRING)
    @Column(name = "doc_type")
    private DocumentType documentType;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Account> accounts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId.equals(user.userId) && name.equals(user.name) && documentNumber.equals(user.documentNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, documentNumber);
    }
}
