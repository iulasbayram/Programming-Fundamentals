import java.util.ArrayList;

public class AuthorList {
	private ArrayList<Author> authorList;
	
	public AuthorList() {
		authorList = new ArrayList<Author>();
	}
	
	public ArrayList<Author> getAuthorList() {
		return authorList;
	}

	public void setAuthorList(ArrayList<Author> authorList) {
		this.authorList = authorList;
	}
	
	public void addAuthor(Author author) {
		authorList.add(author);
	}
	
	public void removeAuthor(Author author){
		authorList.remove(author);
	}
	public void printAuthors(){
		for( Author author : authorList) {
			System.out.println("Authors at authorlist:\n" + author.toString());
		}
	}
}
