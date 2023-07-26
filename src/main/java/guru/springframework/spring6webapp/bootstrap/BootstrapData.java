package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author kevin = new Author();
        kevin.setFirstName("Kevin");
        kevin.setLastName("Sustal");

        Book add = new Book();
        add.setTitle("Domain Driven Design");
        add.setIsbn("123456");

        Author kevinSaved = authorRepository.save(kevin);
        Book addSaved = bookRepository.save(add);

        Author nicole = new Author();
        nicole.setFirstName("Nicole");
        nicole.setLastName("Sustal");

        Book noEJB = new Book();
        noEJB.setTitle("J2EE Development without EJB");
        noEJB.setIsbn("783453453");

        Author nicoleSaved = authorRepository.save(nicole);
        Book noEJBSaved = bookRepository.save(noEJB);

        kevinSaved.getBooks().add(addSaved);
        nicoleSaved.getBooks().add(noEJBSaved);

        authorRepository.save(kevinSaved);
        authorRepository.save(nicoleSaved);

        System.out.println("In Bootstrap");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());

    }
}
