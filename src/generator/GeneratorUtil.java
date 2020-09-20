/*
 * Copyright (C) 2008-12  Bernhard Hobiger
 *
 * This file is part of HoDoKu.
 *
 * HoDoKu is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * HoDoKu is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with HoDoKu. If not, see <http://www.gnu.org/licenses/>.
 */
package generator;

import java.util.List;
import solver.SudokuSolver;
import solver.SudokuSolverFactory;
import sudoku.Chain;
import sudoku.ClipboardMode;
import sudoku.Options;
import sudoku.SolutionStep;
import sudoku.Sudoku2;
import static sudoku.SolutionStep.getCellPrint;
import static sudoku.SolutionStep.getCompactCellPrint;
import sudoku.SolutionType;
import sudoku.Sudoku2;

/**
 *
 * @author lincc
 */
public class GeneratorUtil {
    
    public class Aaalong {
        private char[] narr;
        //128/16=8
        Aaalong(char bits)
        {
            char count=(char)Math.cbrt(bits/16);
            narr=new char[count];
        }
        public String toString()
        {
            //TODO:
            String AStr="";            
            return AStr;
        }        
        public byte signum()
        {
            //TODO:
            return 0;
        }        
        public char charAt(char index)
        {
            //TODO:
            char c=0;
            return c;
        }
        public byte byteAt(char index)
        {
            //TODO:
            byte b=0;
            return b;
        }
        public boolean bitAt(char index)
        {
            //TODO:
            return false;
        }
        public void addLong(long val)
        {
            //TODO:
        }
        public void subLong(long val)
        {
            //TODO:
        }
        public void mulLong(long val)
        {
            //TODO:
        }
        public void divLong(long val)
        {
            //TODO:
        }
        public void inc()
        {
            //TODO:
        }
        public void dec()
        {
            //TODO:
        }
        public char[] parseString(String astr)
        {
            //TODO:
           char[] aarr=new char[8];
           return aarr;
        };
    }
    
    public static String EmptyChar=".";
    public static String PuzzlePrefix="q:";
    public static String SsSPrefix="s:";
    public static int MinStartNum=17;
    public static int MaxStartNum=18;
    public static String Spliter=".";
    private static String Q="";
    private static String A="";
            
    public static void SetFullAns(int [] fs)
    {
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<fs.length;i++)
        {
            sb.append(fs[i]);
        }
        A=sb.toString();
    }
    public static String GetFullAns()
    {
        return A;
    }
    public static void GetSukodu(Sudoku2 sk,StringBuilder out,ClipboardMode mode)
    {
        out.append(PuzzlePrefix);
        if (mode == ClipboardMode.STEPS||mode==ClipboardMode.STEP_SHORT||mode==ClipboardMode.STEP_SHORT_SHORT||mode==ClipboardMode.STEP_SS_ARG)
        {
            for (int i = 0; i < sk.LENGTH; i++)              
                if (sk.getValue(i) == 0 || !sk.isFixed(i))
                    out.append(EmptyChar);                
                else               
                    out.append(Integer.toString(sk.getValue(i)));            
        }
        else
        {
            for (int i = 0; i < sk.LENGTH; i++)
                if(sk.getValue(i) == 0)
                    out.append(EmptyChar);
                else
                    out.append(Integer.toString(sk.getValue(i)));
        }            
        out.append("\r\n"+SsSPrefix+GeneratorUtil.Steps.getSolutionSteps(mode));
    }
    
    public static class Steps{                               
        public static String getStepCode(SolutionType type)
        {
            return type.getArgName()+Spliter;
        }        
        public static String getSolutionSteps(ClipboardMode mode)
        {
            StringBuilder out=new StringBuilder();
            StringBuilder out2=new StringBuilder();
            if(mode == ClipboardMode.STEPS)
            {
                SudokuSolver solver=SudokuSolverFactory.getDefaultSolverInstance();
                List<SolutionStep> steps=solver.getSteps();
                for(int i=0;i<steps.size();i++)
                {
                    SolutionStep sp=steps.get(i);
                    String str=GeneratorUtil.Steps.toString(sp,2);
                    String[] as=str.split("=>");
                    if(as.length==1)
                    {
                        as=str.split(":");                    
                    }
                    //out2.append(as[as.length-1].trim()+";");
                    out.append(str+Spliter);
                }
                //out.append("\r\n"+out2.toString());                
            }
            if(mode==ClipboardMode.STEP_SHORT||mode==ClipboardMode.STEP_SS_ARG||mode==ClipboardMode.STEP_SS_ARG_UPS)
            {
                SudokuSolver solver=SudokuSolverFactory.getDefaultSolverInstance();
                List<SolutionStep> steps=solver.getSteps();
                for(int i=0;i<steps.size();i++)
                {
                    SolutionStep sp=steps.get(i);
                    String str=GeneratorUtil.Steps.toString(sp,2);
                    String[] as=str.split("=>");
                    if(as.length==1)
                    {
                        as=str.split(":");                    
                    }
                    out2.append(as[as.length-1].trim()+Spliter);
                }
                out.append(out2.toString().replaceAll(" ",""));
                if(mode==ClipboardMode.STEP_SS_ARG||mode==ClipboardMode.STEP_SS_ARG_UPS)
                {
                    String ss=out.toString().replaceAll(" ","");
                    String sss=GeneratorUtil.convertToShort(ss);
                    out=new StringBuilder(sss);
                }
            }
            if(mode==ClipboardMode.STEP_SHORT_SHORT||mode==ClipboardMode.STEP_SS_UPS)
            {
                SudokuSolver solver=SudokuSolverFactory.getDefaultSolverInstance();
                List<SolutionStep> steps=solver.getSteps();
                for(int i=0;i<steps.size();i++)
                {
                    SolutionStep sp=steps.get(i);
                    String str=GeneratorUtil.Steps.toString(sp,3);
                    String[] as=str.split("=>");
                    if(as.length==1)
                    {
                        as=str.split(":");                    
                    }
                    out2.append(as[as.length-1].trim()+Spliter);
                }
                String ss=out2.toString().replaceAll(" ","");
                String sss=GeneratorUtil.convertToShort(ss);
                out.append(sss);                                
            }
            return out.toString();
        }
        
        public static String toString(SolutionStep step,int art) {        
            SolutionType type=step.getType();            
            String str = Steps.getStepCode(type);
            if(art==3)
            {
                art=2;
                str="";
            }
            int index = 0;
            StringBuffer tmp;            
            List<Integer>  values=step.getValues();
            List<Integer>  indices=step.getIndices();
            switch (type) {
                case FULL_HOUSE:
                case HIDDEN_SINGLE:
                case NAKED_SINGLE:
                    index = indices.get(0);                    
                    if(art==1)
                    {
                        str += "" + values.get(0);
                    }else
                    {
                        str+=getCellPrint(index, false) + "=" + values.get(0);
                    }                
                    break;
                case HIDDEN_QUADRUPLE:
                case NAKED_QUADRUPLE:
                case HIDDEN_TRIPLE:
                case NAKED_TRIPLE:
                case LOCKED_TRIPLE:
                case HIDDEN_PAIR:
                case NAKED_PAIR:
                case LOCKED_PAIR:
                    index = indices.get(0);                    
                    tmp = new StringBuffer(str);
                    if (art >= 1) {
                        //tmp.append(": ");
                        if (type == SolutionType.HIDDEN_PAIR || type == SolutionType.NAKED_PAIR || type == SolutionType.LOCKED_PAIR) {
                            tmp.append(values.get(0));
                            tmp.append(",");
                            tmp.append(values.get(1));
                        } else if (type == SolutionType.HIDDEN_TRIPLE || type == SolutionType.NAKED_TRIPLE || type == SolutionType.LOCKED_TRIPLE) {
                            tmp.append(values.get(0));
                            tmp.append(",");
                            tmp.append(values.get(1));
                            tmp.append(",");
                            tmp.append(values.get(2));
                        } else if (type == SolutionType.HIDDEN_QUADRUPLE || type == SolutionType.NAKED_QUADRUPLE) {
                            tmp.append(values.get(0));
                            tmp.append(",");
                            tmp.append(values.get(1));
                            tmp.append(",");
                            tmp.append(values.get(2));
                            tmp.append(",");
                            tmp.append(values.get(3));
                        }
                    }
                    if (art >= 2) {
                        tmp.append(" ");
                        //tmp.append(java.util.ResourceBundle.getBundle("intl/SolutionStep").getString("SolutionStep.in"));                    
                        //tmp.append(" ");
                        tmp.append(" in ");
                        tmp.append(getCompactCellPrint(indices));
                        step.getCandidatesToDelete(tmp);
                    }
                    str = tmp.toString();
                    break;
                case LOCKED_CANDIDATES:
                case LOCKED_CANDIDATES_1:
                case LOCKED_CANDIDATES_2:
                    if (art >= 1) {
                        str += ": " + values.get(0);
                    }
                    if (art >= 2) {
                        str += 
                                " "
                                //+ java.util.ResourceBundle.getBundle("intl/SolutionStep").getString("SolutionStep.in")
                                + "in "
                                //+ " " 
                                + step.getEntityShortName() + step.getEntityNumber();
                        tmp = new StringBuffer(str);
                        step.getCandidatesToDelete(tmp);
                        str = tmp.toString();
                    }
                    break;
                case SKYSCRAPER:
                case TWO_STRING_KITE:
                case DUAL_TWO_STRING_KITE:
                    if (art >= 1) {
                        str += "" + values.get(0);
                    }
                    if (art >= 2) {
                        str += " " + 
                                //java.util.ResourceBundle.getBundle("intl/SolutionStep").getString("SolutionStep.in") + " "+ 
                                " in "+
                                step.getCompactCellPrint(indices, 0, 1);
                        if (type == SolutionType.DUAL_TWO_STRING_KITE) {
                            str += "/" + 
                                    //java.util.ResourceBundle.getBundle("intl/SolutionStep").getString("SolutionStep.in") + " "+ 
                                    " in "+
                                    step.getCompactCellPrint(indices, 4, 5);
                        }
                        str += " ("
                                //+ java.util.ResourceBundle.getBundle("intl/SolutionStep").getString("SolutionStep.connected_by") + " "
                                +" cb "
                                + step.getCompactCellPrint(indices, 2, 3) + ")";
                        tmp = new StringBuffer(str);
                        step.getCandidatesToDelete(tmp);
                        str = tmp.toString();
                    }
                    break;
                case EMPTY_RECTANGLE:
                case DUAL_EMPTY_RECTANGLE:
                    if (art >= 1) {
                        str += "" + values.get(0);
                    }
                    if (art >= 2) {
                        str += " " 
                                //+ java.util.ResourceBundle.getBundle("intl/SolutionStep").getString("SolutionStep.in") + " "
                                +"in "
                                + step.getEntityShortName() + step.getEntityNumber()
                                + " (" + getCompactCellPrint(indices, 0, 1);
                        if (type == SolutionType.DUAL_EMPTY_RECTANGLE) {
                            str += "/" + getCompactCellPrint(indices, 2, 3);
                        }
                        str += ")";
                        tmp = new StringBuffer(str);
                        step.getCandidatesToDelete(tmp);
                        str = tmp.toString();
                    }
                    break;
                case W_WING:
                    if (art >= 1) {
                        str += "" + values.get(0) + "/" + values.get(1);
                    }
                    if (art >= 2) {
                        tmp = new StringBuffer(str);
                        //tmp.append(" ");
                        //tmp.append(java.util.ResourceBundle.getBundle("intl/SolutionStep").getString("SolutionStep.in"));
                        tmp.append(" in");
                        tmp.append(" ");
                        tmp.append(getCompactCellPrint(indices, 0, 1));
                        //tmp.append(" ");
                        //tmp.append(java.util.ResourceBundle.getBundle("intl/SolutionStep").getString("SolutionStep.connected_by"));
                        tmp.append(" cb");
                        tmp.append(" ");
                        tmp.append(values.get(1));
                        //tmp.append(" ");
                        //tmp.append(java.util.ResourceBundle.getBundle("intl/SolutionStep").getString("SolutionStep.in"));
                        tmp.append(" in");
                        tmp.append(" ");
                        step.getFinSet(tmp, step.getFins(), false);
                        step.getCandidatesToDelete(tmp);
                        str = tmp.toString();
                    }
                    break;
                case XY_WING:
                case XYZ_WING:
                    if (art >= 1) {
                        str += "" + values.get(0) + "/" + values.get(1);
                    }
                    if (art >= 2) {
                        str += "/" + values.get(2) 
                                //+ " " + java.util.ResourceBundle.getBundle("intl/SolutionStep").getString("SolutionStep.in") 
                                +" in"
                                + " " + getCompactCellPrint(indices);
                        tmp = new StringBuffer(str);
                        step.getCandidatesToDelete(tmp);
                        str = tmp.toString();
                    }
                    break;
                case SIMPLE_COLORS:
                case SIMPLE_COLORS_TRAP:
                case SIMPLE_COLORS_WRAP:
                case MULTI_COLORS:
                case MULTI_COLORS_1:
                case MULTI_COLORS_2:
                    if (art >= 1) {
                        str += "" + values.get(0);
                    }
                    if (art >= 2) {
                        tmp = new StringBuffer(str);
                        step.getColorCellPrint(tmp);
                        step.getCandidatesToDelete(tmp);
                        str = tmp.toString();
                    }
                    break;
                case X_CHAIN:
                case XY_CHAIN:
                case REMOTE_PAIR:
                case TURBOT_FISH:
                case NICE_LOOP:
                case CONTINUOUS_NICE_LOOP:
                case DISCONTINUOUS_NICE_LOOP:
                case GROUPED_NICE_LOOP:
                case GROUPED_CONTINUOUS_NICE_LOOP:
                case GROUPED_DISCONTINUOUS_NICE_LOOP:
                case AIC:
                case GROUPED_AIC:
                    if (art >= 1) {
                        if (type == SolutionType.REMOTE_PAIR) {
                            str += "" + values.get(0) + "/" + values.get(1);
                        } else {
                            str += "" +step. getCandidatesToDeleteDigits();
                        }
    //                    if (type == SolutionType.REMOTE_PAIR) {
    //                        str += ": " + values.get(0) + "/" + values.get(1);
    //                    } else if (type == SolutionType.X_CHAIN || type == SolutionType.XY_CHAIN) {
    //                    //} else if (type == SolutionType.X_CHAIN) {
    //                        //str += ": " + values.get(0);
    //                        str += ": " + candidatesToDelete.get(0).value;
    //                    }
                    }
                    if (art >= 2) {
                        List<Chain> dummy1 = step.getChains();
                        StringBuffer tmpChain = step.getChainString(dummy1.get(0));
                        // adjust nice loop notation
                        if (type == SolutionType.CONTINUOUS_NICE_LOOP || type == SolutionType.GROUPED_CONTINUOUS_NICE_LOOP) {
                            Chain ch = dummy1.get(0);
                            int start = ch.getStart();
                            int cellIndex = ch.getCellIndex(start);
                            while (ch.getCellIndex(start) == cellIndex) {
                                start++;
                            }
                            int end = ch.getEnd();
                            cellIndex = ch.getCellIndex(end);
                            while (ch.getCellIndex(end) == cellIndex) {
                                end--;
                            }
                            end++;
                            tmpChain.insert(0, ch.getCandidate(end) + "= ");
                            tmpChain.append(" =").append(ch.getCandidate(start));
                            //System.out.println(Chain.toString(ch.chain[start]) + "/" + Chain.toString(ch.chain[ch.end]));
                        }
                        if (type == SolutionType.AIC || type == SolutionType.GROUPED_AIC || type == SolutionType.XY_CHAIN) {
                            Chain ch = dummy1.get(0);
                            //System.out.println(Chain.toString(ch.chain[ch.start]) + "/" + Chain.toString(ch.chain[ch.end]));
                            tmpChain.insert(0, ch.getCandidate(ch.getStart()) + "- ");
                            tmpChain.append(" -").append(ch.getCandidate(ch.getEnd()));
                        }
                        //str += " " + getChainString(getChains().get(0));
                        str += " " + tmpChain;
                        tmp = new StringBuffer(str);
                        step.getCandidatesToDelete(tmp);
                        str = tmp.toString();
                    }
                    break;
                case FORCING_CHAIN:
                case FORCING_CHAIN_CONTRADICTION:
                case FORCING_CHAIN_VERITY:
                case FORCING_NET:
                case FORCING_NET_CONTRADICTION:
                case FORCING_NET_VERITY:
                    if (art >= 1) {
                        // Keine dezenten Hinweise bei Forcing Chains...
                    }
                    if (art >= 2) {
                        if (type == SolutionType.FORCING_CHAIN_CONTRADICTION
                                || type == SolutionType.FORCING_NET_CONTRADICTION) {
                            str += " " 
                                    //+ java.util.ResourceBundle.getBundle("intl/SolutionStep").getString("SolutionStep.in") + " " 
                                    +"in "
                                    + step.getEntityShortNameNumber();
                        } else {
                            //str += " Verity";
                        }
                        if (indices.size() > 0) {
                            str += " => " + getCellPrint(indices.get(0), false) + "=" + values.get(0);
                        } else {
                            tmp = new StringBuffer(str);
                            step.getCandidatesToDelete(tmp);
                            str = tmp.toString();
                        }
                        for (int i = 0; i < step.getChains().size(); i++) {
                            str += "fc  " + step.getForcingChainString(step.getChains().get(i));
                        }
                    }
                    break;
                case UNIQUENESS_1:
                case UNIQUENESS_2:
                case UNIQUENESS_3:
                case UNIQUENESS_4:
                case UNIQUENESS_5:
                case UNIQUENESS_6:
                case HIDDEN_RECTANGLE:
                case AVOIDABLE_RECTANGLE_1:
                case AVOIDABLE_RECTANGLE_2:
                    if (art >= 1) {
                        str += "" + values.get(0) + "/" + values.get(1);
                    }
                    if (art >= 2) {
                        str += " in " + getCompactCellPrint(indices);
                        tmp = new StringBuffer(str);
                        step.getCandidatesToDelete(tmp);
                        str = tmp.toString();
                    }
                    break;
                case BUG_PLUS_1:
                    if (art >= 2) {
                        tmp = new StringBuffer(str);
                        step.getCandidatesToDelete(tmp);
                        str = tmp.toString();
                    }
                    break;
                case X_WING:
                case SWORDFISH:
                case JELLYFISH:
                case SQUIRMBAG:
                case WHALE:
                case LEVIATHAN:
                case FINNED_X_WING:
                case FINNED_SWORDFISH:
                case FINNED_JELLYFISH:
                case FINNED_SQUIRMBAG:
                case FINNED_WHALE:
                case FINNED_LEVIATHAN:
                case SASHIMI_X_WING:
                case SASHIMI_SWORDFISH:
                case SASHIMI_JELLYFISH:
                case SASHIMI_SQUIRMBAG:
                case SASHIMI_WHALE:
                case SASHIMI_LEVIATHAN:
                case FRANKEN_X_WING:
                case FRANKEN_SWORDFISH:
                case FRANKEN_JELLYFISH:
                case FRANKEN_SQUIRMBAG:
                case FRANKEN_WHALE:
                case FRANKEN_LEVIATHAN:
                case FINNED_FRANKEN_X_WING:
                case FINNED_FRANKEN_SWORDFISH:
                case FINNED_FRANKEN_JELLYFISH:
                case FINNED_FRANKEN_SQUIRMBAG:
                case FINNED_FRANKEN_WHALE:
                case FINNED_FRANKEN_LEVIATHAN:
                case MUTANT_X_WING:
                case MUTANT_SWORDFISH:
                case MUTANT_JELLYFISH:
                case MUTANT_SQUIRMBAG:
                case MUTANT_WHALE:
                case MUTANT_LEVIATHAN:
                case FINNED_MUTANT_X_WING:
                case FINNED_MUTANT_SWORDFISH:
                case FINNED_MUTANT_JELLYFISH:
                case FINNED_MUTANT_SQUIRMBAG:
                case FINNED_MUTANT_WHALE:
                case FINNED_MUTANT_LEVIATHAN:
                case KRAKEN_FISH:
                case KRAKEN_FISH_TYPE_1:
                case KRAKEN_FISH_TYPE_2:
                    tmp = new StringBuffer();
                    if (step.getIsSiamese()) {
                        //tmp.append(java.util.ResourceBundle.getBundle("intl/SolutionStep").getString("SolutionStep.siamese")).append(" ");
                        tmp.append(" ss ");
                    }
                    tmp.append(tmp);
                    if (art >= 1) {
                        if (type.isKrakenFish()) {
                            tmp.append(": ");
                            step.getCandidatesToDelete(tmp);
                            tmp.append(" ff ").append(step.getSubType().getStepCode());
                        }
                        tmp.append(": ").append(values.get(0));
                    }
                    if (art >= 2) {
                        tmp.append(" ");
                        step.getEntities(tmp, step.getBaseEntities(), true, false);
                        tmp.append(" ");
                        step.getEntities(tmp, step.getBaseEntities(), true, true);
                        //tmp.append(" Positionen: ");
                        int displayMode = Options.getInstance().getFishDisplayMode();
                        if (type.isKrakenFish()) {
                            // no statistics
                            displayMode = 0;
                        }
                        switch (displayMode) {
                            case 0:
                                if (step.getFins().size() > 0) {
                                    tmp.append(" ");
                                    step.getFins(tmp, false, true);
                                }
                                if (step.getEndoFins().size() > 0) {
                                    tmp.append(" ");
                                    step.getFins(tmp, true, true);
                                }
                                break;
                            case 1:
                                step.getFishStatistics(tmp, false);
                                break;
                            case 2:
                                step.getFishStatistics(tmp, true);
                                break;
                        }
                        if (!type.isKrakenFish()) {
                            step.getCandidatesToDelete(tmp);
                        }
                    }
                    if (type.isKrakenFish()) {
                        for (int i = 0; i < step.getChains().size(); i++) {
                            tmp.append(" ff  ").append(step.getChainString(step.getChains().get(i)));
                        }
                    }
                    str = tmp.toString();
                    break;
                case SUE_DE_COQ:
                    tmp = new StringBuffer(str + "");
                    if (art >= 1) {
                        step.getIndexValueSet(tmp);
                        str = tmp.toString();
                    }
                    if (art >= 2) {
                        tmp.append(" (");
                        step.getFinSet(tmp, step.getFins());
                        tmp.append(", ");
                        step.getFinSet(tmp, step.getEndoFins());
                        tmp.append(")");
                        step.getCandidatesToDelete(tmp);
                        str = tmp.toString();
                    }
                    break;
                case ALS_XZ:
                    // Sets A und B stecken in AlsInSolutionStep, X ist eine 2-Elemente lange Chain, alle Z stecken in fins
                    tmp = new StringBuffer(str + "");
                    if (art >= 1) {
                        tmp.append("A=");
                        step.getAls(tmp, 0);
                        str = tmp.toString();
                    }
                    if (art >= 2) {
                        tmp.append(", B=");
                        step.getAls(tmp, 1);
                        tmp.append(", X=");
                        step.getAlsXorZ(tmp, true);
                        if (!step.getFins().isEmpty()) {
                            tmp.append(", Z=");
                            step.getAlsXorZ(tmp, false);
                        }
                        step.getCandidatesToDelete(tmp);
                        str = tmp.toString();
                    }
                    break;
                case ALS_XY_WING:
                    // Sets A, B und C stecken in AlsInSolutionStep, alle Y und Z stecken in endoFins, alle X stecken in fins
                    if (art == 1) {
                        tmp = new StringBuffer(str + "");
                        tmp.append("C=");
                        step.getAls(tmp, 2);
                        str = tmp.toString();
                    }
                    if (art >= 2) {
                        tmp = new StringBuffer(str + "");
                        tmp.append("A=");
                        step.getAls(tmp, 0);
                        tmp.append(", B=");
                        step.getAls(tmp, 1);
                        tmp.append(", C=");
                        step.getAls(tmp, 2);
                        tmp.append(", X,Y=");
                        step.getAlsXorZ(tmp, true);
                        tmp.append(", Z=");
                        step.getAlsXorZ(tmp, false);
                        step.getCandidatesToDelete(tmp);
                        str = tmp.toString();
                    }
                    break;
                case ALS_XY_CHAIN:
                    if (step.getRestrictedCommons().isEmpty()) {
                        // old code -> has to remain for correctly displaying saved files
                        if (art == 1) {
                            tmp = new StringBuffer(str + "");
                            tmp.append(
                                    //java.util.ResourceBundle.getBundle("intl/SolutionStep").getString("SolutionStep.start")
                                    " st "
                            ).append("=");
                            step.getAls(tmp, 0);
                            tmp.append(", ").append(
                                    //java.util.ResourceBundle.getBundle("intl/SolutionStep").getString("SolutionStep.end")
                                    " ed "
                            ).append("=");
                            step.getAls(tmp, step.getAlses().size() - 1);
                            str = tmp.toString();
                        }
                        if (art >= 2) {
                            tmp = new StringBuffer(str + "");
                            char alsChar = 'A';
                            boolean first = true;
                            for (int i = 0; i < step.getAlses().size(); i++) {
                                if (first) {
                                    first = false;
                                } else {
                                    tmp.append(", ");
                                }
                                tmp.append(alsChar++);
                                tmp.append("=");
                                step.getAls(tmp, i);
                            }
                            tmp.append(", RCs=");
                            step.getAlsXorZ(tmp, true);
                            tmp.append(", X=");
                            step.getAlsXorZ(tmp, false);
                            step.getCandidatesToDelete(tmp);
                            str = tmp.toString();
                        }
                    } else {
                        if (art == 1) {
                            tmp = new StringBuffer(str + "");
                            tmp.append(
                                    //java.util.ResourceBundle.getBundle("intl/SolutionStep").getString("SolutionStep.start")
                                    " st "
                            ).append("=");
                            step.getAls(tmp, 0);
                            tmp.append(", ").append(
                                    //java.util.ResourceBundle.getBundle("intl/SolutionStep").getString("SolutionStep.end")
                                    " ed "
                            ).append("=");
                            step.getAls(tmp, step.getAlses().size() - 1);
                            str = tmp.toString();
                        }
                        if (art >= 2) {
                            tmp = new StringBuffer(str + "");
                            step.getCandidatesToDeleteDigits(tmp);
                            tmp.append("- ");
                            for (int i = 0; i < step.getAlses().size(); i++) {
                                step.getAls(tmp, i);
                                if (i < step.getRestrictedCommons().size()) {
                                    step.getRestrictedCommon(step.getRestrictedCommons().get(i), tmp);
                                }
                            }
                            tmp.append(" -");
                            step.getCandidatesToDeleteDigits(tmp);
                            step.getCandidatesToDelete(tmp);
                            str = tmp.toString();
                        }
                    }
                    break;
                case DEATH_BLOSSOM:
                    tmp = new StringBuffer(str + "");
                    if (art >= 1) {
                        tmp.append(getCellPrint(indices.get(0)));
                        str = tmp.toString();
                    }
                    if (art >= 2) {
                        for (int i = 0; i < step.getAlses().size(); i++) {
                            tmp.append(", ");
                            step.getRestrictedCommon(step.getRestrictedCommons().get(i), tmp);
                            step.getAls(tmp, i);
                        }
                        step.getCandidatesToDelete(tmp);
                        str = tmp.toString();
                    }
                    break;
                case TEMPLATE_SET:
                    if (art == 1) {
                        str += "" + values.get(0);
                    }
                    if (art >= 2) {
                        tmp = new StringBuffer(str + "");
                        tmp.append(getCompactCellPrint(indices)).append("=").append(values.get(0));
                        str = tmp.toString();
                    }
                    break;
                case TEMPLATE_DEL:
                    if (art >= 1) {
                        // nichts zusätzlich ausgeben
                    }
                    if (art >= 2) {
                        tmp = new StringBuffer(str + "");
                        step.getCandidatesToDelete(tmp);
                        str = tmp.toString();
                    }
                    break;
                case BRUTE_FORCE:
                    if (art == 1) {
                        str += "" + values.get(0);
                    }
                    if (art >= 2) {
                        tmp = new StringBuffer(str + "");
                        tmp.append(getCompactCellPrint(indices)).append("=").append(values.get(0));
                        str = tmp.toString();
                    }
                    break;
                case INCOMPLETE:
                    str += 
                            " ics ";
                            //java.util.ResourceBundle.getBundle("intl/SolutionStep").getString("SolutionStep.incomplete_solution");
                    break;
                case GIVE_UP:
                    tmp = new StringBuffer();
                    tmp.append(str);
                    if (art >= 1) {
                        tmp.append(":").append(
                                //java.util.ResourceBundle.getBundle("intl/SolutionStep").getString("SolutionStep.dont_know")
                                " dk "
                        );
                    }
                    str = tmp.toString();
                    break;
                default:
                    throw new RuntimeException(java.util.ResourceBundle.getBundle("intl/SolutionStep").getString("SolutionStep.invalid_type") + " (" + type + ")!");
            }
            return str;
        }                
    }
    
    
    private static String idx1 = "@ABCDEFGHIJKLMNOPQRSTUVWXYZ#";
    private static String idx2 ="$123456789abcdefghijklmnop&";
    
    public static String getCellShortStr(int index)
    {
        if(index==-1)
        {
            return "!-1";
        }
        String cells=idx1.charAt(Sudoku2.getLine(index)+1)+idx2.charAt(Sudoku2.getCol(index)+1)+"";
        return cells;
    }
    
    public static String convertToShort(String cellStr)
    {
        String shortCellStr="";
        for(int i=0;i<cellStr.length();i++)
        {
            if(cellStr.charAt(i)=='r')
            {
                int count=CountNumStr(cellStr,i+1);
                for(int j=0;j<count;j++)
                {
                    int n=cellStr.charAt(i+j+1)-48;
                    shortCellStr+=idx1.charAt(n);
                }
                i+=count;
            }
            else if(cellStr.charAt(i)=='c')
            {
                int count=CountNumStr(cellStr,i+1);
                for(int j=0;j<count;j++)
                {
                    int n=cellStr.charAt(i+j+1)-48;
                    shortCellStr+=idx2.charAt(n);                    
                }
                i+=count;
            }
            else
            {
                shortCellStr+=cellStr.charAt(i);
            }
        }
        return shortCellStr;
    }
    
    public static int CountNumStr(String cellStr,int idx)
    {
        int count=0;
        for(int i=idx;i<cellStr.length();i++)
        {
            if(cellStr.charAt(i)>48&&cellStr.charAt(i)<58)
            {
                count++;                
            }else
            {
                break;
            }
        }
        return count;
    }
    
    public static String diamond(long value)
    {
        String str="万亿兆京垓秭穰沟涧正载极";
        //TODO:convert 
        return str;
    }
    
    public static long[] combie(int radix)
    {
        //funny econmy
        //81  2.4万亿亿         2417851639229258349412351--overflow
        //64  184.5亿亿         18446744073709551611--overflow
        //63  92亿亿            9223372036854775806
        //49  56万亿            562949953421311
        //36  687亿             68719476735
        //25  3355万            33554431
        //20  105万             1048575
        //16  6.5万             65535
        //12  4千               4095
        long[] combis=new long[radix+1];
        for(int c=0;c<radix;c++)
        {            
            combis[c]=(long)Math.pow(2, radix-c)-1;
            System.out.print((radix-c)+":"+combis[c]+"\r\n");
        }
        return combis;
    }
    
    public static long[] combi(int radix)
    {
        long[] combis=new long[radix];
        for(int c=0;c<radix;c++)
        {            
            long rtl=cnrt(radix-c);
            combis[c]=rtl;
            System.out.print((c+1)+":"+rtl+"\r\n");
        }        
        return combis;
    }
    
    public static long cnrt(int n)
    {
        long rtl=0;
        for(int i=0;i<n;i++)
        {
            rtl+=cnr(n,i+1);
        }        
        return rtl;
    }
    
    public static long pnr(int n,int r)
    {
        long rtl=fatr(n)/fatr(n-r);
        return rtl;
    }
    
    public static long cnr(int n,int r)
    {
        long rtl=fatr(n)/(fatr(r)*fatr(n-r));
        return rtl;
    }
    
    public static long fatr(int factor)
    {        
        long rlt=factor;
        while(factor>1)
        {
            rlt*=--factor;
        }
        return Math.max(rlt,1);
    }
    
    //中文转Unicode   //copy from net
    public static String gbEncoding(final String gbString) {   //gbString = "测试"
        char[] utfBytes = gbString.toCharArray();   //utfBytes = [测, 试]
        String unicodeBytes = "";
        for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
            String hexB = Integer.toHexString(utfBytes[byteIndex]);   //转换为16进制整型字符串
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes = unicodeBytes + "\\u" + hexB;
        }
        System.out.println("unicodeBytes is: " + unicodeBytes);
        return unicodeBytes;
    }
 
    //Unicode转中文   //copy from net
    public static String decodeUnicode(final String dataStr) {
        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = dataStr.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = dataStr.substring(start + 2, dataStr.length());
            } else {
                charStr = dataStr.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。   
            buffer.append(new Character(letter).toString());
            start = end;
        }
        return buffer.toString();
    }
}
