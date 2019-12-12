package com.networknt.ob.model;

import java.util.Arrays;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OBBranchAndFinancialInstitutionIdentification5  {

    private String SchemeName;
    private String Identification;

    public OBBranchAndFinancialInstitutionIdentification5 () {
    }

    @JsonProperty("SchemeName")
    public String getSchemeName() {
        return SchemeName;
    }

    public void setSchemeName(String SchemeName) {
        this.SchemeName = SchemeName;
    }

    @JsonProperty("Identification")
    public String getIdentification() {
        return Identification;
    }

    public void setIdentification(String Identification) {
        this.Identification = Identification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OBBranchAndFinancialInstitutionIdentification5 OBBranchAndFinancialInstitutionIdentification5 = (OBBranchAndFinancialInstitutionIdentification5) o;

        return Objects.equals(SchemeName, OBBranchAndFinancialInstitutionIdentification5.SchemeName) &&
               Objects.equals(Identification, OBBranchAndFinancialInstitutionIdentification5.Identification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(SchemeName, Identification);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class OBBranchAndFinancialInstitutionIdentification5 {\n");
        sb.append("    SchemeName: ").append(toIndentedString(SchemeName)).append("\n");        sb.append("    Identification: ").append(toIndentedString(Identification)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
