package com.c702t.Cerveza.models.request.specification;

import lombok.Data;

@Data
public class NewsFilterRequest {

    private Long id;
    private String order;

    public NewsFilterRequest(Long id, String order) {
        this.id = id;
        this.order=order;
    }

    public boolean isASC(){ return this.order.compareToIgnoreCase("ASC") == 0;}
    public boolean isDESC(){ return this.order.compareToIgnoreCase("DESC") == 0;}

}