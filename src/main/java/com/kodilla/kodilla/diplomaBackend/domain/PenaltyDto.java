package com.kodilla.kodilla.diplomaBackend.domain;

import java.math.BigDecimal;

public class PenaltyDto {

    private long id;
    private PenaltyReason reason;
    private String details;
    private BigDecimal toBePaid;
    private RentDto rentDto;

    public static class PenaltyDtoBuilder{

        private long id;
        private PenaltyReason reason;
        private String details;
        private BigDecimal toBePaid;
        private RentDto rentDto;

        public PenaltyDtoBuilder id(long id){
            this.id=id;
            return this;
        }

        public PenaltyDtoBuilder reason(PenaltyReason reason){
            this.reason=reason;
            return this;
        }

        public PenaltyDtoBuilder details(String details){
            this.details=details;
            return this;
        }

        public PenaltyDtoBuilder toBePaid(BigDecimal toBePaid){
            this.toBePaid=toBePaid;
            return this;
        }

        public PenaltyDtoBuilder rentDto(RentDto rentDto){
            this.rentDto=rentDto;
            return this;
        }

        public PenaltyDto build(){
            return new PenaltyDto(id, reason, details, toBePaid, rentDto);
        }
    }

    private PenaltyDto(long id, PenaltyReason reason, String details, BigDecimal toBePaid, RentDto rentDto) {
        this.id = id;
        this.reason = reason;
        this.details = details;
        this.toBePaid = toBePaid;
        this.rentDto = rentDto;
    }

    public PenaltyDto() {
    }

    public long getId() {
        return id;
    }

    public PenaltyReason getReason() {
        return reason;
    }

    public String getDetails() {
        return details;
    }

    public BigDecimal getToBePaid() {
        return toBePaid;
    }

    public RentDto getRentDto() {
        return rentDto;
    }
}
