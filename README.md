# LibraryDemo
## Assignment definition

Create a simple object-oriented application (console application) representing a book library
system.
Following objects are required in this scenario:

1. #### BookLibrary
Serves as a library for storing collection of books. Choose the correct collection (consider
search performance). Following functionality needs to be supported:
- contains a collection of books and their quantity(the same book can be registered
multiple times - there can be more pieces of it in the library, right?)
- registering a new book
- retrieving a book from library (borrowing it)
- returning a book to library

2. #### Librarian
Represents your virtual assistant for working with the library (like the lady that works in the
library and you ask her for books). It needs to support communication with you through the
console, more specifically you can ask it to:
- list you the books present in the library (and their quantities)
- get you a book from the library you want to borrow
- put a book that you’re returning back to the library

3. #### Book
Create a class for book that holds some information about the book, like name, value, ....
Use at least 5 properties. Provide read-only access to these information - they can't be
modified, once instance is created.
Demonstrate use of inheritance on the book class - create at least 2 subclasses for a special
kind of book with some extended functionality (f.e. book signed by author that has double
value compared to unsigned book).

#### Extended tasks for plus points :)
- As there is only one library, which design pattern should we use here? (demonstrate)
- Demonstrate use of interface and abstract class when working with books
- Use builder pattern for creating book instances
- Code documentation counts
Technical requirements
- Create a repository in GitHub where this project will be hosted
- Project should a console application written in Java
- Book instances can be created programmatically, before console input is allowed.
Library can be filled with books programmatically as well
- Borrowing a book from library and returning it back needs to be interactive - simulate
communication with Librarian in the console using keyboard input

#### Assignment requirements
- You have 7 days to complete this assignment. Of course you can finish it sooner :)
- Assignment will be reviewed by us (me) once it’s done. After review, we might ask
you to present it or explain some parts of it
- Feel free to be creative

## What has been done
- A complete working console application written in Java
- Different ibrary items include books, posters, and signed books
- JSON-based data storing system for catalog and entity loading
- Simple UI `ConsoleUI`, simulating interaction with a librarian
- Page-by-page content reader for readable items
- Fully supports borrowing/returning flow
- Interactive user input using keyboard
- All book data is loaded programmatically from JSON at startup

## What is missing and why
- Builder pattern for Book. The Book and its subclasses are created directly from JSON files. A builder pattern was not necessary for this purpose.
- Not all functions have been documented, but only the main classes and public methods.

## What can be improved
- Additional item types with their unique interactions.
- Instead of JSON files, the catalog and library could be stored in a database.
- The page reader could be enhanced with better formatting and navigation.
- Right now users can only interact with already created items stored in assets. Next step would be to let users write and save their own books, which could be added to the library dynamically at runtime.
