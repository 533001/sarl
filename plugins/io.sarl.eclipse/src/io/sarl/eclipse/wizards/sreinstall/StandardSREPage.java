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
package io.sarl.eclipse.wizards.sreinstall;

import io.sarl.eclipse.launch.sre.ISREInstall;
import io.sarl.eclipse.launch.sre.SARLRuntime;
import io.sarl.eclipse.launch.sre.SREException;
import io.sarl.eclipse.launch.sre.StandardSREInstall;
import io.sarl.eclipse.util.PluginUtil;

import java.io.File;
import java.text.MessageFormat;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.debug.internal.ui.SWTFactory;
import org.eclipse.jdt.internal.debug.ui.JavaDebugImages;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Text;

import com.google.common.base.Strings;

/**
 * Standard implementation of a page for the SRE installation wizard.
 *
 * @author $Author: sgalland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 */
public class StandardSREPage extends AbstractSREInstallPage {

	private Text sreLibraryTextField;
	private Text sreNameTextField;
	private Text sreMainClassTextField;
	private Text sreIdTextField;

	private StandardSREInstall originalSRE;
	private StandardSREInstall workingCopy;

	/**
	 */
	public StandardSREPage() {
		super(PluginUtil.EMPTY_STRING);
	}

	@Override
	public Image getImage() {
		return JavaDebugImages.get(JavaDebugImages.IMG_WIZBAN_LIBRARY);
	}

	@Override
	public void createControl(Composite p) {
		// create a composite with standard margins and spacing
		Composite composite = new Composite(p, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		// SRE location
		SWTFactory.createLabel(composite, Messages.StandardSREPage_0, 1);
		this.sreLibraryTextField = SWTFactory.createSingleText(composite, 1);
		this.sreLibraryTextField.setEditable(false);
		Button folders = SWTFactory.createPushButton(composite, Messages.StandardSREPage_1, null);
		GridData data = (GridData) folders.getLayoutData();
		data.horizontalAlignment = GridData.END;
		//SRE name
		SWTFactory.createLabel(composite, Messages.StandardSREPage_2, 1);
		this.sreNameTextField = SWTFactory.createSingleText(composite, 2);
		//SRE main class
		SWTFactory.createLabel(composite, Messages.StandardSREPage_3, 1);
		this.sreMainClassTextField = SWTFactory.createSingleText(composite, 2);
		//SRE Id
		SWTFactory.createLabel(composite, Messages.StandardSREPage_8, 1);
		this.sreIdTextField = SWTFactory.createSingleText(composite, 2);
		this.sreIdTextField.setEditable(false);

		//add the listeners now to prevent them from monkeying with initialized settings
		this.sreNameTextField.addModifyListener(new ModifyListener() {
			@SuppressWarnings("synthetic-access")
			@Override
			public void modifyText(ModifyEvent e) {
				StandardSREPage.this.workingCopy.setName(
						StandardSREPage.this.sreNameTextField.getText());
				setPageStatus(validate());
				updatePageStatus();
			}
		});
		this.sreMainClassTextField.addModifyListener(new ModifyListener() {
			@SuppressWarnings("synthetic-access")
			@Override
			public void modifyText(ModifyEvent e) {
				StandardSREPage.this.workingCopy.setMainClass(
						StandardSREPage.this.sreMainClassTextField.getText());
				setPageStatus(validate());
				updatePageStatus();
			}
		});
		folders.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("synthetic-access")
			@Override
			public void widgetSelected(SelectionEvent e) {
				File file;
				if (StandardSREPage.this.workingCopy.getJarFile() != null) {
					file = StandardSREPage.this.workingCopy.getJarFile().toFile();
				} else {
					file = null;
				}

				// XXX: JARFileSelectionDialog may be used for selecting a jar file in the workspace.
				FileDialog dialog = new FileDialog(getShell(), SWT.OPEN);
				dialog.setText(Messages.StandardSREPage_4);
				dialog.setFilterExtensions(new String[] {"*.jar"}); //$NON-NLS-1$
				if (file != null && file.exists()) {
					dialog.setFileName(file.getAbsolutePath());
				}
				String selectedFile = dialog.open();
				if (selectedFile != null) {
					IPath path = Path.fromOSString(selectedFile);
					IWorkspace workspace = ResourcesPlugin.getWorkspace();
					IPath workspaceLocation = workspace.getRoot().getLocation();
					if (workspaceLocation.isPrefixOf(path)) {
						path = workspaceLocation.makeRelativeTo(workspaceLocation);
					}
					setSRELibraryPath(path);
				}
			}
		});
		Dialog.applyDialogFont(composite);
		setControl(composite);
		//PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(),
		//IJavaDebugHelpContextIds.EDIT_JRE_STD_VM_WIZARD_PAGE);

		setPageStatus(validate());
		updatePageStatus();
		initializeFields();
	}

	private void setSRELibraryPath(IPath path) {
		StandardSREPage.this.workingCopy.setJarFile(path);
		if (path != null) {
			this.sreLibraryTextField.setText(path.removeTrailingSeparator().lastSegment());
		} else {
			this.sreLibraryTextField.setText(PluginUtil.EMPTY_STRING);
		}
		setPageStatus(validate());
		this.sreNameTextField.setText(this.workingCopy.getName());
		this.sreMainClassTextField.setText(this.workingCopy.getMainClass());
		updatePageStatus();
	}

	@Override
	public boolean performFinish() {
		try {
			String xml = SARLRuntime.getSREAsXML(this.workingCopy);
			SARLRuntime.setSREFromXML(this.originalSRE, xml);
			return true;
		} catch (CoreException e) {
			setErrorMessage(e.getLocalizedMessage());
			return false;
		}
	}

	@Override
	public void initialize(ISREInstall sre) {
		if (!(sre instanceof StandardSREInstall)) {
			throw new SREException("Illegal SRE type: expecting StandardSREInstall."); //$NON-NLS-1$
		}
		setTitle(MessageFormat.format(Messages.StandardSREPage_7, sre.getName()));
		this.originalSRE = (StandardSREInstall) sre;
		this.workingCopy = this.originalSRE.clone();
		this.workingCopy.setNotify(false);
	}

	@Override
	public ISREInstall createSelection(String id) {
		StandardSREInstall sre = new StandardSREInstall(id);
		sre.revalidate();
		initialize(sre);
		return sre;
	}

	/**
	 * Initialize the dialogs fields.
	 */
	private void initializeFields() {
		IPath path = this.workingCopy.getJarFile();
		if (path != null) {
			this.sreLibraryTextField.setText(path.removeTrailingSeparator().lastSegment());
		} else {
			this.sreLibraryTextField.setText(PluginUtil.EMPTY_STRING);
		}
		//
		String name = this.workingCopy.getNameNoDefault();
		this.sreNameTextField.setText(Strings.nullToEmpty(name));
		//
		String mainClass = this.workingCopy.getMainClass();
		this.sreMainClassTextField.setText(Strings.nullToEmpty(mainClass));
		//
		this.sreIdTextField.setText(this.workingCopy.getId());
	}

	private IStatus validate() {
		IStatus s = this.workingCopy.revalidate();
		if (s.isOK()) {
			s = validateNameAgainstOtherSREs(this.workingCopy.getName());
		}
		return s;
	}

}
