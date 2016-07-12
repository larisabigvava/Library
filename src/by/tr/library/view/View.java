package by.tr.library.view;

import by.tr.library.bean.*;
import by.tr.library.controller.Controller;
import java.util.Scanner;

/**
 * Created by Larisa_Bigvava on 7/8/2016.
 */
public class View {
    private Scanner scanner = new Scanner(System.in);
    private Controller controller = new Controller();
    private User user;

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
        printUserMenu();
        if (user.getRole().equals("ADMIN")) {
            printAdminMenu();
            command = scanner.nextLine();
            while (!command.equals("1")){
                switch (command){
                    case "2":
                        printUserMenu();
                        printAdminMenu();
                        System.out.println("Enter new command");
                        break;
                    case "3":
                        getBooksByTitle();
                        System.out.println("Enter new command");
                        break;
                    case "4":
                        getBooksByAuthor();
                        System.out.println("Enter new command");
                        break;
                    case "5":
                        changePassword();
                        System.out.println("Enter new command");
                        break;
                    case "6":
                        getCatalog();
                        System.out.println("Enter new command");
                        break;
                    case "7":
                        blockUserByLogin();
                        System.out.println("Enter new command");
                        break;
                    case "8":
                        deleteUserByLogin();
                        System.out.println("Enter new command");
                        break;
                    case "9":
                        addBook();
                        System.out.println("Enter new command");
                        break;
                    case "10":
                        deleteBookByTitle();
                        System.out.println("Enter new command");
                        break;
                    case "11":
                        unblockUserByLogin();
                        System.out.println("Enter new command");
                        break;
                    default:
                        System.out.println("Wrong command.");
                        System.out.println("Enter new command:");
                }
                command = scanner.nextLine();
            }
        } else if (user.getRole().equals("USER")) {
            command = scanner.nextLine();
            while (!command.equals("1")) {
                switch (command) {
                    case "2":
                        printUserMenu();
                        if (user.getRole().equals("ADMIN")) {
                            printAdminMenu();
                        }
                        System.out.println("Enter new command");
                        break;
                    case "3":
                        getBooksByTitle();
                        System.out.println("Enter new command");
                        break;
                    case "4":
                        getBooksByAuthor();
                        System.out.println("Enter new command");
                        break;
                    case "5":
                        changePassword();
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
    }

    private void unblockUserByLogin() {
        String login = "";
        while (login.isEmpty()){
            System.out.println("Please enter user's login:");
            login = scanner.nextLine();
        }
        Request request = new Request();
        request.setCommandName("UNBLOCK_USER_BY_LOGIN_COMMAND");
        request.setLogin(login);
        Response response = controller.doAction(request);
        if (response.getErrorMessage() != null){
            System.out.println(response.getErrorMessage());
        } else {
            System.out.println(response.getMessage());
        }
    }

    private void blockUserByLogin() {
        String login = "";
        while (login.isEmpty()){
            System.out.println("Please enter user's login:");
            login = scanner.nextLine();
        }
        Request request = new Request();
        request.setCommandName("UNBLOCK_USER_BY_LOGIN_COMMAND");
        request.setLogin(login);
        Response response = controller.doAction(request);
        if (response.getErrorMessage() != null){
            System.out.println(response.getErrorMessage());
        } else {
            System.out.println(response.getMessage());
        }
    }

    private void registration() {
        String login = "", password1 = "", password2 = "";
        while (login.isEmpty()){
            System.out.println("Please enter your login:");
            login = scanner.nextLine();
        }
        while (password1.isEmpty()){
            System.out.println("Please enter your password:");
            password1 = scanner.nextLine();
        }
        System.out.println("Repeat password");
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
            user = response.getUser();
        }

    }

    private void printUserMenu(){
        System.out.println("Choose a command:");
        System.out.println("1. close program");
        System.out.println("2. print menu");
        System.out.println("3. find book by title");
        System.out.println("4. find books by author");
        System.out.println("5. change password");
        System.out.println("6. get catalog");
    }

    private void printAdminMenu(){
        System.out.println("7. block user by login");
        System.out.println("8. delete user by login");
        System.out.println("9. add book");
        System.out.println("10. delete book by title");
        System.out.println("11. unblock user by login");
    }

    private void authorization(){
        String login = "", password = "";
        while (login.isEmpty()){
            System.out.println("Please enter your login:");
            login = scanner.nextLine();
        }
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
            user = response.getUser();
        }
    }

    private void getBooksByTitle(){
        String title = "";
        while (title.isEmpty()){
            System.out.println("Enter book's title:");
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
            for (Book book: response.getCatalog().getBooks()) {
                printBook(book);
            }
            for (ProgrammerBook programmerBook: response.getCatalog().getProgrammerBooks()) {
                printBook(programmerBook);
            }
        }

    }

    private void printBook(Book book){
        System.out.println("Author: "+book.getAuthor());
        System.out.println("Title: "+book.getTitle());
        System.out.println("Price: "+book.getPrice());
        if (book.getClass() == ProgrammerBook.class){
            ProgrammerBook programmerBook = (ProgrammerBook) book;
            System.out.println("Language: "+programmerBook.getLanguage());
            System.out.println("Level: "+programmerBook.getLevel());
        }
    }

    private void getBooksByAuthor(){
        String author = "";
        while (author.isEmpty()){
            System.out.println("Enter book's author:");
            author = scanner.nextLine();
        }
        Request request = new Request();
        request.setCommandName("GET_BOOKS_BY_AUTHOR_COMMAND");
        request.setAuthor(author);
        Response response = controller.doAction(request);
        if (response.getErrorMessage() != null){
            System.out.println(response.getErrorMessage());
        } else {
            System.out.println(response.getMessage());
            for (Book book: response.getCatalog().getBooks()) {
                printBook(book);
            }
            for (ProgrammerBook programmerBook: response.getCatalog().getProgrammerBooks()) {
                printBook(programmerBook);
            }
        }
    }

    private void addBook(){
        String author = "", title = "", price = "", command = "", language = "", level = "";
        while (author.isEmpty()){
            System.out.println("Enter author");
            author = scanner.nextLine();
        }
        while (title.isEmpty()){
            System.out.println("Enter title");
            title = scanner.nextLine();
        }
        while (price.isEmpty() || !(price.matches("[0-9]+")) || price.startsWith("0")){
            System.out.println("Enter price");
            price = scanner.nextLine();
        }
        Request request = new Request();
        request.setCommandName("ADD_BOOK_COMMAND");
        request.setTitle(title);
        request.setAuthor(author);
        request.setPrice(Integer.parseInt(price));
        while (!(command.equals("y") || command.equals("n"))) {
            System.out.println("Does it programmer book (y/n)?");
            command = scanner.nextLine();
        }
        if (command.equals("y")){
            while (language.isEmpty()){
                System.out.println("Enter language");
                language = scanner.nextLine();
            }
            while (level.isEmpty()){
                System.out.println("Enter level");
                level = scanner.nextLine();
            }
            request.setLanguage(language);
            request.setLevel(level);
        }

        Response response = controller.doAction(request);
        if (response.getErrorMessage() != null){
            System.out.println(response.getErrorMessage());
        } else {
            System.out.println(response.getMessage());
        }
    }

    private void getCatalog(){
        Request request = new Request();
        request.setCommandName("GET_CATALOG_COMMAND");
        Response response = controller.doAction(request);
        if (response.getErrorMessage() != null){
            System.out.println(response.getErrorMessage());
        } else {
            Catalog catalog = response.getCatalog();
            System.out.println(response.getMessage());
            if (catalog != null) {
                for (Book book: catalog.getBooks()) {
                    printBook(book);
                }
                for (ProgrammerBook programmerBook: catalog.getProgrammerBooks()) {
                    printBook(programmerBook);
                }
            }
        }
    }

    private void deleteBookByTitle(){
        String command = "";
        String title = "";
        while (title.isEmpty()){
            System.out.println("Enter book's title:");
            title = scanner.nextLine();
        }
        Request deleteRequest = new Request();
        deleteRequest.setCommandName("DELETE_BOOK_BY_TITLE_COMMAND");
        deleteRequest.setTitle(title);
        Response deleteResponse = controller.doAction(deleteRequest);
        if (deleteResponse.getErrorMessage() != null){
            System.out.println(deleteResponse.getErrorMessage());
        } else {
            System.out.println(deleteResponse.getMessage());
        }
    }

    private void deleteUserByLogin(){
        String login = "";
        while (login.isEmpty() || user.getLogin().equals(login)){
            System.out.println("Enter users's login");
            login = scanner.nextLine();
        }
        Request deleteRequest = new Request();
        deleteRequest.setCommandName("DELETE_USER_BY_LOGIN_COMMAND");
        deleteRequest.setLogin(login);
        Response deleteResponse = controller.doAction(deleteRequest);
        if (deleteResponse.getErrorMessage() != null){
            System.out.println(deleteResponse.getErrorMessage());
        } else {
            System.out.println(deleteResponse.getMessage());
        }
    }

    private void changePassword(){
        String password = "";
        String login = user.getLogin();
        while (password.isEmpty()){
            System.out.println("Enter new password");
            password = scanner.nextLine();
        }
        Request request = new Request();
        request.setCommandName("CHANGE_PASSWORD_COMMAND");
        request.setLogin(login);
        request.setPassword(password);
        Response response = controller.doAction(request);
        if (response.getErrorMessage() != null){
            System.out.println(response.getErrorMessage());
        } else {
            System.out.println(response.getMessage());
        }
    }
}
