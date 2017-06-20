package org.jetbrains.fortran.lang.resolve.ref

import com.intellij.psi.PsiReferenceBase
import org.jetbrains.fortran.lang.psi.FortranCompositeElement

abstract class FortranReferenceBase<T : FortranCompositeElement>(element: T) :
    PsiReferenceBase<T>(element),
    FortranReference