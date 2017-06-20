package org.jetbrains.fortran.lang.psi.ext

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.IStubElementType
import org.jetbrains.fortran.lang.FortranTypes.IDENTIFIER
import org.jetbrains.fortran.lang.core.stubs.FortranDataReferenceStub
import org.jetbrains.fortran.lang.psi.FortranDataReferenceElement
import org.jetbrains.fortran.lang.resolve.ref.FortranDataReferenceImpl

abstract class FortranDataReferenceElementMixin :
    FortranStubbedElementImpl<FortranDataReferenceStub>,
    FortranDataReferenceElement {

    constructor(node: ASTNode) : super(node)

    constructor(stub: FortranDataReferenceStub, nodeType: IStubElementType<*, *>) : super(stub, nodeType)

    override val referenceNameElement = checkNotNull(identifier) {
        "Data reference must contain identifier: $this ${this.text} at ${this.containingFile.virtualFile.path}"
    }

    override val referenceName: String get() = stub?.referenceName ?: referenceNameElement.text

    override fun getReference() = FortranDataReferenceImpl(this)
}

