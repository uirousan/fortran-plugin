package org.jetbrains.fortran.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.jetbrains.fortran.lang.FortranTypes.*;
import org.jetbrains.fortran.lang.psi.*;

public class FortranLabelDoStmtImpl extends FortranExprImpl implements FortranLabelDoStmt {

    public FortranLabelDoStmtImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof FortranVisitor) accept((FortranVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @Nullable
    public FortranExpr getExpr() {
        return findChildByClass(FortranExpr.class);
    }

    @Override
    @Nullable
    public PsiElement getColon() {
        return findChildByType(COLON);
    }

    @Override
    @NotNull
    public PsiElement getDo() {
        return findNotNullChildByType(DO);
    }

    @Override
    @Nullable
    public PsiElement getEol() {
        return findChildByType(EOL);
    }

    @Override
    @Nullable
    public PsiElement getIdentifier() {
        return findChildByType(IDENTIFIER);
    }

    @Override
    @Nullable
    public PsiElement getLabel() { return findChildByType(LABEL); }

}