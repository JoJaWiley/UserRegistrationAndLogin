package com.cakefactory.user.persistence.Address;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Entity
@Table(name = "address")
public class AddressEntity {

    @Id
    String email;

    @NotBlank
    String line1;

    @NotBlank
    String line2;

    @NotBlank
    String postcode;



    public AddressEntity(String line1, String line2, String postcode) {
        this.line1 = line1;
        this.line2 = line2;
        this.postcode = postcode;
    }

    public AddressEntity() {

    }

    public AddressEntity(String email, String line1, String line2, String postcode) {
        this.email = email;
        this.line1 = line1;
        this.line2 = line2;
        this.postcode = postcode;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AddressEntity) {
            AddressEntity other = (AddressEntity) obj;
            return Objects.equals(this.email, other.email);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.email);
    }
}
