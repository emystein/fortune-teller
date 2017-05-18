package io.spring.cloud.samples.zuul.filter;

import org.springframework.stereotype.Component;

import com.google.common.base.Optional;

@Component
public class ServiceVersionRouter extends RouteFilter {

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	public Object runWith(RequestContextWrapper requestContext) {
		String versionedServiceId = getVersionedServiceId(requestContext.getServiceId(), requestContext.getApiVersion());
		
		// routing occurs by setting the new serviceId, which is resolved later by the service discovery
		requestContext.setServiceId(versionedServiceId);
		
		return null;
	}

	private String getVersionedServiceId(String serviceId, Optional<String> version) {
		return serviceId + getVersionedServiceSufix(version);
	}

	private String getVersionedServiceSufix(Optional<String> versionNumber) {		
		// TODO automatically choose the latest version, maybe use a version string 'latest' and configure the service discovery to choose the right version number
		String version = versionNumber.or("2");
		
		return "-" + version;
	}

}
