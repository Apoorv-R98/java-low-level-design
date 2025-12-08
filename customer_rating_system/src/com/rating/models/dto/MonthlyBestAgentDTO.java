package com.rating.models.dto;

import java.time.YearMonth;

public class MonthlyBestAgentDTO {
    private YearMonth month;
    private String agentId;
    private double averageRating;

    public static class Builder {
        private final MonthlyBestAgentDTO dto = new MonthlyBestAgentDTO();
        public Builder month(YearMonth m) { dto.month = m; return this; }
        public Builder agentId(String id) { dto.agentId = id; return this; }
        public Builder averageRating(double avg) { dto.averageRating = avg; return this; }
        public MonthlyBestAgentDTO build() { return dto; }
    }
    
    public static Builder builder() { return new Builder(); }
    
    public YearMonth getMonth() { return month; }
    public String getAgentId() { return agentId; }
    public double getAverageRating() { return averageRating; }

    @Override
    public String toString() {
        return "MonthlyBestAgentDTO{month=" + month + ", agentId='" + agentId + "', avg=" + String.format("%.2f", averageRating) + "}";
    }
}

