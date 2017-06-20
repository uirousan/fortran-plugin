package org.jetbrains.fortran.lang.resolve.ref

import com.intellij.openapi.util.TextRange
import org.jetbrains.fortran.lang.psi.FortranCompositeElement
import org.jetbrains.fortran.lang.psi.FortranDataReferenceElement


class FortranDataReferenceImpl(element: FortranDataReferenceElement) :
    FortranReferenceBase<FortranDataReferenceElement>(element) {
    override fun getVariants(): Array<Any> {
        return emptyArray()
    }

    override fun resolve(): FortranCompositeElement? {
        return null
    }

    override fun getRangeInElement(): TextRange = element.referenceNameElement.textRange
}
