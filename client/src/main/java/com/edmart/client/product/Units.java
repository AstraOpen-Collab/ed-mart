package com.edmart.client.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Units implements Serializable {

    //uos - unit of sales Id from the unit service
    private Long uosId;

    //unit of purchase Id from the unit service
    private Long uopId;
}
