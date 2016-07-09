package by.tr.library.view;

import by.tr.library.bean.Book;
import by.tr.library.bean.Request;
import by.tr.library.bean.Response;
import by.tr.library.controller.Controller;

import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * Created by Larisa_Bigvava on 7/8/2016.
 */
public class View {
    private Scanner scanner = new Scanner(System.in);
    private Controller controller = new Controller();

    public void start(){
        String command = "";
        while (!(command.equals("y") || command.equals("n"))) {
            System.out.println("Do you have an account (y/n)?");
            command = scanner.nextLine();
        }
        if (command.equals("y")) {
            authorization();
        } else if (command.equals("n")) {
            registration();
        }
        printMenu();
        command = scanner.nextLine();
        while (!command.equals("1")){
            switch (command){
                case "2":
                    printMenu();
                    System.out.println("Enter new command");
                    break;
                case "3":
                    getBookByTitle();
                    System.out.println("Enter new command");
                    break;
                case "4":
                    findBooksByAuthor();
                    System.out.println("Enter new command");
                    break;
                case "5":
                    addBook();
                    System.out.println("Enter new command");
                    break;
                case "6":
                    getCatalog();
                    System.out.println("Enter new command");
                    break;
                default:
                    System.out.println("Wrong command.");
                    System.out.println("Enter new command:");
            }
            command = scanner.nextLine();
        }
    }

    private void registration() {
        String login, password1, password2;
        System.out.println("Please enter your login:");
        login = scanner.nextLine();
        while (login.isEmpty()){
            System.out.println("Please enter your login:");
            login = scanner.nextLine();
        }
        System.out.println("Please enter your password:");
        password1 = scanner.nextLine();
        while (password1.isEmpty()){
            System.out.println("Please enter your password:");
            password1 = scanner.nextLine();
        }
        System.out.println("Please enter your password one more time:");
        password2 = scanner.nextLine();
        while (!password1.equals(password2)){
            System.out.println("Passwords don't match. Please  try again");
            password2 = scanner.nextLine();
        }
        Request request = new Request();
        request.setCommandName("REGISTER_COMMAND");
        request.setLogin(login);
        request.setPassword(password1);
        Response response = controller.doAction(request);
        if (response.getErrorMessage() != null){
            System.out.println(response.getErrorMessage());
            System.out.println("Try again");
            registration();
        } else {
            System.out.println(response.getMessage());
        }

    }

    private void printMenu(){
        System.out.println("Choose a command:");
        System.out.println("1. close program");
        System.out.println("2. print menu");
        System.out.println("3. find book by title");
        System.out.println("4. find book by author");
        System.out.println("5. add book");
        System.out.println("6. get catalog");
    }

    private void authorization(){
        String login, password;
        System.out.println("Please enter your login:");
        login = scanner.nextLine();
        while (login.isEmpty()){
            System.out.println("Please enter your login:");
            login = scanner.nextLine();
        }
        System.out.println("Please enter your password:");
        password = scanner.nextLine();
        while (password.isEmpty()){
            System.out.println("Please enter your password:");
            password = scanner.nextLine();
        }
        Request request = new Request();
        request.setCommandName("AUTHORIZATION_COMMAND");
        request.setLogin(login);
        request.setPassword(password);
        Response response = controller.doAction(request);
        if (response.getErrorMessage() != null){
            System.out.println(response.getErrorMessage());
            System.out.println("Try again");
            authorization();
        } else {
            System.out.println(response.getMessage());
        }
    }

    private void getBookByTitle(){
        String title;
        System.out.println("Enter book's title to find:");
        title = scanner.nextLine();
        while (title.isEmpty()){
            System.out.println("Enter book's title to find:");
            title = scanner.nextLine();
        }
        Request request = new Request();
        request.setCommandName("GET_BOOK_BY_TITLE_COMMAND");
        request.setTitle(title);
        Response response = controller.doAction(request);
        if (response.getErrorMessage() != null){
            System.out.println(response.getErrorMessage());
        } else {
            System.out.println(response.getMessage());
            printBook(response.getBook());
        }

    }

    private void printBook(Book book){
        System.out.println(book.getAuthor());
        System.out.println(book.getTitle());
        System.out.println(book.getPrice());
    }

    private void findBooksByAuthor(){
        String author;
        System.out.println("Enter book's author to find:");
        author = scanner.nextLine();
        while (author.isEmpty()){
            author = scanner.nextLine();
        }
        Request request = new Request();
        request.setCommandName("GET_BOOK_BY_AUTHOR_COMMAND");
        request.setAuthor(author);
        Response response = controller.doAction(request);
        if (response.getErrorMessage() != null){
            System.out.println(response.getErrorMessage());
        } else {
            System.out.println(response.getMessage());
            printBook(response.getBook());
        }
    }

    private void addBook(){
        String author, title, price;
        System.out.println("Enter author");
        author = scanner.nextLine();
        while (author.isEmpty()){
            System.out.println("Enter author");
            author = scanner.nextLine();
        }
        System.out.println("Enter title");
        title = scanner.nextLine();
        while (title.isEmpty()){
            System.out.println("Enter title");
            title = scanner.nextLine();
        }
        System.out.println("Enter price");
        price = scanner.nextLine();
        while (price.isEmpty() || !(price.matches("[0-9]+")) || price.startsWith("0")){
            System.out.println("Enter price");
            price = scanner.nextLine();
        }
        Request request = new Request();
        request.setCommandName("ADD_BOOK_COMMAND");
        request.setTitle(title);
        request.setAuthor(author);
        request.setPrice(Integer.parseInt(price));
        Response response = controller.doAction(request);
        if (response.getErrorMessage() != null){
            System.out.println(response.getErrorMessage());
        } else {
            System.out.println(response.getMessage());
            printBook(response.getBook());
        }
    }

    private void getCatalog(){
        Request request = new Request();
        request.setCommandName("GET_CATALOG_COMMAND");
        Response response = controller.doAction(request);
        if (response.getErrorMessage() != null){
            System.out.println(response.getErrorMessage());
        } else {
            List<Book> list = response.getListBook();
            System.out.println(response.getMessage());
            if (!(list == null)) {
                for (Book book: list) {
                    printBook(book);
                }
            }
        }
    }

    private void deleteBookByTitle(){
        String title;
        //TODO
    }

    private void deleteUserById(){
        int id;
        //TODO
    }

    private void deleteUserByLogin(){
        String login;
        //TODO
    }

    private void changePassword(){
        String password;
        //TODO
    }

}
