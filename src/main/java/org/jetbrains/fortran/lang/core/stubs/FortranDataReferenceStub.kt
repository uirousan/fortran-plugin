package org.jetbrains.fortran.lang.core.stubs

import com.intellij.lang.ASTNode
import com.intellij.psi.stubs.*
import org.jetbrains.fortran.lang.psi.FortranDataReferenceElement
import org.jetbrains.fortran.lang.psi.impl.FortranDataReferenceElementImpl
import org.jetbrains.fortran.lang.resolve.ref.FortranDataReferenceImpl

class FortranDataReferenceStub(
    parent: StubElement<*>?, elementType: IStubElementType<*, *>,
    val referenceName: String
) : StubBase<FortranDataReferenceElement>(parent, elementType) {

    object Type : FortranStubElementType<FortranDataReferenceStub, FortranDataReferenceElement>("PATH") {
        override fun shouldCreateStub(node: ASTNode): Boolean = createStubIfParentIsStub(node)

        override fun createPsi(stub: FortranDataReferenceStub) =
            FortranDataReferenceElementImpl(stub, this)

        override fun createStub(psi: FortranDataReferenceElement, parentStub: StubElement<*>?) =
            FortranDataReferenceStub(parentStub, this, psi.referenceName)

        override fun deserialize(dataStream: StubInputStream, parentStub: StubElement<*>?) =
            FortranDataReferenceStub(parentStub, this,
                dataStream.readName()!!.string
            )

        override fun serialize(stub: FortranDataReferenceStub, dataStream: StubOutputStream) =
            with(dataStream) {
                writeName(stub.referenceName)
            }

        override fun indexStub(stub: FortranDataReferenceStub, sink: IndexSink) {
            //NOP
        }
    }
}