package com.internship.smart_contract_auditor.controller;

import com.internship.smart_contract_auditor.model.AuditRequest;
import com.internship.smart_contract_auditor.service.AuditService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/audit")
@CrossOrigin(origins = "*") // Allows us to call this API from any frontend later
public class AuditController {

    private final AuditService auditService;

    // Constructor Injection: Spring automatically feeds the AuditService into this
    // controller
    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    @PostMapping
    public String analyzeContract(@RequestBody AuditRequest request) {
        return auditService.auditContract(request.getContractCode());
    }
}
