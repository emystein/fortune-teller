package io.spring.cloud.samples.zuul.filter;

import org.springframework.stereotype.Component;

import com.netflix.zuul.context.RequestContext;

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

	@Override
	public Object run() {
		RequestContext requestContext = RequestContext.getCurrentContext();
		RequestContextWrapper requestContextWrapper = new RequestContextWrapper(requestContext);
		
		String serviceId = requestContextWrapper.getServiceId();
		
		String versionHeader = requestContextWrapper.getApiVersion();
		
		if (versionHeader != null) {
			versionHeader = "v" + versionHeader;
		} else {
			// TODO automatically choose the latest version, maybe use a version string 'latest' and configure the service discovery to choose the right version number
			versionHeader = "v2";
		}

		String versionedServiceId = serviceId + "-" + versionHeader;

		requestContextWrapper.setServiceId(versionedServiceId);
		
		return null;
	}

}
