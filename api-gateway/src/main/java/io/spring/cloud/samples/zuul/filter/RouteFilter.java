package io.spring.cloud.samples.zuul.filter;

public abstract class RouteFilter extends ContextWrapperAwareFilter {

	public String filterType() {
		return "route";
	}

}
