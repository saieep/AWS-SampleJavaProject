package com.cybage.service;

import java.util.List;

import com.cybage.command.Audit;
import com.cybage.requestdto.AuditDTO;


public interface AuditService {
	public List<Audit> showAudit();
	public void insertAudit(AuditDTO auditDto);
}
