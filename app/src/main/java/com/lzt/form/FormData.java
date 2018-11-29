package com.lzt.form;

import java.util.ArrayList;
import java.util.List;

public class FormData {

    private List<Column> columnList;

    public FormData(Column... columns) {
        columnList = new ArrayList<>();
        for (Column c : columns) {
            columnList.add(c);
        }
        init();
    }

    public FormData(List<Column> columns) {
        columnList = new ArrayList<>(columns);
        init();
    }

    private void init() {
//        int screenWidth = DisplayUtils.getScreenSize()[0];
//        int w = screenWidth / columnList.size();
//        if (w > Column.DEFAULT_MAX_WIDTH) {
//            for (Column column : columnList) {
//                column.setMaxWidth(w);
//            }
//        }
    }

    public List<Column> getColumnList() {
        return columnList;
    }
}
