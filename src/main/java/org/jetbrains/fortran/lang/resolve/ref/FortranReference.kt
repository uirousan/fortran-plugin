package org.jetbrains.fortran.lang.resolve.ref

import com.intellij.psi.PsiReference
import org.jetbrains.fortran.lang.psi.FortranCompositeElement

interface FortranReference : PsiReference {
    override fun getElement(): FortranCompositeElement

    override fun resolve(): FortranCompositeElement?
}