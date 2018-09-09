public class Book
{
    private String isbn;
    private String title;
    private int quantity;
    private double[] prices;

    //Constructor
    public Book(String isbn, String title, int quantity, double[] prices)
    {
        setISBN(isbn);
        setTitle(title);
        setQuantity(quantity);
        setPrices(prices);
    }

    //Accessor Methods (get methods) and Mutator methods (set methods)
    public String getISBN()
    {
        return isbn;
    }

    public void setISBN(String isbn)
    {
        this.isbn = isbn;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String Title)
    {
        title = Title;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public void setPrices(double[] prices)
    {
        this.prices = prices;
    }

    public double[] getPrices()
    {
        return prices;
    }
    
    //Print method
    public void print()
    {
        System.out.printf(
                "ISBN: %-16s "
                + "Title: %-28s "
                + "Qty: %-3d "
                + "Current Price: $%-8.2f "
                + "Previous Price: $%-8.2f\n",
                getISBN(),
                getTitle(),
                getQuantity(),
                getPrices()[0],
                getPrices()[1]);
    }
}