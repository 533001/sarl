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
public abstract class AbstractFormatterTest extends AbstractSarlTest {

	@Inject
	private FormatterTester tester;
	
	/** Assert formatting
	 *
	 * @param input the input.
	 * @param expected the expected input.
	 */
	protected void assertFormatted(String input, final String expected) {
		this.tester.assertFormatted(new Procedures.Procedure1<FormatterTestRequest>() {
			@Override
			public void apply(FormatterTestRequest it) {
				it.setToBeFormatted(input);
				it.setExpectation(expected);
				it.setAllowSyntaxErrors(false);
				it.setAllowUnformattedWhitespace(false);
				it.setUseNodeModel(true);
				it.setUseSerializer(false);
			}
		});
	}
	
}