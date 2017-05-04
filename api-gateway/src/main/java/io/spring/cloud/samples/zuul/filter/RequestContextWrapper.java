package io.spring.cloud.samples.zuul.filter;

import com.netflix.zuul.context.RequestContext;

public class RequestContextWrapper {

	private static final String SERVICE_ID = "serviceId";
	private static final String API_VERSION_HEADER = "X-API-Version";

	private RequestContext requestContext;

	public RequestContextWrapper(RequestContext requestContext) {
		this.requestContext = requestContext;
	}

	public String getServiceId() {
		return (String) requestContext.get(SERVICE_ID);
	}

	public void setServiceId(String serviceId) {
		requestContext.set(SERVICE_ID, serviceId);
	}
	
	public String getApiVersion() {
		return requestContext.getRequest().getHeader(API_VERSION_HEADER);
	}

}
