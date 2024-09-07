package za.tut.ac.bl;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import za.tut.ac.entity.SpazaItem;

public class SpazaShopDB implements SpazaShopInterface<SpazaItem> 
{
    private Connection connection;

    public SpazaShopDB(String dbURL, String name, String password) throws SQLException {
        connection = getConnection(dbURL, name, password);
    }
    
    @Override
    public SpazaItem get(Integer code) {
        String sql = "SELECT Id, Description, quantity, unitPrice " + 
                "FROM ItemsTBL " + 
                "WHERE id =?";
        
        try 
        {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, code);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next())
            {
                Integer id = rs.getInt("Id");
                String description = rs.getString("Description");
                Integer quantity = rs.getInt("Quantity");
                double price = rs.getDouble("UnitPrice");
                
                SpazaItem item = new SpazaItem(id, description, price, quantity);
                
                rs.close();
                
                return item;
                
            } else {
                rs.close();
                return null;
            }
            
        } catch (SQLException ex) {
            System.err.println();
            return null;
        }
    }
    
    @Override
    public boolean update(SpazaItem item, double newPrice) 
    {
        String sql = "UPDATE ItemsTBL SET " +  
                " UnitPrice =?" +
                "WHERE Id =?";
        
        try 
        {
            PreparedStatement ps = connection.prepareStatement(sql);
            
            
            ps.setDouble(1, newPrice);
            ps.setInt(2, item.getId());
            ps.executeUpdate();
            
            return true;
        } catch (SQLException ex) {
            System.err.println();
            return false;
        }
    }
    
     @Override
    public boolean update(SpazaItem item, Integer newQuantity) 
    {
        String sql = "UPDATE ItemsTBL SET " +  
                " Quantity  =?" +
                "WHERE Id =?";
        
        try 
        {
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ps.setInt(1, newQuantity);
            ps.setInt(2, item.getId());
            ps.executeUpdate();
            
            return true;
        } catch (SQLException ex) {
            System.err.println();
            return false;
        }
    }

    @Override
    public boolean delete(SpazaItem item) {
        String sql = "DELETE FROM ItemsTBL " + 
                    "WHERE Id =?";
        
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, item.getId());
            ps.executeUpdate();
            
            return true;
                    
        } catch (SQLException ex) {
            System.err.println(ex);
            return false;
        }
    }

    @Override
    public boolean add(SpazaItem t) {
        String sql = "INSERT INTO ItemsTBL (Id, Description, Quantity, unitPrice) " +
                    "VALUES (?, ?, ?, ?)";
        
        try {   
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ps.setInt(1, t.getId());
            ps.setString(2, t.getDescription());
            ps.setInt(3, t.getQuantity());
            ps.setDouble(4, t.getPrice());
            
            ps.executeUpdate();
            return true;
            
        } catch (SQLException ex) {
            System.err.println(ex);
            return false;
        }
    }

    @Override
    public List<SpazaItem> getAllItems() {
        
            String sql = "SELECT * FROM ItemsTBL " +
                    "ORDER BY Id ASC";
            
            List<SpazaItem> items = new ArrayList<>();
            
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                Integer id = rs.getInt("Id");
                String description = rs.getString("Description");
                Integer quantity = rs.getInt("Quantity");
                double price = rs.getDouble("UnitPrice");
                
                SpazaItem spaza = new SpazaItem(id, description, price, quantity);
                
                items.add(spaza);
            }
            
            return items;
            
        } catch (SQLException ex) {
            System.err.println(ex);
            return null;
        }
    }
    
    private Connection getConnection(String dbURL, String name, String password) throws SQLException
    {
        Connection theConnection = DriverManager.getConnection(dbURL, name, password);
                
        return theConnection;
    } 

}
