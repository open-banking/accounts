package com.networknt.ob.model;

import java.util.Arrays;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OBErrorResponse1  {

    private java.util.List<OBError1> Errors;
    private String Id;
    private String Message;
    private String Code;

    public OBErrorResponse1 () {
    }

    @JsonProperty("Errors")
    public java.util.List<OBError1> getErrors() {
        return Errors;
    }

    public void setErrors(java.util.List<OBError1> Errors) {
        this.Errors = Errors;
    }

    @JsonProperty("Id")
    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    @JsonProperty("Message")
    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    @JsonProperty("Code")
    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OBErrorResponse1 OBErrorResponse1 = (OBErrorResponse1) o;

        return Objects.equals(Errors, OBErrorResponse1.Errors) &&
               Objects.equals(Id, OBErrorResponse1.Id) &&
               Objects.equals(Message, OBErrorResponse1.Message) &&
               Objects.equals(Code, OBErrorResponse1.Code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Errors, Id, Message, Code);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class OBErrorResponse1 {\n");
        sb.append("    Errors: ").append(toIndentedString(Errors)).append("\n");        sb.append("    Id: ").append(toIndentedString(Id)).append("\n");        sb.append("    Message: ").append(toIndentedString(Message)).append("\n");        sb.append("    Code: ").append(toIndentedString(Code)).append("\n");
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
