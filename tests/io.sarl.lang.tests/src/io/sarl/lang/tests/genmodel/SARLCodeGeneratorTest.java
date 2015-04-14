/*
 * Copyright 2014 Sebastian RODRIGUEZ, Nicolas GAUD, Stéphane GALLAND.
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
package io.sarl.lang.tests.genmodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.sarl.lang.SARLInjectorProvider;
import io.sarl.lang.actionprototype.ActionPrototypeProvider;
import io.sarl.lang.genmodel.BlockInnerDocumentationAdapter;
import io.sarl.lang.genmodel.GeneratedCode;
import io.sarl.lang.genmodel.PostDocumentationAdapter;
import io.sarl.lang.genmodel.SARLCodeGenerator;
import io.sarl.lang.sarl.SarlAction;
import io.sarl.lang.sarl.SarlAgent;
import io.sarl.lang.sarl.SarlBehavior;
import io.sarl.lang.sarl.SarlBehaviorUnit;
import io.sarl.lang.sarl.SarlCapacity;
import io.sarl.lang.sarl.SarlEvent;
import io.sarl.lang.sarl.SarlFormalParameter;
import io.sarl.lang.sarl.SarlSkill;
import io.sarl.tests.api.AbstractSarlTest;
import io.sarl.tests.api.AbstractSarlUiTest;
import io.sarl.tests.api.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtend.core.xtend.XtendConstructor;
import org.eclipse.xtend.core.xtend.XtendExecutable;
import org.eclipse.xtend.core.xtend.XtendField;
import org.eclipse.xtend.core.xtend.XtendFile;
import org.eclipse.xtend.core.xtend.XtendMember;
import org.eclipse.xtend.core.xtend.XtendTypeDeclaration;
import org.eclipse.xtext.common.types.JvmConstructor;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.JvmParameterizedTypeReference;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.TypesFactory;
import org.eclipse.xtext.common.types.access.IJvmTypeProvider;
import org.eclipse.xtext.common.types.util.TypeReferences;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.resource.IResourceFactory;
import org.eclipse.xtext.serializer.ISerializer;
import org.eclipse.xtext.xbase.XBlockExpression;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XNumberLiteral;
import org.eclipse.xtext.xbase.XStringLiteral;
import org.eclipse.xtext.xbase.XbaseFactory;
import org.eclipse.xtext.xbase.compiler.DocumentationAdapter;
import org.eclipse.xtext.xbase.compiler.ImportManager;
import org.eclipse.xtext.xbase.impl.XBlockExpressionImpl;
import org.eclipse.xtext.xbase.jvmmodel.JvmModelAssociator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.google.inject.Inject;

/**
 * @author $Author: srodriguez$
 * @version $Name$ $Revision$ $Date$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 */
@RunWith(Suite.class)
@SuiteClasses({
	SARLCodeGeneratorTest.InjectedAttributes.class,
	SARLCodeGeneratorTest.DefaultTypeValue.class,
	SARLCodeGeneratorTest.References.class,
	SARLCodeGeneratorTest.Comments.class,
	SARLCodeGeneratorTest.ScriptLevel.class,
	SARLCodeGeneratorTest.AgentTopElement.class,
	SARLCodeGeneratorTest.BehaviorTopElement.class,
	SARLCodeGeneratorTest.CapacityTopElement.class,
	SARLCodeGeneratorTest.EventTopElement.class,
	SARLCodeGeneratorTest.SkillTopElement.class,
	SARLCodeGeneratorTest.AgentFeatures.class,
	SARLCodeGeneratorTest.BehaviorFeatures.class,
	SARLCodeGeneratorTest.CapacityFeatures.class,
	SARLCodeGeneratorTest.EventFeatures.class,
	SARLCodeGeneratorTest.SkillFeatures.class,
	SARLCodeGeneratorTest.Expressions.class,
	SARLCodeGeneratorTest.FormalParameters.class,
	SARLCodeGeneratorTest.CreateActionFromJvmElement.class,
	SARLCodeGeneratorTest.CreateConstructorFromJvmElement.class,
	SARLCodeGeneratorTest.createActionFromJvmElement.class
})
@SuppressWarnings("all")
public class SARLCodeGeneratorTest {

	/** Check if the import manager contains the given elements.
	 *
	 * @param importManager - the import manager.
	 * @param importDeclarations - the expected import declarations.
	 */
	protected static void assertImports(ImportManager importManager, String... importDeclarations) {
		List<String> imports = importManager.getImports();
		assertEquals("Invalid number of import declarations", importDeclarations.length, imports.size());
		for (String declaration : importDeclarations) {
			assertTrue("Expecting import declaration: " + declaration,
					imports.contains(declaration));
		}
	}

	public static class InjectedAttributes extends AbstractSarlTest {

		@Inject
		private TypeReferences typeReferences;

		@Inject
		private TypesFactory typesFactory;

		@Inject
		private IResourceFactory resourceFactory;

		@Inject
		ActionPrototypeProvider actionSignatureProvider;

		@Inject
		private SARLCodeGenerator gen;

		@Test
		public void getSARLFileExtension() {
			assertEquals("sarl", gen.getSARLFileExtension());
		}

		@Test
		public void getTypeReferences() {
			assertSame(this.typeReferences, gen.getTypeReferences());
		}

		@Test
		public void getTypesFactory() {
			assertSame(this.typesFactory, gen.getTypesFactory());
		}

		@Test
		public void getResourceFactory() {
			IResourceFactory factory = gen.getResourceFactory();
			assertNotNull(factory);
			assertNotSame(this.resourceFactory, factory);
		}

		@Test
		public void getActionSignatureProvider() {
			assertSame(this.actionSignatureProvider, gen.getActionSignatureProvider());
		}

	}

	public static class DefaultTypeValue extends AbstractSarlTest {

		@Inject
		private ISerializer serializer;

		@Inject
		private SARLCodeGenerator gen;

		@Nullable
		private GeneratedCode code;

		@Nullable
		private EObject context;

		@Before
		public void setUp() {
			code = mock(GeneratedCode.class);
			context = mock(EObject.class);
		}

		private void assertSerialized(String expected, EObject actual) {
			String value = this.serializer.serialize(actual);
			assertNotNull(value);
			assertEquals(expected, value);
		}

		@Test
		public void getDefaultXExpressionForType_void() {
			assertNull(gen.getDefaultXExpressionForType(code, context, "void"));
		}

		@Test
		public void getDefaultXExpressionForType_Void() {
			assertNull(gen.getDefaultXExpressionForType(code, context, "java.lang.Void"));
		}

		@Test
		public void getDefaultXExpressionForType_boolean() {
			assertSerialized("false", gen.getDefaultXExpressionForType(code, context, "boolean"));
		}

		@Test
		public void getDefaultXExpressionForType_Boolean() {
			assertSerialized("false", gen.getDefaultXExpressionForType(code, context, "java.lang.Boolean"));
		}

		@Test
		public void getDefaultXExpressionForType_char() {
			assertSerialized("0", gen.getDefaultXExpressionForType(code, context, "char"));
		}

		@Test
		public void getDefaultXExpressionForType_Character() {
			assertSerialized("0", gen.getDefaultXExpressionForType(code, context, "java.lang.Character"));
		}

		@Test
		public void getDefaultXExpressionForType_byte() {
			assertSerialized("0", gen.getDefaultXExpressionForType(code, context, "byte"));
		}

		@Test
		public void getDefaultXExpressionForType_Byte() {
			assertSerialized("0", gen.getDefaultXExpressionForType(code, context, "java.lang.Byte"));
		}

		@Test
		public void getDefaultXExpressionForType_short() {
			assertSerialized("0", gen.getDefaultXExpressionForType(code, context, "short"));
		}

		@Test
		public void getDefaultXExpressionForType_Short() {
			assertSerialized("0", gen.getDefaultXExpressionForType(code, context, "java.lang.Short"));
		}

		@Test
		public void getDefaultXExpressionForType_int() {
			assertSerialized("0", gen.getDefaultXExpressionForType(code, context, "int"));
		}

		@Test
		public void getDefaultXExpressionForType_Integer() {
			assertSerialized("0", gen.getDefaultXExpressionForType(code, context, "java.lang.Integer"));
		}

		@Test
		public void getDefaultXExpressionForType_long() {
			assertSerialized("0", gen.getDefaultXExpressionForType(code, context, "long"));
		}

		@Test
		public void getDefaultXExpressionForType_Long() {
			assertSerialized("0", gen.getDefaultXExpressionForType(code, context, "java.lang.Long"));
		}

		@Test
		public void getDefaultXExpressionForType_float() {
			assertSerialized("0.0f", gen.getDefaultXExpressionForType(code, context, "float"));
		}

		@Test
		public void getDefaultXExpressionForType_Float() {
			assertSerialized("0.0f", gen.getDefaultXExpressionForType(code, context, "java.lang.Float"));
		}

		@Test
		public void getDefaultXExpressionForType_double() {
			assertSerialized("0.0", gen.getDefaultXExpressionForType(code, context, "double"));
		}

		@Test
		public void getDefaultXExpressionForType_Double() {
			assertSerialized("0.0", gen.getDefaultXExpressionForType(code, context, "java.lang.Double"));
		}

		@Test
		public void getDefaultXExpressionForType_String() {
			assertSerialized("null", gen.getDefaultXExpressionForType(code, context, "java.lang.String"));
		}

		@Test
		public void getDefaultXExpressionForType_AnyObject() {
			assertSerialized("null", gen.getDefaultXExpressionForType(code, context, "io.sarl.lang.tests.Dummy"));
		}

	}

	public static class References extends AbstractSarlTest {

		@Inject
		private SARLCodeGenerator gen;

		@Nullable
		private GeneratedCode code;

		@Nullable
		private EObject context;

		@Nullable
		private ImportManager importManager;

		@Nullable
		private JvmType jvmType;

		@Before
		public void setUp() {
			importManager = new ImportManager();
			code = mock(GeneratedCode.class);
			context = mock(EObject.class);

			Resource eResource = mock(Resource.class);
			ResourceSet eResourceSet = mock(ResourceSet.class);
			Resource.Factory.Registry registry = mock(Resource.Factory.Registry.class);
			Map factoryMap = mock(Map.class);
			IJvmTypeProvider typeProvider = mock(IJvmTypeProvider.class);
			jvmType = mock(JvmType.class);

			when(code.getImportManager()).thenReturn(importManager);
			when(typeProvider.findTypeByName(Matchers.anyString())).thenReturn(jvmType);
			when(factoryMap.get(Matchers.any())).thenReturn(typeProvider);
			when(registry.getProtocolToFactoryMap()).thenReturn(factoryMap);
			when(eResourceSet.getResourceFactoryRegistry()).thenReturn(registry);
			when(eResource.getResourceSet()).thenReturn(eResourceSet);
			when(context.eResource()).thenReturn(eResource);
		}

		@Test
		public void newTypeRef_String() {
			when(jvmType.getIdentifier()).thenReturn("java.lang.String");
			when(jvmType.getQualifiedName(Matchers.anyChar())).thenReturn("java.lang.String");
			when(jvmType.getQualifiedName()).thenReturn("java.lang.String");
			when(jvmType.getSimpleName()).thenReturn("String");
			//
			JvmParameterizedTypeReference ref = gen.newTypeRef(code, "java.lang.String", context);
			//
			assertNotNull(ref);
			assertEquals("java.lang.String", ref.getIdentifier());
			assertContains(importManager.getImports());
		}

		@Test
		public void newTypeRef_Foo() {
			when(jvmType.getIdentifier()).thenReturn("foo.Foo");
			when(jvmType.getQualifiedName(Matchers.anyChar())).thenReturn("foo.Foo");
			when(jvmType.getQualifiedName()).thenReturn("foo.Foo");
			when(jvmType.getSimpleName()).thenReturn("Foo");
			//
			JvmParameterizedTypeReference ref = gen.newTypeRef(code, "foo.Foo", context);
			//
			assertNotNull(ref);
			assertEquals("foo.Foo", ref.getIdentifier());
			assertContains(importManager.getImports(), "foo.Foo");
		}

	}

	public static class Comments extends AbstractSarlTest {

		@Inject
		private SARLCodeGenerator gen;

		@Nullable
		private GeneratedCode code;

		@Nullable
		private EObject context;

		@Nullable
		private EObject object;

		@Nullable
		private XBlockExpression block;

		@Before
		public void setUp() {
			code = mock(GeneratedCode.class);
			context = mock(EObject.class);
			object = new EObjectImpl() {};
			block = new XBlockExpressionImpl() {};
		}

		private <T extends Adapter> T assertAdapter(Class<T> expectedType, EObject actual) {
			for(Adapter adapter : actual.eAdapters()) {
				if (expectedType.isInstance(adapter)) {
					return expectedType.cast(adapter);
				}
			}
			fail("Expecting the adapter of type: " + expectedType.getName());
			return null;
		}

		@Test
		public void attachComment() {
			gen.attachComment(code, object, "my comment");
			DocumentationAdapter adapter = assertAdapter(DocumentationAdapter.class, object);
			assertEquals("my comment", adapter.getDocumentation());
		}

		@Test
		public void attachPostComment() {
			gen.attachPostComment(code, object, "my comment");
			PostDocumentationAdapter adapter = assertAdapter(PostDocumentationAdapter.class, object);
			assertEquals("my comment", adapter.getDocumentation());
		}

		@Test
		public void attachInnerComment() {
			gen.attachInnerComment(code, block, "my comment");
			BlockInnerDocumentationAdapter adapter = assertAdapter(BlockInnerDocumentationAdapter.class, block);
			assertEquals("my comment", adapter.getDocumentation());
		}

	}

	public static class ScriptLevel extends AbstractSarlTest {

		@Inject
		private SARLCodeGenerator gen;

		@Nullable
		private ResourceSet resourceSet;

		@Nullable
		private Resource resource;

		@Nullable
		private EList<EObject> content;

		@Nullable
		private GeneratedCode code;

		@Before
		public void setUp() {
			resourceSet = mock(ResourceSet.class);
			content = mock(EList.class);
			resource = mock(Resource.class);
			when(resource.getContents()).thenReturn(content);
			when(resource.getResourceSet()).thenReturn(resourceSet);
			code = gen.createScript(resource, "io.sarl.lang.tests");
			assertNotNull(code);
		}

		@Test
		public void sarlScript()  {
			XtendFile script = code.getSarlScript();
			assertNotNull(script);
			assertEquals("io.sarl.lang.tests", script.getPackage());
			assertTrue(script.getXtendTypes().isEmpty());
			assertNull(script.getImportSection());
		}

		@Test
		public void codeGenerator()  {
			SARLCodeGenerator codeGenerator = code.getCodeGenerator();
			assertSame(gen, codeGenerator);
		}

		@Test
		public void importManager()  {
			ImportManager importManager = code.getImportManager();
			assertNotNull(importManager);
			assertTrue(importManager.getImports().isEmpty());
		}

		@Test
		public void resourceSet()  {
			assertSame(resourceSet, code.getResourceSet());
		}

	}

	public static class AgentTopElement extends AbstractSarlTest {

		@Inject
		private SARLCodeGenerator gen;

		@Nullable
		private GeneratedCode code;

		@Nullable
		private XtendFile context;

		@Nullable
		private ImportManager importManager;

		@Nullable
		private JvmType jvmType;

		@Nullable
		private EList<XtendTypeDeclaration> elements;

		@Before
		public void setUp() {
			elements = new BasicEList<>();
			importManager = new ImportManager();
			code = mock(GeneratedCode.class);
			context = mock(XtendFile.class);

			Resource eResource = mock(Resource.class);
			ResourceSet eResourceSet = mock(ResourceSet.class);
			Resource.Factory.Registry registry = mock(Resource.Factory.Registry.class);
			Map factoryMap = mock(Map.class);
			IJvmTypeProvider typeProvider = mock(IJvmTypeProvider.class);
			jvmType = mock(JvmType.class);

			when(code.getImportManager()).thenReturn(importManager);
			when(code.getSarlScript()).thenReturn(context);
			when(typeProvider.findTypeByName(Matchers.anyString())).thenReturn(jvmType);
			when(factoryMap.get(Matchers.any())).thenReturn(typeProvider);
			when(registry.getProtocolToFactoryMap()).thenReturn(factoryMap);
			when(eResourceSet.getResourceFactoryRegistry()).thenReturn(registry);
			when(eResource.getResourceSet()).thenReturn(eResourceSet);
			when(context.eResource()).thenReturn(eResource);
			when(context.getXtendTypes()).thenReturn(elements);
		}

		@Test
		public void nullSuperClass()  {
			SarlAgent agent = gen.createAgent(code, "MyAgent", null);
			assertNotNull(agent);
			assertEquals("MyAgent", agent.getName());
			assertNull(agent.getExtends());
			assertTrue(agent.getMembers().isEmpty());
		}

		@Test
		public void agentSuperClass()  {
			SarlAgent agent = gen.createAgent(code, "MyAgent", "io.sarl.lang.core.Agent");
			assertNotNull(agent);
			assertEquals("MyAgent", agent.getName());
			assertNull(agent.getExtends());
			assertTrue(agent.getMembers().isEmpty());
		}

		@Test
		public void subagentSuperClass()  {
			when(jvmType.getIdentifier()).thenReturn("foo.ecore.SubAgent");
			when(jvmType.getQualifiedName(Matchers.anyChar())).thenReturn("foo.ecore.SubAgent");
			when(jvmType.getQualifiedName()).thenReturn("foo.ecore.SubAgent");
			when(jvmType.getSimpleName()).thenReturn("SubAgent");
			//
			SarlAgent agent = gen.createAgent(code, "MyAgent", "foo.ecore.SubAgent");
			//
			assertNotNull(agent);
			assertEquals("MyAgent", agent.getName());
			assertTypeReferenceIdentifier(agent.getExtends(), "foo.ecore.SubAgent");
			assertTrue(agent.getMembers().isEmpty());
		}

		@Test
		public void otherSuperClass()  {
			when(jvmType.getIdentifier()).thenReturn("foo.Foo");
			when(jvmType.getQualifiedName(Matchers.anyChar())).thenReturn("foo.Foo");
			when(jvmType.getQualifiedName()).thenReturn("foo.Foo");
			when(jvmType.getSimpleName()).thenReturn("Foo");
			//
			SarlAgent agent = gen.createAgent(code, "MyAgent", "foo.Foo");
			//
			assertNotNull(agent);
			assertEquals("MyAgent", agent.getName());
			assertTypeReferenceIdentifier(agent.getExtends(), "foo.Foo");
			assertTrue(agent.getMembers().isEmpty());
		}

	}

	public static class BehaviorTopElement extends AbstractSarlTest {

		@Inject
		private SARLCodeGenerator gen;

		@Nullable
		private GeneratedCode code;

		@Nullable
		private XtendFile context;

		@Nullable
		private ImportManager importManager;

		@Nullable
		private JvmType jvmType;

		@Nullable
		private EList<XtendTypeDeclaration> elements;

		@Before
		public void setUp() {
			elements = new BasicEList();
			importManager = new ImportManager();
			code = mock(GeneratedCode.class);
			context = mock(XtendFile.class);

			Resource eResource = mock(Resource.class);
			ResourceSet eResourceSet = mock(ResourceSet.class);
			Resource.Factory.Registry registry = mock(Resource.Factory.Registry.class);
			Map factoryMap = mock(Map.class);
			IJvmTypeProvider typeProvider = mock(IJvmTypeProvider.class);
			jvmType = mock(JvmType.class);

			when(code.getImportManager()).thenReturn(importManager);
			when(code.getSarlScript()).thenReturn(context);
			when(typeProvider.findTypeByName(Matchers.anyString())).thenReturn(jvmType);
			when(factoryMap.get(Matchers.any())).thenReturn(typeProvider);
			when(registry.getProtocolToFactoryMap()).thenReturn(factoryMap);
			when(eResourceSet.getResourceFactoryRegistry()).thenReturn(registry);
			when(eResource.getResourceSet()).thenReturn(eResourceSet);
			when(context.eResource()).thenReturn(eResource);
			when(context.getXtendTypes()).thenReturn(elements);
		}

		@Test
		public void nullSuperClass()  {
			SarlBehavior behavior = gen.createBehavior(code, "MyBehavior", null);
			assertNotNull(behavior);
			assertEquals("MyBehavior", behavior.getName());
			assertNull(behavior.getExtends());
			assertTrue(behavior.getMembers().isEmpty());
		}

		@Test
		public void behaviorSuperClass()  {
			SarlBehavior behavior = gen.createBehavior(code, "MyBehavior", "io.sarl.lang.core.Behavior");
			assertNotNull(behavior);
			assertEquals("MyBehavior", behavior.getName());
			assertNull(behavior.getExtends());
			assertTrue(behavior.getMembers().isEmpty());
		}

		@Test
		public void subbehaviorSuperClass()  {
			when(jvmType.getIdentifier()).thenReturn("foo.ecore.SubBehavior");
			when(jvmType.getQualifiedName(Matchers.anyChar())).thenReturn("foo.ecore.SubBehavior");
			when(jvmType.getQualifiedName()).thenReturn("foo.ecore.SubBehavior");
			when(jvmType.getSimpleName()).thenReturn("SubBehavior");
			//
			SarlBehavior behavior = gen.createBehavior(code, "MyBehavior", "foo.ecore.SubBehavior");
			//
			assertNotNull(behavior);
			assertEquals("MyBehavior", behavior.getName());
			assertTypeReferenceIdentifier(behavior.getExtends(), "foo.ecore.SubBehavior");
			assertTrue(behavior.getMembers().isEmpty());
		}

		@Test
		public void otherSuperClass()  {
			when(jvmType.getIdentifier()).thenReturn("foo.Foo");
			when(jvmType.getQualifiedName(Matchers.anyChar())).thenReturn("foo.Foo");
			when(jvmType.getQualifiedName()).thenReturn("foo.Foo");
			when(jvmType.getSimpleName()).thenReturn("Foo");
			//
			SarlBehavior behavior = gen.createBehavior(code, "MyBehavior", "foo.Foo");
			//
			assertNotNull(behavior);
			assertEquals("MyBehavior", behavior.getName());
			assertTypeReferenceIdentifier(behavior.getExtends(), "foo.Foo");
			assertTrue(behavior.getMembers().isEmpty());
		}

	}

	public static class CapacityTopElement extends AbstractSarlTest {

		@Inject
		private SARLCodeGenerator gen;

		@Nullable
		private GeneratedCode code;

		@Nullable
		private XtendFile context;

		@Nullable
		private ImportManager importManager;

		@Nullable
		private JvmType jvmType;

		@Nullable
		private EList<XtendTypeDeclaration> elements;

		@Before
		public void setUp() {
			elements = new BasicEList();
			importManager = new ImportManager();
			code = mock(GeneratedCode.class);
			context = mock(XtendFile.class);

			Resource eResource = mock(Resource.class);
			ResourceSet eResourceSet = mock(ResourceSet.class);
			Resource.Factory.Registry registry = mock(Resource.Factory.Registry.class);
			Map factoryMap = mock(Map.class);
			IJvmTypeProvider typeProvider = mock(IJvmTypeProvider.class);
			jvmType = mock(JvmType.class);

			when(code.getImportManager()).thenReturn(importManager);
			when(code.getSarlScript()).thenReturn(context);
			when(typeProvider.findTypeByName(Matchers.anyString())).thenReturn(jvmType);
			when(factoryMap.get(Matchers.any())).thenReturn(typeProvider);
			when(registry.getProtocolToFactoryMap()).thenReturn(factoryMap);
			when(eResourceSet.getResourceFactoryRegistry()).thenReturn(registry);
			when(eResource.getResourceSet()).thenReturn(eResourceSet);
			when(context.eResource()).thenReturn(eResource);
			when(context.getXtendTypes()).thenReturn(elements);
		}

		@Test
		public void nullSuperClass()  {
			SarlCapacity capacity = gen.createCapacity(code, "MyCapacity", null);
			assertNotNull(capacity);
			assertEquals("MyCapacity", capacity.getName());
			assertTrue(capacity.getExtends().isEmpty());
			assertTrue(capacity.getMembers().isEmpty());
		}

		@Test
		public void behaviorSuperClass()  {
			SarlCapacity capacity = gen.createCapacity(code, "MyCapacity", "io.sarl.lang.core.Capacity");
			assertNotNull(capacity);
			assertEquals("MyCapacity", capacity.getName());
			assertTrue(capacity.getExtends().isEmpty());
			assertTrue(capacity.getMembers().isEmpty());
		}

		@Test
		public void subbehaviorSuperClass()  {
			when(jvmType.getIdentifier()).thenReturn("foo.ecore.SubCapacity");
			when(jvmType.getQualifiedName(Matchers.anyChar())).thenReturn("foo.ecore.SubCapacity");
			when(jvmType.getQualifiedName()).thenReturn("foo.ecore.SubCapacity");
			when(jvmType.getSimpleName()).thenReturn("SubCapacity");
			//
			SarlCapacity capacity = gen.createCapacity(code, "MyCapacity", "foo.ecore.SubCapacity");
			//
			assertNotNull(capacity);
			assertEquals("MyCapacity", capacity.getName());
			assertTypeReferenceIdentifiers(capacity.getExtends(), "foo.ecore.SubCapacity");
			assertTrue(capacity.getMembers().isEmpty());
		}

		@Test
		public void otherSuperClass()  {
			when(jvmType.getIdentifier()).thenReturn("foo.Foo");
			when(jvmType.getQualifiedName(Matchers.anyChar())).thenReturn("foo.Foo");
			when(jvmType.getQualifiedName()).thenReturn("foo.Foo");
			when(jvmType.getSimpleName()).thenReturn("Foo");
			//
			SarlCapacity capacity = gen.createCapacity(code, "MyCapacity", "foo.Foo");
			//
			assertNotNull(capacity);
			assertEquals("MyCapacity", capacity.getName());
			assertTypeReferenceIdentifiers(capacity.getExtends(), "foo.Foo");
			assertTrue(capacity.getMembers().isEmpty());
		}

	}

	public static class EventTopElement extends AbstractSarlTest {

		@Inject
		private SARLCodeGenerator gen;

		@Nullable
		private GeneratedCode code;

		@Nullable
		private XtendFile context;

		@Nullable
		private ImportManager importManager;

		@Nullable
		private JvmType jvmType;

		@Nullable
		private EList<XtendTypeDeclaration> elements;

		@Before
		public void setUp() {
			elements = new BasicEList();
			importManager = new ImportManager();
			code = mock(GeneratedCode.class);
			context = mock(XtendFile.class);

			Resource eResource = mock(Resource.class);
			ResourceSet eResourceSet = mock(ResourceSet.class);
			Resource.Factory.Registry registry = mock(Resource.Factory.Registry.class);
			Map factoryMap = mock(Map.class);
			IJvmTypeProvider typeProvider = mock(IJvmTypeProvider.class);
			jvmType = mock(JvmType.class);

			when(code.getImportManager()).thenReturn(importManager);
			when(code.getSarlScript()).thenReturn(context);
			when(typeProvider.findTypeByName(Matchers.anyString())).thenReturn(jvmType);
			when(factoryMap.get(Matchers.any())).thenReturn(typeProvider);
			when(registry.getProtocolToFactoryMap()).thenReturn(factoryMap);
			when(eResourceSet.getResourceFactoryRegistry()).thenReturn(registry);
			when(eResource.getResourceSet()).thenReturn(eResourceSet);
			when(context.eResource()).thenReturn(eResource);
			when(context.getXtendTypes()).thenReturn(elements);
		}

		@Test
		public void nullSuperClass()  {
			SarlEvent event = gen.createEvent(code, "MyEvent", null);
			assertNotNull(event);
			assertEquals("MyEvent", event.getName());
			assertNull(event.getExtends());
			assertTrue(event.getMembers().isEmpty());
		}

		@Test
		public void behaviorSuperClass()  {
			SarlEvent event = gen.createEvent(code, "MyEvent", "io.sarl.lang.core.Event");
			assertNotNull(event);
			assertEquals("MyEvent", event.getName());
			assertNull(event.getExtends());
			assertTrue(event.getMembers().isEmpty());
		}

		@Test
		public void subbehaviorSuperClass()  {
			when(jvmType.getIdentifier()).thenReturn("foo.ecore.SubEvent");
			when(jvmType.getQualifiedName(Matchers.anyChar())).thenReturn("foo.ecore.SubEvent");
			when(jvmType.getQualifiedName()).thenReturn("foo.ecore.SubEvent");
			when(jvmType.getSimpleName()).thenReturn("SubEvent");
			//
			SarlEvent event = gen.createEvent(code, "MyEvent", "foo.ecore.SubEvent");
			//
			assertNotNull(event);
			assertEquals("MyEvent", event.getName());
			assertTypeReferenceIdentifier(event.getExtends(), "foo.ecore.SubEvent");
			assertTrue(event.getMembers().isEmpty());
		}

		@Test
		public void otherSuperClass()  {
			when(jvmType.getIdentifier()).thenReturn("foo.Foo");
			when(jvmType.getQualifiedName(Matchers.anyChar())).thenReturn("foo.Foo");
			when(jvmType.getQualifiedName()).thenReturn("foo.Foo");
			when(jvmType.getSimpleName()).thenReturn("Foo");
			//
			SarlEvent event = gen.createEvent(code, "MyEvent", "foo.Foo");
			//
			assertNotNull(event);
			assertEquals("MyEvent", event.getName());
			assertTypeReferenceIdentifier(event.getExtends(), "foo.Foo");
			assertTrue(event.getMembers().isEmpty());
		}

	}

	public static class SkillTopElement extends AbstractSarlTest {

		@Inject
		private SARLCodeGenerator gen;

		@Nullable
		private GeneratedCode code;

		@Nullable
		private XtendFile context;

		@Nullable
		private ImportManager importManager;

		@Nullable
		private EList<XtendTypeDeclaration> elements;

		@Before
		public void setUp() {
			elements = new BasicEList();
			importManager = new ImportManager();
			code = mock(GeneratedCode.class);
			context = mock(XtendFile.class);

			Resource eResource = mock(Resource.class);
			ResourceSet eResourceSet = mock(ResourceSet.class);
			Resource.Factory.Registry registry = mock(Resource.Factory.Registry.class);
			Map factoryMap = mock(Map.class);
			IJvmTypeProvider typeProvider = mock(IJvmTypeProvider.class);

			when(code.getImportManager()).thenReturn(importManager);
			when(code.getSarlScript()).thenReturn(context);
			when(typeProvider.findTypeByName(Matchers.anyString())).thenAnswer(new Answer<JvmType>() {
				@Override
				public JvmType answer(InvocationOnMock it)
						throws Throwable {
					String typeName = (String) it.getArguments()[0];
					int idx = typeName.lastIndexOf(".");
					String simpleName;
					if (idx >= 0) {
						simpleName = typeName.substring(idx + 1);
					} else {
						simpleName = typeName;
					}
					JvmType jvmType = mock(JvmType.class);
					when(jvmType.getIdentifier()).thenReturn(typeName);
					when(jvmType.getQualifiedName(Matchers.anyChar())).thenReturn(typeName);
					when(jvmType.getQualifiedName()).thenReturn(typeName);
					when(jvmType.getSimpleName()).thenReturn(simpleName);
					return jvmType;
				}
			});

			when(factoryMap.get(Matchers.any())).thenReturn(typeProvider);
			when(registry.getProtocolToFactoryMap()).thenReturn(factoryMap);
			when(eResourceSet.getResourceFactoryRegistry()).thenReturn(registry);
			when(eResource.getResourceSet()).thenReturn(eResourceSet);
			when(context.eResource()).thenReturn(eResource);
			when(context.getXtendTypes()).thenReturn(elements);
		}

		@Test
		public void nullSuperClass_noSuperInterfaces()  {
			SarlSkill skill = gen.createSkill(code, "MySkill", null, Collections.<String>emptyList());
			assertNotNull(skill);
			assertEquals("MySkill", skill.getName());
			assertNull(skill.getExtends());
			assertTrue(skill.getImplements().isEmpty());
			assertTrue(skill.getMembers().isEmpty());
		}

		@Test
		public void behaviorSuperClass_noSuperInterfaces()  {
			SarlSkill skill = gen.createSkill(code, "MySkill", "io.sarl.lang.core.Skill", Collections.<String>emptyList());
			assertNotNull(skill);
			assertEquals("MySkill", skill.getName());
			assertNull(skill.getExtends());
			assertTrue(skill.getImplements().isEmpty());
			assertTrue(skill.getMembers().isEmpty());
		}

		@Test
		public void subbehaviorSuperClass_noSuperInterface()  {
			SarlSkill skill = gen.createSkill(code, "MySkill", "foo.ecore.SubSkill", Collections.<String>emptyList());
			//
			assertNotNull(skill);
			assertEquals("MySkill", skill.getName());
			assertTypeReferenceIdentifier(skill.getExtends(), "foo.ecore.SubSkill");
			assertTrue(skill.getImplements().isEmpty());
			assertTrue(skill.getMembers().isEmpty());
		}

		@Test
		public void otherSuperClass_noSuperInterface()  {
			SarlSkill skill = gen.createSkill(code, "MySkill", "foo.Foo", Collections.<String>emptyList());
			//
			assertNotNull(skill);
			assertEquals("MySkill", skill.getName());
			assertTypeReferenceIdentifier(skill.getExtends(), "foo.Foo");
			assertTrue(skill.getImplements().isEmpty());
			assertTrue(skill.getMembers().isEmpty());
		}

		@Test
		public void nullSuperClass_oneSuperInterfaces()  {
			SarlSkill skill = gen.createSkill(code, "MySkill", null, Collections.singleton("foo.ecore.SubCapacity"));
			assertNotNull(skill);
			assertEquals("MySkill", skill.getName());
			assertNull(skill.getExtends());
			assertTypeReferenceIdentifiers(skill.getImplements(), "foo.ecore.SubCapacity");
			assertTrue(skill.getMembers().isEmpty());
		}

		@Test
		public void behaviorSuperClass_oneSuperInterfaces()  {
			SarlSkill skill = gen.createSkill(code, "MySkill", "io.sarl.lang.core.Skill", Collections.singleton("foo.ecore.SubCapacity"));
			assertNotNull(skill);
			assertEquals("MySkill", skill.getName());
			assertNull(skill.getExtends());
			assertTypeReferenceIdentifiers(skill.getImplements(), "foo.ecore.SubCapacity");
			assertTrue(skill.getMembers().isEmpty());
		}

		@Test
		public void subbehaviorSuperClass_oneSuperInterface()  {
			SarlSkill skill = gen.createSkill(code, "MySkill", "foo.ecore.SubSkill", Collections.singleton("foo.ecore.SubCapacity"));
			//
			assertNotNull(skill);
			assertEquals("MySkill", skill.getName());
			assertTypeReferenceIdentifier(skill.getExtends(), "foo.ecore.SubSkill");
			assertTypeReferenceIdentifiers(skill.getImplements(), "foo.ecore.SubCapacity");
			assertTrue(skill.getMembers().isEmpty());
		}

		@Test
		public void otherSuperClass_oneSuperInterface()  {
			SarlSkill skill = gen.createSkill(code, "MySkill", "foo.Foo", Collections.singleton("foo.ecore.SubCapacity"));
			//
			assertNotNull(skill);
			assertEquals("MySkill", skill.getName());
			assertTypeReferenceIdentifier(skill.getExtends(), "foo.Foo");
			assertTypeReferenceIdentifiers(skill.getImplements(), "foo.ecore.SubCapacity");
			assertTrue(skill.getMembers().isEmpty());
		}

		@Test
		public void nullSuperClass_twoSuperInterfaces()  {
			SarlSkill skill = gen.createSkill(code, "MySkill", null, Arrays.asList("foo.ecore.SubCapacity", "foo.ecore.SubCapacity2"));
			assertNotNull(skill);
			assertEquals("MySkill", skill.getName());
			assertNull(skill.getExtends());
			assertTypeReferenceIdentifiers(skill.getImplements(), "foo.ecore.SubCapacity", "foo.ecore.SubCapacity2");
			assertTrue(skill.getMembers().isEmpty());
		}

		@Test
		public void behaviorSuperClass_twoSuperInterfaces()  {
			SarlSkill skill = gen.createSkill(code, "MySkill", "io.sarl.lang.core.Skill", Arrays.asList("foo.ecore.SubCapacity", "foo.ecore.SubCapacity2"));
			assertNotNull(skill);
			assertEquals("MySkill", skill.getName());
			assertNull(skill.getExtends());
			assertTypeReferenceIdentifiers(skill.getImplements(), "foo.ecore.SubCapacity", "foo.ecore.SubCapacity2");
			assertTrue(skill.getMembers().isEmpty());
		}

		@Test
		public void subbehaviorSuperClass_twoSuperInterface()  {
			SarlSkill skill = gen.createSkill(code, "MySkill", "foo.ecore.SubSkill", Arrays.asList("foo.ecore.SubCapacity", "foo.ecore.SubCapacity2"));
			//
			assertNotNull(skill);
			assertEquals("MySkill", skill.getName());
			assertTypeReferenceIdentifier(skill.getExtends(), "foo.ecore.SubSkill");
			assertTypeReferenceIdentifiers(skill.getImplements(), "foo.ecore.SubCapacity", "foo.ecore.SubCapacity2");
			assertTrue(skill.getMembers().isEmpty());
		}

		@Test
		public void otherSuperClass_twoSuperInterface()  {
			SarlSkill skill = gen.createSkill(code, "MySkill", "foo.Foo", Arrays.asList("foo.ecore.SubCapacity", "foo.ecore.SubCapacity2"));
			//
			assertNotNull(skill);
			assertEquals("MySkill", skill.getName());
			assertTypeReferenceIdentifier(skill.getExtends(), "foo.Foo");
			assertTypeReferenceIdentifiers(skill.getImplements(), "foo.ecore.SubCapacity", "foo.ecore.SubCapacity2");
			assertTrue(skill.getMembers().isEmpty());
		}

	}

	public static class AgentFeatures extends AbstractSarlTest {

		@Inject
		private SARLCodeGenerator gen;

		@Nullable
		private GeneratedCode code;

		@Nullable
		private EList<XtendMember> features;

		@Nullable
		private XBlockExpression block;

		@Nullable
		private SarlAgent agent;

		@Nullable
		private ImportManager importManager;

		@Before
		public void setUp() {
			importManager = new ImportManager();
			features = new BasicEList();
			code = mock(GeneratedCode.class);
			agent = mock(SarlAgent.class);
			block = XbaseFactory.eINSTANCE.createXBlockExpression();

			Resource eResource = mock(Resource.class);
			ResourceSet eResourceSet = mock(ResourceSet.class);
			Resource.Factory.Registry registry = mock(Resource.Factory.Registry.class);
			Map factoryMap = mock(Map.class);
			IJvmTypeProvider typeProvider = mock(IJvmTypeProvider.class);

			when(typeProvider.findTypeByName(Matchers.anyString())).thenAnswer(new Answer<JvmType>() {
				@Override
				public JvmType answer(InvocationOnMock it) throws Throwable {
					String typeName = (String) it.getArguments()[0];
					int idx = typeName.lastIndexOf(".");
					String simpleName;
					if (idx >= 0) {
						simpleName = typeName.substring(idx + 1);
					} else {
						simpleName = typeName;
					}
					JvmType jvmType = mock(JvmType.class);
					when(jvmType.getIdentifier()).thenReturn(typeName);
					when(jvmType.getQualifiedName(Matchers.anyChar())).thenReturn(typeName);
					when(jvmType.getQualifiedName()).thenReturn(typeName);
					when(jvmType.getSimpleName()).thenReturn(simpleName);
					return jvmType;
				}
			});

			when(factoryMap.get(Matchers.any())).thenReturn(typeProvider);
			when(registry.getProtocolToFactoryMap()).thenReturn(factoryMap);
			when(eResourceSet.getResourceFactoryRegistry()).thenReturn(registry);
			when(eResource.getResourceSet()).thenReturn(eResourceSet);
			when(agent.eResource()).thenReturn(eResource);
			when(agent.getMembers()).thenReturn(features);
			when(code.getImportManager()).thenReturn(importManager);
		}

		@Test
		public void returnNull()  {
			SarlAction action = gen.createAction(code, agent, "myFct", null, block);
			//
			assertNotNull(action);
			assertEquals("myFct", action.getName());
			assertNull(action.getReturnType());
			assertTrue(action.getParameters().isEmpty());
			assertSame(block, action.getExpression());
		}

		@Test
		public void returnBoolean()  {
			SarlAction action = gen.createAction(code, agent, "myFct", "boolean", block);
			//
			assertNotNull(action);
			assertEquals("myFct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "boolean");
			assertTrue(action.getParameters().isEmpty());
			assertSame(block, action.getExpression());
		}

		@Test
		public void returnObject()  {
			SarlAction action = gen.createAction(code, agent, "myFct", "java.lang.String", block);
			//
			assertNotNull(action);
			assertEquals("myFct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "java.lang.String");
			assertTrue(action.getParameters().isEmpty());
			assertSame(block, action.getExpression());
		}

		@Test
		public void createConstructor()  {
			XtendConstructor constructor = gen.createConstructor(code, agent, block);
			//
			assertNotNull(constructor);
			assertTrue(constructor.getParameters().isEmpty());
			assertSame(block, constructor.getExpression());
		}

		@Test
		public void createBehaviorUnit_noGuard()  {
			SarlBehaviorUnit unit = gen.createBehaviorUnit(code, agent, "foo.ecore.SubEvent", null, block);
			//
			assertNotNull(unit);
			assertTypeReferenceIdentifier(unit.getName(), "foo.ecore.SubEvent");
			assertNull(unit.getGuard());
			assertSame(block, unit.getExpression());
		}

		@Test
		public void createBehaviorUnit_aGuard()  {
			XBlockExpression guard = XbaseFactory.eINSTANCE.createXBlockExpression();
			//
			SarlBehaviorUnit unit = gen.createBehaviorUnit(code, agent, "foo.ecore.SubEvent", guard, block);
			//
			assertNotNull(unit);
			assertTypeReferenceIdentifier(unit.getName(), "foo.ecore.SubEvent");
			assertSame(guard, unit.getGuard());
			assertSame(block, unit.getExpression());
		}

		@Test(expected = IllegalArgumentException.class)
		public void createVariableGeneratedCodeFeatureContainerStringString_nullType()  {
			gen.createVariable(code, agent, "myVar", (String) null);
		}

		@Test(expected = IllegalArgumentException.class)
		public void createVariableGeneratedCodeFeatureContainerStringString_nullExpression()  {
			gen.createVariable(code, agent, "myVar", (XExpression) null);
		}

		@Test
		public void createVariableGeneratedCodeFeatureContainerStringString()  {
			XtendField variable = gen.createVariable(code, agent, "myVar", "java.lang.String");
			//
			assertNotNull(variable);
			assertEquals("myVar", variable.getName());
			assertTypeReferenceIdentifier(variable.getType(), "java.lang.String");
			assertNull(variable.getInitialValue());
			assertFalse(variable.isFinal());
		}

		@Test
		public void createVariableGeneratedCodeFeatureContainerStringXExpression()  {
			XNumberLiteral numberLiteral = XbaseFactory.eINSTANCE.createXNumberLiteral();
			numberLiteral.setValue("2.3f"); //$NON-NLS-1$
			//
			XtendField variable = gen.createVariable(code, agent, "myVar", numberLiteral);
			//
			assertNotNull(variable);
			assertEquals("myVar", variable.getName());
			assertNull(variable.getType());
			assertSame(numberLiteral, variable.getInitialValue());
			assertFalse(variable.isFinal());
		}

		@Test(expected = IllegalArgumentException.class)
		public void createValueGeneratedCodeFeatureContainerStringString_nullType()  {
			gen.createValue(code, agent, "myConst", (String) null);
		}

		@Test(expected = IllegalArgumentException.class)
		public void createValueGeneratedCodeFeatureContainerStringString_nullExpression()  {
			gen.createValue(code, agent, "myConst", (XExpression) null);
		}

		@Test
		public void createValueGeneratedCodeFeatureContainerStringString()  {
			XtendField value = gen.createValue(code, agent, "myConst", "java.lang.String");
			//
			assertNotNull(value);
			assertEquals("myConst", value.getName());
			assertTypeReferenceIdentifier(value.getType(), "java.lang.String");
			assertNull(value.getInitialValue());
			assertTrue(value.isFinal());
		}

		@Test
		public void createValueGeneratedCodeFeatureContainerStringXExpression()  {
			XNumberLiteral  numberLiteral = XbaseFactory.eINSTANCE.createXNumberLiteral();
			numberLiteral.setValue("2.3f"); //$NON-NLS-1$
			//
			XtendField value = gen.createValue(code, agent, "myConst", numberLiteral);
			//
			assertNotNull(value);
			assertEquals("myConst", value.getName());
			assertNull(value.getType());
			assertSame(numberLiteral, value.getInitialValue());
			assertTrue(value.isFinal());
		}

	}

	public static class BehaviorFeatures extends AbstractSarlTest {

		@Inject
		private SARLCodeGenerator gen;

		@Nullable
		private GeneratedCode code;

		@Nullable
		private EList<XtendMember> features;

		@Nullable
		private XBlockExpression block;

		@Nullable
		private SarlBehavior behavior;

		@Nullable
		private ImportManager importManager;

		@Before
		public void setUp() {
			importManager = new ImportManager();
			features = new BasicEList();
			code = mock(GeneratedCode.class);
			behavior = mock(SarlBehavior.class);
			block = XbaseFactory.eINSTANCE.createXBlockExpression();

			Resource eResource = mock(Resource.class);
			ResourceSet eResourceSet = mock(ResourceSet.class);
			Resource.Factory.Registry registry = mock(Resource.Factory.Registry.class);
			Map factoryMap = mock(Map.class);
			IJvmTypeProvider typeProvider = mock(IJvmTypeProvider.class);

			when(typeProvider.findTypeByName(Matchers.anyString())).thenAnswer(new Answer<JvmType>() {
				@Override
				public JvmType answer(InvocationOnMock it) throws Throwable {
					String typeName = (String) it.getArguments()[0];
					int idx = typeName.lastIndexOf(".");
					String simpleName;
					if (idx >= 0) {
						simpleName = typeName.substring(idx + 1);
					} else {
						simpleName = typeName;
					}
					JvmType jvmType = mock(JvmType.class);
					when(jvmType.getIdentifier()).thenReturn(typeName);
					when(jvmType.getQualifiedName(Matchers.anyChar())).thenReturn(typeName);
					when(jvmType.getQualifiedName()).thenReturn(typeName);
					when(jvmType.getSimpleName()).thenReturn(simpleName);
					return jvmType;
				}
			});
			when(factoryMap.get(Matchers.any())).thenReturn(typeProvider);
			when(registry.getProtocolToFactoryMap()).thenReturn(factoryMap);
			when(eResourceSet.getResourceFactoryRegistry()).thenReturn(registry);
			when(eResource.getResourceSet()).thenReturn(eResourceSet);
			when(behavior.eResource()).thenReturn(eResource);
			when(behavior.getMembers()).thenReturn(features);
			when(code.getImportManager()).thenReturn(importManager);
		}

		@Test
		public void returnNull()  {
			SarlAction action = gen.createAction(code, behavior, "myFct", null, block);
			//
			assertNotNull(action);
			assertEquals("myFct", action.getName());
			assertNull(action.getReturnType());
			assertTrue(action.getParameters().isEmpty());
			assertSame(block, action.getExpression());
		}

		@Test
		public void returnBoolean()  {
			SarlAction action = gen.createAction(code, behavior, "myFct", "boolean", block);
			//
			assertNotNull(action);
			assertEquals("myFct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "boolean");
			assertTrue(action.getParameters().isEmpty());
			assertSame(block, action.getExpression());
		}

		@Test
		public void returnObject()  {
			SarlAction action = gen.createAction(code, behavior, "myFct", "java.lang.String", block);
			//
			assertNotNull(action);
			assertEquals("myFct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "java.lang.String");
			assertTrue(action.getParameters().isEmpty());
			assertSame(block, action.getExpression());
		}

		@Test
		public void createConstructor()  {
			XtendConstructor constructor = gen.createConstructor(code, behavior, block);
			//
			assertNotNull(constructor);
			assertTrue(constructor.getParameters().isEmpty());
			assertSame(block, constructor.getExpression());
		}

		@Test
		public void createBehaviorUnit_noGuard()  {
			SarlBehaviorUnit unit = gen.createBehaviorUnit(code, behavior, "foo.ecore.SubEvent", null, block);
			//
			assertNotNull(unit);
			assertTypeReferenceIdentifier(unit.getName(), "foo.ecore.SubEvent");
			assertNull(unit.getGuard());
			assertSame(block, unit.getExpression());
		}

		@Test
		public void createBehaviorUnit_aGuard()  {
			XBlockExpression guard = XbaseFactory.eINSTANCE.createXBlockExpression();
			//
			SarlBehaviorUnit unit = gen.createBehaviorUnit(code, behavior, "foo.ecore.SubEvent", guard, block);
			//
			assertNotNull(unit);
			assertTypeReferenceIdentifier(unit.getName(), "foo.ecore.SubEvent");
			assertSame(guard, unit.getGuard());
			assertSame(block, unit.getExpression());
		}

		@Test(expected = IllegalArgumentException.class)
		public void createVariableGeneratedCodeFeatureContainerStringString_nullType()  {
			gen.createVariable(code, behavior, "myVar", (String) null);
		}

		@Test(expected = IllegalArgumentException.class)
		public void createVariableGeneratedCodeFeatureContainerStringString_nullExpression()  {
			gen.createVariable(code, behavior, "myVar", (XExpression) null);
		}

		@Test
		public void createVariableGeneratedCodeFeatureContainerStringString()  {
			XtendField variable = gen.createVariable(code, behavior, "myVar", "java.lang.String");
			//
			assertNotNull(variable);
			assertEquals("myVar", variable.getName());
			assertTypeReferenceIdentifier(variable.getType(), "java.lang.String");
			assertNull(variable.getInitialValue());
			assertFalse(variable.isFinal());
		}

		@Test
		public void createVariableGeneratedCodeFeatureContainerStringXExpression()  {
			XNumberLiteral numberLiteral = XbaseFactory.eINSTANCE.createXNumberLiteral();
			numberLiteral.setValue("2.3f"); //$NON-NLS-1$
			//
			XtendField variable = gen.createVariable(code, behavior, "myVar", numberLiteral);
			//
			assertNotNull(variable);
			assertEquals("myVar", variable.getName());
			assertNull(variable.getType());
			assertSame(numberLiteral, variable.getInitialValue());
			assertFalse(variable.isFinal());
		}

		@Test(expected = IllegalArgumentException.class)
		public void createValueGeneratedCodeFeatureContainerStringString_nullType()  {
			gen.createValue(code, behavior, "myConst", (String) null);
		}

		@Test(expected = IllegalArgumentException.class)
		public void createValueGeneratedCodeFeatureContainerStringString_nullExpression()  {
			gen.createValue(code, behavior, "myConst", (XExpression) null);
		}

		@Test
		public void createValueGeneratedCodeFeatureContainerStringString()  {
			XtendField value = gen.createValue(code, behavior, "myConst", "java.lang.String");
			//
			assertNotNull(value);
			assertEquals("myConst", value.getName());
			assertTypeReferenceIdentifier(value.getType(), "java.lang.String");
			assertNull(value.getInitialValue());
			assertTrue(value.isFinal());
		}

		@Test
		public void createValueGeneratedCodeFeatureContainerStringXExpression()  {
			XNumberLiteral numberLiteral = XbaseFactory.eINSTANCE.createXNumberLiteral();
			numberLiteral.setValue("2.3f"); //$NON-NLS-1$
			//
			XtendField value = gen.createValue(code, behavior, "myConst", numberLiteral);
			//
			assertNotNull(value);
			assertEquals("myConst", value.getName());
			assertNull(value.getType());
			assertSame(numberLiteral, value.getInitialValue());
			assertTrue(value.isFinal());
		}

	}

	public static class CapacityFeatures extends AbstractSarlTest {

		@Inject
		private SARLCodeGenerator gen;

		@Nullable
		private GeneratedCode code;

		@Nullable
		private EList<XtendMember> features;

		@Nullable
		private SarlCapacity capacity;

		@Nullable
		private ImportManager importManager;

		@Before
		public void setUp() {
			importManager = new ImportManager();
			features = new BasicEList();
			code = mock(GeneratedCode.class);
			capacity = mock(SarlCapacity.class);

			Resource eResource = mock(Resource.class);
			ResourceSet eResourceSet = mock(ResourceSet.class);
			Resource.Factory.Registry registry = mock(Resource.Factory.Registry.class);
			Map factoryMap = mock(Map.class);
			IJvmTypeProvider typeProvider = mock(IJvmTypeProvider.class);

			when(typeProvider.findTypeByName(Matchers.anyString())).thenAnswer(new Answer<JvmType>(){
				@Override
				public JvmType answer(InvocationOnMock it) throws Throwable {
					String typeName = (String) it.getArguments()[0];
					int idx = typeName.lastIndexOf(".");
					String simpleName;
					if (idx >= 0) {
						simpleName = typeName.substring(idx + 1);
					} else {
						simpleName = typeName;
					}
					JvmType jvmType = mock(JvmType.class);
					when(jvmType.getIdentifier()).thenReturn(typeName);
					when(jvmType.getQualifiedName(Matchers.anyChar())).thenReturn(typeName);
					when(jvmType.getQualifiedName()).thenReturn(typeName);
					when(jvmType.getSimpleName()).thenReturn(simpleName);
					return jvmType;
				}
			});
			when(factoryMap.get(Matchers.any())).thenReturn(typeProvider);
			when(registry.getProtocolToFactoryMap()).thenReturn(factoryMap);
			when(eResourceSet.getResourceFactoryRegistry()).thenReturn(registry);
			when(eResource.getResourceSet()).thenReturn(eResourceSet);
			when(capacity.eResource()).thenReturn(eResource);
			when(capacity.getMembers()).thenReturn(features);
			when(code.getImportManager()).thenReturn(importManager);
		}

		@Test
		public void returnNull()  {
			SarlAction action = gen.createAction(code, capacity, "myFct", null, null);
			//
			assertNotNull(action);
			assertEquals("myFct", action.getName());
			assertNull(action.getReturnType());
			assertTrue(action.getParameters().isEmpty());
			assertNull(action.getExpression());
		}

		@Test
		public void returnBoolean()  {
			SarlAction action = gen.createAction(code, capacity, "myFct", "boolean", null);
			//
			assertNotNull(action);
			assertEquals("myFct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "boolean");
			assertTrue(action.getParameters().isEmpty());
			assertNull(action.getExpression());
		}

		@Test
		public void returnObject()  {
			SarlAction action = gen.createAction(code, capacity, "myFct", "java.lang.String", null);
			//
			assertNotNull(action);
			assertEquals("myFct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "java.lang.String");
			assertTrue(action.getParameters().isEmpty());
			assertNull(action.getExpression());
		}

	}

	public static class EventFeatures extends AbstractSarlTest {

		@Inject
		private SARLCodeGenerator gen;

		@Nullable
		private GeneratedCode code;

		@Nullable
		private EList<XtendMember> features;

		@Nullable
		private XBlockExpression block;

		@Nullable
		private SarlEvent event;

		@Nullable
		private ImportManager importManager;

		@Before
		public void setUp() {
			importManager = new ImportManager();
			features = new BasicEList();
			code = mock(GeneratedCode.class);
			event = mock(SarlEvent.class);
			block = XbaseFactory.eINSTANCE.createXBlockExpression();

			Resource eResource = mock(Resource.class);
			ResourceSet eResourceSet = mock(ResourceSet.class);
			Resource.Factory.Registry registry = mock(Resource.Factory.Registry.class);
			Map factoryMap = mock(Map.class);
			IJvmTypeProvider typeProvider = mock(IJvmTypeProvider.class);

			when(typeProvider.findTypeByName(Matchers.anyString())).thenAnswer(new Answer<JvmType>() {
				@Override
				public JvmType answer(InvocationOnMock it) throws Throwable {
					String typeName = (String) it.getArguments()[0];
					int idx = typeName.lastIndexOf(".");
					String simpleName;
					if (idx >= 0) {
						simpleName = typeName.substring(idx + 1);
					} else {
						simpleName = typeName;
					}
					JvmType jvmType = mock(JvmType.class);
					when(jvmType.getIdentifier()).thenReturn(typeName);
					when(jvmType.getQualifiedName(Matchers.anyChar())).thenReturn(typeName);
					when(jvmType.getQualifiedName()).thenReturn(typeName);
					when(jvmType.getSimpleName()).thenReturn(simpleName);
					return jvmType;
				}
			});
			when(factoryMap.get(Matchers.any())).thenReturn(typeProvider);
			when(registry.getProtocolToFactoryMap()).thenReturn(factoryMap);
			when(eResourceSet.getResourceFactoryRegistry()).thenReturn(registry);
			when(eResource.getResourceSet()).thenReturn(eResourceSet);
			when(event.eResource()).thenReturn(eResource);
			when(event.getMembers()).thenReturn(features);
			when(code.getImportManager()).thenReturn(importManager);
		}

		@Test
		public void createConstructor()  {
			XtendConstructor constructor = gen.createConstructor(code, event, block);
			//
			assertNotNull(constructor);
			assertTrue(constructor.getParameters().isEmpty());
			assertSame(block, constructor.getExpression());
		}

		@Test(expected = IllegalArgumentException.class)
		public void createVariableGeneratedCodeFeatureContainerStringString_nullType()  {
			gen.createVariable(code, event, "myVar", (String) null);
		}

		@Test(expected = IllegalArgumentException.class)
		public void createVariableGeneratedCodeFeatureContainerStringString_nullExpression()  {
			gen.createVariable(code, event, "myVar", (XExpression) null);
		}

		@Test
		public void createVariableGeneratedCodeFeatureContainerStringString()  {
			XtendField variable = gen.createVariable(code, event, "myVar", "java.lang.String");
			//
			assertNotNull(variable);
			assertEquals("myVar", variable.getName());
			assertTypeReferenceIdentifier(variable.getType(), "java.lang.String");
			assertNull(variable.getInitialValue());
			assertFalse(variable.isFinal());
		}

		@Test
		public void createVariableGeneratedCodeFeatureContainerStringXExpression()  {
			XNumberLiteral numberLiteral = XbaseFactory.eINSTANCE.createXNumberLiteral();
			numberLiteral.setValue("2.3f"); //$NON-NLS-1$
			//
			XtendField variable = gen.createVariable(code, event, "myVar", numberLiteral);
			//
			assertNotNull(variable);
			assertEquals("myVar", variable.getName());
			assertNull(variable.getType());
			assertSame(numberLiteral, variable.getInitialValue());
			assertFalse(variable.isFinal());
		}

		@Test(expected = IllegalArgumentException.class)
		public void createValueGeneratedCodeFeatureContainerStringString_nullType()  {
			gen.createValue(code, event, "myConst", (String) null);
		}

		@Test(expected = IllegalArgumentException.class)
		public void createValueGeneratedCodeFeatureContainerStringString_nullExpression()  {
			gen.createValue(code, event, "myConst", (XExpression) null);
		}

		@Test
		public void createValueGeneratedCodeFeatureContainerStringString()  {
			XtendField value = gen.createValue(code, event, "myConst", "java.lang.String");
			//
			assertNotNull(value);
			assertEquals("myConst", value.getName());
			assertTypeReferenceIdentifier(value.getType(), "java.lang.String");
			assertNull(value.getInitialValue());
			assertTrue(value.isFinal());
		}

		@Test
		public void createValueGeneratedCodeFeatureContainerStringXExpression()  {
			XNumberLiteral numberLiteral = XbaseFactory.eINSTANCE.createXNumberLiteral();
			numberLiteral.setValue("2.3f"); //$NON-NLS-1$
			//
			XtendField value = gen.createValue(code, event, "myConst", numberLiteral);
			//
			assertNotNull(value);
			assertEquals("myConst", value.getName());
			assertNull(value.getType());
			assertSame(numberLiteral, value.getInitialValue());
			assertTrue(value.isFinal());
		}

	}

	public static class SkillFeatures extends AbstractSarlTest {

		@Inject
		private SARLCodeGenerator gen;

		@Nullable
		private GeneratedCode code;

		@Nullable
		private EList<XtendMember> features;

		@Nullable
		private XBlockExpression block;

		@Nullable
		private SarlSkill skill;

		@Nullable
		private ImportManager importManager;

		@Before
		public void setUp() {
			importManager = new ImportManager();
			features = new BasicEList();
			code = mock(GeneratedCode.class);
			skill = mock(SarlSkill.class);
			block = XbaseFactory.eINSTANCE.createXBlockExpression();

			Resource eResource = mock(Resource.class);
			ResourceSet eResourceSet = mock(ResourceSet.class);
			Resource.Factory.Registry registry = mock(Resource.Factory.Registry.class);
			Map factoryMap = mock(Map.class);
			IJvmTypeProvider typeProvider = mock(IJvmTypeProvider.class);

			when(typeProvider.findTypeByName(Matchers.anyString())).thenAnswer(new Answer<JvmType>() {
				@Override
				public JvmType answer(InvocationOnMock it) throws Throwable {
					String typeName = (String) it.getArguments()[0];
					int idx = typeName.lastIndexOf(".");
					String simpleName;
					if (idx >= 0) {
						simpleName = typeName.substring(idx + 1);
					} else {
						simpleName = typeName;
					}
					JvmType jvmType = mock(JvmType.class);
					when(jvmType.getIdentifier()).thenReturn(typeName);
					when(jvmType.getQualifiedName(Matchers.anyChar())).thenReturn(typeName);
					when(jvmType.getQualifiedName()).thenReturn(typeName);
					when(jvmType.getSimpleName()).thenReturn(simpleName);
					return jvmType;

				}
			});
			when(factoryMap.get(Matchers.any())).thenReturn(typeProvider);
			when(registry.getProtocolToFactoryMap()).thenReturn(factoryMap);
			when(eResourceSet.getResourceFactoryRegistry()).thenReturn(registry);
			when(eResource.getResourceSet()).thenReturn(eResourceSet);
			when(skill.eResource()).thenReturn(eResource);
			when(skill.getMembers()).thenReturn(features);
			when(code.getImportManager()).thenReturn(importManager);
		}

		@Test
		public void returnNull()  {
			SarlAction action = gen.createAction(code, skill, "myFct", null, block);
			//
			assertNotNull(action);
			assertEquals("myFct", action.getName());
			assertNull(action.getReturnType());
			assertTrue(action.getParameters().isEmpty());
			assertSame(block, action.getExpression());
		}

		@Test
		public void returnBoolean()  {
			SarlAction action = gen.createAction(code, skill, "myFct", "boolean", block);
			//
			assertNotNull(action);
			assertEquals("myFct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "boolean");
			assertTrue(action.getParameters().isEmpty());
			assertSame(block, action.getExpression());
		}

		@Test
		public void returnObject()  {
			SarlAction action = gen.createAction(code, skill, "myFct", "java.lang.String", block);
			//
			assertNotNull(action);
			assertEquals("myFct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "java.lang.String");
			assertTrue(action.getParameters().isEmpty());
			assertSame(block, action.getExpression());
		}

		@Test
		public void createConstructor()  {
			XtendConstructor constructor = gen.createConstructor(code, skill, block);
			//
			assertNotNull(constructor);
			assertTrue(constructor.getParameters().isEmpty());
			assertSame(block, constructor.getExpression());
		}

		@Test(expected = IllegalArgumentException.class)
		public void createVariableGeneratedCodeFeatureContainerStringString_nullType()  {
			gen.createVariable(code, skill, "myVar", (String) null);
		}

		@Test(expected = IllegalArgumentException.class)
		public void createVariableGeneratedCodeFeatureContainerStringString_nullExpression()  {
			gen.createVariable(code, skill, "myVar", (XExpression) null);
		}

		@Test
		public void createVariableGeneratedCodeFeatureContainerStringString()  {
			XtendField variable = gen.createVariable(code, skill, "myVar", "java.lang.String");
			//
			assertNotNull(variable);
			assertEquals("myVar", variable.getName());
			assertTypeReferenceIdentifier(variable.getType(), "java.lang.String");
			assertNull(variable.getInitialValue());
			assertFalse(variable.isFinal());
		}

		@Test
		public void createVariableGeneratedCodeFeatureContainerStringXExpression()  {
			XNumberLiteral numberLiteral = XbaseFactory.eINSTANCE.createXNumberLiteral();
			numberLiteral.setValue("2.3f"); //$NON-NLS-1$
			//
			XtendField variable = gen.createVariable(code, skill, "myVar", numberLiteral);
			//
			assertNotNull(variable);
			assertEquals("myVar", variable.getName());
			assertNull(variable.getType());
			assertSame(numberLiteral, variable.getInitialValue());
			assertFalse(variable.isFinal());
		}

		@Test(expected = IllegalArgumentException.class)
		public void createValueGeneratedCodeFeatureContainerStringString_nullType()  {
			gen.createValue(code, skill, "myConst", (String) null);
		}

		@Test(expected = IllegalArgumentException.class)
		public void createValueGeneratedCodeFeatureContainerStringString_nullExpression()  {
			gen.createValue(code, skill, "myConst", (XExpression) null);
		}

		@Test
		public void createValueGeneratedCodeFeatureContainerStringString()  {
			XtendField value = gen.createValue(code, skill, "myConst", "java.lang.String");
			//
			assertNotNull(value);
			assertEquals("myConst", value.getName());
			assertTypeReferenceIdentifier(value.getType(), "java.lang.String");
			assertNull(value.getInitialValue());
			assertTrue(value.isFinal());
		}

		@Test
		public void createValueGeneratedCodeFeatureContainerStringXExpression()  {
			XNumberLiteral numberLiteral = XbaseFactory.eINSTANCE.createXNumberLiteral();
			numberLiteral.setValue("2.3f"); //$NON-NLS-1$
			//
			XtendField value = gen.createValue(code, skill, "myConst", numberLiteral);
			//
			assertNotNull(value);
			assertEquals("myConst", value.getName());
			assertNull(value.getType());
			assertSame(numberLiteral, value.getInitialValue());
			assertTrue(value.isFinal());
		}

	}

	public static class Expressions extends AbstractSarlTest {

		@Inject
		private SARLCodeGenerator gen;

		@Mock
		private GeneratedCode code;

		@Mock
		private ResourceSet eResourceSet;

		@Inject
		private ImportManager importManager;

		@Before
		public void setUp() {
			Resource eResource = mock(Resource.class);
			ResourceSet eResourceSet = mock(ResourceSet.class);
			Resource.Factory.Registry registry = mock(Resource.Factory.Registry.class);
			Map factoryMap = mock(Map.class);
			IJvmTypeProvider typeProvider = mock(IJvmTypeProvider.class);

			when(typeProvider.findTypeByName(Matchers.anyString())).thenAnswer(new Answer<JvmType>() {
				@Override
				public JvmType answer(InvocationOnMock it) throws Throwable {
					String typeName = (String) it.getArguments()[0];
					int idx = typeName.lastIndexOf(".");
					String simpleName;
					if (idx >= 0) {
						simpleName = typeName.substring(idx + 1);
					} else {
						simpleName = typeName;
					}
					JvmType jvmType = mock(JvmType.class);
					when(jvmType.getIdentifier()).thenReturn(typeName);
					when(jvmType.getQualifiedName(Matchers.anyChar())).thenReturn(typeName);
					when(jvmType.getQualifiedName()).thenReturn(typeName);
					when(jvmType.getSimpleName()).thenReturn(simpleName);
					return jvmType;
				}
			});
			when(factoryMap.get(Matchers.any())).thenReturn(typeProvider);
			when(registry.getProtocolToFactoryMap()).thenReturn(factoryMap);
			when(eResourceSet.getResourceFactoryRegistry()).thenReturn(registry);
			when(eResource.getResourceSet()).thenReturn(eResourceSet);
		}

		@Test
		public void createXExpression_null()  {
			XExpression expr = gen.createXExpression(null, eResourceSet, this.importManager);
			//
			assertNull(expr);
			assertImports(this.importManager);
		}

		@Test
		public void createXExpression_empty()  {
			XExpression expr = gen.createXExpression("", eResourceSet, this.importManager);
			//
			assertNull(expr);
			assertImports(this.importManager);
		}

	}

	public static class FormalParameters extends AbstractSarlTest {

		@Inject
		private SARLCodeGenerator gen;

		@Nullable
		private GeneratedCode code;

		@Nullable
		private EObject context;

		@Nullable
		private XtendExecutable container;

		@Nullable
		private ImportManager importManager;

		@Nullable
		private EList<Resource> resources;

		@Before
		public void setUp() {
			importManager = new ImportManager();
			code = mock(GeneratedCode.class);
			context = mock(EObject.class);
			container = mock(XtendExecutable.class);

			Resource eResource = mock(Resource.class);
			ResourceSet eResourceSet = mock(ResourceSet.class);
			Resource.Factory.Registry registry = mock(Resource.Factory.Registry.class);
			Map factoryMap = mock(Map.class);
			IJvmTypeProvider typeProvider = mock(IJvmTypeProvider.class);

			when(typeProvider.findTypeByName(Matchers.anyString())).thenAnswer(new Answer<JvmType>() {
				@Override
				public JvmType answer(InvocationOnMock it) throws Throwable {
					String typeName = (String) it.getArguments()[0];
					int idx = typeName.lastIndexOf(".");
					String simpleName;
					if (idx >= 0) {
						simpleName = typeName.substring(idx + 1);
					} else {
						simpleName = typeName;
					}
					JvmType jvmType = mock(JvmType.class);
					when(jvmType.getIdentifier()).thenReturn(typeName);
					when(jvmType.getQualifiedName(Matchers.anyChar())).thenReturn(typeName);
					when(jvmType.getQualifiedName()).thenReturn(typeName);
					when(jvmType.getSimpleName()).thenReturn(simpleName);
					return jvmType;
				}
			});
			when(factoryMap.get(Matchers.any())).thenReturn(typeProvider);
			when(registry.getProtocolToFactoryMap()).thenReturn(factoryMap);
			when(eResourceSet.getResourceFactoryRegistry()).thenReturn(registry);
			when(eResourceSet.getResource(Matchers.any(URI.class), Matchers.anyBoolean())).thenReturn(null);
			when(eResourceSet.getResources()).thenReturn(resources);
			when(eResource.getResourceSet()).thenReturn(eResourceSet);
			when(container.eResource()).thenReturn(eResource);
			when(container.getParameters()).thenReturn(new BasicEList());
			when(code.getImportManager()).thenReturn(importManager);
			when(code.getResourceSet()).thenReturn(eResourceSet);
		}

		@Test(expected = IllegalArgumentException.class)
		public void createVarArgs_nullType()  {
			gen.createVarArgs(code, container, "myParam", null);
		}

		@Test
		public void createVarArgs()  {
			SarlFormalParameter param = gen.createVarArgs(code, container, "myParam", "boolean");
			//
			assertNotNull(param);
			assertEquals("myParam", param.getName());
			assertNull(param.getDefaultValue());
			assertTypeReferenceIdentifier(param.getParameterType(), "boolean");
			//
			assertParameterNames(container.getParameters(), "myParam");
			assertParameterVarArg(container.getParameters());
		}

		@Test(expected = IllegalArgumentException.class)
		public void createFormalParameterGeneratedCodeParameterizedFeatureStringStringStringResourceSet_nullType_noDefaultValue()  {
			gen.createFormalParameter(code, container, "myParam", null, null, code.getResourceSet());
		}

		@Test(expected = IllegalArgumentException.class)
		public void createFormalParameterGeneratedCodeParameterizedFeatureStringStringStringResourceSet_nullType_defaultValue()  {
			gen.createFormalParameter(code, container, "myParam", null, "true", code.getResourceSet());
		}

		@Test
		public void createFormalParameterGeneratedCodeParameterizedFeatureStringStringStringResourceSet_noDefaultValue()  {
			SarlFormalParameter param = gen.createFormalParameter(code, container, "myParam", "java.lang.String", null, code.getResourceSet());
			//
			assertNotNull(param);
			assertEquals("myParam", param.getName());
			assertNull(param.getDefaultValue());
			assertTypeReferenceIdentifier(param.getParameterType(), "java.lang.String");
			//
			assertParameterNames(container.getParameters(), "myParam");
		}

		@Test(expected = IllegalArgumentException.class)
		public void createFormalParameterGeneratedCodeParameterizedFeatureStringStringXExpression_nullType_noDefaultValue()  {
			gen.createFormalParameter(code, container, "myParam", null, null);
		}

		@Test(expected = IllegalArgumentException.class)
		public void createFormalParameterGeneratedCodeParameterizedFeatureStringStringXExpression_nullType_defaultValue()  {
			XStringLiteral expr = XbaseFactory.eINSTANCE.createXStringLiteral();
			expr.setValue("abc");
			gen.createFormalParameter(code, container, "myParam", null, expr);
		}

		@Test
		public void createFormalParameterGeneratedCodeParameterizedFeatureStringStringXExpression_noDefaultValue()  {
			SarlFormalParameter param = gen.createFormalParameter(code, container, "myParam", "java.lang.String", null);
			//
			assertNotNull(param);
			assertEquals("myParam", param.getName());
			assertNull(param.getDefaultValue());
			assertTypeReferenceIdentifier(param.getParameterType(), "java.lang.String");
			//
			assertParameterNames(container.getParameters(), "myParam");
		}

		@Test
		public void createFormalParameterGeneratedCodeParameterizedFeatureStringStringXExpression_defaultValue()  {
			XStringLiteral expr = XbaseFactory.eINSTANCE.createXStringLiteral();
			expr.setValue("abc");
			SarlFormalParameter param = gen.createFormalParameter(code, container, "myParam", "java.lang.String", expr);
			//
			assertNotNull(param);
			assertEquals("myParam", param.getName());
			assertSame(expr, param.getDefaultValue());
			assertTypeReferenceIdentifier(param.getParameterType(), "java.lang.String");
			//
			assertParameterNames(container.getParameters(), "myParam");
		}

	}

	public static class CreateActionFromJvmElement extends AbstractSarlUiTest {

		@Inject
		private SARLCodeGenerator generator;

		/** Associator of the JVM elements and the SARL elements.
		 */
		@Inject
		protected JvmModelAssociator jvmModelAssociator;

		@Inject
		private ImportManager importManager;

		private JvmOperation createJvmFeature(String... sarlCode) throws Exception {
			String sarlFilename = generateFilename();
			XtendFile sarlScript = this.helper.createSARLScript(sarlFilename, multilineString(sarlCode));
			this.helper.waitForAutoBuild();
			EObject feature = sarlScript.getXtendTypes().get(0).getMembers().get(0);
			EObject jvmElement = this.jvmModelAssociator.getPrimaryJvmElement(feature);
			return (JvmOperation) jvmElement;
		}

		@Test
		public void noParam_noReturn() throws Exception {
			JvmOperation operation = createJvmFeature(
					// Code
					"agent A1 {",
					"	def fct {}",
					"}");
			//
			SarlAction action = this.generator.createAction(operation, this.importManager);
			//
			assertNotNull(action);
			assertEquals("fct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "void");
			//
			assertEquals(0, action.getFiredEvents().size());
			//
			assertEquals(0, action.getParameters().size());
			//
			assertImports(this.importManager);
		}

		@Test
		public void stdParam_noReturn() throws Exception {
			JvmOperation operation = createJvmFeature(
					// Code
					"import java.net.URL",
					"agent A1 {",
					"	def fct(a : URL, b : int) {}",
					"}");
			//
			SarlAction action = this.generator.createAction(operation, this.importManager);
			//
			assertNotNull(action);
			assertEquals("fct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "void");
			//
			assertEquals(0, action.getFiredEvents().size());
			//
			assertEquals(2, action.getParameters().size());
			assertParameterNames(action.getParameters(), "a", "b");
			assertParameterTypes(action.getParameters(), "java.net.URL", "int");
			assertParameterDefaultValues(action.getParameters(),
					null,
					null);
			assertNoParameterVarArg(action.getParameters());
			//
			assertImports(this.importManager, "java.net.URL");
		}

		@Test
		public void variadicParam_noReturn() throws Exception {
			JvmOperation operation = createJvmFeature(
					// Code
					"import java.net.URL",
					"agent A1 {",
					"	def fct(a : URL, b : int*) {}",
					"}");
			//
			SarlAction action = this.generator.createAction(operation, this.importManager);
			//
			assertNotNull(action);
			assertEquals("fct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "void");
			//
			assertEquals(0, action.getFiredEvents().size());
			//
			assertEquals(2, action.getParameters().size());
			assertParameterNames(action.getParameters(), "a", "b");
			assertParameterTypes(action.getParameters(), "java.net.URL", "int");
			assertParameterDefaultValues(action.getParameters(),
					null,
					null);
			assertParameterVarArg(action.getParameters());
			//
			assertImports(this.importManager, "java.net.URL");
		}

		@Test
		public void defaultValue_noReturn() throws Exception {
			JvmOperation operation = createJvmFeature(
					// Code
					"import java.net.URL",
					"agent A1 {",
					"	def fct(a : URL, b : int=4, c : char) {}",
					"}");
			//
			SarlAction action = this.generator.createAction(operation, this.importManager);
			//
			assertNotNull(action);
			assertEquals("fct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "void");
			//
			assertEquals(0, action.getFiredEvents().size());
			//
			assertEquals(3, action.getParameters().size());
			assertParameterNames(action.getParameters(), "a", "b", "c");
			assertParameterTypes(action.getParameters(), "java.net.URL", "int", "char");
			assertParameterDefaultValues(action.getParameters(),
					null,
					XNumberLiteral.class, "4",
					null);
			assertNoParameterVarArg(action.getParameters());
			//
			assertImports(this.importManager, "java.net.URL");
		}

		@Test
		public void variadicParam_defaultValue_noReturn() throws Exception {
			JvmOperation operation = createJvmFeature(
					// Code
					"import java.net.URL",
					"agent A1 {",
					"	def fct(a : URL, b : int=4, c : char*) {}",
					"}");
			//
			SarlAction action = this.generator.createAction(operation, this.importManager);
			//
			assertNotNull(action);
			assertEquals("fct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "void");
			//
			assertEquals(0, action.getFiredEvents().size());
			//
			assertEquals(3, action.getParameters().size());
			assertParameterNames(action.getParameters(), "a", "b", "c");
			assertParameterTypes(action.getParameters(), "java.net.URL", "int", "char");
			assertParameterDefaultValues(action.getParameters(),
					null,
					XNumberLiteral.class, "4",
					null);
			assertParameterVarArg(action.getParameters());
			//
			assertImports(this.importManager, "java.net.URL");
		}

		@Test
		public void noParam_returnValue() throws Exception {
			JvmOperation operation = createJvmFeature(
					// Code
					"agent A1 {",
					"	def fct : String { null }",
					"}");
			//
			SarlAction action = this.generator.createAction(operation, this.importManager);
			//
			assertNotNull(action);
			assertEquals("fct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "java.lang.String");
			//
			assertEquals(0, action.getFiredEvents().size());
			//
			assertEquals(0, action.getParameters().size());
			//
			assertImports(this.importManager);
		}

		@Test
		public void stdParam_returnValue() throws Exception {
			JvmOperation operation = createJvmFeature(
					// Code
					"import java.net.URL",
					"agent A1 {",
					"	def fct(a : URL, b : int) : String { null }",
					"}");
			//
			SarlAction action = this.generator.createAction(operation, this.importManager);
			//
			assertNotNull(action);
			assertEquals("fct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "java.lang.String");
			//
			assertEquals(0, action.getFiredEvents().size());
			//
			assertEquals(2, action.getParameters().size());
			assertParameterNames(action.getParameters(), "a", "b");
			assertParameterTypes(action.getParameters(), "java.net.URL", "int");
			assertParameterDefaultValues(action.getParameters(),
					null,
					null);
			assertNoParameterVarArg(action.getParameters());
			//
			assertImports(this.importManager, "java.net.URL");
		}

		@Test
		public void variadicParam_returnValue() throws Exception {
			JvmOperation operation = createJvmFeature(
					// Code
					"import java.net.URL",
					"agent A1 {",
					"	def fct(a : URL, b : int*) : String { null }",
					"}");
			//
			SarlAction action = this.generator.createAction(operation, this.importManager);
			//
			assertNotNull(action);
			assertEquals("fct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "java.lang.String");
			//
			assertEquals(0, action.getFiredEvents().size());
			//
			assertEquals(2, action.getParameters().size());
			assertParameterNames(action.getParameters(), "a", "b");
			assertParameterTypes(action.getParameters(), "java.net.URL", "int");
			assertParameterDefaultValues(action.getParameters(),
					null,
					null);
			assertParameterVarArg(action.getParameters());
			//
			assertImports(this.importManager, "java.net.URL");
		}

		@Test
		public void defaultValue_returnValue() throws Exception {
			JvmOperation operation = createJvmFeature(
					// Code
					"import java.net.URL",
					"agent A1 {",
					"	def fct(a : URL, b : int=4, c : char) : String { null }",
					"}");
			//
			SarlAction action = this.generator.createAction(operation, this.importManager);
			//
			assertNotNull(action);
			assertEquals("fct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "java.lang.String");
			//
			assertEquals(0, action.getFiredEvents().size());
			//
			assertEquals(3, action.getParameters().size());
			assertParameterNames(action.getParameters(), "a", "b", "c");
			assertParameterTypes(action.getParameters(), "java.net.URL", "int", "char");
			assertParameterDefaultValues(action.getParameters(),
					null,
					XNumberLiteral.class, "4",
					null);
			assertNoParameterVarArg(action.getParameters());
			//
			assertImports(this.importManager, "java.net.URL");
		}

		@Test
		public void variadicParam_defaultValue_returnValue() throws Exception {
			JvmOperation operation = createJvmFeature(
					// Code
					"import java.net.URL",
					"agent A1 {",
					"	def fct(a : URL, b : int=4, c : char*) : String { null }",
					"}");
			//
			SarlAction action = this.generator.createAction(operation, this.importManager);
			//
			assertNotNull(action);
			assertEquals("fct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "java.lang.String");
			//
			assertEquals(0, action.getFiredEvents().size());
			//
			assertEquals(3, action.getParameters().size());
			assertParameterNames(action.getParameters(), "a", "b", "c");
			assertParameterTypes(action.getParameters(), "java.net.URL", "int", "char");
			assertParameterDefaultValues(action.getParameters(),
					null,
					XNumberLiteral.class, "4",
					null);
			assertParameterVarArg(action.getParameters());
			//
			assertImports(this.importManager, "java.net.URL");
		}

		@Test
		public void noParam_noReturn_fireEvents() throws Exception {
			JvmOperation operation = createJvmFeature(
					// Code
					"agent A1 {",
					"	def fct fires MyEvent {}",
					"}",
					"event MyEvent");
			//
			SarlAction action = this.generator.createAction(operation, this.importManager);
			//
			assertNotNull(action);
			assertEquals("fct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "void");
			//
			assertEquals(1, action.getFiredEvents().size());
			assertTypeReferenceIdentifiers(
					action.getFiredEvents(),
					"MyEvent");
			//
			assertEquals(0, action.getParameters().size());
			//
			assertImports(this.importManager);
		}

		@Test
		public void stdParam_noReturn_fireEvents() throws Exception {
			JvmOperation operation = createJvmFeature(
					// Code
					"import java.net.URL",
					"agent A1 {",
					"	def fct(a : URL, b : int) fires MyEvent {}",
					"}",
					"event MyEvent");
			//
			SarlAction action = this.generator.createAction(operation, this.importManager);
			//
			assertNotNull(action);
			assertEquals("fct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "void");
			//
			assertEquals(1, action.getFiredEvents().size());
			assertTypeReferenceIdentifiers(
					action.getFiredEvents(),
					"MyEvent");
			//
			assertEquals(2, action.getParameters().size());
			assertParameterNames(action.getParameters(), "a", "b");
			assertParameterTypes(action.getParameters(), "java.net.URL", "int");
			assertParameterDefaultValues(action.getParameters(),
					null,
					null);
			assertNoParameterVarArg(action.getParameters());
			//
			assertImports(this.importManager, "java.net.URL");
		}

		@Test
		public void variadicParam_noReturn_fireEvents() throws Exception {
			JvmOperation operation = createJvmFeature(
					// Code
					"import java.net.URL",
					"agent A1 {",
					"	def fct(a : URL, b : int*) fires MyEvent {}",
					"}",
					"event MyEvent");
			//
			SarlAction action = this.generator.createAction(operation, this.importManager);
			//
			assertNotNull(action);
			assertEquals("fct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "void");
			//
			assertEquals(1, action.getFiredEvents().size());
			assertTypeReferenceIdentifiers(
					action.getFiredEvents(),
					"MyEvent");
			//
			assertEquals(2, action.getParameters().size());
			assertParameterNames(action.getParameters(), "a", "b");
			assertParameterTypes(action.getParameters(), "java.net.URL", "int");
			assertParameterDefaultValues(action.getParameters(),
					null,
					null);
			assertNoParameterVarArg(action.getParameters());
			//
			assertImports(this.importManager, "java.net.URL");
		}

		@Test
		public void defaultValue_noReturn_fireEvents() throws Exception {
			JvmOperation operation = createJvmFeature(
					// Code
					"import java.net.URL",
					"agent A1 {",
					"	def fct(a : URL, b : int=4, c : char) fires MyEvent {}",
					"}",
					"event MyEvent");
			//
			SarlAction action = this.generator.createAction(operation, this.importManager);
			//
			assertNotNull(action);
			assertEquals("fct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "void");
			//
			assertEquals(1, action.getFiredEvents().size());
			assertTypeReferenceIdentifiers(
					action.getFiredEvents(),
					"MyEvent");
			//
			assertEquals(3, action.getParameters().size());
			assertParameterNames(action.getParameters(), "a", "b", "c");
			assertParameterTypes(action.getParameters(), "java.net.URL", "int", "char");
			assertParameterDefaultValues(action.getParameters(),
					null,
					XNumberLiteral.class, "4",
					null);
			assertNoParameterVarArg(action.getParameters());
			//
			assertImports(this.importManager, "java.net.URL");
		}

		@Test
		public void variadicParam_defaultValue_noReturn_fireEvents() throws Exception {
			JvmOperation operation = createJvmFeature(
					// Code
					"import java.net.URL",
					"agent A1 {",
					"	def fct(a : URL, b : int=4, c : char*) fires MyEvent {}",
					"}",
					"event MyEvent");
			//
			SarlAction action = this.generator.createAction(operation, this.importManager);
			//
			assertNotNull(action);
			assertEquals("fct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "void");
			//
			assertEquals(1, action.getFiredEvents().size());
			assertTypeReferenceIdentifiers(
					action.getFiredEvents(),
					"MyEvent");
			//
			assertEquals(3, action.getParameters().size());
			assertParameterNames(action.getParameters(), "a", "b", "c");
			assertParameterTypes(action.getParameters(), "java.net.URL", "int", "char");
			assertParameterDefaultValues(action.getParameters(),
					null,
					XNumberLiteral.class, "4",
					null);
			assertParameterVarArg(action.getParameters());
			//
			assertImports(this.importManager, "java.net.URL");
		}

		@Test
		public void noParam_returnValue_fireEvents() throws Exception {
			JvmOperation operation = createJvmFeature(
					// Code
					"agent A1 {",
					"	def fct : String fires MyEvent { null }",
					"}",
					"event MyEvent");
			//
			SarlAction action = this.generator.createAction(operation, this.importManager);
			//
			assertNotNull(action);
			assertEquals("fct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "java.lang.String");
			//
			assertEquals(1, action.getFiredEvents().size());
			assertTypeReferenceIdentifiers(
					action.getFiredEvents(),
					"MyEvent");
			//
			assertEquals(0, action.getParameters().size());
			//
			assertImports(this.importManager);
		}

		@Test
		public void stdParam_returnValue_fireEvents() throws Exception {
			JvmOperation operation = createJvmFeature(
					// Code
					"import java.net.URL",
					"agent A1 {",
					"	def fct(a : URL, b : int) : String fires MyEvent { null }",
					"}",
					"event MyEvent");
			//
			SarlAction action = this.generator.createAction(operation, this.importManager);
			//
			assertNotNull(action);
			assertEquals("fct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "java.lang.String");
			//
			assertEquals(1, action.getFiredEvents().size());
			assertTypeReferenceIdentifiers(
					action.getFiredEvents(),
					"MyEvent");
			//
			assertEquals(2, action.getParameters().size());
			assertParameterNames(action.getParameters(), "a", "b");
			assertParameterTypes(action.getParameters(), "java.net.URL", "int");
			assertParameterDefaultValues(action.getParameters(),
					null,
					null);
			assertNoParameterVarArg(action.getParameters());
			//
			assertImports(this.importManager, "java.net.URL");
		}

		@Test
		public void variadicParam_returnValue_fireEvents() throws Exception {
			JvmOperation operation = createJvmFeature(
					// Code
					"import java.net.URL",
					"agent A1 {",
					"	def fct(a : URL, b : int*) : String fires MyEvent { null }",
					"}",
					"event MyEvent");
			//
			SarlAction action = this.generator.createAction(operation, this.importManager);
			//
			assertNotNull(action);
			assertEquals("fct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "java.lang.String");
			//
			assertEquals(1, action.getFiredEvents().size());
			assertTypeReferenceIdentifiers(
					action.getFiredEvents(),
					"MyEvent");
			//
			assertEquals(2, action.getParameters().size());
			assertParameterNames(action.getParameters(), "a", "b");
			assertParameterTypes(action.getParameters(), "java.net.URL", "int");
			assertParameterDefaultValues(action.getParameters(),
					null,
					null);
			assertParameterVarArg(action.getParameters());
			//
			assertImports(this.importManager, "java.net.URL");
		}

		@Test
		public void defaultValue_returnValue_fireEvents() throws Exception {
			JvmOperation operation = createJvmFeature(
					// Code
					"import java.net.URL",
					"agent A1 {",
					"	def fct(a : URL, b : int=4, c : char) : String fires MyEvent { null }",
					"}",
					"event MyEvent");
			//
			SarlAction action = this.generator.createAction(operation, this.importManager);
			//
			assertNotNull(action);
			assertEquals("fct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "java.lang.String");
			//
			assertEquals(1, action.getFiredEvents().size());
			assertTypeReferenceIdentifiers(
					action.getFiredEvents(),
					"MyEvent");
			//
			assertEquals(3, action.getParameters().size());
			assertParameterNames(action.getParameters(), "a", "b", "c");
			assertParameterTypes(action.getParameters(), "java.net.URL", "int", "char");
			assertParameterDefaultValues(action.getParameters(),
					null,
					XNumberLiteral.class, "4",
					null);
			assertNoParameterVarArg(action.getParameters());
			//
			assertImports(this.importManager, "java.net.URL");
		}

		@Test
		public void variadicParam_defaultValue_returnValue_fireEvents() throws Exception {
			JvmOperation operation = createJvmFeature(
					// Code
					"import java.net.URL",
					"agent A1 {",
					"	def fct(a : URL, b : int=4, c : char*) : String fires MyEvent { null }",
					"}",
					"event MyEvent");
			//
			SarlAction action = this.generator.createAction(operation, this.importManager);
			//
			assertNotNull(action);
			assertEquals("fct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "java.lang.String");
			//
			assertEquals(1, action.getFiredEvents().size());
			assertTypeReferenceIdentifiers(
					action.getFiredEvents(),
					"MyEvent");
			//
			assertEquals(3, action.getParameters().size());
			assertParameterNames(action.getParameters(), "a", "b", "c");
			assertParameterTypes(action.getParameters(), "java.net.URL", "int", "char");
			assertParameterDefaultValues(action.getParameters(),
					null,
					XNumberLiteral.class, "4",
					null);
			assertParameterVarArg(action.getParameters());
			//
			assertImports(this.importManager, "java.net.URL");
		}

	}

	public static class CreateConstructorFromJvmElement extends AbstractSarlUiTest {

		@Inject
		private SARLCodeGenerator generator;

		/** Associator of the JVM elements and the SARL elements.
		 */
		@Inject
		protected JvmModelAssociator jvmModelAssociator;

		@Inject
		private ImportManager importManager;

		private JvmConstructor createJvmFeature(String... sarlCode) throws Exception {
			String sarlFilename = generateFilename();
			XtendFile sarlScript = this.helper.createSARLScript(sarlFilename, multilineString(sarlCode));
			this.helper.waitForAutoBuild();
			EObject feature = sarlScript.getXtendTypes().get(0).getMembers().get(0);
			EObject jvmElement = this.jvmModelAssociator.getPrimaryJvmElement(feature);
			return (JvmConstructor) jvmElement;
		}

		@Test
		public void noParam() throws Exception {
			JvmConstructor cons = createJvmFeature(
					// Code
					"event E1 {",
					"	new() {}",
					"}");
			//
			XtendConstructor action = this.generator.createConstructor(cons, this.importManager);
			//
			assertNotNull(action);
			//
			assertEquals(0, action.getParameters().size());
			//
			assertImports(this.importManager);
		}

		@Test
		public void stdParam() throws Exception {
			JvmConstructor cons = createJvmFeature(
					// Code
					"import java.net.URL",
					"event E1 {",
					"	new (a : URL, b : int) {}",
					"}");
			//
			XtendConstructor action = this.generator.createConstructor(cons, this.importManager);
			//
			assertNotNull(action);
			//
			assertEquals(2, action.getParameters().size());
			assertParameterNames(action.getParameters(), "a", "b");
			assertParameterTypes(action.getParameters(), "java.net.URL", "int");
			assertParameterDefaultValues(action.getParameters(),
					null,
					null);
			assertNoParameterVarArg(action.getParameters());
			//
			assertImports(this.importManager, "java.net.URL");
		}

		@Test
		public void variadicParam() throws Exception {
			JvmConstructor cons = createJvmFeature(
					// Code
					"import java.net.URL",
					"event E1 {",
					"	new (a : URL, b : int*) {}",
					"}");
			//
			XtendConstructor action = this.generator.createConstructor(cons, this.importManager);
			//
			assertNotNull(action);
			//
			assertEquals(2, action.getParameters().size());
			assertParameterNames(action.getParameters(), "a", "b");
			assertParameterTypes(action.getParameters(), "java.net.URL", "int");
			assertParameterDefaultValues(action.getParameters(),
					null,
					null);
			assertParameterVarArg(action.getParameters());
			//
			assertImports(this.importManager, "java.net.URL");
		}

		@Test
		public void defaultValue() throws Exception {
			JvmConstructor cons = createJvmFeature(
					// Code
					"import java.net.URL",
					"event E1 {",
					"	new (a : URL, b : int=4, c : char) {}",
					"}");
			//
			XtendConstructor action = this.generator.createConstructor(cons, this.importManager);
			//
			assertNotNull(action);
			//
			assertEquals(3, action.getParameters().size());
			assertParameterNames(action.getParameters(), "a", "b", "c");
			assertParameterTypes(action.getParameters(), "java.net.URL", "int", "char");
			assertParameterDefaultValues(action.getParameters(),
					null,
					XNumberLiteral.class, "4",
					null);
			assertNoParameterVarArg(action.getParameters());
			//
			assertImports(this.importManager, "java.net.URL");
		}

		@Test
		public void variadicParam_defaultValue() throws Exception {
			JvmConstructor cons = createJvmFeature(
					// Code
					"import java.net.URL",
					"event E1 {",
					"	new (a : URL, b : int=4, c : char*) {}",
					"}");
			//
			XtendConstructor action = this.generator.createConstructor(cons, this.importManager);
			//
			assertNotNull(action);
			//
			assertEquals(3, action.getParameters().size());
			assertParameterNames(action.getParameters(), "a", "b", "c");
			assertParameterTypes(action.getParameters(), "java.net.URL", "int", "char");
			assertParameterDefaultValues(action.getParameters(),
					null,
					XNumberLiteral.class, "4",
					null);
			assertParameterVarArg(action.getParameters());
			//
			assertImports(this.importManager, "java.net.URL");
		}

	}

	@InjectWith(SARLInjectorProvider.class)
	public static class createActionFromJvmElement extends AbstractSarlUiTest {

		@Inject
		private SARLCodeGenerator generator;

		/** Associator of the JVM elements and the SARL elements.
		 */
		@Inject
		protected JvmModelAssociator jvmModelAssociator;

		@Inject
		private ImportManager importManager;

		private JvmOperation createJvmFeature(String... sarlCode) throws Exception {
			String sarlFilename = generateFilename();
			XtendFile sarlScript = this.helper.createSARLScript(sarlFilename, multilineString(sarlCode));
			this.helper.waitForAutoBuild();
			EObject feature = sarlScript.getXtendTypes().get(0).getMembers().get(0);
			EObject jvmElement = this.jvmModelAssociator.getPrimaryJvmElement(feature);
			return (JvmOperation) jvmElement;
		}

		@Test
		public void noParam_noReturn() throws Exception {
			JvmOperation operation = createJvmFeature(
					// Code
					"capacity C1 {",
					"	def fct",
					"}");
			//
			SarlAction action = this.generator.createAction(operation, this.importManager);
			//
			assertNotNull(action);
			assertEquals("fct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "void");
			//
			assertEquals(0, action.getFiredEvents().size());
			//
			assertEquals(0, action.getParameters().size());
			//
			assertImports(this.importManager);
		}

		@Test
		public void stdParam_noReturn() throws Exception {
			JvmOperation operation = createJvmFeature(
					// Code
					"import java.net.URL",
					"capacity C1 {",
					"	def fct(a : URL, b : int)",
					"}");
			//
			SarlAction action = this.generator.createAction(operation, this.importManager);
			//
			assertNotNull(action);
			assertEquals("fct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "void");
			//
			assertEquals(0, action.getFiredEvents().size());
			//
			assertEquals(2, action.getParameters().size());
			assertParameterNames(action.getParameters(), "a", "b");
			assertParameterTypes(action.getParameters(), "java.net.URL", "int");
			assertParameterDefaultValues(action.getParameters(),
					null,
					null);
			assertNoParameterVarArg(action.getParameters());
			//
			assertImports(this.importManager, "java.net.URL");
		}

		@Test
		public void variadicParam_noReturn() throws Exception {
			JvmOperation operation = createJvmFeature(
					// Code
					"import java.net.URL",
					"capacity C1 {",
					"	def fct(a : URL, b : int*)",
					"}");
			//
			SarlAction action = this.generator.createAction(operation, this.importManager);
			//
			assertNotNull(action);
			assertEquals("fct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "void");
			//
			assertEquals(0, action.getFiredEvents().size());
			//
			assertEquals(2, action.getParameters().size());
			assertParameterNames(action.getParameters(), "a", "b");
			assertParameterTypes(action.getParameters(), "java.net.URL", "int");
			assertParameterDefaultValues(action.getParameters(),
					null,
					null);
			assertParameterVarArg(action.getParameters());
			//
			assertImports(this.importManager, "java.net.URL");
		}

		@Test
		public void defaultValue_noReturn() throws Exception {
			JvmOperation operation = createJvmFeature(
					// Code
					"import java.net.URL",
					"capacity C1 {",
					"	def fct(a : URL, b : int=4, c : char)",
					"}");
			//
			SarlAction action = this.generator.createAction(operation, this.importManager);
			//
			assertNotNull(action);
			assertEquals("fct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "void");
			//
			assertEquals(0, action.getFiredEvents().size());
			//
			assertEquals(3, action.getParameters().size());
			assertParameterNames(action.getParameters(), "a", "b", "c");
			assertParameterTypes(action.getParameters(), "java.net.URL", "int", "char");
			assertParameterDefaultValues(action.getParameters(),
					null,
					XNumberLiteral.class, "4",
					null);
			assertNoParameterVarArg(action.getParameters());
			//
			assertImports(this.importManager, "java.net.URL");
		}

		@Test
		public void variadicParam_defaultValue_noReturn() throws Exception {
			JvmOperation operation = createJvmFeature(
					// Code
					"import java.net.URL",
					"capacity C1 {",
					"	def fct(a : URL, b : int=4, c : char*)",
					"}");
			//
			SarlAction action = this.generator.createAction(operation, this.importManager);
			//
			assertNotNull(action);
			assertEquals("fct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "void");
			//
			assertEquals(0, action.getFiredEvents().size());
			//
			assertEquals(3, action.getParameters().size());
			assertParameterNames(action.getParameters(), "a", "b", "c");
			assertParameterTypes(action.getParameters(), "java.net.URL", "int", "char");
			assertParameterDefaultValues(action.getParameters(),
					null,
					XNumberLiteral.class, "4",
					null);
			assertParameterVarArg(action.getParameters());
			//
			assertImports(this.importManager, "java.net.URL");
		}

		@Test
		public void noParam_returnValue() throws Exception {
			JvmOperation operation = createJvmFeature(
					// Code
					"capacity C1 {",
					"	def fct : String",
					"}");
			//
			SarlAction action = this.generator.createAction(operation, this.importManager);
			//
			assertNotNull(action);
			assertEquals("fct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "java.lang.String");
			//
			assertEquals(0, action.getFiredEvents().size());
			//
			assertEquals(0, action.getParameters().size());
			//
			assertImports(this.importManager);
		}

		@Test
		public void stdParam_returnValue() throws Exception {
			JvmOperation operation = createJvmFeature(
					// Code
					"import java.net.URL",
					"capacity C1 {",
					"	def fct(a : URL, b : int) : String",
					"}");
			//
			SarlAction action = this.generator.createAction(operation, this.importManager);
			//
			assertNotNull(action);
			assertEquals("fct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "java.lang.String");
			//
			assertEquals(0, action.getFiredEvents().size());
			//
			assertEquals(2, action.getParameters().size());
			assertParameterNames(action.getParameters(), "a", "b");
			assertParameterTypes(action.getParameters(), "java.net.URL", "int");
			assertParameterDefaultValues(action.getParameters(),
					null,
					null);
			assertNoParameterVarArg(action.getParameters());
			//
			assertImports(this.importManager, "java.net.URL");
		}

		@Test
		public void variadicParam_returnValue() throws Exception {
			JvmOperation operation = createJvmFeature(
					// Code
					"import java.net.URL",
					"capacity C1 {",
					"	def fct(a : URL, b : int*) : String",
					"}");
			//
			SarlAction action = this.generator.createAction(operation, this.importManager);
			//
			assertNotNull(action);
			assertEquals("fct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "java.lang.String");
			//
			assertEquals(0, action.getFiredEvents().size());
			//
			assertEquals(2, action.getParameters().size());
			assertParameterNames(action.getParameters(), "a", "b");
			assertParameterTypes(action.getParameters(), "java.net.URL", "int");
			assertParameterDefaultValues(action.getParameters(),
					null,
					null);
			assertParameterVarArg(action.getParameters());
			//
			assertImports(this.importManager, "java.net.URL");
		}

		@Test
		public void defaultValue_returnValue() throws Exception {
			JvmOperation operation = createJvmFeature(
					// Code
					"import java.net.URL",
					"capacity C1 {",
					"	def fct(a : URL, b : int=4, c : char) : String",
					"}");
			//
			SarlAction action = this.generator.createAction(operation, this.importManager);
			//
			assertNotNull(action);
			assertEquals("fct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "java.lang.String");
			//
			assertEquals(0, action.getFiredEvents().size());
			//
			assertEquals(3, action.getParameters().size());
			assertParameterNames(action.getParameters(), "a", "b", "c");
			assertParameterTypes(action.getParameters(), "java.net.URL", "int", "char");
			assertParameterDefaultValues(action.getParameters(),
					null,
					XNumberLiteral.class, "4",
					null);
			assertNoParameterVarArg(action.getParameters());
			//
			assertImports(this.importManager, "java.net.URL");
		}

		@Test
		public void variadicParam_defaultValue_returnValue() throws Exception {
			JvmOperation operation = createJvmFeature(
					// Code
					"import java.net.URL",
					"capacity C1 {",
					"	def fct(a : URL, b : int=4, c : char*) : String",
					"}");
			//
			SarlAction action = this.generator.createAction(operation, this.importManager);
			//
			assertNotNull(action);
			assertEquals("fct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "java.lang.String");
			//
			assertEquals(0, action.getFiredEvents().size());
			//
			assertEquals(3, action.getParameters().size());
			assertParameterNames(action.getParameters(), "a", "b", "c");
			assertParameterTypes(action.getParameters(), "java.net.URL", "int", "char");
			assertParameterDefaultValues(action.getParameters(),
					null,
					XNumberLiteral.class, "4",
					null);
			assertParameterVarArg(action.getParameters());
			//
			assertImports(this.importManager, "java.net.URL");
		}

		@Test
		public void noParam_noReturn_fireEvents() throws Exception {
			JvmOperation operation = createJvmFeature(
					// Code
					"capacity C1 {",
					"	def fct fires MyEvent",
					"}",
					"event MyEvent");
			//
			SarlAction action = this.generator.createAction(operation, this.importManager);
			//
			assertNotNull(action);
			assertEquals("fct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "void");
			//
			assertEquals(1, action.getFiredEvents().size());
			assertTypeReferenceIdentifiers(
					action.getFiredEvents(),
					"MyEvent");
			//
			assertEquals(0, action.getParameters().size());
			//
			assertImports(this.importManager);
		}

		@Test
		public void stdParam_noReturn_fireEvents() throws Exception {
			JvmOperation operation = createJvmFeature(
					// Code
					"import java.net.URL",
					"capacity C1 {",
					"	def fct(a : URL, b : int) fires MyEvent",
					"}",
					"event MyEvent");
			//
			SarlAction action = this.generator.createAction(operation, this.importManager);
			//
			assertNotNull(action);
			assertEquals("fct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "void");
			//
			assertEquals(1, action.getFiredEvents().size());
			assertTypeReferenceIdentifiers(
					action.getFiredEvents(),
					"MyEvent");
			//
			assertEquals(2, action.getParameters().size());
			assertParameterNames(action.getParameters(), "a", "b");
			assertParameterTypes(action.getParameters(), "java.net.URL", "int");
			assertParameterDefaultValues(action.getParameters(),
					null,
					null);
			assertNoParameterVarArg(action.getParameters());
			//
			assertImports(this.importManager, "java.net.URL");
		}

		@Test
		public void variadicParam_noReturn_fireEvents() throws Exception {
			JvmOperation operation = createJvmFeature(
					// Code
					"import java.net.URL",
					"capacity C1 {",
					"	def fct(a : URL, b : int*) fires MyEvent",
					"}",
					"event MyEvent");
			//
			SarlAction action = this.generator.createAction(operation, this.importManager);
			//
			assertNotNull(action);
			assertEquals("fct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "void");
			//
			assertEquals(1, action.getFiredEvents().size());
			assertTypeReferenceIdentifiers(
					action.getFiredEvents(),
					"MyEvent");
			//
			assertEquals(2, action.getParameters().size());
			assertParameterNames(action.getParameters(), "a", "b");
			assertParameterTypes(action.getParameters(), "java.net.URL", "int");
			assertParameterDefaultValues(action.getParameters(),
					null,
					null);
			assertParameterVarArg(action.getParameters());
			//
			assertImports(this.importManager, "java.net.URL");
		}

		@Test
		public void defaultValue_noReturn_fireEvents() throws Exception {
			JvmOperation operation = createJvmFeature(
					// Code
					"import java.net.URL",
					"capacity C1 {",
					"	def fct(a : URL, b : int=4, c : char) fires MyEvent",
					"}",
					"event MyEvent");
			//
			SarlAction action = this.generator.createAction(operation, this.importManager);
			//
			assertNotNull(action);
			assertEquals("fct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "void");
			//
			assertEquals(1, action.getFiredEvents().size());
			assertTypeReferenceIdentifiers(
					action.getFiredEvents(),
					"MyEvent");
			//
			assertEquals(3, action.getParameters().size());
			assertParameterNames(action.getParameters(), "a", "b", "c");
			assertParameterTypes(action.getParameters(), "java.net.URL", "int", "char");
			assertParameterDefaultValues(action.getParameters(),
					null,
					XNumberLiteral.class, "4",
					null);
			assertNoParameterVarArg(action.getParameters());
			//
			assertImports(this.importManager, "java.net.URL");
		}

		@Test
		public void variadicParam_defaultValue_noReturn_fireEvents() throws Exception {
			JvmOperation operation = createJvmFeature(
					// Code
					"import java.net.URL",
					"capacity C1 {",
					"	def fct(a : URL, b : int=4, c : char*) fires MyEvent",
					"}",
					"event MyEvent");
			//
			SarlAction action = this.generator.createAction(operation, this.importManager);
			//
			assertNotNull(action);
			assertEquals("fct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "void");
			//
			assertEquals(1, action.getFiredEvents().size());
			assertTypeReferenceIdentifiers(
					action.getFiredEvents(),
					"MyEvent");
			//
			assertEquals(3, action.getParameters().size());
			assertParameterNames(action.getParameters(), "a", "b", "c");
			assertParameterTypes(action.getParameters(), "java.net.URL", "int", "char");
			assertParameterDefaultValues(action.getParameters(),
					null,
					XNumberLiteral.class, "4",
					null);
			assertParameterVarArg(action.getParameters());
			//
			assertImports(this.importManager, "java.net.URL");
		}

		@Test
		public void noParam_returnValue_fireEvents() throws Exception {
			JvmOperation operation = createJvmFeature(
					// Code
					"capacity C1 {",
					"	def fct : String fires MyEvent",
					"}",
					"event MyEvent");
			//
			SarlAction action = this.generator.createAction(operation, this.importManager);
			//
			assertNotNull(action);
			assertEquals("fct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "java.lang.String");
			//
			assertEquals(1, action.getFiredEvents().size());
			assertTypeReferenceIdentifiers(
					action.getFiredEvents(),
					"MyEvent");
			//
			assertEquals(0, action.getParameters().size());
			//
			assertImports(this.importManager);
		}

		@Test
		public void stdParam_returnValue_fireEvents() throws Exception {
			JvmOperation operation = createJvmFeature(
					// Code
					"import java.net.URL",
					"capacity C1 {",
					"	def fct(a : URL, b : int) : String fires MyEvent",
					"}",
					"event MyEvent");
			//
			SarlAction action = this.generator.createAction(operation, this.importManager);
			//
			assertNotNull(action);
			assertEquals("fct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "java.lang.String");
			//
			assertEquals(1, action.getFiredEvents().size());
			assertTypeReferenceIdentifiers(
					action.getFiredEvents(),
					"MyEvent");
			//
			assertEquals(2, action.getParameters().size());
			assertParameterNames(action.getParameters(), "a", "b");
			assertParameterTypes(action.getParameters(), "java.net.URL", "int");
			assertParameterDefaultValues(action.getParameters(),
					null,
					null);
			assertNoParameterVarArg(action.getParameters());
			//
			assertImports(this.importManager, "java.net.URL");
		}

		@Test
		public void variadicParam_returnValue_fireEvents() throws Exception {
			JvmOperation operation = createJvmFeature(
					// Code
					"import java.net.URL",
					"capacity C1 {",
					"	def fct(a : URL, b : int*) : String fires MyEvent",
					"}",
					"event MyEvent");
			//
			SarlAction action = this.generator.createAction(operation, this.importManager);
			//
			assertNotNull(action);
			assertEquals("fct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "java.lang.String");
			//
			assertEquals(1, action.getFiredEvents().size());
			assertTypeReferenceIdentifiers(
					action.getFiredEvents(),
					"MyEvent");
			//
			assertEquals(2, action.getParameters().size());
			assertParameterNames(action.getParameters(), "a", "b");
			assertParameterTypes(action.getParameters(), "java.net.URL", "int");
			assertParameterDefaultValues(action.getParameters(),
					null,
					null);
			assertParameterVarArg(action.getParameters());
			//
			assertImports(this.importManager, "java.net.URL");
		}

		@Test
		public void defaultValue_returnValue_fireEvents() throws Exception {
			JvmOperation operation = createJvmFeature(
					// Code
					"import java.net.URL",
					"capacity C1 {",
					"	def fct(a : URL, b : int=4, c : char) : String fires MyEvent",
					"}",
					"event MyEvent");
			//
			SarlAction action = this.generator.createAction(operation, this.importManager);
			//
			assertNotNull(action);
			assertEquals("fct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "java.lang.String");
			//
			assertEquals(1, action.getFiredEvents().size());
			assertTypeReferenceIdentifiers(
					action.getFiredEvents(),
					"MyEvent");
			//
			assertEquals(3, action.getParameters().size());
			assertParameterNames(action.getParameters(), "a", "b", "c");
			assertParameterTypes(action.getParameters(), "java.net.URL", "int", "char");
			assertParameterDefaultValues(action.getParameters(),
					null,
					XNumberLiteral.class, "4",
					null);
			assertNoParameterVarArg(action.getParameters());
			//
			assertImports(this.importManager, "java.net.URL");
		}

		@Test
		public void variadicParam_defaultValue_returnValue_fireEvents() throws Exception {
			JvmOperation operation = createJvmFeature(
					// Code
					"import java.net.URL",
					"capacity C1 {",
					"	def fct(a : URL, b : int=4, c : char*) : String fires MyEvent",
					"}",
					"event MyEvent");
			//
			SarlAction action = this.generator.createAction(operation, this.importManager);
			//
			assertNotNull(action);
			assertEquals("fct", action.getName());
			assertTypeReferenceIdentifier(action.getReturnType(), "java.lang.String");
			//
			assertEquals(1, action.getFiredEvents().size());
			assertTypeReferenceIdentifiers(
					action.getFiredEvents(),
					"MyEvent");
			//
			assertEquals(3, action.getParameters().size());
			assertParameterNames(action.getParameters(), "a", "b", "c");
			assertParameterTypes(action.getParameters(), "java.net.URL", "int", "char");
			assertParameterDefaultValues(action.getParameters(),
					null,
					XNumberLiteral.class, "4",
					null);
			assertParameterVarArg(action.getParameters());
			//
			assertImports(this.importManager, "java.net.URL");
		}

	}

}
