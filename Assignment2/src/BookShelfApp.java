
public class BookShelfApp {
	
	public static void main(String[] args) {
		
		DataAccessLayer d1 = new DataAccessLayer();
		BookShelf bookshelf = new BookShelf();
		AuthorList authorlist = new AuthorList();
		d1.readBooks(bookshelf , authorlist);
		BookShelfMenu menu = new BookShelfMenu();
		
		menu.init(bookshelf , authorlist);
		
		
		//bookshelf.printBooks();
		//authorlist.printAuthors();
	}
}
