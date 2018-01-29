import java.util.ArrayList;
import java.util.Scanner;

public class BookShelfMenu {
	public void printMenu() {
		System.out.println("__________MENU__________");
		System.out.println("1)Add a Book.\n2)Remove a book.\n3)Search books by genre.\n4)Search books by publishing year.\n5)Search books by author name.");
		System.out.println("6)Search books by price.\n7)Calculate total price of books in the bookshelf.\n8)Calculate total price of books by author.\n9)Quit");
		System.out.println("Choose a operation entering its number,If you want to quit choose 9th operation!:");
	}
	
	public void init(BookShelf bookshelf , AuthorList authorlist) {
		printMenu();
		boolean printmenu = true;
		Scanner choiceOperation = new Scanner(System.in);
		boolean mainBool = true;
		while(mainBool) {	
			String choice = choiceOperation.nextLine();
				if( choice.equals("1")) {
					String title = takeTitle();
					String ISBN ="";
					boolean booL = true;
					while(booL){
						boolean ISBNexist = false;
						ISBN = takeISBNnumber();
						for(Book book : bookshelf.getBookShelf()){
							if(ISBN.equals(book.getISBNnumber())) {ISBNexist = true;}
						}
						if(ISBNexist == true){
							System.out.println("There is already a book which have this ISBN number.Give a different ISBN:");
							}
						else{booL = false;}
					  }
					Genre genre = BookShelfMenu.getGenre(takeGenre());
					String publishing_year = takePublishingYear();
					String publishing_place = takePublishingCompany();
					double price = takeprice();

					Book book = new Book(title,ISBN,genre,publishing_year,publishing_place,price);
					bookshelf.getBookShelf().add(book);
					
					int author_count = takeAuthorCount();
					ArrayList<String> author_name_list = new ArrayList<String>(); // to control whether author is added before or not,this list is used
					for ( int a = 0 ; a < author_count ; a++) {
						System.out.println("Enter " + (a+1) + "'th author's name and surname:");
						String author_name = takeAuthorName();
						author_name_list.add(author_name);
					}
					for(String nameOfAuthors : author_name_list){
						boolean authorExist = false;						
						for( Author authoR : authorlist.getAuthorList()){
							if(nameOfAuthors.equalsIgnoreCase(authoR.getName())) {
								authoR.addBook(book);
								book.addAuthor(authoR);
								System.out.println("Author: " + nameOfAuthors.toUpperCase()  +" is added before.This book is adding his booklist directly.");
								authorExist = true; 
							}
						 }
					  if (authorExist == false) {
						System.out.println("Author: " + nameOfAuthors.toUpperCase()  +" is not added before.Give informations to add:");
						Author author = new Author(nameOfAuthors, takeBirthYear(), takeBirthPlace());
						authorlist.addAuthor(author);						
						author.addBook(book);
						book.addAuthor(author);
						System.out.println(title.toUpperCase() + " is added to booklist of author " + nameOfAuthors.toUpperCase() + ".");
					 }
				}
				System.out.println(title.toUpperCase() + " is added succesfully to bookshelf with its author(s).");
			}
			else if(choice.equals("2")) {
				if(bookshelf.getBookShelf().size() > 0) {
					boolean bool1 = true;
					int index = 0;
					while(bool1) {
						String isbn = takeISBNnumber();
						for(Book booK : bookshelf.getBookShelf()) {
							if (isbn.equalsIgnoreCase(booK.getISBNnumber())){
								System.out.println("Book:"+ booK.getTitle().toUpperCase() + " is removed succesfully!");
								index = bookshelf.getBookShelf().indexOf(booK); // index of book which have the given ISBN number
								bool1 = false;
								}
							} 
					  if(bool1 == true) {System.out.println("There is no book which has the given ISBN number!");}
					}
					
					Book removing_book = bookshelf.getBookShelf().get(index);  //this book will be removed after controlling the author count at following loops
					for ( Author authorOfBook : removing_book.getAuthors() ){   // this loop will execute as long as book's author size
						String nameOfAuthor =  authorOfBook.getName();  //  Choosing the author's names one by one
						authorOfBook.removeBook(removing_book);   // book is being removed from chosen author's booklist
						
						if ( (authorOfBook.getBookList().size()) == 0) {  // after removing, if there is 0 book remains at booklist of author,program remove author from authorlist
							System.out.println("Author: " + authorOfBook.getName().toUpperCase() + " is removed succesfully because there is no book of him anymore at bookshelf!" );
							authorlist.getAuthorList().remove(authorOfBook); } } 
				
					bookshelf.getBookShelf().remove(index);  // books which user wants to remove is being removed from bookshelf					
				 }
				else{System.out.println("There is no book at bookshelf to remove.Please add a book firstly!");}
			}		
			else if (choice.equals("3")){
				if(bookshelf.getBookShelf().size() > 0) {
					String genre = takeGenre();
					Genre genreOfBook = BookShelfMenu.getGenre(genre);
					boolean bookExist = false;
					System.out.println("Books which have the given genre:");
					for ( Book book : bookshelf.getBookShelf() ) {
						if(genreOfBook.equals(book.getGenre())) {
							System.out.println( book.toString());
							bookExist = true;
						}
					 }
					if( bookExist == false) {System.out.println("There is no book which have the given genre!");}
				   }
			   else{System.out.println("There is no book at bookshelf to search.Please add books to search!");}
			}
			else if ( choice.equals("4")) {
				if(bookshelf.getBookShelf().size() > 0) {
					String pYear = takePublishingYear();
					boolean bookExist = false;
					System.out.println("Books which have the given publishing year:");
					for ( Book book : bookshelf.getBookShelf()) {
						if(pYear.equals(book.getPublishing_year())) {
							System.out.println( book.toString());
							bookExist = true;
						}
					}
					if( bookExist == false) {System.out.println("There is no book which have the given publishing year!");}
				   }
				else{System.out.println("There is no book at bookshelf to search.Please add books to search!");}
			}
			else if ( choice.equals("5")) {
				if(bookshelf.getBookShelf().size() > 0) {
					System.out.println("Enter author name and surname:");
					String authorName = takeAuthorName();
					boolean bookExist = false;
					for ( Author author : authorlist.getAuthorList()) {
						if(authorName.equalsIgnoreCase(author.getName())) {
							for ( Book book : author.getBookList()){
								System.out.println(book.toString());
								}
						    bookExist = true;
							}
						}
					if( bookExist == false) {System.out.println("There is no book of this author at bookshelf!");}
				}
				else{System.out.println("There is no book at bookshelf to search.Please add books to search!!");}
			}				
			else if(choice.equals("6")) {
				if(bookshelf.getBookShelf().size() > 0) {
					boolean validLimit = true;
					while(validLimit) {
						System.out.println("Enter lover limit");
						double lower_limit = takeLimit();
						System.out.println("Enter upper limit");
						double upper_limit = takeLimit();
						if ( upper_limit >= lower_limit) {
							int counter = 0;   //count of book at given range
							System.out.println("Books whose price is in the given range:");
							for ( Book book : bookshelf.getBookShelf()) {
								if( book.getPrice() >= lower_limit && book.getPrice() <= upper_limit) {
									counter++;
									System.out.println(book.toString());
								}
							}
							if (counter == 0) {System.out.println("There is no book in the given range");} 
							validLimit = false;
						}
						else {System.out.println("Please enter a valid lower and upper limit!"); }
					}
				}
			 else{System.out.println("There is no book at bookshelf to search.Please add books to search!");}
		  }
			else if ( choice.equals("7")) {
				double totalprice = 0.0;
				for (int i = 0 ; i < bookshelf.getBookShelf().size() ; i++) {
					totalprice += bookshelf.getBookShelf().get(i).getPrice();
				}
				System.out.println("Total price of all books in bookshelf: $" + totalprice);				
			}
			else if( choice.equals("8")){
				double author_total_price = 0.0;
				System.out.println("Enter author name and surname to get total price of her/his books:");
				String authorName = takeAuthorName();
				boolean bookExist = false;
				for ( Author author : authorlist.getAuthorList()) {
					if(authorName.equalsIgnoreCase(author.getName())) {
						for ( Book book : author.getBookList()){
							 author_total_price += book.getPrice();}
							 bookExist = true;
					}
				}
				if( bookExist == false) {System.out.println("There is no book of this author at bookshelf!");}
				System.out.println("Total price of all books of " + authorName.toUpperCase() + " is: $" + author_total_price + ".");
			}
			else if (choice.equals("9")){  // TO FINISH THE PROGRAMME OPERATION
				mainBool = false;
			 	printmenu = false;
			}
			else { // for invalid operation choice
			 	mainBool = true  ;
			 	System.out.println("Please choose a valid operation!");}		
			if(printmenu == true){printMenu();} // after operation choice, print menu or not,if user wants to continue,print menu
			else{System.out.println("End of the application.See You!");} // if user choose quit print this
		}
	}
	
	public String takeTitle()
	{
		String title = "";
		boolean bool = true;
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter title of book:");
		while (bool) {
			title = scan.nextLine();
			if (!title.matches(".*[\\p{Punct}].*") && title.matches("[\\S].*") ){
				bool = false;}
			else{System.out.println("Enter a valid book title please:");}
		}
	return title;
	}
	public String takeISBNnumber(){
		String isbn = "";
		boolean bool = true;
		Scanner scan = new Scanner(System.in);
		while (bool) {
			System.out.println("Enter ISBN number of book:");
			isbn = scan.nextLine();
			isbn.replaceAll("( |-)", "");
			if (isbn.matches("^{1}[0-9]{3}[-]{1}[0-9]{3}[-]{1}[0-9]{2}[-]{1}[0-9]{4}[-]{1}[0-9]{1}$") ){
				bool = false;}
			else{System.out.println("Enter a valid ISBN at format: |||-|||-||-||||-| \nAnd do not forget the '-' between group of numbers.");}
		}
		return isbn;
	}
	public String takeGenre(){
		Scanner input = new Scanner(System.in);
		System.out.println("Enter genre of book: SCIENCE,DRAMA,ADVENTURE,HORROR,HISTORY or COMICS");	
		String genre = input.nextLine();
		return genre;
	}
	public String takePublishingYear(){
		String publishing_year = "";		
		Scanner scan = new Scanner(System.in);
		boolean bool = true;
		System.out.println("Enter publishing year of book:");
		while (bool) {
			publishing_year = scan.nextLine();
			if (publishing_year.matches("[0-9]*") && publishing_year.matches("[\\S].*" ) &&  Integer.parseInt(publishing_year) < 2018) {
				bool = false;}
			else{System.out.println("Enter a valid publishing year please:");}
		}
		return publishing_year;
	}
	public String takePublishingCompany(){
		String publishing_company = "";
		boolean bool = true;
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter publishing company of book:");
		while (bool) {
			publishing_company = scan.nextLine();
			if (publishing_company.matches("[^0-9]*") && !publishing_company.matches(".*[\\p{Punct}].*") && publishing_company.matches("[\\S].*") ){
				bool = false;}
			else{System.out.println("Enter a valid publishing company please:");}
		}
		return publishing_company;
	}
	
	public int takeAuthorCount(){
		String author_count = "";		
		Scanner scan = new Scanner(System.in);
		System.out.println("How many author does the book have?");
		boolean bool = true;
		while (bool) {
			author_count = scan.nextLine();
			if (author_count.matches("[0-9]*") && author_count.matches("[\\S].*" ) &&  Integer.parseInt(author_count) < 10) {
				bool = false;}
			else{System.out.println("Enter a valid author count please:");}
		}
		return  Integer.parseInt(author_count);
	}
	public String takeAuthorName(){
		String authorName = "";
		boolean bool = true;
		Scanner scan = new Scanner(System.in);
		while (bool) {
			authorName = scan.nextLine();
			if (authorName.matches("[^0-9]*") && !authorName.matches(".*[\\p{Punct}].*") && authorName.matches("[\\S].*") ){
				bool = false;}
			else{System.out.println("Enter a valid author name please:");}
		}
		return authorName;
	}
	public String takeBirthYear()
	{
		String birth_year = "";		
		Scanner scan = new Scanner(System.in);
		boolean bool = true;
		System.out.println("Enter birth year of author:");
		while (bool) {
			birth_year = scan.nextLine();
			if (birth_year.matches("[0-9]*") && !birth_year.matches(".*[\\p{Punct}].*") && birth_year.matches("[\\S].*" ) &&  Integer.parseInt(birth_year) < 2016) {
				bool = false;}
			else{System.out.println("Enter a valid birth year please:");}
		}
		return birth_year;
	}	
	public String takeBirthPlace()
	{
		String birthPlace = "";
		boolean bool = true;
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter birth place of author:");
		while (bool) {
			birthPlace = scan.nextLine();
			if (birthPlace.matches("[^0-9]*") && !birthPlace.matches(".*[\\p{Punct}].*") && birthPlace.matches("[\\S].*") ){
				bool = false;}
			else{System.out.println("Enter a valid birth place!");}
		}
	return birthPlace;
	}	
	
	public double takeLimit(){
		String limit = "";		
		Scanner scan = new Scanner(System.in);
		boolean bool = true;
		while (bool) {
			limit = scan.nextLine();
			if (limit.matches("[0-9].*") && limit.matches("[\\S].*"  )) {
				bool = false;}
			else{System.out.println("Enter a valid limit please:");}
		}
		return Double.parseDouble(limit);
	}
	public double takeprice(){
		String price = "";		
		Scanner scan = new Scanner(System.in);
		boolean bool = true;
		System.out.println("Enter price of book:");
		while (bool) {
			price = scan.nextLine();
			if (price.matches("[0-9].*") && price.matches("[\\S].*") ) {
				bool = false;}
			else{System.out.println("Enter a valid price please:");}
		}
		return Double.parseDouble(price);
	}
	public static Genre getGenre(String genre) {
		Genre genRe = null;
		boolean bool = true;
		
		while(bool) {
		if ( genre.equalsIgnoreCase("science")){ genRe = Genre.SCIENCE; bool = false;}
		else if ( genre.equalsIgnoreCase("drama")) {genRe = Genre.DRAMA; bool = false;}
		else if ( genre.equalsIgnoreCase("adventure")) {genRe = Genre.ADVENTURE; bool = false;}
		else if ( genre.equalsIgnoreCase("horror")) {genRe = Genre.HORROR; bool = false;}
		else if ( genre.equalsIgnoreCase("history")) {genRe = Genre.HISTORY; bool = false;}
		else if ( genre.equalsIgnoreCase("comics")) {genRe = Genre.COMICS; bool = false;}
		
		else {System.out.println("Wrong genre!");
			Scanner input = new Scanner(System.in);
			System.out.println("Enter genre of book: SCIENCE,DRAMA,ADVENTURE,HORROR,HISTORY or COMICS");	
			genre = input.nextLine();}
		}
		return genRe;
	}

}