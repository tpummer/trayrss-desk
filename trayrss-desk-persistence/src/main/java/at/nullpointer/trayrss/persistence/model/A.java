package at.nullpointer.trayrss.persistence.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table( name = "A" )
public class A {

    /**
     * Id
     */
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    /**
     * Bs
     */
    @OneToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "a" )
    private Set<B> b = new HashSet<B>();

}
