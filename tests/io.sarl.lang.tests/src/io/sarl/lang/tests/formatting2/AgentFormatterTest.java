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

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import com.google.inject.Inject;
import junit.framework.TestSuite;
import org.eclipse.xtext.junit4.formatter.FormatterTestRequest;
import org.eclipse.xtext.junit4.formatter.FormatterTester;
import org.eclipse.xtext.util.Strings;
import org.eclipse.xtext.xbase.lib.Procedures;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.builders.AllDefaultPossibilitiesBuilder;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.runners.model.InitializationError;

import io.sarl.lang.tests.compilation.general.ArgDefaultValueCompilerTest;
import io.sarl.tests.api.AbstractSarlTest;

/** Tests for formatting agents.
 *
 * @author $Author: sgalland$
 * @version $Name$ $Revision$ $Date$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 */
@RunWith(Suite.class)
@SuiteClasses({
	AgentFormatterTest.FullFormatterAPITest.class,
})
@SuppressWarnings("all")
public class AgentFormatterTest {

	/**
	 * @author $Author: sgalland$
	 * @version $FullVersion$
	 * @mavengroupid $GroupId$
	 * @mavenartifactid $ArtifactId$
	 */
	public static class FullFormatterAPITest extends AbstractFormatterTest {
	
		@Test
		public void empty() throws Exception {
			String source = "agent  EntityX{}";
			String expected = multilineString(
					"agent EntityX {",
					"}",
					"");
			assertFormatted(source, expected);
		}
	
		@Test
		public void modifiers() throws Exception {
			String source = "public    static    agent EntityX{}";
			String expected = multilineString(
					"public static agent EntityX {",
					"}",
					"");
			assertFormatted(source, expected);
		}
	
		@Test
		public void twoAnnotations() throws Exception {
			String source = "@Pure@Beta    agent EntityX{}";
			String expected = multilineString(
					"@Pure @Beta agent EntityX {",
					"}",
					"");
			assertFormatted(source, expected);
		}
	
		@Test
		public void threeAnnotations() throws Exception {
			String source = "@Pure@Beta\n@Hello    agent EntityX{}";
			String expected = multilineString(
					"@Pure @Beta",
					"@Hello agent EntityX {",
					"}",
					"");
			assertFormatted(source, expected);
		}
	
		@Test
		public void annotationValue() throws Exception {
			String source = "@SuppressWarnings(        value= \"name\"   )agent EntityX{}";
			String expected = multilineString(
					"@SuppressWarnings(value = \"name\") agent EntityX {",
					"}",
					"");
			assertFormatted(source, expected);
		}
	
		@Test
		public void annotationImplicitValue() throws Exception {
			String source = "@SuppressWarnings(   \"name\"   )agent EntityX{}";
			String expected = multilineString(
					"@SuppressWarnings(\"name\") agent EntityX {",
					"}",
					"");
			assertFormatted(source, expected);
		}
	
		@Test
		public void extend() throws Exception {
			String source = "agent EntityX    extends    EntityY {}";
			String expected = multilineString(
					"agent EntityX extends EntityY {",
					"}",
					"");
			assertFormatted(source, expected);
		}
	
		@Test
		public void memberIndent() throws Exception {
			String source = "agent EntityX{var x:int}";
			String expected = multilineString(
					"agent EntityX {",
					"	var x : int",
					"}",
					"");
			assertFormatted(source, expected);
		}
	
		@Test
		public void mlStandardComment1() throws Exception {
			String source = "/*Hello world.\n* That's the second line.\n*/agent A{}";
			String expected = multilineString(
					"/* Hello world.",
					" * That's the second line.",
					" */",
					"agent A {",
					"}",
					"");
			assertFormatted(source, expected);
		}
	
		@Test
		public void mlStandardComment2() throws Exception {
			String source = "/*Hello world.\nThat's the second line.*/agent A{}";
			String expected = multilineString(
					"/* Hello world.",
					" * That's the second line.",
					" */",
					"agent A {",
					"}",
					"");
			assertFormatted(source, expected);
		}
	
		@Test
		public void mlStandardComment3() throws Exception {
			String source = "/*      Hello world.     */agent A{}";
			String expected = multilineString(
					"/* Hello world.",
					" */",
					"agent A {",
					"}",
					"");
			assertFormatted(source, expected);
		}
	
		@Test
		public void mlStandardComment4() throws Exception {
			String source = "/*      Hello world.     */agent A{/*Second comment*/}";
			String expected = multilineString(
					"/* Hello world.",
					" */",
					"agent A {",
					"\t/* Second comment",
					"\t */",
					"}",
					"");
			assertFormatted(source, expected);
		}
	
		@Test
		public void mlStandardComment5() throws Exception {
			String source = "/*\tHello world.     */agent A{}";
			String expected = multilineString(
					"/* Hello world.",
					" */",
					"agent A {",
					"}",
					"");
			assertFormatted(source, expected);
		}
	
		@Test
		public void mlStandardComment6() throws Exception {
			String source = "/*  Hello world.     */agent A{}";
			String expected = multilineString(
					"/* Hello world.",
					" */",
					"agent A {",
					"}",
					"");
			assertFormatted(source, expected);
		}
	
		@Test
		public void mlJavaComment() throws Exception {
			String source = "/**Hello world.\nThat's the second line.*/agent A{}";
			String expected = multilineString(
					"/** Hello world.",
					" * That's the second line.",
					" */",
					"agent A {",
					"}",
					"");
			assertFormatted(source, expected);
		}
	
		@Test
		public void slComment() throws Exception {
			String source = "\n//Hello world.\nagent A{}";
			String expected = multilineString(
					"// Hello world.",
					"agent A {",
					"}",
					"");
			assertFormatted(source, expected);
		}

	}
	
}