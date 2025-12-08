package com.rating.models;

public class Agent {
    private final String agentId;
    private final String name;

    public Agent(String agentId, String name) {
        this.agentId = agentId;
        this.name = name;
    }

    public String getAgentId() { return agentId; }
    public String getName() { return name; }
}

