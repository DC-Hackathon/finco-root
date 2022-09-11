package com.bravura.finco.model.asset;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(name = "Asset", description = "Asset Details")
public class Asset implements Serializable {

    @Schema(
            format = "Long",
            description = "Primary key to uniquely identify a record.",
            accessMode = Schema.AccessMode.READ_ONLY,
            example = "10001")
    @JsonProperty("id")
    private Long id;

    @Schema(
            format = "string",
            description =
                    "ISIN-International Securities Identification Number (ISIN) to uniquely identifies a specific security.",
            accessMode = Schema.AccessMode.READ_ONLY,
            example = "GB0031829507")
    @JsonProperty("isin")
    private String isin;

    @Schema(
            format = "string",
            description = "Asset reference",
            accessMode = Schema.AccessMode.READ_ONLY,
            example = "12345")
    @JsonProperty("assetReference")
    private String assetReference;

    @Schema(
            format = "string",
            description = "Asset's short name",
            accessMode = Schema.AccessMode.READ_ONLY,
            example = "DiagPlcOrd28")
    @JsonProperty("name")
    private String name;

    @Schema(
            format = "string",
            description = " Asset's domicile country ISO code",
            accessMode = Schema.AccessMode.READ_ONLY,
            example = "USA")
    @JsonProperty("domicileCountryIsoCode")
    private String domicileCountryIsoCode;

    @Schema(
            description = "Asset Currency ",
            accessMode = Schema.AccessMode.READ_ONLY,
            example = "GBP")
    @JsonProperty("issuedCurrency")
    private String issuedCurrency;

    @Schema(
            format = "date-time",
            description = "The Next date on which Income Distribution will run on the asset.",
            accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("nextBookCloseDate")
    private LocalDate nextBooksCloseDate;

    @Schema(
            format = "date-time",
            description = "The Last date on which Income Distribution was run on the asset.",
            accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("lastExecutedDate")
    private LocalDate lastExecutedDate;

    @Schema(
            description =
                    "Specifies if Equalisation is applicable on asset, during Income Distribution.",
            accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("distributesEqualisation")
    private Boolean distributesEqualisation;

    @Schema(
            description = " Specified if Distribution is of type Income, Interest or Dividend.",
            accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("distributionIncomeType")
    private Object distributionIncomeType;

    @Schema(description = "Asset Redemption Method", accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("assetRedemptionMethod")
    private Object redemptionMethod;

    @Schema(
            description = "Frequency at which Distribution needs to be re-run in system.",
            accessMode = Schema.AccessMode.READ_ONLY,
            example = "HYYR")
    @JsonProperty("distributionFrequencyInterval")
    private Object distributionFrequencyInterval;

    @Schema(
            description = "Asset Price Decimal",
            example = "1.0",
            accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("assetPriceDecimal")
    private Integer pricePrecision;

    @Schema(
            description = "Asset tolerance percentage",
            example = "1.0",
            accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("tolerancePercentage")
    private BigDecimal unitTolerance;

    @Schema(
            description = "Asset Unit Decimal",
            example = "2.0",
            accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("assetUnitDecimal")
    private Integer unitPrecision;

    @Schema(description = "Distributes Dividends", accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("distributesDividends")
    private Boolean distributesDividends;

    @Schema(description = "Distribution Interest", accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("distributesInterest")
    private Boolean distributesInterest;

    @Schema(description = "Cash", accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("cash")
    private Boolean cash;

    @Schema(description = "Asset Unique Id", accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("assetUniqueId")
    private Long assetUniqueId;

    @Schema(description = "Source Id", accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("sourceId")
    private String sourceId;

    @Schema(description = "Distribution Option", accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("distributionOption")
    private List<Object> distributionOption;

    @Schema(description = "Asset Manager Detail", accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("assetManager")
    private Object assetManager;

    @Schema(description = "Asset Market Identifier", accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("assetMarketIdentifier")
    private Map<Object, Object> identifiers = new HashMap<>();

    @Schema(description = "Default Income Stream", accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("defaultIncomeStream")
    private Object incomeStream;


}
