package com.example.notificacoesService.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class SortByDate implements Comparator<Notificacao> {
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public int compare(Notificacao o1, Notificacao o2) {
        if (o1.getData() == null && o2.getData() == null) {
            return 0;
        } else if (o1.getData() == null) {
            return -1;
        } else if (o2.getData() == null) {
            return 1;
        }
        try {
            Date date1 = formatter.parse(o1.getData());
            Date date2 = formatter.parse(o2.getData());
            return date1.compareTo(date2);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
