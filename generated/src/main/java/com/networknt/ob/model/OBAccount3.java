package com.networknt.ob.model;

import java.util.Arrays;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OBAccount3  {

    private java.util.List<OBCashAccount5> Account;
    private OBBranchAndFinancialInstitutionIdentification5 Servicer;
    private String AccountId;
    private String Description;
    private String Currency;
    private OBExternalAccountType1Code AccountType;
    private OBExternalAccountSubType1Code AccountSubType;
    private String Nickname;

    public OBAccount3 () {
    }

    @JsonProperty("Account")
    public java.util.List<OBCashAccount5> getAccount() {
        return Account;
    }

    public void setAccount(java.util.List<OBCashAccount5> Account) {
        this.Account = Account;
    }

    @JsonProperty("Servicer")
    public OBBranchAndFinancialInstitutionIdentification5 getServicer() {
        return Servicer;
    }

    public void setServicer(OBBranchAndFinancialInstitutionIdentification5 Servicer) {
        this.Servicer = Servicer;
    }

    @JsonProperty("AccountId")
    public String getAccountId() {
        return AccountId;
    }

    public void setAccountId(String AccountId) {
        this.AccountId = AccountId;
    }

    @JsonProperty("Description")
    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    @JsonProperty("Currency")
    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String Currency) {
        this.Currency = Currency;
    }

    @JsonProperty("AccountType")
    public OBExternalAccountType1Code getAccountType() {
        return AccountType;
    }

    public void setAccountType(OBExternalAccountType1Code AccountType) {
        this.AccountType = AccountType;
    }

    @JsonProperty("AccountSubType")
    public OBExternalAccountSubType1Code getAccountSubType() {
        return AccountSubType;
    }

    public void setAccountSubType(OBExternalAccountSubType1Code AccountSubType) {
        this.AccountSubType = AccountSubType;
    }

    @JsonProperty("Nickname")
    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String Nickname) {
        this.Nickname = Nickname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OBAccount3 OBAccount3 = (OBAccount3) o;

        return Objects.equals(Account, OBAccount3.Account) &&
               Objects.equals(Servicer, OBAccount3.Servicer) &&
               Objects.equals(AccountId, OBAccount3.AccountId) &&
               Objects.equals(Description, OBAccount3.Description) &&
               Objects.equals(Currency, OBAccount3.Currency) &&
               Objects.equals(AccountType, OBAccount3.AccountType) &&
               Objects.equals(AccountSubType, OBAccount3.AccountSubType) &&
               Objects.equals(Nickname, OBAccount3.Nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Account, Servicer, AccountId, Description, Currency, AccountType, AccountSubType, Nickname);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class OBAccount3 {\n");
        sb.append("    Account: ").append(toIndentedString(Account)).append("\n");        sb.append("    Servicer: ").append(toIndentedString(Servicer)).append("\n");        sb.append("    AccountId: ").append(toIndentedString(AccountId)).append("\n");        sb.append("    Description: ").append(toIndentedString(Description)).append("\n");        sb.append("    Currency: ").append(toIndentedString(Currency)).append("\n");        sb.append("    AccountType: ").append(toIndentedString(AccountType)).append("\n");        sb.append("    AccountSubType: ").append(toIndentedString(AccountSubType)).append("\n");        sb.append("    Nickname: ").append(toIndentedString(Nickname)).append("\n");
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
