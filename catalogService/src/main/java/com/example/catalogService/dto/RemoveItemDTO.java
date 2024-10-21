package com.example.catalogService.dto;

public class RemoveItemDTO {
    private String code;
    private int lojaid;

    public RemoveItemDTO() {
    }

    public RemoveItemDTO(String code, int lojaid) {
        this.code = code;
        this.lojaid = lojaid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getLojaid() {
        return lojaid;
    }

    public void setLojaid(int lojaid) {
        this.lojaid = lojaid;
    }
}
