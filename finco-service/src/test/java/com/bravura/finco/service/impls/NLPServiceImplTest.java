package com.bravura.finco.service.impls;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bravura.finco.repository.QueryRepository;
import com.bravura.finco.service.ProductService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;

@ContextConfiguration(classes = {NLPServiceImpl.class})
@ExtendWith(SpringExtension.class)
class NLPServiceImplTest {
    @Autowired
    private NLPServiceImpl nLPServiceImpl;

    @MockBean
    private ProductService productService;

    @MockBean
    private QueryRepository queryRepository;

    @MockBean
    private WebClient webClient;

    /**
     * Method under test: {@link NLPServiceImpl#getNlp(String)}
     */
    @Test
    void testGetNlp() {
        when(webClient.post()).thenReturn(null);
        assertNull(nLPServiceImpl.getNlp("Search String").getNlpResponse());
        verify(webClient).post();
    }

    /**
     * Method under test: {@link NLPServiceImpl#getNlp(String)}
     */
    @Test
    void testGetNlp2() {
        when(webClient.post()).thenThrow(new NullPointerException(" looking for results from nlp ..."));
        assertNull(nLPServiceImpl.getNlp("Search String").getNlpResponse());
        verify(webClient).post();
    }

    /**
     * Method under test: {@link NLPServiceImpl#getNlp(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetNlp3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: searchString is marked non-null but is null
        //       at com.bravura.finco.service.impls.NLPServiceImpl.getNlp(NLPServiceImpl.java:40)
        //   In order to prevent getNlp(String)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getNlp(String).
        //   See https://diff.blue/R013 to resolve this issue.

        when(webClient.post()).thenReturn(null);
        nLPServiceImpl.getNlp(null);
    }
}

