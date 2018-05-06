import java.util.ArrayList;
public class Store {
	
	private static ArrayList<Books> myStore = new ArrayList<>();

	public static void main(String[] args) {
		add("978-0471791911","Java for Everyone", 10, 68.00, 65.15);
		add("978-0596009205", "Head First Java", 2, 50.00, 55.50 );
		add("978-0321996329", "Core Java for the Impatient", 0, 101.70, 123.20);
		printAllBooks();
		printAveragePrices();
		findBook("978-0321996329");
		findBook("001");

	}

	public static void add(String ISBN, String title, int quantity, double price1, double price2){
		double[] prices = {price1, price2};
		Books newItem = new Books(ISBN, title, quantity, prices);
		myStore.add(newItem);
	}

	public static void printAllBooks(){
		System.out.println("Books Sold Here");
		for(int i=0; i < myStore.size(); i++){
			myStore.get(i).print();
		}
	}

	
	public static void printAveragePrices(){
		System.out.println("Print Average Prices");
		for(Books eachItem : myStore){
			double average = (eachItem.getPrices()[0] + eachItem.getPrices()[1])/2.0;
			System.out.println("Item No: " + eachItem.getISBN() + " average price: $" +
			average);
		}
	}

	public static void findBook(String ISBN){
		for( Books b: myStore){
			if(b.getISBN().equals(ISBN)){
				if(b.getQuantity() > 0){
					System.out.println(ISBN + " is in stock.");
					return;
				}else{
					System.out.println(ISBN + " will have to be ordered");
					return;
				}
			}
		}
		System.out.println("We do not sell " + ISBN);

	}

}
