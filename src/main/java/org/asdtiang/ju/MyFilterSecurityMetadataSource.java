package org.asdtiang.ju;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

public class MyFilterSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	public List<ConfigAttribute> getAttributes(Object object) {
		FilterInvocation fi = (FilterInvocation) object;
			String url = fi.getRequestUrl();
			String httpMethod = fi.getRequest().getMethod();
			List<ConfigAttribute> attributes = new ArrayList<ConfigAttribute>();

			// Lookup your database (or other source) using this information and populate the
			// list of attributes

			return attributes;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}
}