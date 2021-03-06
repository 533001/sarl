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

package io.sarl.lang.ui.outline;

import javax.inject.Named;

import com.google.common.base.Strings;
import com.google.inject.Inject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.swt.graphics.Image;
import org.eclipse.xtend.core.xtend.XtendMember;
import org.eclipse.xtend.core.xtend.XtendPackage;
import org.eclipse.xtend.core.xtend.XtendTypeDeclaration;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmFeature;
import org.eclipse.xtext.common.types.JvmParameterizedTypeReference;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.ui.editor.outline.IOutlineNode;
import org.eclipse.xtext.ui.editor.outline.impl.DocumentRootNode;
import org.eclipse.xtext.ui.editor.outline.impl.EObjectNode;
import org.eclipse.xtext.ui.editor.outline.impl.EStructuralFeatureNode;
import org.eclipse.xtext.xbase.annotations.ui.outline.XbaseWithAnnotationsOutlineTreeProvider;

import io.sarl.lang.jvmmodel.SarlJvmModelAssociations;
import io.sarl.lang.sarl.SarlAction;
import io.sarl.lang.sarl.SarlBehaviorUnit;
import io.sarl.lang.sarl.SarlCapacityUses;
import io.sarl.lang.sarl.SarlConstructor;
import io.sarl.lang.sarl.SarlField;
import io.sarl.lang.sarl.SarlRequiredCapacity;
import io.sarl.lang.sarl.SarlScript;

/**
 * Customization of the default outline structure.
 *
 * @author $Author: sgalland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 * @see "https://www.eclipse.org/Xtext/documentation/304_ide_concepts.html#outline"
 */
public class SARLOutlineTreeProvider extends XbaseWithAnnotationsOutlineTreeProvider {

	@Inject
	private SarlJvmModelAssociations associations;

	@Inject
	@Named("DiagnosticDecorator")
	private ILabelDecorator diagnoticDecorator;

	/** Create a node for the SARL script.
	 *
	 * @param parentNode - the parent node.
	 * @param modelElement - the feature container for which a node should be created.
	 */
	protected void _createChildren(DocumentRootNode parentNode, SarlScript modelElement) {
		if (!Strings.isNullOrEmpty(modelElement.getPackage())) {
			createEStructuralFeatureNode(
					parentNode, modelElement,
					XtendPackage.Literals.XTEND_FILE__PACKAGE,
					this.imageDispatcher.invoke(getClass().getPackage()),
					// Do not use the text dispatcher below for avoiding to obtain
					// the filename of the script.
					modelElement.getPackage(),
					true);
		}
		if (modelElement.getImportSection() != null && !modelElement.getImportSection().getImportDeclarations().isEmpty()) {
			createNode(parentNode, modelElement.getImportSection());
		}
		for (final XtendTypeDeclaration topElement : modelElement.getXtendTypes()) {
			createNode(parentNode, topElement);
		}
	}

	/** Create a node for the given feature container.
	 *
	 * @param parentNode - the parent node.
	 * @param modelElement - the feature container for which a node should be created.
	 */
	protected void _createNode(DocumentRootNode parentNode, XtendTypeDeclaration modelElement) {
		final EStructuralFeatureNode elementNode = createEStructuralFeatureNode(
				parentNode,
				modelElement,
				XtendPackage.Literals.XTEND_TYPE_DECLARATION__NAME,
				this.imageDispatcher.invoke(modelElement),
				this.textDispatcher.invoke(modelElement),
				modelElement.getMembers().isEmpty());
		if (!modelElement.getMembers().isEmpty()) {
			EObjectNode capacityUseNode = null;
			EObjectNode capacityRequirementNode = null;

			for (final EObject feature : modelElement.getMembers()) {
				if (feature instanceof SarlField
						|| feature instanceof SarlAction
						|| feature instanceof SarlBehaviorUnit
						|| feature instanceof SarlConstructor
						|| feature instanceof XtendTypeDeclaration) {
					createNode(elementNode, feature);
				} else if (feature instanceof SarlCapacityUses) {
					capacityUseNode = createCapacityUseNode(elementNode, (SarlCapacityUses) feature, capacityUseNode);
				} else if (feature instanceof SarlRequiredCapacity) {
					capacityRequirementNode = createRequiredCapacityNode(elementNode,
							(SarlRequiredCapacity) feature, capacityRequirementNode);
				}
			}
		}
	}

	private EObjectNode createCapacityUseNode(EStructuralFeatureNode elementNode, SarlCapacityUses feature,
			EObjectNode oldCapacityUseNode) {
		EObjectNode capacityUseNode = oldCapacityUseNode;
		if (capacityUseNode == null) {
			capacityUseNode = createEObjectNode(
					elementNode, feature,
					this.imageDispatcher.invoke(feature),
					this.textDispatcher.invoke(feature),
					false);
		}
		for (final JvmParameterizedTypeReference item : feature.getCapacities()) {
			createEObjectNode(
					capacityUseNode, item,
					this.imageDispatcher.invoke(item),
					this.textDispatcher.invoke(item),
					true);
		}
		return capacityUseNode;
	}

	private EObjectNode createRequiredCapacityNode(EStructuralFeatureNode elementNode, SarlRequiredCapacity feature,
			EObjectNode oldCapacityRequirementNode) {
		EObjectNode capacityRequirementNode = oldCapacityRequirementNode;
		if (capacityRequirementNode == null) {
			capacityRequirementNode = createEObjectNode(
					elementNode, feature,
					this.imageDispatcher.invoke(feature),
					this.textDispatcher.invoke(feature),
					false);
		}
		for (final JvmParameterizedTypeReference item : feature.getCapacities()) {
			createEObjectNode(
					capacityRequirementNode, item,
					this.imageDispatcher.invoke(item),
					this.textDispatcher.invoke(item),
					true);
		}
		return capacityRequirementNode;
	}

	/** Replies if the type declaration element is a leaf in the outline.
	 *
	 * @param modelElement - the model element.
	 * @return <code>true</code> if it is a leaf, <code>false</code> otherwise.
	 */
	@SuppressWarnings("static-method")
	protected boolean _isLeaf(XtendTypeDeclaration modelElement) {
		return modelElement.getMembers().isEmpty();
	}

	/** Replies if the member element is a leaf in the outline.
	 *
	 * @param modelElement - the model element.
	 * @return <code>true</code> if it is a leaf, <code>false</code> otherwise.
	 */
	@SuppressWarnings("static-method")
	protected boolean _isLeaf(XtendMember modelElement) {
		return true;
	}

	@Override
	protected EObjectNode createEObjectNode(
			IOutlineNode parentNode,
			EObject modelElement, Image image, Object text,
			boolean isLeaf) {
		final SARLEObjectNode objectNode = new SARLEObjectNode(modelElement, parentNode, image, text, isLeaf);
		configureNode(parentNode, modelElement, objectNode);
		return objectNode;
	}

	private void configureNode(IOutlineNode parentNode, EObject modelElement, SARLEObjectNode objectNode) {
		final EObject primarySourceElement = this.associations.getPrimarySourceElement(modelElement);
		final ICompositeNode parserNode = NodeModelUtils.getNode(
				(primarySourceElement == null) ? modelElement : primarySourceElement);

		if (parserNode != null) {
			objectNode.setTextRegion(parserNode.getTextRegion());
		}

		if (isLocalElement(parentNode, modelElement)) {
			objectNode.setShortTextRegion(this.locationInFileProvider.getSignificantTextRegion(modelElement));
		}

		objectNode.setStatic(isStatic(modelElement));
	}

	private static boolean isStatic(EObject element) {
		if (element instanceof JvmFeature) {
			return ((JvmFeature) element).isStatic();
		}
		if (element instanceof JvmDeclaredType) {
			return ((JvmDeclaredType) element).isStatic();
		}
		if (element instanceof XtendMember) {
			try {
				return ((XtendMember) element).isStatic();
			} catch (Exception exception) {
				// Some XtendMember does not support
			}
		}
		return false;
	}

	@Override
	protected Image _image(Object modelElement) {
		final Image img = super._image(modelElement);
		return this.diagnoticDecorator.decorateImage(img, modelElement);
	}

}
