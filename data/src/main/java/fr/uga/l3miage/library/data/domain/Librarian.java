package fr.uga.l3miage.library.data.domain;

import java.util.Objects;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue("L")
@Table(name="librarian")
public class Librarian extends Person {
    @OneToOne
    private Librarian manager;
    
    public Librarian getManager() {
        return manager;
    }

    public void setManager(Librarian manager) {
        this.manager = manager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Librarian librarian)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(manager, librarian.manager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), manager);
    }
}
