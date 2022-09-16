package com.bravura.finco.model;

import io.swagger.v3.oas.annotations.media.Schema;

public class AddressDetailsType {

    @Schema(description = "")
    private Long id = null;

    @Schema(required = true, description = "")
    private CodeIdentifierType typeCode = null;

    @Schema(required = true, description = "")
    private Boolean primaryType = false;

    @Schema(required = true, description = "")
    private Boolean primary = false;

    @Schema(required = true, description = "")
    private String line1 = null;

    @Schema(description = "")
    private String line2 = null;

    @Schema(description = "")
    private String line3 = null;

    @Schema(description = "")
    private String line4 = null;

    @Schema(description = "")
    private String suburb = null;

    @Schema(description = "")
    private String city = null;

    @Schema(description = "")
    private String postcode = null;

    @Schema(description = "")
    private CountryIdentifierType country = null;

    public Long getId() {
        return id;
    }

    public CodeIdentifierType getTypeCode() {
        return typeCode;
    }

    public Boolean getPrimaryType() {
        return primaryType;
    }

    public Boolean getPrimary() {
        return primary;
    }

    public String getLine1() {
        return line1;
    }

    public String getLine2() {
        return line2;
    }

    public String getLine3() {
        return line3;
    }

    public String getLine4() {
        return line4;
    }

    public String getSuburb() {
        return suburb;
    }

    public String getCity() {
        return city;
    }

    public String getPostcode() {
        return postcode;
    }

    public CountryIdentifierType getCountry() {
        return country;
    }


}
