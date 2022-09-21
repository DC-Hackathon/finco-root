package com.bravura.finco.service.impls.mocks;

import com.bravura.finco.model.security.AuthToken;

public class MockData {

    public static AuthToken authToken = new AuthToken("auth token");

    public static String bearerUri = "http://localhost:8080/distributions/api/oidc/oauth2/token?username=p2gupta@bravurasolutions.com&password=dstr@12345&";

    public static String dstrUrl = "http://localhost:8080/distributions/api";

    static {
        authToken.setAccessToken("static auth token");
    }
}
