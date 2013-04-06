package at.nullpointer.trayrss.persistence.dao;

import javax.inject.Inject;

import lombok.Setter;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

import at.nullpointer.trayrss.persistence.model.A;
import at.nullpointer.trayrss.persistence.model.B;

/**
 * Class under Test {@link ARepository}
 * 
 * @author Thomas Pummer
 * @since 1.4
 */
@TransactionConfiguration( defaultRollback = true )
@ContextConfiguration
public class ARepositoryTest
        extends AbstractTransactionalTestNGSpringContextTests {

    @Inject
    @Setter
    private ARepository aRepository;

    @Inject
    @Setter
    private BRepository bRepository;


    @Test( groups = { "integration" } )
    public void testSaveWithA() {

        A a = new A();
        aRepository.saveAndFlush( a );

        B b = new B();
        b.setA( a );
        bRepository.saveAndFlush( b );

        A findOne = aRepository.findOne( a.getId() );
        Assert.assertEquals( findOne.getB().size(), 1 );

    }
}
