package com.dbs.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "service_binding")
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
public class ServiceBinding {

	@Id
	private String id;

	@Column(nullable = false)
	private String instanceId;

	@JsonSerialize
	@JsonProperty("service_id")
	@Column(nullable = false)
	private String serviceId;

	@JsonSerialize
	@JsonProperty("plan_id")
	@Column(nullable = false)
	private String planId;

	@JsonSerialize
	@JsonProperty("app_guid")
	@Column(nullable = true)
	private String appGuid;

	@OneToOne(cascade = CascadeType.ALL)
	private Credentials credentials;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getAppGuid() {
		return appGuid;
	}

	public void setAppGuid(String appGuid) {
		this.appGuid = appGuid;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		ServiceBinding requested = (ServiceBinding) o;

		if (!id.equals(requested.id))
			return false;
		if (!instanceId.equals(requested.instanceId))
			return false;
		if (!serviceId.equals(requested.serviceId))
			return false;
		if (!planId.equals(requested.planId))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + id.hashCode();
		result = 31 * result + instanceId.hashCode();
		result = 31 * result + serviceId.hashCode();
		result = 31 * result + planId.hashCode();
		return result;
	}
}
