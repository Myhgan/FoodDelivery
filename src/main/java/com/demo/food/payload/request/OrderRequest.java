package com.demo.food.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    private int userId;
    private int resId;
    private int[] foodIds;
}
