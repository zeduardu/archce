package dev.arch420x0.archce.application.models;

import dev.arch420x0.archce.application.interfaces.RequestModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterEntityInterestReq implements RequestModel<Long> {
    private String name;
    private String background;
    private String purpose;
    private String scope;
}
