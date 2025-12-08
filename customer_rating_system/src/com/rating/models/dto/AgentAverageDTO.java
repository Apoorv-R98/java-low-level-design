package com.rating.models.dto;

public class AgentAverageDTO {
    private String agentId;
    private double averageRating;

    public static class Builder {
        private final AgentAverageDTO dto = new AgentAverageDTO();
        public Builder agentId(String id) { dto.agentId = id; return this; }
        public Builder averageRating(double avg) { dto.averageRating = avg; return this; }
        public AgentAverageDTO build() { return dto; }
    }
    
    public static Builder builder() { return new Builder(); }
    public String getAgentId() { return agentId; }
    public double getAverageRating() { return averageRating; }

    @Override
    public String toString() {
        return "AgentAverageDTO{agentId='" + agentId + "', avg=" + String.format("%.2f", averageRating) + "}";
    }
}

