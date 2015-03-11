/**
 */
package io.sarl.lang.sarl.util;

import io.sarl.lang.sarl.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.eclipse.xtend.core.xtend.XtendAnnotationTarget;
import org.eclipse.xtend.core.xtend.XtendExecutable;
import org.eclipse.xtend.core.xtend.XtendFunction;
import org.eclipse.xtend.core.xtend.XtendMember;
import org.eclipse.xtend.core.xtend.XtendParameter;
import org.eclipse.xtend.core.xtend.XtendTypeDeclaration;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see io.sarl.lang.sarl.SarlPackage
 * @generated
 */
public class SarlSwitch<T> extends Switch<T>
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static SarlPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SarlSwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = SarlPackage.eINSTANCE;
    }
  }

  /**
   * Checks whether this is a switch for the given package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @parameter ePackage the package in question.
   * @return whether this is a switch for the given package.
   * @generated
   */
  @Override
  protected boolean isSwitchFor(EPackage ePackage)
  {
    return ePackage == modelPackage;
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  @Override
  protected T doSwitch(int classifierID, EObject theEObject)
  {
    switch (classifierID)
    {
      case SarlPackage.SARL_ACTION:
      {
        SarlAction sarlAction = (SarlAction)theEObject;
        T result = caseSarlAction(sarlAction);
        if (result == null) result = caseXtendFunction(sarlAction);
        if (result == null) result = caseXtendExecutable(sarlAction);
        if (result == null) result = caseXtendMember(sarlAction);
        if (result == null) result = caseXtendAnnotationTarget(sarlAction);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SarlPackage.SARL_BEHAVIOR_UNIT:
      {
        SarlBehaviorUnit sarlBehaviorUnit = (SarlBehaviorUnit)theEObject;
        T result = caseSarlBehaviorUnit(sarlBehaviorUnit);
        if (result == null) result = caseXtendMember(sarlBehaviorUnit);
        if (result == null) result = caseXtendAnnotationTarget(sarlBehaviorUnit);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SarlPackage.SARL_CAPACITY_USES:
      {
        SarlCapacityUses sarlCapacityUses = (SarlCapacityUses)theEObject;
        T result = caseSarlCapacityUses(sarlCapacityUses);
        if (result == null) result = caseXtendMember(sarlCapacityUses);
        if (result == null) result = caseXtendAnnotationTarget(sarlCapacityUses);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SarlPackage.SARL_REQUIRED_CAPACITY:
      {
        SarlRequiredCapacity sarlRequiredCapacity = (SarlRequiredCapacity)theEObject;
        T result = caseSarlRequiredCapacity(sarlRequiredCapacity);
        if (result == null) result = caseXtendMember(sarlRequiredCapacity);
        if (result == null) result = caseXtendAnnotationTarget(sarlRequiredCapacity);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SarlPackage.SARL_EVENT:
      {
        SarlEvent sarlEvent = (SarlEvent)theEObject;
        T result = caseSarlEvent(sarlEvent);
        if (result == null) result = caseXtendTypeDeclaration(sarlEvent);
        if (result == null) result = caseXtendMember(sarlEvent);
        if (result == null) result = caseXtendAnnotationTarget(sarlEvent);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SarlPackage.SARL_AGENT:
      {
        SarlAgent sarlAgent = (SarlAgent)theEObject;
        T result = caseSarlAgent(sarlAgent);
        if (result == null) result = caseXtendTypeDeclaration(sarlAgent);
        if (result == null) result = caseXtendMember(sarlAgent);
        if (result == null) result = caseXtendAnnotationTarget(sarlAgent);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SarlPackage.SARL_CAPACITY:
      {
        SarlCapacity sarlCapacity = (SarlCapacity)theEObject;
        T result = caseSarlCapacity(sarlCapacity);
        if (result == null) result = caseXtendTypeDeclaration(sarlCapacity);
        if (result == null) result = caseXtendMember(sarlCapacity);
        if (result == null) result = caseXtendAnnotationTarget(sarlCapacity);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SarlPackage.SARL_BEHAVIOR:
      {
        SarlBehavior sarlBehavior = (SarlBehavior)theEObject;
        T result = caseSarlBehavior(sarlBehavior);
        if (result == null) result = caseXtendTypeDeclaration(sarlBehavior);
        if (result == null) result = caseXtendMember(sarlBehavior);
        if (result == null) result = caseXtendAnnotationTarget(sarlBehavior);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SarlPackage.SARL_SKILL:
      {
        SarlSkill sarlSkill = (SarlSkill)theEObject;
        T result = caseSarlSkill(sarlSkill);
        if (result == null) result = caseXtendTypeDeclaration(sarlSkill);
        if (result == null) result = caseXtendMember(sarlSkill);
        if (result == null) result = caseXtendAnnotationTarget(sarlSkill);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SarlPackage.SARL_FORMAL_PARAMETER:
      {
        SarlFormalParameter sarlFormalParameter = (SarlFormalParameter)theEObject;
        T result = caseSarlFormalParameter(sarlFormalParameter);
        if (result == null) result = caseXtendParameter(sarlFormalParameter);
        if (result == null) result = caseXtendAnnotationTarget(sarlFormalParameter);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Action</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Action</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSarlAction(SarlAction object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Behavior Unit</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Behavior Unit</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSarlBehaviorUnit(SarlBehaviorUnit object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Capacity Uses</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Capacity Uses</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSarlCapacityUses(SarlCapacityUses object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Required Capacity</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Required Capacity</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSarlRequiredCapacity(SarlRequiredCapacity object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Event</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Event</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSarlEvent(SarlEvent object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Agent</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Agent</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSarlAgent(SarlAgent object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Capacity</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Capacity</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSarlCapacity(SarlCapacity object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Behavior</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Behavior</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSarlBehavior(SarlBehavior object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Skill</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Skill</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSarlSkill(SarlSkill object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Formal Parameter</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Formal Parameter</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSarlFormalParameter(SarlFormalParameter object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Annotation Target</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Annotation Target</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXtendAnnotationTarget(XtendAnnotationTarget object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Member</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Member</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXtendMember(XtendMember object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Executable</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Executable</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXtendExecutable(XtendExecutable object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Function</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Function</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXtendFunction(XtendFunction object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Type Declaration</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Type Declaration</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXtendTypeDeclaration(XtendTypeDeclaration object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Parameter</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Parameter</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXtendParameter(XtendParameter object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  @Override
  public T defaultCase(EObject object)
  {
    return null;
  }

} //SarlSwitch
