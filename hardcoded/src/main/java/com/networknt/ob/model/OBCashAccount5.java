package com.networknt.ob.model;

import java.util.Arrays;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OBCashAccount5  {

    private String SchemeName;
    private String Identification;
    private String SecondaryIdentification;
    private String Name;

    public OBCashAccount5 () {
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

    @JsonProperty("SecondaryIdentification")
    public String getSecondaryIdentification() {
        return SecondaryIdentification;
    }

    public void setSecondaryIdentification(String SecondaryIdentification) {
        this.SecondaryIdentification = SecondaryIdentification;
    }

    @JsonProperty("Name")
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OBCashAccount5 OBCashAccount5 = (OBCashAccount5) o;

        return Objects.equals(SchemeName, OBCashAccount5.SchemeName) &&
               Objects.equals(Identification, OBCashAccount5.Identification) &&
               Objects.equals(SecondaryIdentification, OBCashAccount5.SecondaryIdentification) &&
               Objects.equals(Name, OBCashAccount5.Name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(SchemeName, Identification, SecondaryIdentification, Name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class OBCashAccount5 {\n");
        sb.append("    SchemeName: ").append(toIndentedString(SchemeName)).append("\n");        sb.append("    Identification: ").append(toIndentedString(Identification)).append("\n");        sb.append("    SecondaryIdentification: ").append(toIndentedString(SecondaryIdentification)).append("\n");        sb.append("    Name: ").append(toIndentedString(Name)).append("\n");
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
