/*
 * $Id$
 *
 * SARL is an general-purpose agent programming language.
 * More details on http://www.sarl.io
 *
 * Copyright (C) 2014-2016 the original authors or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.janusproject.modules.nonetwork;

import com.google.common.util.concurrent.Service;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;
import io.janusproject.kernel.services.jdk.network.NoNetworkService;
import io.janusproject.services.network.NetworkService;

/**
 * Module that provides the network layer based on the NoNetwork library.
 *
 * @author $Author: sgalland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 */
public class NoNetworkModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(NetworkService.class).to(NoNetworkService.class).in(Singleton.class);

		// Complete the binding for: Set<Service>
		// (This set is given to the service manager to launch the services).
		final Multibinder<Service> serviceSetBinder = Multibinder.newSetBinder(binder(), Service.class);
		serviceSetBinder.addBinding().to(NoNetworkService.class);
	}

}
