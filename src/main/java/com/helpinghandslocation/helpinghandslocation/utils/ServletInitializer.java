package com.helpinghandslocation.helpinghandslocation.utils;

import com.helpinghandslocation.helpinghandslocation.HelpinghandslocationApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(HelpinghandslocationApplication.class);
	}

}
