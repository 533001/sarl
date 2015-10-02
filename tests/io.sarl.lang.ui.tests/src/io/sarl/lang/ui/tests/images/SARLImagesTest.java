/*
 * Copyright (C) 2014-2015 Sebastian RODRIGUEZ, Nicolas GAUD, Stéphane GALLAND.
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
 */
package io.sarl.lang.ui.tests.images;

import io.sarl.lang.ui.images.SARLImages;
import io.sarl.tests.api.AbstractSarlUiTest;

import org.eclipse.jdt.internal.ui.JavaPluginImages;
import org.eclipse.jdt.ui.JavaElementImageDescriptor;
import org.eclipse.xtext.common.types.JvmVisibility;
import org.junit.Test;

import com.google.inject.Inject;

/**
 * @author $Author: sgalland$
 * @version $Name$ $Revision$ $Date$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 */
public class SARLImagesTest extends AbstractSarlUiTest {

	@Inject
	private SARLImages images;

	/**
	 */
	@Test
	public void forPackage() {
		//assertBundleImage("packd_obj.gif", this.images.forPackage()); //$NON-NLS-1$
		assertJdtImage(
				JavaPluginImages.DESC_OBJS_PACKDECL, 0,
				this.images.forPackage());
	}

	/**
	 */
	@Test
	public void forAgent() {
		assertBundleImage("sarl-agent.png", //$NON-NLS-1$
				this.images.forAgent(JvmVisibility.PUBLIC, 0));
	}

	/**
	 */
	@Test
	public void forBehavior() {
		assertBundleImage("sarl-behavior.png", //$NON-NLS-1$
				this.images.forBehavior(JvmVisibility.PUBLIC, 0));
	}

	/**
	 */
	@Test
	public void forCapacity() {
		assertBundleImage("sarl-capacity.png", //$NON-NLS-1$
				this.images.forCapacity(JvmVisibility.PUBLIC, 0));
	}

	/**
	 */
	@Test
	public void forSkill() {
		assertBundleImage("sarl-skill.png", //$NON-NLS-1$
				this.images.forSkill(JvmVisibility.PUBLIC, 0));
	}

	/**
	 */
	@Test
	public void forEvent() {
		assertBundleImage("sarl-event.png", //$NON-NLS-1$
				this.images.forEvent(JvmVisibility.PUBLIC, 0));
	}

	/**
	 */
	@Test
	public void forBehaviorUnit() {
		assertBundleImage("sarl-behavior-unit.png", this.images.forBehaviorUnit()); //$NON-NLS-1$
	}

	/**
	 */
	@Test
	public void forAction() {
		assertJdtImage(
				JavaPluginImages.DESC_MISC_PUBLIC, 0,
				this.images.forOperation(JvmVisibility.PUBLIC, 0));
	}

	/**
	 */
	@Test
	public void forActionSignature() {
		assertJdtImage(
				JavaPluginImages.DESC_MISC_PUBLIC, JavaElementImageDescriptor.ABSTRACT,
				this.images.forOperation(JvmVisibility.PUBLIC, JavaElementImageDescriptor.ABSTRACT));
	}
	
	/**
	 */
	@Test
	public void forFile() {
		assertBundleImage("sarl-file.png", this.images.forFile()); //$NON-NLS-1$
	}

	/**
	 */
	@Test
	public void forField_writable() {
		assertJdtImage(
				JavaPluginImages.DESC_FIELD_PROTECTED, 0,
				this.images.forField(JvmVisibility.PROTECTED, 0));
	}

	/**
	 */
	@Test
	public void forField_notWritable() {
		assertJdtImage(
				JavaPluginImages.DESC_FIELD_PROTECTED, JavaElementImageDescriptor.FINAL,
				this.images.forField(JvmVisibility.PROTECTED, JavaElementImageDescriptor.FINAL));
	}

	/**
	 */
	@Test
	public void forCapacityUses() {
		assertJdtImage(
				JavaPluginImages.DESC_OBJS_IMPCONT, 0,
				this.images.forCapacityUses());
	}

	/**
	 */
	@Test
	public void forCapacityUse() {
		assertBundleImage("imp_obj.gif", this.images.forCapacityUse()); //$NON-NLS-1$
	}

	/**
	 */
	@Test
	public void forCapacityRequirements() {
		assertJdtImage(
				JavaPluginImages.DESC_OBJS_IMPCONT, 0,
				this.images.forCapacityRequirements());
	}

	/**
	 */
	@Test
	public void forCapacityRequirement() {
		assertBundleImage("sarl-capacity.png", this.images.forCapacityRequirement()); //$NON-NLS-1$
	}

}
