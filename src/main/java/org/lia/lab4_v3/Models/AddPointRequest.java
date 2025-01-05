package org.lia.lab4_v3.Models;

import lombok.Data;

@Data
public class AddPointRequest {
    private Double x;
    private Double y;
    private Double r;
    private long creatorId;
    private String username;
    private String password;
}
