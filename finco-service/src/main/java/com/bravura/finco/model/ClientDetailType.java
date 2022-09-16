package com.bravura.finco.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.OffsetDateTime;

public class ClientDetailType {

    @Schema(description = "")
    private Long id = null;

    @Schema(description = "")
    private CodeIdentifierType typeCode = null;

    @Schema(description = "")
    private String surname = null;

    @Schema(description = "")
    private String forename = null;

    @Schema(description = "")
    private CodeIdentifierType title = null;

    @Schema(description = "")
    private String forename2 = null;

    @Schema(description = "")
    private String forename3 = null;

    @Schema(description = "")
    private String initials = null;

    @Schema(description = "")
    private String salutation = null;

    @Schema(description = "")
    private CodeIdentifierType gender = null;

    @Schema(description = "")
    private OffsetDateTime dateOfBirth = null;

    @Schema(description = "")
    private Boolean ageAdmitted = null;

    @Schema(description = "")
    private OffsetDateTime dateOfDeath = null;

    @Schema(description = "")
    private CountryIdentifierType country = null;

    @Schema(description = "")
    private String contactName = null;

    @Schema(description = "")
    private String businessName = null;

    @Schema(description = "")
    private String homePhone = null;

    @Schema(description = "")
    private String businessPhone = null;

    @Schema(description = "")
    private String mobilePhone = null;

    @Schema(description = "")
    private String fax = null;

    @Schema(description = "")
    private CodeIdentifierType maritalStatus = null;

    @Schema(description = "")
    private Boolean disclaimerSigned = null;

    @Schema(description = "")
    private CodeIdentifierType preferredLanguage = null;

    @Schema(description = "")
    private String mailingName = null;

    @Schema(description = "")
    private CodeIdentifierType status = null;

    @Schema(description = "")
    private CodeIdentifierType onlineStatus = null;

    @Schema(description = "")
    private Boolean wrapIndicator = null;

    @Schema(description = "")
    private Boolean holdCorrespondence = null;

    @Schema(description = "")
    private Boolean canSolicit = null;

    @Schema(description = "")
    private CodeIdentifierType preferredRisk = null;

    @Schema(description = "")
    private Boolean secureSalaryFlag = null;

    @Schema(description = "")
    private CodeIdentifierType investorTypeCompany = null;

    @Schema(description = "")
    private CodeIdentifierType investorTypePersonal = null;

    @Schema(description = "")
    private OffsetDateTime plannedRetirementDate = null;

    @Schema(description = "")
    private CodeIdentifierType nationality = null;

    @Schema(description = "")
    private CodeIdentifierType correspondenceViewOptionCode = null;


    public Long getId() {
        return id;
    }

    public CodeIdentifierType getTypeCode() {
        return typeCode;
    }

    public String getSurname() {
        return surname;
    }

    public String getForename() {
        return forename;
    }

    public CodeIdentifierType getTitle() {
        return title;
    }

    public String getForename2() {
        return forename2;
    }

    public String getForename3() {
        return forename3;
    }

    public String getInitials() {
        return initials;
    }

    public String getSalutation() {
        return salutation;
    }

    public CodeIdentifierType getGender() {
        return gender;
    }

    public OffsetDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public Boolean getAgeAdmitted() {
        return ageAdmitted;
    }

    public OffsetDateTime getDateOfDeath() {
        return dateOfDeath;
    }

    public CountryIdentifierType getCountry() {
        return country;
    }

    public String getContactName() {
        return contactName;
    }

    public String getBusinessName() {
        return businessName;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getBusinessPhone() {
        return businessPhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getFax() {
        return fax;
    }

    public CodeIdentifierType getMaritalStatus() {
        return maritalStatus;
    }

    public Boolean getDisclaimerSigned() {
        return disclaimerSigned;
    }

    public CodeIdentifierType getPreferredLanguage() {
        return preferredLanguage;
    }

    public String getMailingName() {
        return mailingName;
    }

    public CodeIdentifierType getStatus() {
        return status;
    }

    public CodeIdentifierType getOnlineStatus() {
        return onlineStatus;
    }

    public Boolean getWrapIndicator() {
        return wrapIndicator;
    }

    public Boolean getHoldCorrespondence() {
        return holdCorrespondence;
    }

    public Boolean getCanSolicit() {
        return canSolicit;
    }

    public CodeIdentifierType getPreferredRisk() {
        return preferredRisk;
    }

    public Boolean getSecureSalaryFlag() {
        return secureSalaryFlag;
    }

    public CodeIdentifierType getInvestorTypeCompany() {
        return investorTypeCompany;
    }

    public CodeIdentifierType getInvestorTypePersonal() {
        return investorTypePersonal;
    }

    public OffsetDateTime getPlannedRetirementDate() {
        return plannedRetirementDate;
    }

    public CodeIdentifierType getNationality() {
        return nationality;
    }

    public CodeIdentifierType getCorrespondenceViewOptionCode() {
        return correspondenceViewOptionCode;
    }

}
