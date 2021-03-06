/*
 * Copyright (C) 2014-2016 the original authors or authors.
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
package io.sarl.lang.tests.formatting2;

import com.google.inject.Inject;
import org.eclipse.xtext.junit4.formatter.FormatterTestRequest;
import org.eclipse.xtext.junit4.formatter.FormatterTester;
import org.eclipse.xtext.xbase.lib.Procedures;

import io.sarl.tests.api.AbstractSarlTest;

/** Abstract test of a SARL formatter.
 *
 * @author $Author: sgalland$
 * @version $Name$ $Revision$ $Date$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 */
@SuppressWarnings("all")
public abstract class AbstractMemberFormatterTest extends AbstractSarlTest {

	@Inject
	private FormatterTester tester;
	
	/** Assert formatting
	 *
	 * @param input the input.
	 * @param expected the expected input.
	 */
	protected void assertFormatted(CharSequence input, final CharSequence expected) {
		this.tester.assertFormatted(new Procedures.Procedure1<FormatterTestRequest>() {
			@Override
			public void apply(FormatterTestRequest it) {
				it.setToBeFormatted(input);
				it.setExpectation(expected);
				it.setAllowSyntaxErrors(false);
				it.setUseNodeModel(true);
				it.setUseSerializer(false);
			}
		});
	}
	
	/** Replies the keyword for declaring the top element.
	 */
	protected String declarationKeyword() {
		return "agent";
	}
	
	/** Replies the prefix code.
	 */
	protected String prefix() {
		return declarationKeyword() + " EntityX{";
	}
	
	/** Replies the formatted prefix code.
	 */
	protected String formattedPrefix() {
		return declarationKeyword() + " EntityX {";
	}

	/** Replies the postfix code.
	 */
	protected String postfix() {
		return "}";
	}

	/** Replies the formatted postfix code.
	 */
	protected String formattedPostfix() {
		return "}\n";
	}
	
	/** Build an unformatted code.
	 *
	 * @param line the line of code.
	 * @return the unformatted code with the prefix and postfix.
	 */
	protected String unformattedCode(String code) {
		return prefix() + code + postfix();
	}

	/** Build a formatted code.
	 *
	 * @param lines the lines of code.
	 * @return the formatted code with the prefix and postfix.
	 */
	protected String formattedCode(String... lines) {
		return formattedPrefix() + "\n" + multilineString(lines) + "\n" + formattedPostfix();
	}

}