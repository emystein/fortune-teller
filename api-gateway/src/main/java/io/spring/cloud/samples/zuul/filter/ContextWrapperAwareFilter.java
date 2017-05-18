package io.spring.cloud.samples.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public abstract class ContextWrapperAwareFilter extends ZuulFilter {

	public final Object run() {
		return runWith(getRequestContext());
	}

	public abstract Object runWith(RequestContextWrapper requestContext);

	private RequestContextWrapper getRequestContext() {
		return new RequestContextWrapper(RequestContext.getCurrentContext());
	}

}
