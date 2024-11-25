package com.example.catalogService.dto;

public class RemoveItemDTO {
    private int itemID;

    public RemoveItemDTO() {
    }

    public RemoveItemDTO(int itemID) {
        this.itemID = itemID;
    }

    public int getIDItem() {
        return itemID;
    }

    public void setIDItem(int itemID) {
        this.itemID = itemID;
    }
}
