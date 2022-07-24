package com.troika;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.wadl.internal.WadlResource;
import org.springframework.stereotype.Component;

import com.troika.aggregator.services.impl.CompanyAggServiceImpl;
import com.troika.aggregator.services.impl.JobAggServiceImpl;
import com.troika.aggregator.services.impl.LoginAggServiceImpl;
import com.troika.aggregator.services.impl.ManageMasterDataAggServiceImpl;
import com.troika.aggregator.services.impl.MasterDataAggServiceImpl;
import com.troika.aggregator.services.impl.UserAggServiceImpl;
import com.troika.exceptionhandling.GenericExceptionHandler;
import com.troika.helper.filter.TroikaFilterAuthentication;
import com.troika.helper.filter.TroikaOutFilter;

@Component
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		registerEndpoints();
		registerFilters();
		registerResources();
		register(GenericExceptionHandler.class);

	}

	private void registerFilters() {
		register(TroikaFilterAuthentication.class);
		register(TroikaOutFilter.class);
		register(new MultiPartFeature());
	}

	private void registerEndpoints() {

		register(WadlResource.class);
		register(CompanyAggServiceImpl.class);
		register(JobAggServiceImpl.class);
		register(UserAggServiceImpl.class);
		register(MasterDataAggServiceImpl.class);
		register(LoginAggServiceImpl.class);
		register(ManageMasterDataAggServiceImpl.class);
	}

	private void registerResources() {
		register(MultiPartFeature.class);
	}
}