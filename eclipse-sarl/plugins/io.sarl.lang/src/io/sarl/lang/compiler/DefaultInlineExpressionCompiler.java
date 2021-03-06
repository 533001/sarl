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

package io.sarl.lang.compiler;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.inject.Singleton;

import com.google.inject.Inject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtend.core.macro.ConstantExpressionsInterpreter;
import org.eclipse.xtend.core.xtend.XtendExecutable;
import org.eclipse.xtend.core.xtend.XtendFunction;
import org.eclipse.xtext.common.types.JvmAnnotationReference;
import org.eclipse.xtext.common.types.JvmAnnotationTarget;
import org.eclipse.xtext.common.types.JvmBooleanAnnotationValue;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.JvmStringAnnotationValue;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.JvmTypeAnnotationValue;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.common.types.util.TypeReferences;
import org.eclipse.xtext.util.PolymorphicDispatcher;
import org.eclipse.xtext.util.Strings;
import org.eclipse.xtext.xbase.XBlockExpression;
import org.eclipse.xtext.xbase.XBooleanLiteral;
import org.eclipse.xtext.xbase.XCastedExpression;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XInstanceOfExpression;
import org.eclipse.xtext.xbase.XNullLiteral;
import org.eclipse.xtext.xbase.XNumberLiteral;
import org.eclipse.xtext.xbase.XReturnExpression;
import org.eclipse.xtext.xbase.XStringLiteral;
import org.eclipse.xtext.xbase.XTypeLiteral;
import org.eclipse.xtext.xbase.compiler.ImportManager;
import org.eclipse.xtext.xbase.compiler.output.FakeTreeAppendable;
import org.eclipse.xtext.xbase.jvmmodel.JvmAnnotationReferenceBuilder;
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder;
import org.eclipse.xtext.xbase.lib.Inline;
import org.eclipse.xtext.xbase.typesystem.util.CommonTypeComputationServices;

import io.sarl.lang.generator.GeneratorConfig2;
import io.sarl.lang.generator.GeneratorConfigProvider2;


/** Compiler for crating inline expressions.
 *
 * @author $Author: sgalland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 * @since 0.4
 * @see Inline
 */
@Singleton
public class DefaultInlineExpressionCompiler implements IInlineExpressionCompiler {

	@Inject
	private JvmAnnotationReferenceBuilder.Factory annotationRefBuilderFactory;

	@Inject
	private CommonTypeComputationServices services;

	@Inject
	private JvmTypesBuilder typeBuilder;

	@Inject
	private TypeReferences typeReferences;

	@Inject
	private ConstantExpressionsInterpreter expressionInterpreter;

	@Inject
	private GeneratorConfigProvider2 configProvider;

	private final PolymorphicDispatcher<Void> generateDispatcher;

	/** Constructor.
	 */
	public DefaultInlineExpressionCompiler() {
		this.generateDispatcher = new PolymorphicDispatcher<Void>(
				"_generate", 3, 3, //$NON-NLS-1$
				Collections.singletonList(this)) {
			@Override
			protected Void handleNoSuchMethod(Object... params) {
				return null;
			}
		};
	}

	/** Create an appendable.
	 *
	 * @param imports the import manager.
	 * @return the appendable.
	 */
	@SuppressWarnings("static-method")
	protected InlineAnnotationTreeAppendable newAppendable(ImportManager imports) {
		return new InlineAnnotationTreeAppendable(imports);
	}

	private static XExpression filterSingleOperation(XExpression expression) {
		XExpression content = expression;
		while (content instanceof XBlockExpression) {
			final XBlockExpression blockExpr = (XBlockExpression) content;
			if (blockExpr.getExpressions().size() == 1) {
				content = blockExpr.getExpressions().get(0);
			} else {
				content = null;
			}
		}
		return content;
	}

	@Override
	public void appendInlineAnnotation(JvmAnnotationTarget target, ResourceSet resourceSet,
			String inlineExpression, JvmTypeReference... types) {
		appendInlineAnnotation(target, resourceSet, inlineExpression, false, false, types);
	}

	/** Append the inline annotation to the given operation.
	 *
	 * @param target the target of the annotation.
	 * @param resourceSet the resource set that is associated to the given operation.
	 * @param inlineExpression the inline expression.
	 * @param isConstantExpression indicates if the expression is a constant.
	 * @param isStatementExpression indicates if the expression is a statement.
	 * @param types the types to import if the inline expression is used. The references are cloned by this function.
	 */
	protected void appendInlineAnnotation(JvmAnnotationTarget target, ResourceSet resourceSet,
			String inlineExpression, boolean isConstantExpression, boolean isStatementExpression,
			JvmTypeReference... types) {
		final JvmAnnotationReferenceBuilder annotationTypesBuilder = this.annotationRefBuilderFactory.create(
				resourceSet);
		final JvmAnnotationReference annotationReference = annotationTypesBuilder.annotationRef(
				Inline.class);

		final AnnotationInformation annotationInfo = new AnnotationInformation(annotationReference);

		// Value
		final JvmStringAnnotationValue annotationValue = this.services.getTypesFactory()
				.createJvmStringAnnotationValue();
		annotationValue.getValues().add(inlineExpression);
		annotationValue.setOperation(annotationInfo.valueOperation);
		annotationReference.getExplicitValues().add(annotationValue);

		// Imported
		for (final JvmTypeReference type : types) {
			final JvmTypeAnnotationValue annotationImportedType = this.services.getTypesFactory()
					.createJvmTypeAnnotationValue();
			annotationImportedType.getValues().add(this.typeBuilder.cloneWithProxies(type));
			annotationImportedType.setOperation(annotationInfo.importedOperation);
			annotationReference.getExplicitValues().add(annotationImportedType);
		}

		// Constant
		if (isConstantExpression) {
			final JvmBooleanAnnotationValue annotationConstant = this.services.getTypesFactory()
					.createJvmBooleanAnnotationValue();
			annotationConstant.getValues().add(Boolean.valueOf(isConstantExpression));
			annotationConstant.setOperation(annotationInfo.constantExpressionOperation);
			annotationReference.getExplicitValues().add(annotationConstant);
		}

		// Statement
		if (isStatementExpression) {
			final JvmBooleanAnnotationValue annotationStatement = this.services.getTypesFactory()
					.createJvmBooleanAnnotationValue();
			annotationStatement.getValues().add(Boolean.valueOf(isStatementExpression));
			annotationStatement.setOperation(annotationInfo.statementExpressionOperation);
			annotationReference.getExplicitValues().add(annotationStatement);
		}

		target.getAnnotations().add(annotationReference);
	}

	@Override
	public void appendInlineAnnotation(JvmAnnotationTarget target, XtendExecutable source) {
		final ImportManager imports = new ImportManager();
		final InlineAnnotationTreeAppendable result = newAppendable(imports);
		generate(source.getExpression(), source, result);

		final String content = result.getContent();
		if (!Strings.isEmpty(content)) {
			final List<String> importedTypes = imports.getImports();
			final JvmTypeReference[] importArray = new JvmTypeReference[importedTypes.size()];
			for (int i = 0; i < importArray.length; ++i) {
				importArray[i] = this.typeReferences.getTypeForName(importedTypes.get(i), source);
			}
			appendInlineAnnotation(target, source.eResource().getResourceSet(), content,
					result.isConstant(), result.isStatement(), importArray);
		}
	}

	/** Append the inline annotation to the given operation.
	 *
	 * @param expression the expression of the operation.
	 * @param feature the feature that contains the expression.
	 * @param output the inline code.
	 */
	protected void generate(XExpression expression, XtendExecutable feature, InlineAnnotationTreeAppendable output) {
		final XExpression realExpression = filterSingleOperation(expression);
		if (realExpression != null) {
			final GeneratorConfig2 config = this.configProvider.get(feature);
			if (config.isUseExpressionInterpreterForInlineAnnotation()
					&& feature instanceof XtendFunction) {
				try {
					final Object evaluationResult = this.expressionInterpreter.evaluate(realExpression,
							((XtendFunction) feature).getReturnType());
					if (evaluationResult instanceof CharSequence) {
						output.appendStringConstant(evaluationResult.toString());
					} else if (evaluationResult instanceof JvmTypeReference) {
						output.appendTypeConstant(((JvmTypeReference) evaluationResult).getType());
					} else {
						output.appendConstant(Objects.toString(evaluationResult));
					}
					return;
				} catch (Exception exception) {
					// Ignore all the exceptions
				}
			}
			this.generateDispatcher.invoke(realExpression, feature, output);
		}
	}

	/** Append the inline code for the given XBooleanLiteral.
	 *
	 * @param expression the expression of the operation.
	 * @param feature the feature that contains the expression.
	 * @param output the output.
	 */
	@SuppressWarnings("static-method")
	protected void _generate(XBooleanLiteral expression, XtendExecutable feature, InlineAnnotationTreeAppendable output) {
		output.appendConstant(Boolean.toString(expression.isIsTrue()));
	}

	/** Append the inline code for the given XNullLiteral.
	 *
	 * @param expression the expression of the operation.
	 * @param feature the feature that contains the expression.
	 * @param output the output.
	 */
	@SuppressWarnings("static-method")
	protected void _generate(XNullLiteral expression, XtendExecutable feature, InlineAnnotationTreeAppendable output) {
		output.appendConstant(Objects.toString(null));
	}

	/** Append the inline code for the given XNumberLiteral.
	 *
	 * @param expression the expression of the operation.
	 * @param feature the feature that contains the expression.
	 * @param output the output.
	 */
	@SuppressWarnings("static-method")
	protected void _generate(XNumberLiteral expression, XtendExecutable feature, InlineAnnotationTreeAppendable output) {
		output.appendConstant(expression.getValue());
	}

	/** Append the inline code for the given XStringLiteral.
	 *
	 * @param expression the expression of the operation.
	 * @param feature the feature that contains the expression.
	 * @param output the output.
	 */
	@SuppressWarnings("static-method")
	protected void _generate(XStringLiteral expression, XtendExecutable feature, InlineAnnotationTreeAppendable output) {
		output.appendStringConstant(expression.getValue());
	}

	/** Append the inline code for the given XTypeLiteral.
	 *
	 * @param expression the expression of the operation.
	 * @param feature the feature that contains the expression.
	 * @param output the output.
	 */
	@SuppressWarnings("static-method")
	protected void _generate(XTypeLiteral expression, XtendExecutable feature, InlineAnnotationTreeAppendable output) {
		output.appendTypeConstant(expression.getType());
	}

	/** Append the inline code for the given XCastedExpression.
	 *
	 * @param expression the expression of the operation.
	 * @param feature the feature that contains the expression.
	 * @param output the output.
	 */
	protected void _generate(XCastedExpression expression, XtendExecutable feature, InlineAnnotationTreeAppendable output) {
		final InlineAnnotationTreeAppendable child = newAppendable(output.getImportManager());
		generate(expression.getTarget(), feature, child);
		final String childContent = child.getContent();
		if (!Strings.isEmpty(childContent)) {
			output.append("("); //$NON-NLS-1$
			output.append(expression.getType().getType());
			output.append(")"); //$NON-NLS-1$
			output.append(childContent);
			output.setConstant(child.isConstant());
		}
	}

	/** Append the inline code for the given XInstanceOfExpression.
	 *
	 * @param expression the expression of the operation.
	 * @param feature the feature that contains the expression.
	 * @param output the output.
	 */
	protected void _generate(XInstanceOfExpression expression, XtendExecutable feature,
			InlineAnnotationTreeAppendable output) {
		final InlineAnnotationTreeAppendable child = newAppendable(output.getImportManager());
		generate(expression.getExpression(), feature, child);
		final String childContent = child.getContent();
		if (!Strings.isEmpty(childContent)) {
			output.append(childContent);
			output.append(" instanceof "); //$NON-NLS-1$
			output.append(expression.getType().getType());
			output.setConstant(child.isConstant());
		}
	}

	/** Append the inline code for the given XReturnLiteral.
	 *
	 * @param expression the expression of the operation.
	 * @param feature the feature that contains the expression.
	 * @param output the output.
	 */
	protected void _generate(XReturnExpression expression, XtendExecutable feature, InlineAnnotationTreeAppendable output) {
		generate(expression.getExpression(), feature, output);
	}

	/**
	 * Information about the line annotation.
	 *
	 *
	 * @author $Author: sgalland$
	 * @version $FullVersion$
	 * @mavengroupid $GroupId$
	 * @mavenartifactid $ArtifactId$
	 * @since 0.4
	 */
	@SuppressWarnings("checkstyle:visibilitymodifier")
	private static class AnnotationInformation {

		public final JvmOperation valueOperation;

		public final JvmOperation importedOperation;

		public final JvmOperation constantExpressionOperation;

		public final JvmOperation statementExpressionOperation;

		/** Construct.
		 * @param annotationReference annotation reference.
		 */
		AnnotationInformation(JvmAnnotationReference annotationReference) {
			JvmOperation value = null;
			JvmOperation imported = null;
			JvmOperation constant = null;
			JvmOperation statement = null;
			final Iterator<JvmOperation> operationIterator = annotationReference.getAnnotation()
					.getDeclaredOperations().iterator();
			while ((value == null || imported == null || constant == null || statement == null)
					&& operationIterator.hasNext()) {
				final JvmOperation annotationOperation = operationIterator.next();
				if (annotationOperation.getSimpleName().equals("value")) { //$NON-NLS-1$
					value = annotationOperation;
				} else if (annotationOperation.getSimpleName().equals("imported")) { //$NON-NLS-1$
					imported = annotationOperation;
				} else if (annotationOperation.getSimpleName().equals("constantExpression")) { //$NON-NLS-1$
					constant = annotationOperation;
				} else if (annotationOperation.getSimpleName().equals("statementExpression")) { //$NON-NLS-1$
					statement = annotationOperation;
				}
			}
			assert value != null;
			assert imported != null;
			assert constant != null;
			assert statement != null;
			this.valueOperation = value;
			this.importedOperation = imported;
			this.constantExpressionOperation = constant;
			this.statementExpressionOperation = statement;
		}

	}

	/**
	 * Appendable for creating an inline expression.
	 *
	 * @author $Author: sgalland$
	 * @version $FullVersion$
	 * @mavengroupid $GroupId$
	 * @mavenartifactid $ArtifactId$
	 * @since 0.4
	 */
	@SuppressWarnings("checkstyle:visibilitymodifier")
	protected static class InlineAnnotationTreeAppendable extends FakeTreeAppendable {

		private boolean isConstant;

		private boolean isStatement;

		/** Constructor.
		 *
		 * @param imports the manager of imports.
		 */
		public InlineAnnotationTreeAppendable(ImportManager imports) {
			super(imports);
		}

		@Override
		public ImportManager getImportManager() {
			// Change the visibility.
			return super.getImportManager();
		}

		/** Replies if the expression is constant.
		 *
		 * @return is constant.
		 */
		public boolean isConstant() {
			return this.isConstant;
		}

		/** Replies if the expression is statement.
		 *
		 * @return is statement.
		 */
		public boolean isStatement() {
			return this.isStatement;
		}

		/** Change the constant flag.
		 *
		 * @param isConstant is a constant.
		 */
		public void setConstant(boolean isConstant) {
			this.isConstant = isConstant;
		}

		/** Change the statement flag.
		 *
		 * @param isStatement is a statement.
		 */
		public void setStatement(boolean isStatement) {
			this.isStatement = isStatement;
		}

		/** Append a constant.
		 *
		 * @param constant the constant.
		 */
		public void appendConstant(String constant) {
			append(constant);
			this.isConstant = true;
		}

		/** Append a type constant.
		 *
		 * @param type the type.
		 */
		public void appendTypeConstant(JvmType type) {
			append(type);
			append(".class"); //$NON-NLS-1$
			setConstant(true);
		}

		/** Append a string constant.
		 *
		 * @param stringValue the value of the string.
		 */
		public void appendStringConstant(String stringValue) {
			appendConstant("\"" //$NON-NLS-1$
					+ org.eclipse.xtext.util.Strings.convertToJavaString(stringValue)
					+ "\""); //$NON-NLS-1$
		}

	}

}
