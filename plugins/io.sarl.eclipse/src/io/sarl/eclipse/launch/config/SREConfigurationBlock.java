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
package io.sarl.eclipse.launch.config;

import io.sarl.eclipse.launch.sre.ISREInstall;
import io.sarl.eclipse.launch.sre.ISREInstallChangedListener;
import io.sarl.eclipse.launch.sre.SARLRuntime;
import io.sarl.eclipse.launch.sre.SREInstallChangedAdapter;
import io.sarl.eclipse.preferences.SREsPreferencePage;
import io.sarl.eclipse.util.PluginUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.debug.internal.ui.SWTFactory;
import org.eclipse.jdt.internal.debug.ui.actions.ControlAccessibleListener;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.dialogs.PreferencesUtil;

/**
 * Block of widgets that permits to select and
 * configure a SARL runtime environment.
 *
 * @author $Author: sgalland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 */
public class SREConfigurationBlock {

	/** Property name for the SRE configuration.
	 */
	public static final String PROPERTY_SRE_CONFIGURATION = "PROPERTY_SRE_CONFIGURATION"; //$NON-NLS-1$

	/**
	 * This block's control.
	 */
	private Composite control;

	/**
	 * SRE change listeners.
	 */
	private final ListenerList listeners = new ListenerList();

	private ISREInstallChangedListener sreListener;

	private final List<ISREInstall> runtimeEnvironments = new ArrayList<>();
	private Combo runtimeEnvironmentCombo;
	private Button runtimeEnvironmentSearchButton;

	/**
	 */
	public SREConfigurationBlock() {
		//
	}

	/** Add listener on the changes in the SRE configuration.
	 *
	 * @param listener - the listener.
	 */
	public void addPropertyChangeListener(IPropertyChangeListener listener) {
		this.listeners.add(listener);
	}

	/** Add listener on the changes in the SRE configuration.
	 *
	 * @param listener - the listener.
	 */
	public void removePropertyChangeListener(IPropertyChangeListener listener) {
		this.listeners.remove(listener);
	}

	private void firePropertyChange() {
		PropertyChangeEvent event = new PropertyChangeEvent(this,
				PROPERTY_SRE_CONFIGURATION, null,
				getSelectedSRE());
		Object[] listeners = this.listeners.getListeners();
		for (int i = 0; i < listeners.length; i++) {
			IPropertyChangeListener listener = (IPropertyChangeListener) listeners[i];
			listener.propertyChange(event);
		}
	}

	/**
	 * Creates this block's control in the given control.
	 *
	 * @param parent - containing control
	 * @return the control.
	 * @see #getControl()
	 */
	public Control createControl(Composite parent) {
		this.control = SWTFactory.createComposite(
				parent, parent.getFont(), 1, 1, GridData.FILL_HORIZONTAL);
		Group group = SWTFactory.createGroup(this.control,
				Messages.RuntimeEnvironmentTab_1,
				2, 1, GridData.FILL_HORIZONTAL);
		this.runtimeEnvironmentCombo = SWTFactory.createCombo(
				group,
				SWT.DROP_DOWN | SWT.READ_ONLY,
				1,
				new String[0]);
		this.runtimeEnvironmentCombo.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("synthetic-access")
			@Override
			public void widgetSelected(SelectionEvent e) {
				firePropertyChange();
			}
		});
		ControlAccessibleListener.addListener(this.runtimeEnvironmentCombo, group.getText());
		this.runtimeEnvironmentSearchButton = SWTFactory.createPushButton(
				group, Messages.RuntimeEnvironmentTab_2, null);
		this.runtimeEnvironmentSearchButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleInstalledSREsButtonSelected();
			}
		});
		
		return getControl();
	}

	/**
	 * Returns this block's control.
	 *
	 * @return control
	 */
	public Composite getControl() {
		return this.control;
	}

	/** Change the selected SRE.
	 *
	 * @param sre - the sre, if <code>null</code> reset to default.
	 * @return <code>true</code> if the selection changed.
	 */
	public boolean selectSRE(ISREInstall sre) {
		ISREInstall theSRE = sre;
		if (theSRE == null) {
			theSRE = SARLRuntime.getDefaultSREInstall();
		}
		if (theSRE != null) {
			boolean changed = false;
			ISREInstall oldSRE = getSelectedSRE();
			if (oldSRE != theSRE) {
				int index = indexOf(theSRE);
				if (index >= 0) {
					this.runtimeEnvironmentCombo.select(index);
					changed = true;
				}
			}
			if (!this.runtimeEnvironments.isEmpty()) {
				int selection = this.runtimeEnvironmentCombo.getSelectionIndex();
				if (selection < 0 || selection >= this.runtimeEnvironments.size()) {
					// Ensure that there is a selected element
					selection = indexOf(theSRE);
					this.runtimeEnvironmentCombo.select((selection < 0) ? 0 : selection);
					changed = true;
				}
			}
			if (changed) {
				firePropertyChange();
				return true;
			}
		}
		return false;
	}

	private int indexOf(ISREInstall sre) {
		Iterator<ISREInstall> iterator = this.runtimeEnvironments.iterator();
		for (int i = 0; iterator.hasNext(); ++i) {
			ISREInstall s = iterator.next();
			if  (s.getId().equals(sre.getId())) {
				return i;
			}
		}
		return -1;
	}

	private String[] getSRELabels() {
		String[] labels = new String[this.runtimeEnvironments.size()];
		for (int i = 0; i < this.runtimeEnvironments.size(); ++i) {
			labels[i] = this.runtimeEnvironments.get(i).getName();
		}
		return labels;
	}


	/** Replies the selected SARL runtime environment.
	 *
	 * @return the SARL runtime environment or <code>null</code> if
	 * there is no selected SRE.
	 */
	public ISREInstall getSelectedSRE() {
		int index = this.runtimeEnvironmentCombo.getSelectionIndex();
		if (index >= 0 && index < this.runtimeEnvironments.size()) {
			return this.runtimeEnvironments.get(index);
		}
		return null;
	}

	/** Update the enable state of the block.
	 */
	public void updateEnableState() {
		this.runtimeEnvironmentCombo.setEnabled(!this.runtimeEnvironments.isEmpty());
	}

	/** Initialize the block with the installed JREs.
	 * The selection and the components states are not updated
	 * by this function.
	 */
	public void initialize() {
		// Initialize the SRE list
		this.runtimeEnvironments.clear();
		ISREInstall[] sres = SARLRuntime.getSREInstalls();
		Arrays.sort(sres, new Comparator<ISREInstall>() {
			@Override
			public int compare(ISREInstall o1, ISREInstall o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		List<String> labels = new ArrayList<>(sres.length);
		for (int i = 0; i < sres.length; ++i) {
			if (sres[i].getValidity().isOK()) {
				this.runtimeEnvironments.add(sres[i]);
				labels.add(sres[i].getName());
			}
		}
		this.runtimeEnvironmentCombo.setItems(labels.toArray(new String[labels.size()]));
		// Wait for SRE list updates.
		this.sreListener = new InstallChange();
		SARLRuntime.addSREInstallChangedListener(this.sreListener);
	}

	/** Dispose the block.
	 */
	public void dispose() {
		if (this.sreListener != null) {
			SARLRuntime.removeSREInstallChangedListener(this.sreListener);
			this.sreListener = null;
		}
	}

	/** Invoked when the user want to search for a SARL runtime environment.
	 */
	protected void handleInstalledSREsButtonSelected() {
		PreferencesUtil.createPreferenceDialogOn(
				getControl().getShell(),
				SREsPreferencePage.ID,
				new String[] {SREsPreferencePage.ID},
				null).open();
	}

	/** Validate that the given SRE is valid in the context of the SRE configuration.
	 *
	 * @param sre - the SRE.
	 * @return the state of the validation, never <code>null</code>.
	 */
	public IStatus validate(ISREInstall sre) {
		if (this.runtimeEnvironments.isEmpty()) {
			return PluginUtil.createStatus(IStatus.ERROR,
					Messages.RuntimeEnvironmentTab_7);
		}
		return sre.getValidity();
	}

	/**
	 * @author $Author: sgalland$
	 * @version $FullVersion$
	 * @mavengroupid $GroupId$
	 * @mavenartifactid $ArtifactId$
	 */
	private class InstallChange extends SREInstallChangedAdapter {

		/**
		 */
		public InstallChange() {
			//
		}

		@SuppressWarnings("synthetic-access")
		@Override
		public void sreRemoved(ISREInstall sre) {
			int index = indexOf(sre);
			if (index >= 0) {
				ISREInstall selection = getSelectedSRE();
				SREConfigurationBlock.this.runtimeEnvironments.remove(index);
				SREConfigurationBlock.this.runtimeEnvironmentCombo.setItems(getSRELabels());
				if (!selectSRE(selection)) {
					firePropertyChange();
				}
			}
		}

		@SuppressWarnings("synthetic-access")
		@Override
		public void sreChanged(org.eclipse.jdt.launching.PropertyChangeEvent event) {
			firePropertyChange();
		}

		@SuppressWarnings("synthetic-access")
		@Override
		public void sreAdded(ISREInstall sre) {
			if (validate(sre).isOK()) {
				ISREInstall selection = getSelectedSRE();
				SREConfigurationBlock.this.runtimeEnvironments.add(sre);
				SREConfigurationBlock.this.runtimeEnvironmentCombo.setItems(getSRELabels());
				if (selection == null) {
					selectSRE(sre);
				} else {
					selectSRE(selection);
				}
			}
		}

	}

}
