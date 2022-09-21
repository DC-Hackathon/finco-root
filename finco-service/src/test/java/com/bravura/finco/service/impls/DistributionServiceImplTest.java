package com.bravura.finco.service.impls;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bravura.finco.exception.TechnicalException;
import com.bravura.finco.model.FincoResponse;
import com.bravura.finco.model.security.AuthToken;
import com.bravura.finco.service.impls.mocks.MockData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@ContextConfiguration(classes = {DistributionServiceImpl.class})
@ExtendWith(SpringExtension.class)
class DistributionServiceImplTest {

    @Autowired
    private DistributionServiceImpl distributionServiceImpl;

    @MockBean(name = "dist")
    private WebClient webClient;

    @MockBean
    private AuthToken authToken;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        this.webClient = WebClient.builder().baseUrl(MockData.dstrUrl).build();
        distributionServiceImpl.bearerURI = "http://localhost:8080/distributions/api";
    }
    /**
     * Method under test: {@link DistributionServiceImpl#callDistributionProduct(FincoResponse)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCallDistributionProduct() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.bravura.finco.service.impls.DistributionServiceImpl.callDistributionProduct(DistributionServiceImpl.java:51)
        //   In order to prevent callDistributionProduct(FincoResponse)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   callDistributionProduct(FincoResponse).
        //   See https://diff.blue/R013 to resolve this issue.

        when((WebClient.RequestHeadersUriSpec<?>) webClient.get()).thenReturn(null);
        distributionServiceImpl.callDistributionProduct(new FincoResponse());
    }

    /**
     * Method under test: {@link DistributionServiceImpl#callDistributionProduct(FincoResponse)}
     */
    @Test
    void testCallDistributionProduct2() {
        when((WebClient.RequestHeadersUriSpec<?>) webClient.get())
                .thenThrow(new TechnicalException("An error occurred"));
        FincoResponse fincoResponse = new FincoResponse();
        assertThrows(TechnicalException.class,
                () -> distributionServiceImpl.callDistributionProduct(fincoResponse));
        verify(webClient).get();
    }

    @Test
    void testCallDistributionProduct3() {
        final var mock = Mockito.mock(WebClient.class);
        final var uriSpecMock = Mockito.mock(WebClient.RequestHeadersUriSpec.class);
        final var headersSpecMock = Mockito.mock(WebClient.RequestHeadersSpec.class);
        final var responseSpecMock = Mockito.mock(WebClient.ResponseSpec.class);

        when(mock.get()).thenReturn(uriSpecMock);
        when((uriSpecMock.uri(MockData.bearerUri))).thenReturn(headersSpecMock);
        when(headersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(AuthToken.class)).thenReturn(Mono.just(MockData.authToken));
//        when(webClient.get()
//                .uri(MockData.bearerUri)
//                .retrieve()
//                .bodyToMono(AuthToken.class)
//                .block()).thenReturn(MockData.authToken);

        FincoResponse fincoResponse = new FincoResponse();

        assertNull(distributionServiceImpl.callDistributionProduct(fincoResponse));
    }

}

