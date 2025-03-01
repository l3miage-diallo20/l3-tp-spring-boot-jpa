package fr.uga.l3miage.library.data.repo;

import fr.uga.l3miage.library.data.domain.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository implements CRUDRepository<Long, Book> {

    private final EntityManager entityManager;

    @Autowired
    public BookRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Book save(Book author) {
        entityManager.persist(author);
        return author;
    }

    @Override
    public Book get(Long id) {
        return entityManager.find(Book.class, id);
    }


    @Override
    public void delete(Book author) {
        entityManager.remove(author);
    }

    /**
     * Renvoie tous les auteurs par ordre alphabétique
     * @return une liste de livres
     */
    public List<Book> all() {
        // TODO créer les named query
        Query res1 =entityManager.createNamedQuery("all-books", Book.class);
        List <Book> res = res1.getResultList();
        return res;
    }

    /**
     * Trouve les livres dont le titre contient la chaine passée (non sensible à la casse)
     * @param titlePart tout ou partie du titre
     * @return une liste de livres
     */
    public List<Book> findByContainingTitle(String titlePart) {
        // TODO créer les named query
        List<Book> res =  entityManager.createNamedQuery("find-books-by-title", Book.class)
                // TODO completer l'appel pour utiliser le paramètre de cette méthode
                .setParameter("title","%" + titlePart + "%")
                .getResultList();
            return res;
    }

    /**
     * Trouve les livres d'un auteur donnée dont le titre contient la chaine passée (non sensible à la casse)
     * @param authorId id de l'auteur
     * @param titlePart tout ou partie d'un titre de livré
     * @return une liste de livres
     */
    public List<Book> findByAuthorIdAndContainingTitle(Long authorId, String titlePart) {
        // TODO créer les named query
        String search = "%" + titlePart + "%";
        final List<Book> res =entityManager.createNamedQuery("find-books-by-author-and-title", Book.class)
                // TODO completer l'appel pour utiliser les paramètres de cette méthode
                .setParameter(1,authorId)
                .setParameter("name",search)
                .getResultList();
                return res;
    }

    /**
     * Recherche des livres dont le nom de l'auteur contient la chaine passée (non sensible à la casse)
     * @param namePart tout ou partie du nom
     * @return une liste de livres
     */
    public List<Book> findBooksByAuthorContainingName(String namePart) {
        // TODO créer les named query
       String search = "%" + namePart + "%";
        List<Book> res =  entityManager.createNamedQuery("find-books-by-authors-name", Book.class)
                // TODO completer l'appel pour utiliser le paramètre de cette méthode
                .setParameter("name",search)
                .getResultList();
                return res ;
    }
    
    /**
     * Trouve des livres avec un nombre d'auteurs supérieur au compte donné
     * @param count le compte minimum d'auteurs
     * @return une liste de livres
     */
    public List<Book> findBooksHavingAuthorCountGreaterThan(int count) {
        // TODO créer les named query
        List <Book> res = entityManager.createNamedQuery("find-books-by-several-authors", Book.class)
                // TODO completer l'appel pour utiliser le paramètre de cette méthode
                .setParameter(1,count)
                .getResultList();
                return res;
    }

}
