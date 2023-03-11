package fr.uga.l3miage.library.data.repo;

import fr.uga.l3miage.library.data.domain.User;
import fr.uga.l3miage.library.data.domain.Person;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository implements CRUDRepository<String, User> {

    private final EntityManager entityManager;

    @Autowired
    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User save(User entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public User get(String id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void delete(User entity) {
        entityManager.remove(entity);
    }

    @Override
    public List<User> all() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    /**
     * Trouve tous les utilisateurs ayant plus de l'age passé
     * @param age l'age minimum de l'utilisateur
     * @return
     */
    public List<User> findAllOlderThan(int age) {

        String sql = "SELECT p FROM Person p WHERE TIMESTAMPDIFF(YEAR, p.birth, CURRENT_DATE) >:age";
        List<Person> personnes = entityManager.createQuery(sql,Person.class)
        .setParameter("age",age)
        .getResultList();

        List<User> users = new ArrayList<>();
        for(Person p : personnes){
            User user = entityManager.createQuery("select u from Utilisateur u where u.id = :id",User.class)
            .setParameter("id",p.getId())
            .getSingleResult();
            users.add(user);
        }
        
         
       
        
        /*String sql = "SELECT p FROM Person p WHERE DATEDIFF('yy', p.birth, CURRENT_DATE) > :age";
        List<User> res = entityManager.createQuery("sql",User.class)
        .setParameter("age",age)
        .getResultList();
         */

        return null;
    }
}
