package com.eceris.durango;

import java.util.Arrays;

public enum Region {

	PROD(ProductionBootstrap.class), DEV(DevelopmentBootstrap.class);

	private final Class<? extends Bootstraper> bootstraper;

	Region(Class<? extends Bootstraper> bootstrapClass) {
		this.bootstraper = bootstrapClass;
	}

	Class<? extends Bootstraper> getBootstrapClass() {
		return this.bootstraper;
	}

	static Class<? extends Bootstraper> getBootstrapClassOf(String profileName) {
		return Arrays.stream(Region.values()).filter(profile -> profile.name().equalsIgnoreCase(profileName))
				.map(profile -> profile.getBootstrapClass()).findFirst()
				.orElseThrow(() -> new IllegalStateException("Not found profile: " + profileName));
	}
}
