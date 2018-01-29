import java.util.ArrayList;

public class BookShelf {
	private  ArrayList<Book> bookShelf;
	
	public BookShelf() {
		bookShelf = new ArrayList<Book>();
	}
	
	public ArrayList<Book> getBookShelf() {
		return bookShelf;
	}
	
	public void setBookShelf(ArrayList<Book> bookShelf) {
		this.bookShelf = bookShelf;
	}
	
	public void addBook(Book book) {
		bookShelf.add(book);
	}

	public void removeBook(Book book) {
		bookShelf.remove(book);
	}
	public void printBooks() {
		for( Book book : bookShelf){
			System.out.println("Books at bookshelf:\n" + book.toString());
		}
	}
}
