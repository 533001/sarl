/**
 */
package io.sarl.lang.sarl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.xtend.core.xtend.XtendPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see io.sarl.lang.sarl.SarlFactory
 * @model kind="package"
 * @generated
 */
public interface SarlPackage extends EPackage
{
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "sarl";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.sarl.io/lang/SARL";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "sarl";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SarlPackage eINSTANCE = io.sarl.lang.sarl.impl.SarlPackageImpl.init();

	/**
	 * The meta object id for the '{@link io.sarl.lang.sarl.impl.SarlScriptImpl <em>Script</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see io.sarl.lang.sarl.impl.SarlScriptImpl
	 * @see io.sarl.lang.sarl.impl.SarlPackageImpl#getSarlScript()
	 * @generated
	 */
	int SARL_SCRIPT = 0;

	/**
	 * The feature id for the '<em><b>Import Section</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_SCRIPT__IMPORT_SECTION = XtendPackage.XTEND_FILE__IMPORT_SECTION;

	/**
	 * The feature id for the '<em><b>Xtend Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_SCRIPT__XTEND_TYPES = XtendPackage.XTEND_FILE__XTEND_TYPES;

	/**
	 * The feature id for the '<em><b>Package</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_SCRIPT__PACKAGE = XtendPackage.XTEND_FILE__PACKAGE;

	/**
	 * The number of structural features of the '<em>Script</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_SCRIPT_FEATURE_COUNT = XtendPackage.XTEND_FILE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link io.sarl.lang.sarl.impl.SarlFieldImpl <em>Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see io.sarl.lang.sarl.impl.SarlFieldImpl
	 * @see io.sarl.lang.sarl.impl.SarlPackageImpl#getSarlField()
	 * @generated
	 */
	int SARL_FIELD = 1;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_FIELD__ANNOTATIONS = XtendPackage.XTEND_FIELD__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Annotation Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_FIELD__ANNOTATION_INFO = XtendPackage.XTEND_FIELD__ANNOTATION_INFO;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_FIELD__MODIFIERS = XtendPackage.XTEND_FIELD__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Declaring Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_FIELD__DECLARING_TYPE = XtendPackage.XTEND_FIELD__DECLARING_TYPE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_FIELD__NAME = XtendPackage.XTEND_FIELD__NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_FIELD__TYPE = XtendPackage.XTEND_FIELD__TYPE;

	/**
	 * The feature id for the '<em><b>Initial Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_FIELD__INITIAL_VALUE = XtendPackage.XTEND_FIELD__INITIAL_VALUE;

	/**
	 * The number of structural features of the '<em>Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_FIELD_FEATURE_COUNT = XtendPackage.XTEND_FIELD_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link io.sarl.lang.sarl.impl.SarlActionImpl <em>Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see io.sarl.lang.sarl.impl.SarlActionImpl
	 * @see io.sarl.lang.sarl.impl.SarlPackageImpl#getSarlAction()
	 * @generated
	 */
	int SARL_ACTION = 2;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_ACTION__ANNOTATIONS = XtendPackage.XTEND_FUNCTION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Annotation Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_ACTION__ANNOTATION_INFO = XtendPackage.XTEND_FUNCTION__ANNOTATION_INFO;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_ACTION__MODIFIERS = XtendPackage.XTEND_FUNCTION__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Declaring Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_ACTION__DECLARING_TYPE = XtendPackage.XTEND_FUNCTION__DECLARING_TYPE;

	/**
	 * The feature id for the '<em><b>Exceptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_ACTION__EXCEPTIONS = XtendPackage.XTEND_FUNCTION__EXCEPTIONS;

	/**
	 * The feature id for the '<em><b>Type Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_ACTION__TYPE_PARAMETERS = XtendPackage.XTEND_FUNCTION__TYPE_PARAMETERS;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_ACTION__EXPRESSION = XtendPackage.XTEND_FUNCTION__EXPRESSION;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_ACTION__PARAMETERS = XtendPackage.XTEND_FUNCTION__PARAMETERS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_ACTION__NAME = XtendPackage.XTEND_FUNCTION__NAME;

	/**
	 * The feature id for the '<em><b>Return Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_ACTION__RETURN_TYPE = XtendPackage.XTEND_FUNCTION__RETURN_TYPE;

	/**
	 * The feature id for the '<em><b>Create Extension Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_ACTION__CREATE_EXTENSION_INFO = XtendPackage.XTEND_FUNCTION__CREATE_EXTENSION_INFO;

	/**
	 * The feature id for the '<em><b>Fired Events</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_ACTION__FIRED_EVENTS = XtendPackage.XTEND_FUNCTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_ACTION_FEATURE_COUNT = XtendPackage.XTEND_FUNCTION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link io.sarl.lang.sarl.impl.SarlConstructorImpl <em>Constructor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see io.sarl.lang.sarl.impl.SarlConstructorImpl
	 * @see io.sarl.lang.sarl.impl.SarlPackageImpl#getSarlConstructor()
	 * @generated
	 */
	int SARL_CONSTRUCTOR = 3;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_CONSTRUCTOR__ANNOTATIONS = XtendPackage.XTEND_CONSTRUCTOR__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Annotation Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_CONSTRUCTOR__ANNOTATION_INFO = XtendPackage.XTEND_CONSTRUCTOR__ANNOTATION_INFO;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_CONSTRUCTOR__MODIFIERS = XtendPackage.XTEND_CONSTRUCTOR__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Declaring Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_CONSTRUCTOR__DECLARING_TYPE = XtendPackage.XTEND_CONSTRUCTOR__DECLARING_TYPE;

	/**
	 * The feature id for the '<em><b>Exceptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_CONSTRUCTOR__EXCEPTIONS = XtendPackage.XTEND_CONSTRUCTOR__EXCEPTIONS;

	/**
	 * The feature id for the '<em><b>Type Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_CONSTRUCTOR__TYPE_PARAMETERS = XtendPackage.XTEND_CONSTRUCTOR__TYPE_PARAMETERS;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_CONSTRUCTOR__EXPRESSION = XtendPackage.XTEND_CONSTRUCTOR__EXPRESSION;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_CONSTRUCTOR__PARAMETERS = XtendPackage.XTEND_CONSTRUCTOR__PARAMETERS;

	/**
	 * The number of structural features of the '<em>Constructor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_CONSTRUCTOR_FEATURE_COUNT = XtendPackage.XTEND_CONSTRUCTOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link io.sarl.lang.sarl.impl.SarlBehaviorUnitImpl <em>Behavior Unit</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see io.sarl.lang.sarl.impl.SarlBehaviorUnitImpl
	 * @see io.sarl.lang.sarl.impl.SarlPackageImpl#getSarlBehaviorUnit()
	 * @generated
	 */
	int SARL_BEHAVIOR_UNIT = 4;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_BEHAVIOR_UNIT__ANNOTATIONS = XtendPackage.XTEND_MEMBER__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Annotation Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_BEHAVIOR_UNIT__ANNOTATION_INFO = XtendPackage.XTEND_MEMBER__ANNOTATION_INFO;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_BEHAVIOR_UNIT__MODIFIERS = XtendPackage.XTEND_MEMBER__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Declaring Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_BEHAVIOR_UNIT__DECLARING_TYPE = XtendPackage.XTEND_MEMBER__DECLARING_TYPE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_BEHAVIOR_UNIT__NAME = XtendPackage.XTEND_MEMBER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Guard</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_BEHAVIOR_UNIT__GUARD = XtendPackage.XTEND_MEMBER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_BEHAVIOR_UNIT__EXPRESSION = XtendPackage.XTEND_MEMBER_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Behavior Unit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_BEHAVIOR_UNIT_FEATURE_COUNT = XtendPackage.XTEND_MEMBER_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link io.sarl.lang.sarl.impl.SarlCapacityUsesImpl <em>Capacity Uses</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see io.sarl.lang.sarl.impl.SarlCapacityUsesImpl
	 * @see io.sarl.lang.sarl.impl.SarlPackageImpl#getSarlCapacityUses()
	 * @generated
	 */
	int SARL_CAPACITY_USES = 5;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_CAPACITY_USES__ANNOTATIONS = XtendPackage.XTEND_MEMBER__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Annotation Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_CAPACITY_USES__ANNOTATION_INFO = XtendPackage.XTEND_MEMBER__ANNOTATION_INFO;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_CAPACITY_USES__MODIFIERS = XtendPackage.XTEND_MEMBER__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Declaring Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_CAPACITY_USES__DECLARING_TYPE = XtendPackage.XTEND_MEMBER__DECLARING_TYPE;

	/**
	 * The feature id for the '<em><b>Capacities</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_CAPACITY_USES__CAPACITIES = XtendPackage.XTEND_MEMBER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Capacity Uses</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_CAPACITY_USES_FEATURE_COUNT = XtendPackage.XTEND_MEMBER_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link io.sarl.lang.sarl.impl.SarlRequiredCapacityImpl <em>Required Capacity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see io.sarl.lang.sarl.impl.SarlRequiredCapacityImpl
	 * @see io.sarl.lang.sarl.impl.SarlPackageImpl#getSarlRequiredCapacity()
	 * @generated
	 */
	int SARL_REQUIRED_CAPACITY = 6;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_REQUIRED_CAPACITY__ANNOTATIONS = XtendPackage.XTEND_MEMBER__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Annotation Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_REQUIRED_CAPACITY__ANNOTATION_INFO = XtendPackage.XTEND_MEMBER__ANNOTATION_INFO;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_REQUIRED_CAPACITY__MODIFIERS = XtendPackage.XTEND_MEMBER__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Declaring Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_REQUIRED_CAPACITY__DECLARING_TYPE = XtendPackage.XTEND_MEMBER__DECLARING_TYPE;

	/**
	 * The feature id for the '<em><b>Capacities</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_REQUIRED_CAPACITY__CAPACITIES = XtendPackage.XTEND_MEMBER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Required Capacity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_REQUIRED_CAPACITY_FEATURE_COUNT = XtendPackage.XTEND_MEMBER_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link io.sarl.lang.sarl.impl.SarlClassImpl <em>Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see io.sarl.lang.sarl.impl.SarlClassImpl
	 * @see io.sarl.lang.sarl.impl.SarlPackageImpl#getSarlClass()
	 * @generated
	 */
	int SARL_CLASS = 7;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_CLASS__ANNOTATIONS = XtendPackage.XTEND_CLASS__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Annotation Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_CLASS__ANNOTATION_INFO = XtendPackage.XTEND_CLASS__ANNOTATION_INFO;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_CLASS__MODIFIERS = XtendPackage.XTEND_CLASS__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Declaring Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_CLASS__DECLARING_TYPE = XtendPackage.XTEND_CLASS__DECLARING_TYPE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_CLASS__NAME = XtendPackage.XTEND_CLASS__NAME;

	/**
	 * The feature id for the '<em><b>Members</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_CLASS__MEMBERS = XtendPackage.XTEND_CLASS__MEMBERS;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_CLASS__EXTENDS = XtendPackage.XTEND_CLASS__EXTENDS;

	/**
	 * The feature id for the '<em><b>Implements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_CLASS__IMPLEMENTS = XtendPackage.XTEND_CLASS__IMPLEMENTS;

	/**
	 * The feature id for the '<em><b>Type Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_CLASS__TYPE_PARAMETERS = XtendPackage.XTEND_CLASS__TYPE_PARAMETERS;

	/**
	 * The number of structural features of the '<em>Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_CLASS_FEATURE_COUNT = XtendPackage.XTEND_CLASS_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link io.sarl.lang.sarl.impl.SarlInterfaceImpl <em>Interface</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see io.sarl.lang.sarl.impl.SarlInterfaceImpl
	 * @see io.sarl.lang.sarl.impl.SarlPackageImpl#getSarlInterface()
	 * @generated
	 */
	int SARL_INTERFACE = 8;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_INTERFACE__ANNOTATIONS = XtendPackage.XTEND_INTERFACE__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Annotation Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_INTERFACE__ANNOTATION_INFO = XtendPackage.XTEND_INTERFACE__ANNOTATION_INFO;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_INTERFACE__MODIFIERS = XtendPackage.XTEND_INTERFACE__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Declaring Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_INTERFACE__DECLARING_TYPE = XtendPackage.XTEND_INTERFACE__DECLARING_TYPE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_INTERFACE__NAME = XtendPackage.XTEND_INTERFACE__NAME;

	/**
	 * The feature id for the '<em><b>Members</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_INTERFACE__MEMBERS = XtendPackage.XTEND_INTERFACE__MEMBERS;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_INTERFACE__EXTENDS = XtendPackage.XTEND_INTERFACE__EXTENDS;

	/**
	 * The feature id for the '<em><b>Type Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_INTERFACE__TYPE_PARAMETERS = XtendPackage.XTEND_INTERFACE__TYPE_PARAMETERS;

	/**
	 * The number of structural features of the '<em>Interface</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_INTERFACE_FEATURE_COUNT = XtendPackage.XTEND_INTERFACE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link io.sarl.lang.sarl.impl.SarlEnumerationImpl <em>Enumeration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see io.sarl.lang.sarl.impl.SarlEnumerationImpl
	 * @see io.sarl.lang.sarl.impl.SarlPackageImpl#getSarlEnumeration()
	 * @generated
	 */
	int SARL_ENUMERATION = 9;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_ENUMERATION__ANNOTATIONS = XtendPackage.XTEND_ENUM__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Annotation Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_ENUMERATION__ANNOTATION_INFO = XtendPackage.XTEND_ENUM__ANNOTATION_INFO;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_ENUMERATION__MODIFIERS = XtendPackage.XTEND_ENUM__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Declaring Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_ENUMERATION__DECLARING_TYPE = XtendPackage.XTEND_ENUM__DECLARING_TYPE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_ENUMERATION__NAME = XtendPackage.XTEND_ENUM__NAME;

	/**
	 * The feature id for the '<em><b>Members</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_ENUMERATION__MEMBERS = XtendPackage.XTEND_ENUM__MEMBERS;

	/**
	 * The number of structural features of the '<em>Enumeration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_ENUMERATION_FEATURE_COUNT = XtendPackage.XTEND_ENUM_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link io.sarl.lang.sarl.impl.SarlAnnotationTypeImpl <em>Annotation Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see io.sarl.lang.sarl.impl.SarlAnnotationTypeImpl
	 * @see io.sarl.lang.sarl.impl.SarlPackageImpl#getSarlAnnotationType()
	 * @generated
	 */
	int SARL_ANNOTATION_TYPE = 10;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_ANNOTATION_TYPE__ANNOTATIONS = XtendPackage.XTEND_ANNOTATION_TYPE__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Annotation Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_ANNOTATION_TYPE__ANNOTATION_INFO = XtendPackage.XTEND_ANNOTATION_TYPE__ANNOTATION_INFO;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_ANNOTATION_TYPE__MODIFIERS = XtendPackage.XTEND_ANNOTATION_TYPE__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Declaring Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_ANNOTATION_TYPE__DECLARING_TYPE = XtendPackage.XTEND_ANNOTATION_TYPE__DECLARING_TYPE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_ANNOTATION_TYPE__NAME = XtendPackage.XTEND_ANNOTATION_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Members</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_ANNOTATION_TYPE__MEMBERS = XtendPackage.XTEND_ANNOTATION_TYPE__MEMBERS;

	/**
	 * The number of structural features of the '<em>Annotation Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_ANNOTATION_TYPE_FEATURE_COUNT = XtendPackage.XTEND_ANNOTATION_TYPE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link io.sarl.lang.sarl.impl.SarlEnumLiteralImpl <em>Enum Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see io.sarl.lang.sarl.impl.SarlEnumLiteralImpl
	 * @see io.sarl.lang.sarl.impl.SarlPackageImpl#getSarlEnumLiteral()
	 * @generated
	 */
	int SARL_ENUM_LITERAL = 11;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_ENUM_LITERAL__ANNOTATIONS = XtendPackage.XTEND_ENUM_LITERAL__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Annotation Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_ENUM_LITERAL__ANNOTATION_INFO = XtendPackage.XTEND_ENUM_LITERAL__ANNOTATION_INFO;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_ENUM_LITERAL__MODIFIERS = XtendPackage.XTEND_ENUM_LITERAL__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Declaring Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_ENUM_LITERAL__DECLARING_TYPE = XtendPackage.XTEND_ENUM_LITERAL__DECLARING_TYPE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_ENUM_LITERAL__NAME = XtendPackage.XTEND_ENUM_LITERAL__NAME;

	/**
	 * The number of structural features of the '<em>Enum Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_ENUM_LITERAL_FEATURE_COUNT = XtendPackage.XTEND_ENUM_LITERAL_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link io.sarl.lang.sarl.impl.SarlEventImpl <em>Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see io.sarl.lang.sarl.impl.SarlEventImpl
	 * @see io.sarl.lang.sarl.impl.SarlPackageImpl#getSarlEvent()
	 * @generated
	 */
	int SARL_EVENT = 12;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_EVENT__ANNOTATIONS = XtendPackage.XTEND_TYPE_DECLARATION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Annotation Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_EVENT__ANNOTATION_INFO = XtendPackage.XTEND_TYPE_DECLARATION__ANNOTATION_INFO;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_EVENT__MODIFIERS = XtendPackage.XTEND_TYPE_DECLARATION__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Declaring Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_EVENT__DECLARING_TYPE = XtendPackage.XTEND_TYPE_DECLARATION__DECLARING_TYPE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_EVENT__NAME = XtendPackage.XTEND_TYPE_DECLARATION__NAME;

	/**
	 * The feature id for the '<em><b>Members</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_EVENT__MEMBERS = XtendPackage.XTEND_TYPE_DECLARATION__MEMBERS;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_EVENT__EXTENDS = XtendPackage.XTEND_TYPE_DECLARATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_EVENT_FEATURE_COUNT = XtendPackage.XTEND_TYPE_DECLARATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link io.sarl.lang.sarl.impl.SarlSpaceImpl <em>Space</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see io.sarl.lang.sarl.impl.SarlSpaceImpl
	 * @see io.sarl.lang.sarl.impl.SarlPackageImpl#getSarlSpace()
	 * @generated
	 */
	int SARL_SPACE = 13;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_SPACE__ANNOTATIONS = XtendPackage.XTEND_TYPE_DECLARATION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Annotation Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_SPACE__ANNOTATION_INFO = XtendPackage.XTEND_TYPE_DECLARATION__ANNOTATION_INFO;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_SPACE__MODIFIERS = XtendPackage.XTEND_TYPE_DECLARATION__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Declaring Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_SPACE__DECLARING_TYPE = XtendPackage.XTEND_TYPE_DECLARATION__DECLARING_TYPE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_SPACE__NAME = XtendPackage.XTEND_TYPE_DECLARATION__NAME;

	/**
	 * The feature id for the '<em><b>Members</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_SPACE__MEMBERS = XtendPackage.XTEND_TYPE_DECLARATION__MEMBERS;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_SPACE__EXTENDS = XtendPackage.XTEND_TYPE_DECLARATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Space</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_SPACE_FEATURE_COUNT = XtendPackage.XTEND_TYPE_DECLARATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link io.sarl.lang.sarl.impl.SarlAgentImpl <em>Agent</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see io.sarl.lang.sarl.impl.SarlAgentImpl
	 * @see io.sarl.lang.sarl.impl.SarlPackageImpl#getSarlAgent()
	 * @generated
	 */
	int SARL_AGENT = 14;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_AGENT__ANNOTATIONS = XtendPackage.XTEND_TYPE_DECLARATION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Annotation Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_AGENT__ANNOTATION_INFO = XtendPackage.XTEND_TYPE_DECLARATION__ANNOTATION_INFO;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_AGENT__MODIFIERS = XtendPackage.XTEND_TYPE_DECLARATION__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Declaring Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_AGENT__DECLARING_TYPE = XtendPackage.XTEND_TYPE_DECLARATION__DECLARING_TYPE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_AGENT__NAME = XtendPackage.XTEND_TYPE_DECLARATION__NAME;

	/**
	 * The feature id for the '<em><b>Members</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_AGENT__MEMBERS = XtendPackage.XTEND_TYPE_DECLARATION__MEMBERS;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_AGENT__EXTENDS = XtendPackage.XTEND_TYPE_DECLARATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Agent</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_AGENT_FEATURE_COUNT = XtendPackage.XTEND_TYPE_DECLARATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link io.sarl.lang.sarl.impl.SarlCapacityImpl <em>Capacity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see io.sarl.lang.sarl.impl.SarlCapacityImpl
	 * @see io.sarl.lang.sarl.impl.SarlPackageImpl#getSarlCapacity()
	 * @generated
	 */
	int SARL_CAPACITY = 15;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_CAPACITY__ANNOTATIONS = XtendPackage.XTEND_TYPE_DECLARATION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Annotation Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_CAPACITY__ANNOTATION_INFO = XtendPackage.XTEND_TYPE_DECLARATION__ANNOTATION_INFO;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_CAPACITY__MODIFIERS = XtendPackage.XTEND_TYPE_DECLARATION__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Declaring Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_CAPACITY__DECLARING_TYPE = XtendPackage.XTEND_TYPE_DECLARATION__DECLARING_TYPE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_CAPACITY__NAME = XtendPackage.XTEND_TYPE_DECLARATION__NAME;

	/**
	 * The feature id for the '<em><b>Members</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_CAPACITY__MEMBERS = XtendPackage.XTEND_TYPE_DECLARATION__MEMBERS;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_CAPACITY__EXTENDS = XtendPackage.XTEND_TYPE_DECLARATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Capacity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_CAPACITY_FEATURE_COUNT = XtendPackage.XTEND_TYPE_DECLARATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link io.sarl.lang.sarl.impl.SarlBehaviorImpl <em>Behavior</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see io.sarl.lang.sarl.impl.SarlBehaviorImpl
	 * @see io.sarl.lang.sarl.impl.SarlPackageImpl#getSarlBehavior()
	 * @generated
	 */
	int SARL_BEHAVIOR = 16;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_BEHAVIOR__ANNOTATIONS = XtendPackage.XTEND_TYPE_DECLARATION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Annotation Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_BEHAVIOR__ANNOTATION_INFO = XtendPackage.XTEND_TYPE_DECLARATION__ANNOTATION_INFO;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_BEHAVIOR__MODIFIERS = XtendPackage.XTEND_TYPE_DECLARATION__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Declaring Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_BEHAVIOR__DECLARING_TYPE = XtendPackage.XTEND_TYPE_DECLARATION__DECLARING_TYPE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_BEHAVIOR__NAME = XtendPackage.XTEND_TYPE_DECLARATION__NAME;

	/**
	 * The feature id for the '<em><b>Members</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_BEHAVIOR__MEMBERS = XtendPackage.XTEND_TYPE_DECLARATION__MEMBERS;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_BEHAVIOR__EXTENDS = XtendPackage.XTEND_TYPE_DECLARATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Behavior</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_BEHAVIOR_FEATURE_COUNT = XtendPackage.XTEND_TYPE_DECLARATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link io.sarl.lang.sarl.impl.SarlSkillImpl <em>Skill</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see io.sarl.lang.sarl.impl.SarlSkillImpl
	 * @see io.sarl.lang.sarl.impl.SarlPackageImpl#getSarlSkill()
	 * @generated
	 */
	int SARL_SKILL = 17;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_SKILL__ANNOTATIONS = XtendPackage.XTEND_TYPE_DECLARATION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Annotation Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_SKILL__ANNOTATION_INFO = XtendPackage.XTEND_TYPE_DECLARATION__ANNOTATION_INFO;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_SKILL__MODIFIERS = XtendPackage.XTEND_TYPE_DECLARATION__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Declaring Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_SKILL__DECLARING_TYPE = XtendPackage.XTEND_TYPE_DECLARATION__DECLARING_TYPE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_SKILL__NAME = XtendPackage.XTEND_TYPE_DECLARATION__NAME;

	/**
	 * The feature id for the '<em><b>Members</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_SKILL__MEMBERS = XtendPackage.XTEND_TYPE_DECLARATION__MEMBERS;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_SKILL__EXTENDS = XtendPackage.XTEND_TYPE_DECLARATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Implements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_SKILL__IMPLEMENTS = XtendPackage.XTEND_TYPE_DECLARATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Skill</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_SKILL_FEATURE_COUNT = XtendPackage.XTEND_TYPE_DECLARATION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link io.sarl.lang.sarl.impl.SarlFormalParameterImpl <em>Formal Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see io.sarl.lang.sarl.impl.SarlFormalParameterImpl
	 * @see io.sarl.lang.sarl.impl.SarlPackageImpl#getSarlFormalParameter()
	 * @generated
	 */
	int SARL_FORMAL_PARAMETER = 18;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_FORMAL_PARAMETER__ANNOTATIONS = XtendPackage.XTEND_PARAMETER__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_FORMAL_PARAMETER__NAME = XtendPackage.XTEND_PARAMETER__NAME;

	/**
	 * The feature id for the '<em><b>Parameter Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_FORMAL_PARAMETER__PARAMETER_TYPE = XtendPackage.XTEND_PARAMETER__PARAMETER_TYPE;

	/**
	 * The feature id for the '<em><b>Var Arg</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_FORMAL_PARAMETER__VAR_ARG = XtendPackage.XTEND_PARAMETER__VAR_ARG;

	/**
	 * The feature id for the '<em><b>Extension</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_FORMAL_PARAMETER__EXTENSION = XtendPackage.XTEND_PARAMETER__EXTENSION;

	/**
	 * The feature id for the '<em><b>Default Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_FORMAL_PARAMETER__DEFAULT_VALUE = XtendPackage.XTEND_PARAMETER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Formal Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SARL_FORMAL_PARAMETER_FEATURE_COUNT = XtendPackage.XTEND_PARAMETER_FEATURE_COUNT + 1;


	/**
	 * Returns the meta object for class '{@link io.sarl.lang.sarl.SarlScript <em>Script</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Script</em>'.
	 * @see io.sarl.lang.sarl.SarlScript
	 * @generated
	 */
	EClass getSarlScript();

	/**
	 * Returns the meta object for class '{@link io.sarl.lang.sarl.SarlField <em>Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Field</em>'.
	 * @see io.sarl.lang.sarl.SarlField
	 * @generated
	 */
	EClass getSarlField();

	/**
	 * Returns the meta object for class '{@link io.sarl.lang.sarl.SarlAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Action</em>'.
	 * @see io.sarl.lang.sarl.SarlAction
	 * @generated
	 */
	EClass getSarlAction();

	/**
	 * Returns the meta object for the containment reference list '{@link io.sarl.lang.sarl.SarlAction#getFiredEvents <em>Fired Events</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Fired Events</em>'.
	 * @see io.sarl.lang.sarl.SarlAction#getFiredEvents()
	 * @see #getSarlAction()
	 * @generated
	 */
	EReference getSarlAction_FiredEvents();

	/**
	 * Returns the meta object for class '{@link io.sarl.lang.sarl.SarlConstructor <em>Constructor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Constructor</em>'.
	 * @see io.sarl.lang.sarl.SarlConstructor
	 * @generated
	 */
	EClass getSarlConstructor();

	/**
	 * Returns the meta object for class '{@link io.sarl.lang.sarl.SarlBehaviorUnit <em>Behavior Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Behavior Unit</em>'.
	 * @see io.sarl.lang.sarl.SarlBehaviorUnit
	 * @generated
	 */
	EClass getSarlBehaviorUnit();

	/**
	 * Returns the meta object for the containment reference '{@link io.sarl.lang.sarl.SarlBehaviorUnit#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Name</em>'.
	 * @see io.sarl.lang.sarl.SarlBehaviorUnit#getName()
	 * @see #getSarlBehaviorUnit()
	 * @generated
	 */
	EReference getSarlBehaviorUnit_Name();

	/**
	 * Returns the meta object for the containment reference '{@link io.sarl.lang.sarl.SarlBehaviorUnit#getGuard <em>Guard</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Guard</em>'.
	 * @see io.sarl.lang.sarl.SarlBehaviorUnit#getGuard()
	 * @see #getSarlBehaviorUnit()
	 * @generated
	 */
	EReference getSarlBehaviorUnit_Guard();

	/**
	 * Returns the meta object for the containment reference '{@link io.sarl.lang.sarl.SarlBehaviorUnit#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see io.sarl.lang.sarl.SarlBehaviorUnit#getExpression()
	 * @see #getSarlBehaviorUnit()
	 * @generated
	 */
	EReference getSarlBehaviorUnit_Expression();

	/**
	 * Returns the meta object for class '{@link io.sarl.lang.sarl.SarlCapacityUses <em>Capacity Uses</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Capacity Uses</em>'.
	 * @see io.sarl.lang.sarl.SarlCapacityUses
	 * @generated
	 */
	EClass getSarlCapacityUses();

	/**
	 * Returns the meta object for the containment reference list '{@link io.sarl.lang.sarl.SarlCapacityUses#getCapacities <em>Capacities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Capacities</em>'.
	 * @see io.sarl.lang.sarl.SarlCapacityUses#getCapacities()
	 * @see #getSarlCapacityUses()
	 * @generated
	 */
	EReference getSarlCapacityUses_Capacities();

	/**
	 * Returns the meta object for class '{@link io.sarl.lang.sarl.SarlRequiredCapacity <em>Required Capacity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Required Capacity</em>'.
	 * @see io.sarl.lang.sarl.SarlRequiredCapacity
	 * @generated
	 */
	EClass getSarlRequiredCapacity();

	/**
	 * Returns the meta object for the containment reference list '{@link io.sarl.lang.sarl.SarlRequiredCapacity#getCapacities <em>Capacities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Capacities</em>'.
	 * @see io.sarl.lang.sarl.SarlRequiredCapacity#getCapacities()
	 * @see #getSarlRequiredCapacity()
	 * @generated
	 */
	EReference getSarlRequiredCapacity_Capacities();

	/**
	 * Returns the meta object for class '{@link io.sarl.lang.sarl.SarlClass <em>Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Class</em>'.
	 * @see io.sarl.lang.sarl.SarlClass
	 * @generated
	 */
	EClass getSarlClass();

	/**
	 * Returns the meta object for class '{@link io.sarl.lang.sarl.SarlInterface <em>Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Interface</em>'.
	 * @see io.sarl.lang.sarl.SarlInterface
	 * @generated
	 */
	EClass getSarlInterface();

	/**
	 * Returns the meta object for class '{@link io.sarl.lang.sarl.SarlEnumeration <em>Enumeration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Enumeration</em>'.
	 * @see io.sarl.lang.sarl.SarlEnumeration
	 * @generated
	 */
	EClass getSarlEnumeration();

	/**
	 * Returns the meta object for class '{@link io.sarl.lang.sarl.SarlAnnotationType <em>Annotation Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Annotation Type</em>'.
	 * @see io.sarl.lang.sarl.SarlAnnotationType
	 * @generated
	 */
	EClass getSarlAnnotationType();

	/**
	 * Returns the meta object for class '{@link io.sarl.lang.sarl.SarlEnumLiteral <em>Enum Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Enum Literal</em>'.
	 * @see io.sarl.lang.sarl.SarlEnumLiteral
	 * @generated
	 */
	EClass getSarlEnumLiteral();

	/**
	 * Returns the meta object for class '{@link io.sarl.lang.sarl.SarlEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Event</em>'.
	 * @see io.sarl.lang.sarl.SarlEvent
	 * @generated
	 */
	EClass getSarlEvent();

	/**
	 * Returns the meta object for the containment reference '{@link io.sarl.lang.sarl.SarlEvent#getExtends <em>Extends</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Extends</em>'.
	 * @see io.sarl.lang.sarl.SarlEvent#getExtends()
	 * @see #getSarlEvent()
	 * @generated
	 */
	EReference getSarlEvent_Extends();

	/**
	 * Returns the meta object for class '{@link io.sarl.lang.sarl.SarlSpace <em>Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Space</em>'.
	 * @see io.sarl.lang.sarl.SarlSpace
	 * @generated
	 */
	EClass getSarlSpace();

	/**
	 * Returns the meta object for the containment reference '{@link io.sarl.lang.sarl.SarlSpace#getExtends <em>Extends</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Extends</em>'.
	 * @see io.sarl.lang.sarl.SarlSpace#getExtends()
	 * @see #getSarlSpace()
	 * @generated
	 */
	EReference getSarlSpace_Extends();

	/**
	 * Returns the meta object for class '{@link io.sarl.lang.sarl.SarlAgent <em>Agent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Agent</em>'.
	 * @see io.sarl.lang.sarl.SarlAgent
	 * @generated
	 */
	EClass getSarlAgent();

	/**
	 * Returns the meta object for the containment reference '{@link io.sarl.lang.sarl.SarlAgent#getExtends <em>Extends</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Extends</em>'.
	 * @see io.sarl.lang.sarl.SarlAgent#getExtends()
	 * @see #getSarlAgent()
	 * @generated
	 */
	EReference getSarlAgent_Extends();

	/**
	 * Returns the meta object for class '{@link io.sarl.lang.sarl.SarlCapacity <em>Capacity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Capacity</em>'.
	 * @see io.sarl.lang.sarl.SarlCapacity
	 * @generated
	 */
	EClass getSarlCapacity();

	/**
	 * Returns the meta object for the containment reference list '{@link io.sarl.lang.sarl.SarlCapacity#getExtends <em>Extends</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Extends</em>'.
	 * @see io.sarl.lang.sarl.SarlCapacity#getExtends()
	 * @see #getSarlCapacity()
	 * @generated
	 */
	EReference getSarlCapacity_Extends();

	/**
	 * Returns the meta object for class '{@link io.sarl.lang.sarl.SarlBehavior <em>Behavior</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Behavior</em>'.
	 * @see io.sarl.lang.sarl.SarlBehavior
	 * @generated
	 */
	EClass getSarlBehavior();

	/**
	 * Returns the meta object for the containment reference '{@link io.sarl.lang.sarl.SarlBehavior#getExtends <em>Extends</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Extends</em>'.
	 * @see io.sarl.lang.sarl.SarlBehavior#getExtends()
	 * @see #getSarlBehavior()
	 * @generated
	 */
	EReference getSarlBehavior_Extends();

	/**
	 * Returns the meta object for class '{@link io.sarl.lang.sarl.SarlSkill <em>Skill</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Skill</em>'.
	 * @see io.sarl.lang.sarl.SarlSkill
	 * @generated
	 */
	EClass getSarlSkill();

	/**
	 * Returns the meta object for the containment reference '{@link io.sarl.lang.sarl.SarlSkill#getExtends <em>Extends</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Extends</em>'.
	 * @see io.sarl.lang.sarl.SarlSkill#getExtends()
	 * @see #getSarlSkill()
	 * @generated
	 */
	EReference getSarlSkill_Extends();

	/**
	 * Returns the meta object for the containment reference list '{@link io.sarl.lang.sarl.SarlSkill#getImplements <em>Implements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Implements</em>'.
	 * @see io.sarl.lang.sarl.SarlSkill#getImplements()
	 * @see #getSarlSkill()
	 * @generated
	 */
	EReference getSarlSkill_Implements();

	/**
	 * Returns the meta object for class '{@link io.sarl.lang.sarl.SarlFormalParameter <em>Formal Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Formal Parameter</em>'.
	 * @see io.sarl.lang.sarl.SarlFormalParameter
	 * @generated
	 */
	EClass getSarlFormalParameter();

	/**
	 * Returns the meta object for the containment reference '{@link io.sarl.lang.sarl.SarlFormalParameter#getDefaultValue <em>Default Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Default Value</em>'.
	 * @see io.sarl.lang.sarl.SarlFormalParameter#getDefaultValue()
	 * @see #getSarlFormalParameter()
	 * @generated
	 */
	EReference getSarlFormalParameter_DefaultValue();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SarlFactory getSarlFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals
	{
		/**
		 * The meta object literal for the '{@link io.sarl.lang.sarl.impl.SarlScriptImpl <em>Script</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see io.sarl.lang.sarl.impl.SarlScriptImpl
		 * @see io.sarl.lang.sarl.impl.SarlPackageImpl#getSarlScript()
		 * @generated
		 */
		EClass SARL_SCRIPT = eINSTANCE.getSarlScript();

		/**
		 * The meta object literal for the '{@link io.sarl.lang.sarl.impl.SarlFieldImpl <em>Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see io.sarl.lang.sarl.impl.SarlFieldImpl
		 * @see io.sarl.lang.sarl.impl.SarlPackageImpl#getSarlField()
		 * @generated
		 */
		EClass SARL_FIELD = eINSTANCE.getSarlField();

		/**
		 * The meta object literal for the '{@link io.sarl.lang.sarl.impl.SarlActionImpl <em>Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see io.sarl.lang.sarl.impl.SarlActionImpl
		 * @see io.sarl.lang.sarl.impl.SarlPackageImpl#getSarlAction()
		 * @generated
		 */
		EClass SARL_ACTION = eINSTANCE.getSarlAction();

		/**
		 * The meta object literal for the '<em><b>Fired Events</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SARL_ACTION__FIRED_EVENTS = eINSTANCE.getSarlAction_FiredEvents();

		/**
		 * The meta object literal for the '{@link io.sarl.lang.sarl.impl.SarlConstructorImpl <em>Constructor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see io.sarl.lang.sarl.impl.SarlConstructorImpl
		 * @see io.sarl.lang.sarl.impl.SarlPackageImpl#getSarlConstructor()
		 * @generated
		 */
		EClass SARL_CONSTRUCTOR = eINSTANCE.getSarlConstructor();

		/**
		 * The meta object literal for the '{@link io.sarl.lang.sarl.impl.SarlBehaviorUnitImpl <em>Behavior Unit</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see io.sarl.lang.sarl.impl.SarlBehaviorUnitImpl
		 * @see io.sarl.lang.sarl.impl.SarlPackageImpl#getSarlBehaviorUnit()
		 * @generated
		 */
		EClass SARL_BEHAVIOR_UNIT = eINSTANCE.getSarlBehaviorUnit();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SARL_BEHAVIOR_UNIT__NAME = eINSTANCE.getSarlBehaviorUnit_Name();

		/**
		 * The meta object literal for the '<em><b>Guard</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SARL_BEHAVIOR_UNIT__GUARD = eINSTANCE.getSarlBehaviorUnit_Guard();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SARL_BEHAVIOR_UNIT__EXPRESSION = eINSTANCE.getSarlBehaviorUnit_Expression();

		/**
		 * The meta object literal for the '{@link io.sarl.lang.sarl.impl.SarlCapacityUsesImpl <em>Capacity Uses</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see io.sarl.lang.sarl.impl.SarlCapacityUsesImpl
		 * @see io.sarl.lang.sarl.impl.SarlPackageImpl#getSarlCapacityUses()
		 * @generated
		 */
		EClass SARL_CAPACITY_USES = eINSTANCE.getSarlCapacityUses();

		/**
		 * The meta object literal for the '<em><b>Capacities</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SARL_CAPACITY_USES__CAPACITIES = eINSTANCE.getSarlCapacityUses_Capacities();

		/**
		 * The meta object literal for the '{@link io.sarl.lang.sarl.impl.SarlRequiredCapacityImpl <em>Required Capacity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see io.sarl.lang.sarl.impl.SarlRequiredCapacityImpl
		 * @see io.sarl.lang.sarl.impl.SarlPackageImpl#getSarlRequiredCapacity()
		 * @generated
		 */
		EClass SARL_REQUIRED_CAPACITY = eINSTANCE.getSarlRequiredCapacity();

		/**
		 * The meta object literal for the '<em><b>Capacities</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SARL_REQUIRED_CAPACITY__CAPACITIES = eINSTANCE.getSarlRequiredCapacity_Capacities();

		/**
		 * The meta object literal for the '{@link io.sarl.lang.sarl.impl.SarlClassImpl <em>Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see io.sarl.lang.sarl.impl.SarlClassImpl
		 * @see io.sarl.lang.sarl.impl.SarlPackageImpl#getSarlClass()
		 * @generated
		 */
		EClass SARL_CLASS = eINSTANCE.getSarlClass();

		/**
		 * The meta object literal for the '{@link io.sarl.lang.sarl.impl.SarlInterfaceImpl <em>Interface</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see io.sarl.lang.sarl.impl.SarlInterfaceImpl
		 * @see io.sarl.lang.sarl.impl.SarlPackageImpl#getSarlInterface()
		 * @generated
		 */
		EClass SARL_INTERFACE = eINSTANCE.getSarlInterface();

		/**
		 * The meta object literal for the '{@link io.sarl.lang.sarl.impl.SarlEnumerationImpl <em>Enumeration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see io.sarl.lang.sarl.impl.SarlEnumerationImpl
		 * @see io.sarl.lang.sarl.impl.SarlPackageImpl#getSarlEnumeration()
		 * @generated
		 */
		EClass SARL_ENUMERATION = eINSTANCE.getSarlEnumeration();

		/**
		 * The meta object literal for the '{@link io.sarl.lang.sarl.impl.SarlAnnotationTypeImpl <em>Annotation Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see io.sarl.lang.sarl.impl.SarlAnnotationTypeImpl
		 * @see io.sarl.lang.sarl.impl.SarlPackageImpl#getSarlAnnotationType()
		 * @generated
		 */
		EClass SARL_ANNOTATION_TYPE = eINSTANCE.getSarlAnnotationType();

		/**
		 * The meta object literal for the '{@link io.sarl.lang.sarl.impl.SarlEnumLiteralImpl <em>Enum Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see io.sarl.lang.sarl.impl.SarlEnumLiteralImpl
		 * @see io.sarl.lang.sarl.impl.SarlPackageImpl#getSarlEnumLiteral()
		 * @generated
		 */
		EClass SARL_ENUM_LITERAL = eINSTANCE.getSarlEnumLiteral();

		/**
		 * The meta object literal for the '{@link io.sarl.lang.sarl.impl.SarlEventImpl <em>Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see io.sarl.lang.sarl.impl.SarlEventImpl
		 * @see io.sarl.lang.sarl.impl.SarlPackageImpl#getSarlEvent()
		 * @generated
		 */
		EClass SARL_EVENT = eINSTANCE.getSarlEvent();

		/**
		 * The meta object literal for the '<em><b>Extends</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SARL_EVENT__EXTENDS = eINSTANCE.getSarlEvent_Extends();

		/**
		 * The meta object literal for the '{@link io.sarl.lang.sarl.impl.SarlSpaceImpl <em>Space</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see io.sarl.lang.sarl.impl.SarlSpaceImpl
		 * @see io.sarl.lang.sarl.impl.SarlPackageImpl#getSarlSpace()
		 * @generated
		 */
		EClass SARL_SPACE = eINSTANCE.getSarlSpace();

		/**
		 * The meta object literal for the '<em><b>Extends</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SARL_SPACE__EXTENDS = eINSTANCE.getSarlSpace_Extends();

		/**
		 * The meta object literal for the '{@link io.sarl.lang.sarl.impl.SarlAgentImpl <em>Agent</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see io.sarl.lang.sarl.impl.SarlAgentImpl
		 * @see io.sarl.lang.sarl.impl.SarlPackageImpl#getSarlAgent()
		 * @generated
		 */
		EClass SARL_AGENT = eINSTANCE.getSarlAgent();

		/**
		 * The meta object literal for the '<em><b>Extends</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SARL_AGENT__EXTENDS = eINSTANCE.getSarlAgent_Extends();

		/**
		 * The meta object literal for the '{@link io.sarl.lang.sarl.impl.SarlCapacityImpl <em>Capacity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see io.sarl.lang.sarl.impl.SarlCapacityImpl
		 * @see io.sarl.lang.sarl.impl.SarlPackageImpl#getSarlCapacity()
		 * @generated
		 */
		EClass SARL_CAPACITY = eINSTANCE.getSarlCapacity();

		/**
		 * The meta object literal for the '<em><b>Extends</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SARL_CAPACITY__EXTENDS = eINSTANCE.getSarlCapacity_Extends();

		/**
		 * The meta object literal for the '{@link io.sarl.lang.sarl.impl.SarlBehaviorImpl <em>Behavior</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see io.sarl.lang.sarl.impl.SarlBehaviorImpl
		 * @see io.sarl.lang.sarl.impl.SarlPackageImpl#getSarlBehavior()
		 * @generated
		 */
		EClass SARL_BEHAVIOR = eINSTANCE.getSarlBehavior();

		/**
		 * The meta object literal for the '<em><b>Extends</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SARL_BEHAVIOR__EXTENDS = eINSTANCE.getSarlBehavior_Extends();

		/**
		 * The meta object literal for the '{@link io.sarl.lang.sarl.impl.SarlSkillImpl <em>Skill</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see io.sarl.lang.sarl.impl.SarlSkillImpl
		 * @see io.sarl.lang.sarl.impl.SarlPackageImpl#getSarlSkill()
		 * @generated
		 */
		EClass SARL_SKILL = eINSTANCE.getSarlSkill();

		/**
		 * The meta object literal for the '<em><b>Extends</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SARL_SKILL__EXTENDS = eINSTANCE.getSarlSkill_Extends();

		/**
		 * The meta object literal for the '<em><b>Implements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SARL_SKILL__IMPLEMENTS = eINSTANCE.getSarlSkill_Implements();

		/**
		 * The meta object literal for the '{@link io.sarl.lang.sarl.impl.SarlFormalParameterImpl <em>Formal Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see io.sarl.lang.sarl.impl.SarlFormalParameterImpl
		 * @see io.sarl.lang.sarl.impl.SarlPackageImpl#getSarlFormalParameter()
		 * @generated
		 */
		EClass SARL_FORMAL_PARAMETER = eINSTANCE.getSarlFormalParameter();

		/**
		 * The meta object literal for the '<em><b>Default Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SARL_FORMAL_PARAMETER__DEFAULT_VALUE = eINSTANCE.getSarlFormalParameter_DefaultValue();

	}

} //SarlPackage
