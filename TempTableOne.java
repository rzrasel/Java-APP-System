
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rz Rasel
 */
public class TempTableOne {

    public void onListenerHandler() {
        JTable sysTblProjectDetails = new JTable();
        sysTblProjectDetails.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent argMouseEvent) {
                JTable table = (JTable) argMouseEvent.getSource();
                Point point = argMouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                int col = table.columnAtPoint(point);
                if (argMouseEvent.getClickCount() == 2) {
                    // your valueChanged overridden method 
                    System.out.println("----MOUSE_PRESSED: " + row);
                }
            }
        });

        sysTblProjectDetails.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                // do some actions here, for example
                // print first column value from selected row
                //System.out.println(sysTblProjectDetails.getValueAt(sysTblProjectDetails.getSelectedRow(), 0).toString());
            }
        });

        sysTblProjectDetails.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent argMouseEvent) {
                JTable target = (JTable) argMouseEvent.getSource();
                int row = target.getSelectedRow();
                int column = target.getSelectedColumn();
                //selectedRow
                //selectedColumn
                if (argMouseEvent.getClickCount() == 2) {
                    System.out.println("----ROW: " + row + "--COLUMN: " + column);
                    System.out.println(sysTblProjectDetails.getValueAt(sysTblProjectDetails.getSelectedRow(), 0).toString());
                    System.out.println(sysTblProjectDetails.getValueAt(row, column).toString());
                    System.err.println("-----SELE" + sysTblProjectDetails.getSelectedRow());
                }
            }
        });

        sysTblProjectDetails.getModel().addTableModelListener(new TableModelListener() {
            public void tableChanged(TableModelEvent argTableModelEvent) {
                // here goes your code "on cell update"
                System.err.println("|----|: CHHHHHHHHHHHHHHHHHHHHH");
                //http://docs.oracle.com/javase/tutorial/uiswing/components/table.html#modelchange
            }
        });

        sysTblProjectDetails.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent argKeyEvent) {
                if (argKeyEvent.getKeyCode() == KeyEvent.VK_ENTER) {

                    int row = sysTblProjectDetails.getSelectedRow();
                    int column = sysTblProjectDetails.getSelectedColumn();

                    // resul is the new value to insert in the DB
                    String resul = sysTblProjectDetails.getValueAt(row, column).toString();
                    // id is the primary key of my DB
                    String id = sysTblProjectDetails.getValueAt(row, 0).toString();

                    // update is my method to update. Update needs the id for
                    // the where clausule. resul is the value that will receive
                    // the cell and you need column to tell what to update.
                    //------|update(id, resul, column);
                    System.err.println("|----|: " + resul);

                }
            }
        });
    }
}
