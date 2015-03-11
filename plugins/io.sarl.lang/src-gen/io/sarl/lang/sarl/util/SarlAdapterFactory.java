/**
 */
package io.sarl.lang.sarl.util;

import io.sarl.lang.sarl.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.xtend.core.xtend.XtendAnnotationTarget;
import org.eclipse.xtend.core.xtend.XtendExecutable;
import org.eclipse.xtend.core.xtend.XtendFunction;
import org.eclipse.xtend.core.xtend.XtendMember;
import org.eclipse.xtend.core.xtend.XtendParameter;
import org.eclipse.xtend.core.xtend.XtendTypeDeclaration;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see io.sarl.lang.sarl.SarlPackage
 * @generated
 */
public class SarlAdapterFactory extends AdapterFactoryImpl
{
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static SarlPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SarlAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = SarlPackage.eINSTANCE;
    }
  }

  /**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
   * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
   * <!-- end-user-doc -->
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
  @Override
  public boolean isFactoryForType(Object object)
  {
    if (object == modelPackage)
    {
      return true;
    }
    if (object instanceof EObject)
    {
      return ((EObject)object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected SarlSwitch<Adapter> modelSwitch =
    new SarlSwitch<Adapter>()
    {
      @Override
      public Adapter caseSarlAction(SarlAction object)
      {
        return createSarlActionAdapter();
      }
      @Override
      public Adapter caseSarlBehaviorUnit(SarlBehaviorUnit object)
      {
        return createSarlBehaviorUnitAdapter();
      }
      @Override
      public Adapter caseSarlCapacityUses(SarlCapacityUses object)
      {
        return createSarlCapacityUsesAdapter();
      }
      @Override
      public Adapter caseSarlRequiredCapacity(SarlRequiredCapacity object)
      {
        return createSarlRequiredCapacityAdapter();
      }
      @Override
      public Adapter caseSarlEvent(SarlEvent object)
      {
        return createSarlEventAdapter();
      }
      @Override
      public Adapter caseSarlAgent(SarlAgent object)
      {
        return createSarlAgentAdapter();
      }
      @Override
      public Adapter caseSarlCapacity(SarlCapacity object)
      {
        return createSarlCapacityAdapter();
      }
      @Override
      public Adapter caseSarlBehavior(SarlBehavior object)
      {
        return createSarlBehaviorAdapter();
      }
      @Override
      public Adapter caseSarlSkill(SarlSkill object)
      {
        return createSarlSkillAdapter();
      }
      @Override
      public Adapter caseSarlFormalParameter(SarlFormalParameter object)
      {
        return createSarlFormalParameterAdapter();
      }
      @Override
      public Adapter caseXtendAnnotationTarget(XtendAnnotationTarget object)
      {
        return createXtendAnnotationTargetAdapter();
      }
      @Override
      public Adapter caseXtendMember(XtendMember object)
      {
        return createXtendMemberAdapter();
      }
      @Override
      public Adapter caseXtendExecutable(XtendExecutable object)
      {
        return createXtendExecutableAdapter();
      }
      @Override
      public Adapter caseXtendFunction(XtendFunction object)
      {
        return createXtendFunctionAdapter();
      }
      @Override
      public Adapter caseXtendTypeDeclaration(XtendTypeDeclaration object)
      {
        return createXtendTypeDeclarationAdapter();
      }
      @Override
      public Adapter caseXtendParameter(XtendParameter object)
      {
        return createXtendParameterAdapter();
      }
      @Override
      public Adapter defaultCase(EObject object)
      {
        return createEObjectAdapter();
      }
    };

  /**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
  @Override
  public Adapter createAdapter(Notifier target)
  {
    return modelSwitch.doSwitch((EObject)target);
  }


  /**
   * Creates a new adapter for an object of class '{@link io.sarl.lang.sarl.SarlAction <em>Action</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see io.sarl.lang.sarl.SarlAction
   * @generated
   */
  public Adapter createSarlActionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link io.sarl.lang.sarl.SarlBehaviorUnit <em>Behavior Unit</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see io.sarl.lang.sarl.SarlBehaviorUnit
   * @generated
   */
  public Adapter createSarlBehaviorUnitAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link io.sarl.lang.sarl.SarlCapacityUses <em>Capacity Uses</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see io.sarl.lang.sarl.SarlCapacityUses
   * @generated
   */
  public Adapter createSarlCapacityUsesAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link io.sarl.lang.sarl.SarlRequiredCapacity <em>Required Capacity</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see io.sarl.lang.sarl.SarlRequiredCapacity
   * @generated
   */
  public Adapter createSarlRequiredCapacityAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link io.sarl.lang.sarl.SarlEvent <em>Event</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see io.sarl.lang.sarl.SarlEvent
   * @generated
   */
  public Adapter createSarlEventAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link io.sarl.lang.sarl.SarlAgent <em>Agent</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see io.sarl.lang.sarl.SarlAgent
   * @generated
   */
  public Adapter createSarlAgentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link io.sarl.lang.sarl.SarlCapacity <em>Capacity</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see io.sarl.lang.sarl.SarlCapacity
   * @generated
   */
  public Adapter createSarlCapacityAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link io.sarl.lang.sarl.SarlBehavior <em>Behavior</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see io.sarl.lang.sarl.SarlBehavior
   * @generated
   */
  public Adapter createSarlBehaviorAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link io.sarl.lang.sarl.SarlSkill <em>Skill</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see io.sarl.lang.sarl.SarlSkill
   * @generated
   */
  public Adapter createSarlSkillAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link io.sarl.lang.sarl.SarlFormalParameter <em>Formal Parameter</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see io.sarl.lang.sarl.SarlFormalParameter
   * @generated
   */
  public Adapter createSarlFormalParameterAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xtend.core.xtend.XtendAnnotationTarget <em>Annotation Target</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xtend.core.xtend.XtendAnnotationTarget
   * @generated
   */
  public Adapter createXtendAnnotationTargetAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xtend.core.xtend.XtendMember <em>Member</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xtend.core.xtend.XtendMember
   * @generated
   */
  public Adapter createXtendMemberAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xtend.core.xtend.XtendExecutable <em>Executable</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xtend.core.xtend.XtendExecutable
   * @generated
   */
  public Adapter createXtendExecutableAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xtend.core.xtend.XtendFunction <em>Function</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xtend.core.xtend.XtendFunction
   * @generated
   */
  public Adapter createXtendFunctionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xtend.core.xtend.XtendTypeDeclaration <em>Type Declaration</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xtend.core.xtend.XtendTypeDeclaration
   * @generated
   */
  public Adapter createXtendTypeDeclarationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xtend.core.xtend.XtendParameter <em>Parameter</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xtend.core.xtend.XtendParameter
   * @generated
   */
  public Adapter createXtendParameterAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
   * This default implementation returns null.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @generated
   */
  public Adapter createEObjectAdapter()
  {
    return null;
  }

} //SarlAdapterFactory
