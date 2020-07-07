/**
 * Copyright 2018 The original authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
**/

package io.dekorate.knative.decorator;

import io.dekorate.kubernetes.decorator.NamedResourceDecorator;
import io.dekorate.deps.kubernetes.api.model.ObjectMeta;
import io.dekorate.deps.knative.serving.v1.ServiceFluent;

public class ApplyMaxScaleDecorator extends NamedResourceDecorator<ServiceFluent<?>> {

  private static final String MAX_SCALE = "autoscaling.knative.dev/maxScale";

  private final int scale;


  public ApplyMaxScaleDecorator(String name, int scale) {
		super(name);
    this.scale = scale;
	}

	@Override
	public void andThenVisit(ServiceFluent<?> service, ObjectMeta resourceMeta) {
    service.editMetadata()
      .addToLabels(MAX_SCALE, String.valueOf(scale))
      .endMetadata();
	}
}