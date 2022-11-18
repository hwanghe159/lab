package com.example.springdatajpa.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
  private List<UserAddress> userAddresses = new ArrayList<>();

  @Builder
  public User(String name) {
    this.name = name;
  }

  public void removeAddress(@NonNull Long addressId) {
    UserAddress userAddress = this.userAddresses.stream()
        .filter(a -> addressId.equals(a.getId()))
        .findAny()
        .orElseThrow(RuntimeException::new);
//    userAddress.setUser(null);
    this.userAddresses.remove(userAddress);
  }

  public void addAddress(String address) {
    this.userAddresses.add(
        UserAddress.builder()
            .user(this)
            .value(address)
            .build()
    );
  }
}
