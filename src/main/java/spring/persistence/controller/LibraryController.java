package spring.persistence.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import spring.persistence.daos.library.AuthorDao;
import spring.persistence.daos.library.BookDao;
import spring.persistence.daos.library.StyleDao;
import spring.persistence.daos.library.ThemeDao;
import spring.persistence.entities.library.Author;
import spring.persistence.entities.library.Book;
import spring.persistence.entities.library.Contact;
import spring.persistence.entities.library.Style;
import spring.persistence.entities.library.Theme;

@Controller
public class LibraryController {

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private StyleDao styleDao;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private ThemeDao themeDao;

    public void process() {
        Theme[] themeArray = {new Theme("Acción"), new Theme("Suspense"), new Theme("Drama")};
        themeDao.save(Arrays.asList(themeArray));

        Style[] styleArray = {new Style("Infantil", "Lectura sencilla"), new Style("Especializado", "Expertos de la temática")};
        styleDao.save(Arrays.asList(styleArray));

        Author[] authorArray = {new Author("Jesús", "Ber", new Contact("j@gmail.com", 666666661), styleArray[0]),
                new Author("Cris", "Ber", new Contact("c@gmail.com", 666666662), styleArray[0]),
                new Author("Ana", "Ber", new Contact("a@gmail.com", 666666663), styleArray[1]),
                new Author("Sin", "Ber", new Contact("a@gmail.com", 666666663), styleArray[1])};
        authorDao.save(Arrays.asList(authorArray));

        Book[] bookArray = {new Book("isbn1", "La flauta", Arrays.asList(themeArray[0], themeArray[1]), Arrays.asList(authorArray[0])),
                new Book("isbn2", "La mazorca", Arrays.asList(themeArray[1]), Arrays.asList(authorArray[1], authorArray[2])),
                new Book("isbn3", "El pepino", Arrays.asList(themeArray[0]), Arrays.asList(authorArray[2]))};
        bookDao.save(Arrays.asList(bookArray));

        System.out.println(">>>> Books:  " + bookDao.findAll());

        System.out.println(">>>> Authores del estilo: Infantil");
        System.out.println("     " + authorDao.findByStyle(styleArray[0]));

        System.out.println(">>>> Nombre de Authores a partir del nombre del estilo: infantil");
        System.out.println("     " + authorDao.findName(styleArray[0].getName()));

        System.out.println(">>>> Nombre de Authores que tienen algun libro");
        System.out.println("     " + authorDao.findNameByAnyBook());

        System.out.println(">>>> Nombre de Authores que tienen algun libro con el nombre de tema: Acción");
        System.out.println("     " + authorDao.findNameByThemeName("Acción"));

    }
}
