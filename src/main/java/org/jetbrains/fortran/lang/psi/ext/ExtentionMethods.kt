package org.jetbrains.fortran.lang.psi.ext

import org.jetbrains.fortran.lang.FortranTypes.IDENTIFIER
import org.jetbrains.fortran.lang.psi.FortranDataReferenceElement

val FortranDataReferenceElement.identifier
    get() = node.findChildByType(IDENTIFIER)?.psi