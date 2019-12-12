package com.networknt.ob.model;

import java.util.Arrays;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Links  {

    private String Last;
    private String Self;
    private String First;
    private String Prev;
    private String Next;

    public Links () {
    }

    @JsonProperty("Last")
    public String getLast() {
        return Last;
    }

    public void setLast(String Last) {
        this.Last = Last;
    }

    @JsonProperty("Self")
    public String getSelf() {
        return Self;
    }

    public void setSelf(String Self) {
        this.Self = Self;
    }

    @JsonProperty("First")
    public String getFirst() {
        return First;
    }

    public void setFirst(String First) {
        this.First = First;
    }

    @JsonProperty("Prev")
    public String getPrev() {
        return Prev;
    }

    public void setPrev(String Prev) {
        this.Prev = Prev;
    }

    @JsonProperty("Next")
    public String getNext() {
        return Next;
    }

    public void setNext(String Next) {
        this.Next = Next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Links Links = (Links) o;

        return Objects.equals(Last, Links.Last) &&
               Objects.equals(Self, Links.Self) &&
               Objects.equals(First, Links.First) &&
               Objects.equals(Prev, Links.Prev) &&
               Objects.equals(Next, Links.Next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Last, Self, First, Prev, Next);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Links {\n");
        sb.append("    Last: ").append(toIndentedString(Last)).append("\n");        sb.append("    Self: ").append(toIndentedString(Self)).append("\n");        sb.append("    First: ").append(toIndentedString(First)).append("\n");        sb.append("    Prev: ").append(toIndentedString(Prev)).append("\n");        sb.append("    Next: ").append(toIndentedString(Next)).append("\n");
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
