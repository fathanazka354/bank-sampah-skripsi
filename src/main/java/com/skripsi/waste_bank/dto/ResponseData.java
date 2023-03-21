package com.skripsi.waste_bank.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class ResponseData<T> {
    public String status;
    public List<String> message;
    public T data;
}
