package com.bravura.finco.service.impls;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bravura.finco.exception.TechnicalException;
import com.bravura.finco.model.FincoResponse;
import com.bravura.finco.model.NLPResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;

@ContextConfiguration(classes = {SonataServiceImpl.class})
@ExtendWith(SpringExtension.class)
class SonataServiceImplTest {
    @Autowired
    private SonataServiceImpl sonataServiceImpl;

    @MockBean(name = "sbs")
    private WebClient webClient;

    @MockBean
    private WebClient webClient1;

    /**
     * Method under test: {@link SonataServiceImpl#callSonataProduct(FincoResponse)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCallSonataProduct() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.bravura.finco.service.impls.SonataServiceImpl.callSonataProduct(SonataServiceImpl.java:36)
        //   In order to prevent callSonataProduct(FincoResponse)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   callSonataProduct(FincoResponse).
        //   See https://diff.blue/R013 to resolve this issue.

        sonataServiceImpl.callSonataProduct(new FincoResponse());
    }

    /**
     * Method under test: {@link SonataServiceImpl#callSonataProduct(FincoResponse)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCallSonataProduct2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.bravura.finco.service.impls.SonataServiceImpl.callSonataProduct(SonataServiceImpl.java:36)
        //   In order to prevent callSonataProduct(FincoResponse)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   callSonataProduct(FincoResponse).
        //   See https://diff.blue/R013 to resolve this issue.

        FincoResponse fincoResponse = new FincoResponse();
        fincoResponse.setNlpResponse(new NLPResponse());
        sonataServiceImpl.callSonataProduct(fincoResponse);
    }

    /**
     * Method under test: {@link SonataServiceImpl#callSonataProduct(FincoResponse)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCallSonataProduct3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.bravura.finco.service.impls.SonataServiceImpl.callSonataProduct(SonataServiceImpl.java:40)
        //   In order to prevent callSonataProduct(FincoResponse)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   callSonataProduct(FincoResponse).
        //   See https://diff.blue/R013 to resolve this issue.

        when(webClient.post()).thenReturn(null);

        FincoResponse fincoResponse = new FincoResponse();
        fincoResponse.setNlpResponse(new NLPResponse("42", "client", "client", "client", "client", "client", 1));
        sonataServiceImpl.callSonataProduct(fincoResponse);
    }

    /**
     * Method under test: {@link SonataServiceImpl#callSonataProduct(FincoResponse)}
     */
    @Test
    void testCallSonataProduct4() {
        when(webClient.post()).thenReturn(null);
        when(webClient1.post()).thenReturn(null);
        NLPResponse nlpResponse = mock(NLPResponse.class);
        when(nlpResponse.getSER()).thenReturn("Ser");

        FincoResponse fincoResponse = new FincoResponse();
        fincoResponse.setNlpResponse(nlpResponse);
        assertNull(sonataServiceImpl.callSonataProduct(fincoResponse));
        verify(webClient).post();
        verify(nlpResponse, atLeast(1)).getSER();
    }

    /**
     * Method under test: {@link SonataServiceImpl#callSonataProduct(FincoResponse)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCallSonataProduct5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.bravura.finco.service.impls.SonataServiceImpl.callSonataProduct(SonataServiceImpl.java:64)
        //   In order to prevent callSonataProduct(FincoResponse)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   callSonataProduct(FincoResponse).
        //   See https://diff.blue/R013 to resolve this issue.

        when(webClient.post()).thenReturn(null);
        when(webClient1.post()).thenReturn(null);
        NLPResponse nlpResponse = mock(NLPResponse.class);
        when(nlpResponse.getID()).thenReturn("42");
        when(nlpResponse.getSER()).thenReturn("account");

        FincoResponse fincoResponse = new FincoResponse();
        fincoResponse.setNlpResponse(nlpResponse);
        sonataServiceImpl.callSonataProduct(fincoResponse);
    }

    /**
     * Method under test: {@link SonataServiceImpl#callSonataProduct(FincoResponse)}
     */
    @Test
    void testCallSonataProduct6() {
        when(webClient.post()).thenReturn(null);
        when(webClient1.post()).thenReturn(null);
        NLPResponse nlpResponse = mock(NLPResponse.class);
        when(nlpResponse.getID()).thenThrow(new TechnicalException("An error occurred"));
        when(nlpResponse.getSER()).thenReturn("account");

        FincoResponse fincoResponse = new FincoResponse();
        fincoResponse.setNlpResponse(nlpResponse);
        assertThrows(TechnicalException.class, () -> sonataServiceImpl.callSonataProduct(fincoResponse));
        verify(webClient).post();
        verify(nlpResponse).getID();
        verify(nlpResponse, atLeast(1)).getSER();
    }

    /**
     * Method under test: {@link SonataServiceImpl#callSonataProduct(FincoResponse)}
     */
    @Test
    void testCallSonataProduct7() {
        when(webClient.post()).thenReturn(null);
        when(webClient1.post()).thenReturn(null);
        NLPResponse nlpResponse = mock(NLPResponse.class);
        when(nlpResponse.getID()).thenThrow(new TechnicalException("An error occurred"));
        when(nlpResponse.getSER()).thenReturn("client");

        FincoResponse fincoResponse = new FincoResponse();
        fincoResponse.setNlpResponse(nlpResponse);
        assertThrows(TechnicalException.class, () -> sonataServiceImpl.callSonataProduct(fincoResponse));
        verify(webClient).post();
        verify(nlpResponse).getID();
        verify(nlpResponse).getSER();
    }
}

