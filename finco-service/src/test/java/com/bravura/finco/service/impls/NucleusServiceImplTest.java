package com.bravura.finco.service.impls;

import static org.mockito.Mockito.when;

import com.bravura.finco.model.NLPResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;

@ContextConfiguration(classes = {NucleusServiceImpl.class})
@ExtendWith(SpringExtension.class)
class NucleusServiceImplTest {
    @Autowired
    private NucleusServiceImpl nucleusServiceImpl;

    @MockBean(name = "nucleus")
    private WebClient webClient;

    @MockBean
    private WebClient webClient1;

    /**
     * Method under test: {@link NucleusServiceImpl#callNucleusProduct(NLPResponse)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCallNucleusProduct() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.bravura.finco.service.impls.NucleusServiceImpl.callNucleusProduct(NucleusServiceImpl.java:26)
        //   In order to prevent callNucleusProduct(NLPResponse)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   callNucleusProduct(NLPResponse).
        //   See https://diff.blue/R013 to resolve this issue.

        when(webClient.post()).thenReturn(null);
        nucleusServiceImpl.callNucleusProduct(new NLPResponse());
    }
}

