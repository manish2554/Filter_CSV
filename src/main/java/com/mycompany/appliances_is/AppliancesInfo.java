
package com.mycompany.appliances_is;

/**
 *
 * @author sujan
 */
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;
import javax.swing.*;
import javax.swing.table.*;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.*;

public class AppliancesInfo extends JFrame {
   private JTable table;
   private TableModel model;
   static JLabel l;
   public AppliancesInfo() {
     
    
    JMenuBar mb = new JMenuBar();      
    JMenu fileMenu = new JMenu("File");
    fileMenu.setMnemonic(KeyEvent.VK_F);
    mb.add(fileMenu);
    JMenuItem Import = new JMenuItem("Import");
    fileMenu.add(Import);
    JMenu optionMenu = new JMenu("Options");
    optionMenu.setMnemonic(KeyEvent.VK_F);
    mb.add(optionMenu);
        
    setJMenuBar(mb);
    setVisible(true);
    l = new JLabel("no file selected");
    
    //read document
    Import.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String text;
           JFileChooser j = new JFileChooser("E:\\csv");
           j.setAcceptAllFileFilterUsed(false);
           FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .csv files", "csv");
            j.addChoosableFileFilter(restrict);
            // Open the save dialog
            int r = j.showOpenDialog(null);
 
            // if the user selects a file
            if (r == JFileChooser.APPROVE_OPTION) {
                // set the label to the path of the selected file
                l.setText(j.getSelectedFile().getAbsolutePath());
            }
            // if the user cancelled the operation
            else
                l.setText("the user cancelled the operation");
        
         }
      });
      
      //trial data
      Object rows[][] = {
         {1001,"kent air purifier" ,"air purifier ","best seller",8000,500},
                  {1002,"momax air purifier","air purifier ","no recommendation yet",9000,300},
                          {1003,"phillips air care ","air purifier ","best rating ",40000,1200},
                                  {2001,"CG iron","iron","trending",1500,100},
                                   {2002,"baltra steam iron","iron","best seller",1200,50},
                                           {2003,"phillips iron","iron","best rating ",2500,100},
                                                   {3001,"CG refrigerator ","refrigerator","best seller",30000,300},
                                                   {3002,"phillips refrigerator ","refrigerator","trending",40000,200},
                                                           {3003,"LG 190 ltrs refrigerator","refrigerator","no recommendation yet",35000,350},
                                                                   {3004,"samsung double door 190ltrs refrigerator","refrigerator","best rating ",50000,500},
                                                                           {4001,"LG oven","oven","best seller",23000,250},
                                                                                   {4002,"Godrej oven","oven","no recommendation yet",22000,220},
                                                                                           {4003,"samsung oven","oven" ,"best rating ",25000,250},
                                                                                                   {4004,"baltra oven","oven" ,"trending",20000,200}
          };
      Object columns[] = {"App. Mo. No.", "App. Name", "Category", "Recommendation", "Price", "Discount"};
      
      //model table
      model = new DefaultTableModel(rows, columns) {
         public Class getColumnClass(int column) {
            Class returnValue;
            if((column >= 0) && (column < getColumnCount())) {
               returnValue = getValueAt(0, column).getClass();
            } else {
               returnValue = Object.class;
            }
            return returnValue;
         }
      };
      table = new JTable(model);
      
      
      final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
      table.setRowSorter(sorter);
      add(new JScrollPane(table), BorderLayout.CENTER);
      JPanel panel = new JPanel(new BorderLayout());
      JLabel label = new JLabel("Filter");
      panel.add(label, BorderLayout.WEST);
      final JTextField filterText = new JTextField("");
      panel.add(filterText, BorderLayout.CENTER);
      add(panel, BorderLayout.NORTH);
      JButton button = new JButton("Filter");
      button.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String text = filterText.getText();
            if(text.length() == 0) {
               sorter.setRowFilter(null);
            } else {
               try {
                  sorter.setRowFilter(RowFilter.regexFilter(text));
               } catch(PatternSyntaxException pse) {
                     System.out.println("Bad regex pattern");
               }
             }
         }
      });
      //panel.add(l);
      add(button, BorderLayout.SOUTH);
      setSize(400, 400);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
      setVisible(true);
   }
   public static void main(String args[]) {
      new AppliancesInfo();
   }
}