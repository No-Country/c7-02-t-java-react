package com.c702t.Cerveza.models.request.specification;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessFiltersRequest {

    private String city;
    private String state;
    private String country;
    private String order;

    public BusinessFiltersRequest(String city, String state, String country, String order) {
        this.city = city;
        this.state = state;
        this.country = country;
        this.order = order;
    }

    public boolean isASC(){ return this.order.compareToIgnoreCase("ASC") == 0;}
    public boolean isDESC(){ return this.order.compareToIgnoreCase("DESC") == 0;}

}
