package com.cybage.dao;

import java.util.List;

import com.cybage.command.Audit;


public interface AuditDAO {
	public List<Audit> showAudit();
	public void insertAudit(Audit audit);
}
