/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.cartService.services;

import java.time.ZonedDateTime;
import java.util.Comparator;

import com.example.cartService.dto.PagamentoDTO;


class DateComparator implements Comparator<PagamentoDTO> {

    // Method 1
    // To compare customers
    @Override
    public int compare(PagamentoDTO pg1, PagamentoDTO pg2)
    {

        // Comparing customers
        ZonedDateTime date1,date2;
        date1 = ZonedDateTime.parse(pg1.getDataGeracao());
        date2 = ZonedDateTime.parse(pg2.getDataGeracao());
        return date2.toInstant().compareTo(date1.toInstant());
        
    }
}
