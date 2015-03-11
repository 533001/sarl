/*
 * $Id$
 *
 * SARL is an general-purpose agent programming language.
 * More details on http://www.sarl.io
 *
 * Copyright (C) 2014-2015 Sebastian RODRIGUEZ, Nicolas GAUD, Stéphane GALLAND.
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
package io.sarl.docs.gettingstarted

import com.google.inject.Inject
import io.sarl.docs.utils.SARLParser
import io.sarl.docs.utils.SARLSpecCreator
import org.jnario.runner.CreateWith

import static extension io.sarl.docs.utils.SpecificationTools.*
import static extension org.junit.Assume.*
import io.sarl.eclipse.SARLConfig

/* @outline
 *
 * For developing with SARL, you should create a project.
 * This document describes two ways for created SARL projects
 * in Eclipse.
 */
@CreateWith(SARLSpecCreator)
describe "Create First Project" {
	
	@Inject extension SARLParser

	/* For creating a project, you should open your Eclipse and click on
	 * **File > New > Projects**, and select *SARL Project* in
	 * the SARL category.
	 *
	 * ![Select the SARL Project Type](./new_sarl_project_screen_1.png)
	 *
	 *
	 * After clicking on **Next**, the wizard is displaying the first page for creating a SARL project.
	 */
	describe "Create a SARL Project" {
		
		/* You must enter the name of your project. You could change the standard SARL and Java environment
		 * configurations as well.
		 * 
		 *
		 * ![Enter the Project Information](./new_sarl_project_screen_2.png)
		 *
		 *
		 * Then you could click on **Next** for continuing the edition of the project's properties, or
		 * simply click on the **Finish** button for creating the project with the default properties.
		 * 
		 * 
		 * The rest of this section is devoted to the edition of the additional properties for the SARL project.
		 * 
		 * @filter(.*) 
		 */
		fact "Step 1: Entering the project information" {
			"./new_sarl_project_screen_1.png" should beAccessibleFrom this
			"./new_sarl_project_screen_2.png" should beAccessibleFrom this
		}
		 
		/* The second page of the wizard contains the building settings.
		 * Two tabs are really interesting: the *Source* and the *Libraries*.
		 * 
		 * The *Source* tab defines the folders in your project that must contains source code files.
		 * By default, a SARL project is composed of four source folders:
		 *
		 * * `src/main/java`: for your Java classes;
		 * * `src/main/sarl`: for your SARL scripts;
		 * * `src/main/generated-sources/xtend`: for the Java codes generated by the SARL compiler (you should not change them yourself);
		 * * `src/main/resources`: for the files that are not SARL nor Java code.
		 * 
		 * The default output folder is `target/classes`.
		 * 
		 * <note>The names of these folders are following the
		 * conventions of a Maven-based project (described below). In this way, you will be able to 
		 * turn the Maven nature on your SARL project on/off.</note>
		 *
		 * ![Source Code Folders](./new_sarl_project_screen_3.png)
		 * 
		 * @filter(.*) 
		 */
		fact "Step 2: Configuration of the source folders" {
			SARLConfig::FOLDER_SOURCE_JAVA should be "src/main/java"
			SARLConfig::FOLDER_SOURCE_SARL should be "src/main/sarl"
			SARLConfig::FOLDER_SOURCE_GENERATED should be "src/main/generated-sources/xtend"
			SARLConfig::FOLDER_RESOURCES should be "src/main/resources"
			SARLConfig::FOLDER_BIN should be "target/classes"
			"./new_sarl_project_screen_3.png" should beAccessibleFrom this
		}
		 
	}

	/* For creating a project with both the Maven and SARL natures, you should open your 
	 * Eclipse and click on
	 * **File > New > Others > Maven > Maven Project**.
	 * 
	 * Follow the steps of the project creation wizard, and finally click on the **Finish** button.
	 */
	describe "Create a Project with the Maven and SARL Natures" {
		
		/* Open the file `pom.xml`, and edit it for obtaining a content similar to the
		 * configuration below.
		 * 
		 * Replace the version number `%sarlversion%` of SARL
		 * with the one you want to use. You could search on the
		 * [Maven Central Repository](http://search.maven.org) for
		 * the last available version.
		 * The file [VERSION.txt](%sarlmavenrepository%/VERSION.txt)
		 * provides the latest version numbers of the SARL artifacts, as well.
		 * 
		 *     <project>
		 *        ...
		 *        <properties>
		 *           ...
		 *           <sarl.version>%sarlversion%</sarl.version>
		 *        </properties>
		 *        ...
		 *        <build>
		 *           <plugins>
		 *              ...
		 *              <plugin>
		 *                 <groupId>io.sarl.maven</groupId>
		 *                 <artifactId>sarl-maven-plugin</artifactId>
		 *                 <version>${sarl.version}</version>
		 *                 <extensions>true</extensions>
		 *                 <configuration>
		 *                    <source>%compilerlevel%</source>
		 *                    <target>%compilerlevel%</target>
		 *                    <encoding>%encoding%</encoding>
		 *                 </configuration>
		 *              </plugin>
		 *           </plugins>
		 *        </build>
		 *        ...
		 *        <dependencies>
		 *           ...
		 *           <dependency>
		 *              <groupId>io.sarl.maven</groupId>
		 *              <artifactId>io.sarl.maven.sdk</artifactId>
		 *              <version>${sarl.version}</version>
		 *              <type>pom</type>
		 *           </dependency>
		 *           ...
		 *        </dependencies>
		 *        ...
		 *     </project>
		 * 
		 * The Maven configuration is based on the use of `sarl-maven-plugin`.
		 * This plugin is in charge of compiling the SARL and the Java files.
		 * 
		 * <important>You must set the `extensions` tag to true for the 
		 * `sarl-maven-plugin` plugin. If you missed to set it, the plugin
		 * will not able to be integrated in the Maven life-cycle. The
		 * consequence will be that only the Java compiler will be invoked.</important>
		 * 
		 * @filter(.*) 
		 */
		fact "Edit the Maven configuration" {
			// Check if the SARL code is generated in the expected folder
			SARLConfig::FOLDER_SOURCE_GENERATED should be "src/main/generated-sources/xtend"
			// The checks are valid only if the macro replacements were done.
			// The replacements are done by Maven.
			// So, Eclipse Junit tools do not make the replacements.
			System.getProperty("sun.java.command", "").startsWith("org.eclipse.jdt.internal.junit.").assumeFalse
			// URLs should not end with a slash
			"%website%" should beURL "!file"
			"%sarlmavenrepository%" should beURL "!file"
		}
		
		/* For executing your SARL program, you must select a
		 * [runtime environment](%website%/runtime/index.html).
		 * 
		 * The runtime environment that is recommended by the developers of SARL
		 * is [Janus](http://www.janusproject.io). 
		 * 
		 * If you want to embed the runtime environment inside the Jar files
		 * of your SARL application, it is recommended to put it in the
		 * Maven dependencies.
		 * 
		 * <note>You could remove the dependencies to the 
		 * SARL artifacts in the previous Maven configuration. Indeed, the Janus platform depends
		 * already on. You will obtain the SARL artifacts by transitivity.</note>
		 *
		 * Replace the version number (`%janusversion%`) of the [Janus platform](http://www.janusproject.io)
		 * with the one you want to use. You could search on the
		 * [Maven Central Repository](http://search.maven.org) for
		 * the last available version.
		 * The file [VERSION.txt](%janusmavenrepository%/VERSION.txt)
		 * provides the latest version numbers of the Janus artifacts, as well.
		 * 
		 * 
		 *     <project>
		 *        ...
		 *        <properties>
		 *           ...
		 *           <janus.version>%janusversion%</janus.version>
		 *        </properties>
		 *        ...
		 *        <dependencies>
		 *           ...
		 *           <dependency>
		 *              <groupId>io.janusproject</groupId>
		 *              <artifactId>io.janusproject.kernel</artifactId>
		 *              <version>${janus.version}</version>
		 *           </dependency>
		 *           <dependency>
		 *              <groupId>io.sarl.maven</groupId>
		 *              <artifactId>io.sarl.maven.sdk</artifactId>
		 *              <version>${sarl.version}</version>
		 *              <type>pom</type>
		 *              <exclusions>
		 *                 <exclusion>
		 *                    <groupId>com.google.guava</groupId>
		 *                    <artifactId>guava</artifactId>
		 *                 </exclusion>
		 *              </exclusions>
		 *           </dependency>
		 *           ...
		 *        </dependencies>
		 *        ...
		 *     </project>
		 * 
		 * <important>If you want to have the dependencies to both 
		 * `io.sarl.maven.sdk` and `io.janusproject.kernel` in your
		 * POM file, you must be sure that the imported version
		 * of the Google Guava library is the one provided by the Janus
		 * platform. For ensuring this, you must exclude the Guava
		 * library from the transitive dependencies of
		 * `io.sarl.maven.sdk`</important>
		 * 
		 * @filter(.*) 
		 */
		fact "Configuration a runtime environment (optional)" {
			// The checks are valid only if the macro replacements were done.
			// The replacements are done by Maven.
			// So, Eclipse Junit tools do not make the replacements.
			System.getProperty("sun.java.command", "").startsWith("org.eclipse.jdt.internal.junit.").assumeFalse
			// URLs should not end with a slash
			"%website%" should beURL "!file"
			"%janusmavenrepository%" should beURL "!file"
		}
	} 
	
	/*
	 * In the next section, we will learn how to create our first agent.
	 * 
	 * [Next>](AgentDefinitionIntroductionSpec.html)
	 * 
	 * @filter(.*)
	 */
	fact "What's next?" {
		"AgentDefinitionIntroductionSpec.html" should beAccessibleFrom this
	}

}
