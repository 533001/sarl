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

package io.sarl.lang.ui.tests.contentassist;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.inject.Binder;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.name.Named;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.xtext.Constants;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.junit4.internal.LineDelimiters;
import org.eclipse.xtext.junit4.ui.ContentAssistProcessorTestBuilder;
import org.eclipse.xtext.junit4.util.ResourceLoadHelper;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.contentassist.ConfigurableCompletionProposal;
import org.eclipse.xtext.ui.editor.contentassist.ReplacementTextApplier;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.junit.Assert;

import io.sarl.tests.api.AbstractSarlUiTest;
import io.sarl.tests.api.WorkbenchTestHelper;

@SuppressWarnings("all")
public abstract class AbstractContentAssistTest extends AbstractSarlUiTest implements ResourceLoadHelper {

	private String fileExtension;

	@Inject
	private IGrammarAccess access;

	/** Change the file extension for the scripts.
	 *
	 * @param extensions the file extentions, separated by comas.
	 */
	@Inject
	protected void setExtensions(@Named(Constants.FILE_EXTENSIONS) String extensions) {
		this.fileExtension = extensions.split(",")[0];
	}

	@Override
	protected Module[] getInjectionModules() {
		final Module[] originals = super.getInjectionModules();
		final Module[] newModules = Arrays.copyOf(originals, originals.length + 1);
		newModules[newModules.length - 1] = new Module() {
			@Override
			public void configure(Binder binder) {
				binder.bind(ContentAssistProcessorTestBuilder.Factory.class).to(ProcessorTestBuilderFactory.class);
			}
		};
		return newModules;
	}

	@Override
	public final XtextResource getResourceFor(InputStream stream) {
		try {
			final StringBuilder content = new StringBuilder();
			try (final InputStreamReader reader = new InputStreamReader(stream)) {
				final BufferedReader breader = new BufferedReader(reader);
				String line = breader.readLine();
				while (line != null) {
					content.append(line);
					content.append("\n");
					line = breader.readLine();
				}
			}
			final WorkbenchTestHelper helper = helper();
			final IFile file = helper.createFile("Test." + this.fileExtension, content.toString());
			final XtextResource resource = (XtextResource) helper.getResourceSet().createResource(helper.uri(file));
			resource.load(null);
			return resource;
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

	/** Replies the default prefix.
	 *
	 * <p>The prefix is appended when calling {@link #newBuilder()}.
	 *
	 * @return the default prefix.
	 */
	protected String getPrefix() {
		return "";
	}

	/** Replies the default suffix.
	 *
	 * <p>The suffix is appended when calling {@link #newBuilder()}.
	 *
	 * @return the default suffix.
	 */
	protected String getSuffix() {
		return "";
	}

	/** Create a builder for content assist tests.
	 *
	 * @return the builder.
	 * @throws Exception if the builder cannot be created.
	 */
	protected final ContentAssistProcessorTestBuilder newBuilder() throws Exception {
		final ContentAssistProcessorTestBuilder.Factory factory = getInjector().getInstance(
				ContentAssistProcessorTestBuilder.Factory.class);
		ContentAssistProcessorTestBuilder builder = factory.create(this);
		String prefix = getPrefix();
		if (prefix.length() > 0) {
			builder = builder.appendNl(prefix);
		}
		String suffix = getSuffix();
		if (suffix.length() > 0) {
			builder = builder.appendSuffix(suffix);
		}
		return builder;
	}

	/** Flattening the arrays.
	 *
	 * @param arrays the arrays to be flattened.
	 * @return the content of the arrays.
	 */
	protected static String[] expect(String[]... arrays) {
		List<String> expectation = Lists.newArrayList();
		for(String[] array: arrays) {
			expectation.addAll(Arrays.asList(array));
		}
		return expectation.toArray(new String[expectation.size()]);
	}
	
	/** Assert that all the given texts are not in the proposal.
	 *
	 * @param builder the proposal builder.
	 * @param notExpectations the texts to not be proposed.
	 * @return the builder.
	 * @throws Exception
	 */
	protected static ContentAssistProcessorTestBuilder assertNoText(ContentAssistProcessorTestBuilder builder, String... notExpectations)
			throws Exception {
		ICompletionProposal[] computeCompletionProposals = builder.computeCompletionProposals();

		if (computeCompletionProposals == null) {
			computeCompletionProposals = new ICompletionProposal[0];
		}

		Arrays.sort(notExpectations);
		List<String> sortedNotExpectations = Lists.newArrayList();
		for (String expectation : notExpectations) {
			sortedNotExpectations.add(LineDelimiters.toPlatform(expectation));
		}
		
		for (final ICompletionProposal completionProposal : computeCompletionProposals) {
			String proposedText = getProposedText(completionProposal);
			Assert.assertFalse("Unexpected proposal '" + proposedText + "'",
					sortedNotExpectations.contains(proposedText));
		}

		return builder;
	}

	/** Assert that all the given texts are not in the proposal.
	 *
	 * <p>In opposite to {@link ContentAssistProcessorTestBuilder#assertText(String...)}, this function
	 * is not failing if the more text than the given ones are proposed.
	 *
	 * @param builder the proposal builder.
	 * @param expectations the texts to be proposed.
	 * @return the builder.
	 * @throws Exception
	 */
	protected static ContentAssistProcessorTestBuilder assertTextInsideProposals(ContentAssistProcessorTestBuilder builder,
			String... expectations)
			throws Exception {
		ICompletionProposal[] computeCompletionProposals = builder.computeCompletionProposals();

		if (computeCompletionProposals == null) {
			computeCompletionProposals = new ICompletionProposal[0];
		}

		Arrays.sort(expectations);
		final List<String> sortedExpectations = Lists.newArrayList();
		for (String expectation : expectations) {
			sortedExpectations.add(LineDelimiters.toPlatform(expectation));
		}
		
		for (final ICompletionProposal completionProposal : computeCompletionProposals) {
			String proposedText = getProposedText(completionProposal);
			sortedExpectations.remove(proposedText);
		}

		if (!sortedExpectations.isEmpty()) {
			Assert.fail("Expecting text: " + sortedExpectations.toString());
		}
		
		return builder;
	}

	private static String getProposedText(ICompletionProposal completionProposal) {
		String proposedText = completionProposal.getDisplayString();
		if (completionProposal instanceof ConfigurableCompletionProposal) {
			ConfigurableCompletionProposal configurableProposal = (ConfigurableCompletionProposal) completionProposal;
			proposedText = configurableProposal.getReplacementString();
			if (configurableProposal.getTextApplier() instanceof ReplacementTextApplier) {
				proposedText = ((ReplacementTextApplier) configurableProposal.getTextApplier())
						.getActualReplacementString(configurableProposal);
			}
		}
		return proposedText;
	}

	/**
	 * @author $Author: sgalland$
	 * @version $FullVersion$
	 * @mavengroupid $GroupId$
	 * @mavenartifactid $ArtifactId$
	 */
	public static class ProcessorTestBuilderFactory extends ContentAssistProcessorTestBuilder.Factory {

		private final Injector injector;
		
		@Inject
	    public ProcessorTestBuilderFactory(Injector injector) {
	    	super(injector);
	    	this.injector = injector;
	    }
		
		public ContentAssistProcessorTestBuilder create(ResourceLoadHelper resourceLoadHelper) throws Exception {
	    	final ProcessorTestBuilder builder = new ProcessorTestBuilder(this.injector, resourceLoadHelper);
	    	this.injector.injectMembers(builder);
	    	return builder;
	    }
		
	}

	/**
	 * @author $Author: sgalland$
	 * @version $FullVersion$
	 * @mavengroupid $GroupId$
	 * @mavenartifactid $ArtifactId$
	 */
	private static class ProcessorTestBuilder extends ContentAssistProcessorTestBuilder {

		@Inject
		private IWorkbench workbench;
		
		public ProcessorTestBuilder(Injector injector, ResourceLoadHelper helper) throws Exception {
			super(injector, helper);
		}
		
		protected Shell getShell() {
			if (this.workbench != null) {
				final IWorkbenchWindow window = this.workbench.getActiveWorkbenchWindow();
				if (window != null) {
					return window.getShell();
				} else {
					for (final IWorkbenchWindow window1 : this.workbench.getWorkbenchWindows()) {
						Shell shell = window.getShell();
						if (shell != null) {
							return shell;
						}
					}
				}
			}
			return null;
		}
		
		@Override
		protected ICompletionProposal[] computeCompletionProposals(final IXtextDocument xtextDocument, int cursorPosition)
				throws BadLocationException {
			if (isEclipseRuntimeEnvironment()) {
				Shell shell = getShell();
				if (shell != null) {
					return computeCompletionProposals(xtextDocument, cursorPosition, shell);
				}
			}
			final Display display = Display.getDefault();
			final List<ICompletionProposal[]> resultBuffer = new ArrayList<>(1);
			final List<BadLocationException> exceptionBuffer = new ArrayList<>(1);
			display.syncExec(() -> {
				final Shell asyncShell = new Shell(display);
				try {
					final ICompletionProposal[] result = computeCompletionProposals(xtextDocument, cursorPosition, asyncShell);
					resultBuffer.add(result);
				} catch (BadLocationException exception) {
					exceptionBuffer.add(exception); 
				} finally {
					asyncShell.dispose();
				}
			});
			if (!exceptionBuffer.isEmpty()) {
				throw exceptionBuffer.get(0);
			}
			return resultBuffer.get(0);
		}
		
		@Override
		public ContentAssistProcessorTestBuilder appendAndApplyProposal(String model, int position, String proposalString) throws Exception {
			throw new UnsupportedOperationException("must be overriden for Shell access");
		}
		
		public ContentAssistProcessorTestBuilder applyProposal(int position, String proposalString) throws Exception {
			throw new UnsupportedOperationException("must be overriden for Shell access");
		}
		
		public ContentAssistProcessorTestBuilder assertMatchString(String matchString)
				throws Exception {
			throw new UnsupportedOperationException("must be overriden for Shell access");
		}

	}
	
}
