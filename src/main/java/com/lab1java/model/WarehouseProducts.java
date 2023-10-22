package com.lab1java.model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class WarehouseProducts {
    private int productId;
    private int warehouseId;
    private Date createdOn;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public WarehouseProducts(int productId, int warehouseId) {
        this.productId = productId;
        this.warehouseId = warehouseId;
        this.createdOn = Date.valueOf(LocalDate.now());
    }
}
