package com.hotelReservation.dto;

import java.util.Date;

public class SearchFormRequest {

    public Date fromDate;
    public Date toDate;

    public SearchFormRequest() {
    }

    public SearchFormRequest(Date fromDate, Date toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "SearchForm{" +
                "fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }
}
