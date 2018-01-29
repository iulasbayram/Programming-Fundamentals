import java.util.ArrayList;

public class Author {
	private String name;
	private String birth_year;
	private String birth_place;
	private ArrayList<Book> bookList = new ArrayList<Book>();
	
	public Author(String name, String birth_year , String birth_place ) {
		setName(name);
		setBirth_year(birth_year);
		setBirth_place(birth_place);
	}
	public void addBook(Book book) {
		bookList.add(book);
	}
	public void removeBook(Book book) {
		bookList.remove(book);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth_year() {
		return birth_year;
	}
	public void setBirth_year(String birth_year) {
		this.birth_year = birth_year;
	}
	public String getBirth_place() {
		return birth_place;
	}
	public void setBirth_place(String birth_place) {
		this.birth_place = birth_place;
	}

	public ArrayList<Book> getBookList() {
		return bookList;
	}
	public void setbookList(ArrayList<Book> bookList) {
		this.bookList = bookList;
	}
	
	public String toString() {
		return "Author = [Name = " + name + ",birth year = " + birth_year + ",birth place = " + birth_place + "]";
	}
	
	public void printBooks() {
		for( Book book : bookList){
			System.out.println("Books of "+ getName() + ":\n" + book.toString());
		}
	}
}
