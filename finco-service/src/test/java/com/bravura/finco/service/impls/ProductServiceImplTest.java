package com.bravura.finco.service.impls;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bravura.finco.model.FincoResponse;
import com.bravura.finco.model.NLPResponse;
import com.bravura.finco.service.DistributionService;
import com.bravura.finco.service.NucleusService;
import com.bravura.finco.service.SonataService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProductServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ProductServiceImplTest {
    @MockBean
    private DistributionService distributionService;

    @MockBean
    private NucleusService nucleusService;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @MockBean
    private SonataService sonataService;

    /**
     * Method under test: {@link ProductServiceImpl#getProduct(FincoResponse)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetProduct() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.bravura.finco.service.impls.ProductServiceImpl.getProduct(ProductServiceImpl.java:27)
        //   In order to prevent getProduct(FincoResponse)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getProduct(FincoResponse).
        //   See https://diff.blue/R013 to resolve this issue.

        productServiceImpl.getProduct(new FincoResponse());
    }

    /**
     * Method under test: {@link ProductServiceImpl#getProduct(FincoResponse)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetProduct2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.bravura.finco.service.impls.ProductServiceImpl.getProduct(ProductServiceImpl.java:27)
        //   In order to prevent getProduct(FincoResponse)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getProduct(FincoResponse).
        //   See https://diff.blue/R013 to resolve this issue.

        FincoResponse fincoResponse = new FincoResponse();
        fincoResponse.setNlpResponse(new NLPResponse());
        productServiceImpl.getProduct(fincoResponse);
    }

    /**
     * Method under test: {@link ProductServiceImpl#getProduct(FincoResponse)}
     */
    @Test
    void testGetProduct3() {
        FincoResponse fincoResponse = new FincoResponse();
        fincoResponse.setNlpResponse(new NLPResponse("42", "PROD", "SER", "ID", "Span", "Intent", 1));
        assertNull(productServiceImpl.getProduct(fincoResponse));
    }

    /**
     * Method under test: {@link ProductServiceImpl#getProduct(FincoResponse)}
     */
    @Test
    void testGetProduct4() {
        NLPResponse nlpResponse = mock(NLPResponse.class);
        when(nlpResponse.getPROD()).thenReturn("Prod");

        FincoResponse fincoResponse = new FincoResponse();
        fincoResponse.setNlpResponse(nlpResponse);
        assertNull(productServiceImpl.getProduct(fincoResponse));
        verify(nlpResponse).getPROD();
    }
}

