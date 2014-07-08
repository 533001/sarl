/*
 * $Id$
 *
 * SARL is an general-purpose agent programming language.
 * More details on http://www.sarl.io
 *
 * Copyright (C) 2014 Sebastian RODRIGUEZ, Nicolas GAUD, Stéphane GALLAND.
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
package io.sarl.lang.ui.outline

import io.sarl.lang.sarl.Action
import io.sarl.lang.sarl.ActionSignature
import io.sarl.lang.sarl.Agent
import io.sarl.lang.sarl.Attribute
import io.sarl.lang.sarl.Behavior
import io.sarl.lang.sarl.BehaviorUnit
import io.sarl.lang.sarl.Capacity
import io.sarl.lang.sarl.CapacityUses
import io.sarl.lang.sarl.Constructor
import io.sarl.lang.sarl.Event
import io.sarl.lang.sarl.FeatureContainer
import io.sarl.lang.sarl.RequiredCapacity
import io.sarl.lang.sarl.SarlPackage
import io.sarl.lang.sarl.SarlScript
import io.sarl.lang.sarl.Skill
import org.eclipse.jdt.internal.ui.JavaPluginImages
import org.eclipse.xtext.ui.editor.outline.impl.DefaultOutlineTreeProvider
import org.eclipse.xtext.ui.editor.outline.impl.DocumentRootNode
import org.eclipse.xtext.ui.editor.outline.impl.EObjectNode

/**
 * Customization of the default outline structure.
 *
 * see http://www.eclipse.org/Xtext/documentation.html#outline
 */
class SARLOutlineTreeProvider extends DefaultOutlineTreeProvider { 
	
	protected def _createChildren(DocumentRootNode parentNode, SarlScript modelElement) {
		if (modelElement.name!==null) {
			createEStructuralFeatureNode(
				parentNode, modelElement,
				SarlPackage.Literals.SARL_SCRIPT__NAME, 
				JavaPluginImages::get(JavaPluginImages::IMG_OBJS_PACKDECL),
				modelElement.name,
				true)
		}
		if (modelElement.importSection!==null && !modelElement.importSection.importDeclarations.empty) {
			createNode(parentNode, modelElement.importSection)
		}
		for(topElement : modelElement.elements) {
			createNode(parentNode, topElement)
		}
	}
	
	protected def _createNode(DocumentRootNode parentNode, FeatureContainer modelElement) {
		var elementNode = createEStructuralFeatureNode(
			parentNode, modelElement,
			SarlPackage.Literals.NAMED_ELEMENT__NAME, 
			imageDispatcher.invoke(modelElement),
			textDispatcher.invoke(modelElement),
			modelElement.features.empty)
		if (!modelElement.features.empty) {
			
			var EObjectNode capacityUseNode = null
			var EObjectNode capacityRequirementNode = null
			
			for(feature : modelElement.features) {
				if (feature instanceof Attribute) {
					createNode(elementNode, feature)
				}
				else if (feature instanceof Action) {
					createNode(elementNode, feature)
				}
				else if (feature instanceof ActionSignature) {
					createNode(elementNode, feature)
				}
				else if (feature instanceof BehaviorUnit) {
					createNode(elementNode, feature)
				}
				else if (feature instanceof Constructor) {
					createNode(elementNode, feature)
				}
				else if (feature instanceof CapacityUses) {
					if (capacityUseNode===null) {
						capacityUseNode = createEObjectNode(
								elementNode, feature,
								imageDispatcher.invoke(feature),
								textDispatcher.invoke(feature),
								false)			
					}
					for(item : feature.capacitiesUsed) {
						createEObjectNode(
									capacityUseNode, item,
									imageDispatcher.invoke(item),
									textDispatcher.invoke(item),
									true)		
					}
				}
				else if (feature instanceof RequiredCapacity) {
					if (capacityRequirementNode===null) {
						capacityRequirementNode = createEObjectNode(
								elementNode, feature,
								imageDispatcher.invoke(feature),
								textDispatcher.invoke(feature),
								false)			
					}
					for(item : feature.requiredCapacities) {
						createEObjectNode(
									capacityRequirementNode, item,
									imageDispatcher.invoke(item),
									textDispatcher.invoke(item),
									true)		
					}
				}
			}
		}
	}

	protected def boolean _isLeaf(Agent modelElement) {
  		modelElement.features.empty
	}

	protected def boolean _isLeaf(Capacity modelElement) {
  		modelElement.features.empty
	}

	protected def boolean _isLeaf(Skill modelElement) {
  		modelElement.features.empty
	}

	protected def boolean _isLeaf(Event modelElement) {
  		modelElement.features.empty
	}

	protected def boolean _isLeaf(Behavior modelElement) {
  		modelElement.features.empty
	}

	protected def boolean _isLeaf(Action modelElement) {
  		true
	}
	
	protected def boolean _isLeaf(ActionSignature modelElement) {
  		true
	}

	protected def boolean _isLeaf(Constructor modelElement) {
  		true
	}

	protected def boolean _isLeaf(BehaviorUnit modelElement) {
  		true
	}

	protected def boolean _isLeaf(Attribute modelElement) {
  		true
	}

}
