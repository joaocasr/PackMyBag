package com.example.catalogService.dto;

import java.util.Set;

public class ItemIdsDTO {
    private Set<Integer> ids;

    public ItemIdsDTO() {
    }

    public ItemIdsDTO(Set<Integer> ids) {
        this.ids = ids;
    }

    public Set<Integer> getIds() {
        return ids;
    }

    public void setIds(Set<Integer> ids) {
        this.ids = ids;
    }
}
