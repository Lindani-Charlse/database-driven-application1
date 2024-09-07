package mamazalaspazashopapp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import za.tut.ac.bl.SpazaShopDB;
import za.tut.ac.entity.SpazaItem;

public class MamazalaSpazaShopApp 
{
    public static void main(String[] args) throws SQLException 
    {
        SpazaItem item;
        
        SpazaShopDB db = new SpazaShopDB("jdbc:derby://localhost:1527/SpazaShopDB;create=true", "app", "1234");
        
        Integer choice = displayMenu();
        Integer id;
        List<SpazaItem> list = new ArrayList<>();
        
        while(choice != 7)
        {
            switch(choice)
            {
                case 1:
                    // add item
                    item = get();
                    db.add(item);
                    JOptionPane.showMessageDialog(null, item.getDescription() + " Successfully added!!", "Success Message", JOptionPane.INFORMATION_MESSAGE);
                    break;
                    
                case 2:
                    // get item
                    item = db.get(getId());
                    display(item);
                    break;
                    
                case 3:
                    // get all items
                    list = db.getAllItems();
                    display(list);
                    break;
                    
                case 4:
                    // delete item
                    item = db.get(getId());
                    db.delete(item);
                    break;
                    
                case 5:
                    // change price 
                    id = getId();
                    item = db.get(id);
                    
                    // get new price from the user
                    double price = getNewPrice(item);
                    db.update(item, price);
                    break;
                    
                case 6:
                    // change quantity
                    id = getId();
                    item = db.get(id);
                    
                    // get new quantity fro the user
                    Integer quantity = getNewQuantity(item);
                    db.update(item, quantity);
                    break;
            }
            
            choice = displayMenu();
        }
    }
    
    public static Integer displayMenu()
    {
        Integer option = Integer.parseInt(JOptionPane.showInputDialog(null,
                        "Please choose from the menu below:\n" +
                        "Press [1] to Add Item \n" +
                        "Press [2] to Get Item \n" +
                        "Press [3] to Get All Items \n" +
                        "Press [4] to Delete Item \n" +
                        "Press [5] to Change Unit Price \n" +
                        "Press [6] to Change Item Quantity \n" +
                        "Press [7] to Exit" ));
        
        return option;
    }
    
    public static SpazaItem get()
    {
        Integer id = Integer.parseInt(JOptionPane.showInputDialog("Please enter item id"));
        String description = JOptionPane.showInputDialog("Please enter item description");
        double unitPrice = Double.parseDouble(JOptionPane.showInputDialog("Please enter " + description + "\'s price"));
        Integer quantity = Integer.parseInt(JOptionPane.showInputDialog("Please enter item quantity"));
        
        SpazaItem item = new SpazaItem(id, description, unitPrice, quantity);
        
        return item;
    }
    
    public static void display(SpazaItem item)
    {
        JOptionPane.showMessageDialog(null, item);
    }
    
    public static void display(List<SpazaItem> list)
    {
        JOptionPane.showMessageDialog(null, list);
    }
    
    public static Integer getId()
    {
        return Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter item ID"));
    }
    
    public static double getNewPrice(SpazaItem item)
    {
        return Double.parseDouble(JOptionPane.showInputDialog(null, "Please enter new price for " + item.getDescription()));
    }
    
    public static Integer getNewQuantity(SpazaItem item)
    {
        return Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter new quantity for " + item.getDescription()));
    }
    
}
