import java.util.ArrayList;

public class Book {
	private	String title;
	private String ISBNnumber;
	private Genre genre;
	private String publishing_year;
	private String publishing_company;
	private double price;
	private ArrayList<Author> authors = new ArrayList<Author>();

	public Book(String title,String ISBNnumber,Genre genre,String publishing_year,String publishing_company,double price) {
		setTitle(title);
		setISBNnumber(ISBNnumber);
		setGenre(genre);
		setPublishing_year(publishing_year);
		setPublishing_company(publishing_company);
		setPrice(price);
	}
	public void addAuthor(Author author){
		authors.add(author);
	}
	public void removeAuthor(Author author) {
		authors.remove(author);
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getISBNnumber() {
		return ISBNnumber;
	}
	public void setISBNnumber(String iSBNnumber) {
		ISBNnumber = iSBNnumber;
	}
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	public String getPublishing_year() {
		return publishing_year;
	}
	public void setPublishing_year(String publishing_year) {
		this.publishing_year = publishing_year;
	}
	public String getPublishing_company() {
		return publishing_company;
	}
	public void setPublishing_company(String publishing_company) {
		this.publishing_company = publishing_company;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public ArrayList<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(ArrayList<Author> authors) {
		this.authors = authors;
	}

	public String toString(){
		return "Book = [title = "+ title.toUpperCase() + " ,ISBN number = " + ISBNnumber + ", genre = " + genre + ", publishing year = " + publishing_year + 
				", publishing company = " + publishing_company + ", price = " + price + "]";
	}
	public void printAuthors(){
		for( Author author : authors) {
			System.out.println("Authors of book " + getTitle() + ":\n" + author.toString());
		}
	}
}
