import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class DataAccessLayer {
		
	public void readBooks(BookShelf bookshelf , AuthorList authorlist) {	
		try{
			Scanner readBook = new Scanner(new File("books.dat"));
			Scanner readAuthor = new Scanner (new File("authors.dat"));

			while(readBook.hasNextLine()) {      // ADD BOOKS TO BOOKSHELF FROM FILE books.dat
				String bookInfo = readBook.nextLine();
				StringTokenizer st = new StringTokenizer(bookInfo , ",");
				String title = st.nextToken();
				String ISBN = st.nextToken();
				Genre genre = BookShelfMenu.getGenre(st.nextToken());
				String publishing_year = st.nextToken();
				String publishing_company = st.nextToken();
				double price = Double.parseDouble(st.nextToken());
				Book book = new Book(title,ISBN,genre,publishing_year,publishing_company,price);
				bookshelf.addBook(book);
			}
			while(readAuthor.hasNextLine()) {     // ADD AUTHORS TO AUTHORLIST FROM FILE authors.dat
				String authorInfo = readAuthor.nextLine();
				StringTokenizer sto = new StringTokenizer(authorInfo, ",");
				String name = sto.nextToken();
				String birth_year = sto.nextToken();
				String birth_place = sto.nextToken();
				Author author = new Author(name,birth_year,birth_place);
				authorlist.addAuthor(author);
			}
			
			for(int i = 0 ; i < bookshelf.getBookShelf().size() ; i++) { // ADD AUTHORS TO AUTHORLIST OF BOOK AND BOOKS TO BOOKLIST OF AUTHOR
				bookshelf.getBookShelf().get(i).addAuthor( authorlist.getAuthorList().get(i) );
				authorlist.getAuthorList().get(i).addBook( bookshelf.getBookShelf().get(i) );				
			}
			readBook.close();
			readAuthor.close();
		}
		catch(IOException e) {
			System.out.println("Error is =" + e.getMessage());
		}	
	}
}
