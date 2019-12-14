package com.networknt.ob.model;

import java.util.Arrays;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Meta  {

    private java.time.LocalDateTime FirstAvailableDateTime;
    private Integer TotalPages;
    private java.time.LocalDateTime LastAvailableDateTime;

    public Meta () {
    }

    @JsonProperty("FirstAvailableDateTime")
    public java.time.LocalDateTime getFirstAvailableDateTime() {
        return FirstAvailableDateTime;
    }

    public void setFirstAvailableDateTime(java.time.LocalDateTime FirstAvailableDateTime) {
        this.FirstAvailableDateTime = FirstAvailableDateTime;
    }

    @JsonProperty("TotalPages")
    public Integer getTotalPages() {
        return TotalPages;
    }

    public void setTotalPages(Integer TotalPages) {
        this.TotalPages = TotalPages;
    }

    @JsonProperty("LastAvailableDateTime")
    public java.time.LocalDateTime getLastAvailableDateTime() {
        return LastAvailableDateTime;
    }

    public void setLastAvailableDateTime(java.time.LocalDateTime LastAvailableDateTime) {
        this.LastAvailableDateTime = LastAvailableDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Meta Meta = (Meta) o;

        return Objects.equals(FirstAvailableDateTime, Meta.FirstAvailableDateTime) &&
               Objects.equals(TotalPages, Meta.TotalPages) &&
               Objects.equals(LastAvailableDateTime, Meta.LastAvailableDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(FirstAvailableDateTime, TotalPages, LastAvailableDateTime);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Meta {\n");
        sb.append("    FirstAvailableDateTime: ").append(toIndentedString(FirstAvailableDateTime)).append("\n");        sb.append("    TotalPages: ").append(toIndentedString(TotalPages)).append("\n");        sb.append("    LastAvailableDateTime: ").append(toIndentedString(LastAvailableDateTime)).append("\n");
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
