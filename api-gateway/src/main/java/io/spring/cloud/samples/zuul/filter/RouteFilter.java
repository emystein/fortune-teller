package io.spring.cloud.samples.zuul.filter;

import com.netflix.zuul.ZuulFilter;

public abstract class RouteFilter extends ZuulFilter {

	@Override
	public String filterType() {
		return "route";
	}

}
