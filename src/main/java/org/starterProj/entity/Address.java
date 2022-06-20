package org.starterProj.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Embeddable
public class Address {

    @Column(name = "address1")
    private String address1;

    @Column(name = "address2")
    private String address2;

    @Column(name = "city")
    private String city;

    @NotNull
    @Size(max=10)
    @Column(name = "postcode", length = 10, nullable = false)
    private String postcode;

    @NotNull
    @Size(max= 2)
    @Column(name = "country", length = 2, nullable = false)
    private String country;


    //distiction entre les données entré
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return
                Objects.equals(address1, address.address1) &&
                Objects.equals(address2, address.address2) &&
                Objects.equals(city, address.city) &&
                Objects.equals(postcode, address.postcode) &&
                Objects.equals(country, address.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address1, address2, city, postcode, country);
    }
}
