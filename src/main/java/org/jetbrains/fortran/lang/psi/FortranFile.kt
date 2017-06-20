package org.jetbrains.fortran.lang.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import org.jetbrains.fortran.FortranFileType
import org.jetbrains.fortran.FortranLanguage
import org.jetbrains.fortran.lang.core.stubs.FortranFileStub

class FortranFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, FortranLanguage.INSTANCE) {

    override fun getFileType(): FortranFileType = FortranFileType.INSTANCE

    override fun getStub(): FortranFileStub? = super.getStub() as FortranFileStub?
}
