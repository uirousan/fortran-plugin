package org.jetbrains.fortran.lang.core.stubs

fun factory(name: String): FortranStubElementType<*, *> = when (name) {
    "DATA_REFERENCE_ELEMENT" -> FortranDataReferenceStub.Type
    else -> error("Unknown element $name")
}
