/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.notification;

import org.openmrs.BaseOpenmrsObject;

import java.util.Map;

public class Template extends BaseOpenmrsObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1782906754736853557L;
	
	// Persisted
	private Integer id;
	
	private String name;
	
	private String template;
	
	private Integer ordinal;
	
	private String sender;
	
	private String recipients;
	
	private String subject;
	
	// Not persisted
	@SuppressWarnings("unchecked")
	private Map data;
	
	private String content;
	
	public Template() {
	}
	
	public Template(int id, String name, String template) {
		this.id = id;
		this.name = name;
		this.template = template;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setTemplate(String template) {
		this.template = template;
	}
	
	public String getTemplate() {
		return template;
	}
	
	public void setRecipients(String recipients) {
		this.recipients = recipients;
	}
	
	public String getRecipients() {
		return this.recipients;
	}
	
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public String getSender() {
		return this.sender;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getSubject() {
		return this.subject;
	}
	
	public void setOrdinal(Integer ordinal) {
		this.ordinal = ordinal;
	}
	
	public Integer getOrdinal() {
		return ordinal;
	}
	
	@SuppressWarnings("unchecked")
	public void setData(Map data) {
		this.data = data;
	}
	
	@SuppressWarnings("unchecked")
	public Map getData() {
		return this.data;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
}
