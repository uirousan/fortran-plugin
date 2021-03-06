package org.jetbrains.fortran.lang.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.parser.GeneratedParserUtilBase;

import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import static org.jetbrains.fortran.lang.FortranTypes.*;
import static org.jetbrains.fortran.lang.FortranTypes.COLON;
import static org.jetbrains.fortran.lang.parser.FortranParser.end_do;
import static org.jetbrains.fortran.lang.parser.FortranParser.execution_part_construct;
import static org.jetbrains.fortran.lang.parser.FortranParser.loop_control;
import static org.jetbrains.fortran.lang.parser.FortranParserUtil.parseIdentifier;
import static org.jetbrains.fortran.lang.parser.FortranParserUtil.parseKeyword;
import static org.jetbrains.fortran.lang.psi.FortranTokenType.KEYWORD;
import static org.jetbrains.fortran.lang.psi.FortranTokenType.WORD;

public class LabeledDoConstructParser implements GeneratedParserUtilBase.Parser {

    @Override
    public boolean parse(PsiBuilder builder, int level) {
        if (!recursion_guard_(builder, level, "labeled_do_construct")) return false;
        if (!nextTokenIs(builder, "<labeled do construct>", WORD, KEYWORD, IDENTIFIER)) return false;
        boolean result, pinned;
        PsiBuilder.Marker marker = enter_section_(builder, level, _COLLAPSE_, LABELED_DO_CONSTRUCT, "<labeled do construct>");
        int labelValue = parseLabelDoStmt(builder, level + 1);
        result = (labelValue != -1);
        pinned = result; // pin = 1
        result = result && report_error_(builder, parseLabeledDoBlock(builder, level + 1, labelValue));
        result= pinned && (end_do(builder, level+1)
                           || new LabeledDoConstructParser().parse(builder, level+1)
                           || doTermActionStmt(builder, level + 1)) && result;
        exit_section_(builder, level, marker, result, pinned, null);
        return result || pinned;
    }

    private static boolean parseDoStmtWithTheSameLabel(PsiBuilder builder, int level, int testLabel) {
        if (!recursion_guard_(builder, level, "label_do_stmt")) return false;
        if (!nextTokenIs(builder, "<label do stmt>", WORD, KEYWORD, IDENTIFIER)) return false;
        boolean result, pinned;
        PsiBuilder.Marker marker_ = enter_section_(builder, level, _NONE_, LABEL_DO_STMT, "<label do stmt>");
        consumeTokens(builder, 0, IDENTIFIER, COLON);
        result = parseKeyword(builder, level + 1, "DO");
        int labelValue = new LabelParser().parseAndGetLabel(builder, level + 1);
        result = result && (labelValue == testLabel);
        pinned = result; // pin = 3
        result = result && report_error_(builder, loop_control(builder, level + 1));
        result = pinned && consumeToken(builder, EOL) && result;
        exit_section_(builder, level, marker_, false, false, null);
        return result || pinned;
    }
//
//  do_stmt
//
    private static int parseLabelDoStmt(PsiBuilder builder, int level) {
        if (!recursion_guard_(builder, level, "label_do_stmt")) return -1;
        if (!nextTokenIs(builder, "<label do stmt>", WORD, KEYWORD, IDENTIFIER)) return -1;
        boolean result, pinned;
        PsiBuilder.Marker marker_ = enter_section_(builder, level, _NONE_, LABEL_DO_STMT, "<label do stmt>");
        parseLoopName(builder, level+1);
        result = parseKeyword(builder, level + 1, "DO");
        int labelValue = new LabelParser().parseAndGetLabel(builder, level + 1);
        result = result && (labelValue != -1);
        pinned = result; // pin = 3
        consumeToken(builder, COMMA);
        result = result && report_error_(builder, loop_control(builder, level + 1));
        exit_section_(builder, level, marker_, result, pinned, null);
        if (!(result || pinned)) return -1;
        return labelValue;
    }

    private static void parseLoopName(PsiBuilder builder, int level) {
        if (!recursion_guard_(builder, level, "parseLoopName")) return;
        boolean result;
        PsiBuilder.Marker marker = enter_section_(builder);
        result = parseIdentifier(builder, level + 1);
        result = result && consumeToken(builder, COLON);
        exit_section_(builder, marker, null, result);
    }
//
//  block
//

    private static boolean parseLabeledDoBlock (PsiBuilder builder, int level, int stopLabel) {
        if (!recursion_guard_(builder, level, "block")) return false;
        PsiBuilder.Marker marker_ = enter_section_(builder, level, _COLLAPSE_, BLOCK, "<block>");
        int pos_ = current_position_(builder);
        while (true) {
            if (!doblock_line(builder, level + 1, stopLabel)) break;
            if (!empty_element_parsed_guard_(builder, "block", pos_)) break;
            pos_ = current_position_(builder);
        }
        exit_section_(builder, level, marker_, true, false, null);
        return true;
    }

    private static boolean doblock_line(PsiBuilder builder, int level, int stopLabel) {
        if (!recursion_guard_(builder, level, "block_0")) return false;
        boolean result_;
        PsiBuilder.Marker marker_ = enter_section_(builder);
        // label? label != stopLabel
        result_ = new LabelParser().parseOptionalAndDifferent(builder, level + 1, stopLabel);
        // !LabeledDoConstruct with the same label
        result_ = result_ && !parseDoStmtWithTheSameLabel(builder, level+1, stopLabel);
        // eol | execution_part_construct
        result_ = result_ && (consumeToken(builder, EOL) || execution_part_construct(builder, level + 1));
        exit_section_(builder, marker_, null, result_);
        return result_;
    }


    private static boolean doTermActionStmt(PsiBuilder builder, int level) {
        if (!recursion_guard_(builder, level, "do_term_action_stmt")) return false;
        boolean result, pinned;
        PsiBuilder.Marker marker = enter_section_(builder, level, _NONE_, DO_TERM_ACTION_STMT, "<do term action stmt>");
        pinned = new LabelParser().parse(builder, level + 1);
        result = execution_part_construct(builder, level + 1);
        exit_section_(builder, level, marker, result, pinned, null);
        return result || pinned;
    }
}
