
package za.tut.ac.entity;

public class SpazaItem 
{
    private Integer id;
    private Integer quantity;
    private double price;
    private String description;
    
    public SpazaItem(){
        
    }

    public SpazaItem(Integer id, String description, double price, Integer quantity) {
        this.id = id;       
        this.quantity = quantity;
        this.price = price;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "SpazaShop{" + "id=" + id + ", quantity=" + quantity + ", price=" + price + ", description=" + description + '}';
    }
}
