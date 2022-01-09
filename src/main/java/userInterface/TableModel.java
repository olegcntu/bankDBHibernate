package userInterface;

import dbEntities.ArrGenerate;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableModel extends AbstractTableModel {

    private final int columnCount;
    private final int rowCount;
    private final String[] column;
    private final List<ArrGenerate> dataArrayList;

    public TableModel(int columnCount, List<ArrGenerate> list, String[] column) {
        this.columnCount = columnCount;
        this.rowCount=list.size();
        this.column=column;

        this.dataArrayList=list;

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

}
