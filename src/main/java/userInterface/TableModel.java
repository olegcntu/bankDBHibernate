package userInterface;

import dbEntities.ArrGenerate;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TableModel extends AbstractTableModel {

    private int columnCount;
    private int rowCount;
    private String[] column;
    private List<ArrGenerate> dataArrayList;

    public TableModel(int columnCount, List<ArrGenerate> list, String[] column) {
        this.columnCount = columnCount;
        this.rowCount=list.size();
        this.column=column;

        this.dataArrayList=list;
//        dataArrayList=new ArrayList<String[]>();
//        dataArrayList.add(new String[]{"2","12","1","12","1-1","1"});

    }

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String []rows = dataArrayList.get(rowIndex).toTable();

        return  rows[columnIndex];
    }


    @Override
    public String getColumnName(int columnIndex) {
        return column[columnIndex];
    }

    public void addDate() {

    }
}
