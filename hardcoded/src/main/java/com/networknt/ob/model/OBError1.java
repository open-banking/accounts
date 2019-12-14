package com.networknt.ob.model;

import java.util.Arrays;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OBError1  {

    private String Path;
    private String ErrorCode;
    private String Message;
    private String Url;

    public OBError1 () {
    }

    @JsonProperty("Path")
    public String getPath() {
        return Path;
    }

    public void setPath(String Path) {
        this.Path = Path;
    }

    @JsonProperty("ErrorCode")
    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String ErrorCode) {
        this.ErrorCode = ErrorCode;
    }

    @JsonProperty("Message")
    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    @JsonProperty("Url")
    public String getUrl() {
        return Url;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OBError1 OBError1 = (OBError1) o;

        return Objects.equals(Path, OBError1.Path) &&
               Objects.equals(ErrorCode, OBError1.ErrorCode) &&
               Objects.equals(Message, OBError1.Message) &&
               Objects.equals(Url, OBError1.Url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Path, ErrorCode, Message, Url);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class OBError1 {\n");
        sb.append("    Path: ").append(toIndentedString(Path)).append("\n");        sb.append("    ErrorCode: ").append(toIndentedString(ErrorCode)).append("\n");        sb.append("    Message: ").append(toIndentedString(Message)).append("\n");        sb.append("    Url: ").append(toIndentedString(Url)).append("\n");
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
