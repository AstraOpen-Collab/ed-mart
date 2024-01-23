package com.edmart.client.vendor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Contact implements Serializable {

    private String name;
    private String phone;
    private String fax;
    private String email;
    private String website;

}
