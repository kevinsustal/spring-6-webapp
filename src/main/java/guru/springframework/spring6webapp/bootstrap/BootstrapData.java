package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {




        //Adding first author
        Author kevin = new Author();
        kevin.setFirstName("Kevin");
        kevin.setLastName("Sustal");

        Book add = new Book();
        add.setTitle("Domain Driven Design");
        add.setIsbn("123456");

        Author kevinSaved = authorRepository.save(kevin);
        Book addSaved = bookRepository.save(add);


        //Adding second Author
        Author nicole = new Author();
        nicole.setFirstName("Nicole");
        nicole.setLastName("Sustal");

        //Adding books
        Book noEJB = new Book();
        noEJB.setTitle("J2EE Development without EJB");
        noEJB.setIsbn("783453453");

        Author nicoleSaved = authorRepository.save(nicole);
        Book noEJBSaved = bookRepository.save(noEJB);

        kevinSaved.getBooks().add(addSaved);
        nicoleSaved.getBooks().add(noEJBSaved);
        addSaved.getAuthors().add(kevinSaved);
        noEJBSaved.getAuthors().add(nicole);


        //Ading Publisher
        Publisher macmillan = new Publisher();
        macmillan.setPublisherName("Macmillan Publishers");
        macmillan.setAddress("Calamba, Philippines");
        macmillan.setCity("Calamba");
        macmillan.setState("Laguna");
        macmillan.setZip("4027");
        Publisher macmillanSaved = publisherRepository.save(macmillan);

        addSaved.setPublisher(macmillanSaved);
        noEJBSaved.setPublisher(macmillanSaved);


        authorRepository.save(kevinSaved);
        authorRepository.save(nicoleSaved);
        bookRepository.save(addSaved);
        bookRepository.save(noEJBSaved);


        //
        System.out.println("In Bootstrap");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());
        System.out.println("Publisher Count: " + publisherRepository.count());

    }
}
