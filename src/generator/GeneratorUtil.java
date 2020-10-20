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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import solver.SudokuSolver;
import solver.SudokuSolverFactory;
import sudoku.Candidate;
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
    
    public static String eascii=" ¡¢£¤¥¦§¨©ª«¬­®¯°±²³´µ¶·¸¹º»¼½¾¿ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖ×ØÙÚÛÜÝÞßàáâãäåæçèéêëìíîïðñòóôõö÷øùúûüýþÿ";
    public static String ascii="!#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";
    public static String all_ascii=ascii+eascii;    
    public static String EmptyChar=".";
    public static boolean ISPrefix=false;
    public static String PuzzlePrefix="q:";
    public static String SsSPrefix="s:";
    public static String AnsPrefix="a:";
    
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
    
    public static String GetPuzzleStr(String str)
    {
        String rstr=(int)Math.sqrt(str.length())+".";
        for(int i=0;i<str.length();i++)
        {
            if(str.charAt(i)!='0'&&str.charAt(i)!='.')
            {                
                rstr+=Steps.GetEIIndice(i)+Steps.GetEIValue(Integer.parseInt(str.charAt(i)+""));                
            }
        }
        return rstr;
    }
    public static void GetSukodu(Sudoku2 sk,StringBuilder out,ClipboardMode mode)
    {
        StringBuilder o=new StringBuilder();
        if(ISPrefix)
            o.append(PuzzlePrefix);
        if (mode == ClipboardMode.STEPS||mode==ClipboardMode.STEP_SHORT||mode==ClipboardMode.STEP_SHORT_SHORT||mode==ClipboardMode.STEP_SS_ARG)
        {
            for (int i = 0; i < sk.LENGTH; i++)              
                if (sk.getValue(i) == 0 || !sk.isFixed(i))
                    o.append(EmptyChar);                
                else               
                    o.append(Integer.toString(sk.getValue(i)));            
        }
        else
        {
            for (int i = 0; i < sk.LENGTH; i++)
                if(sk.getValue(i) == 0)
                    o.append(EmptyChar);
                else
                    o.append(Integer.toString(sk.getValue(i)));
        }
        String str=GetPuzzleStr(o.toString());
        out.append(str);
        if(ISPrefix)
            out.append("\n"+SsSPrefix+GeneratorUtil.Steps.getSolutionSteps(mode));
        else
            out.append("\n"+GeneratorUtil.Steps.getSolutionSteps(mode));
        //String astr=GeneratorUtil.Steps.getSolutionSteps(mode);
        Integer len=str.length();
        System.out.print(len+":"+out.toString());
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
                    out2.append(str);
                }
                //String ss=out2.toString().replaceAll(" ","");
                //String sss=GeneratorUtil.convertToShort(ss);
                out.append(out2.toString());
            }
            return out.toString();
        }
        
        public static String GetEIIndice(int idx)
        {               
            return ascii.substring(idx,idx+1);
        }
        
        public static String GetEIValue(int idx)
        {            
            return ascii.substring(idx,idx+1);
        }
        
        public static void getCandidatesToDelete(SolutionStep step,StringBuffer tmp) {            
            ArrayList<Candidate> tmpList = (ArrayList<Candidate>) ((ArrayList<Candidate>) step.getCandidatesToDelete()).clone();
            ArrayList<Integer> candList = new ArrayList<Integer>();
            ArrayList<String> cands=new ArrayList<String>();
            StringBuilder debug=new StringBuilder();
            while (tmpList.size() > 0) {
                Candidate firstCand = tmpList.remove(0);
                candList.clear();
                cands.clear();
                candList.add(firstCand.getIndex());
                Integer t=ascii.length()-firstCand.getValue();                
                String dstr=ascii.substring(firstCand.getIndex(),firstCand.getIndex()+1)+""+ascii.substring(t,t+1)+"";
                tmp.append(dstr);
                tmp.append("");
                debug.append(firstCand.getIndex()+"!"+firstCand.getValue()+":"+t);
                //System.out.println(firstCand.getValue()+":"+all_ascii.substring(t,t+1));
                Iterator<Candidate> it = tmpList.iterator();
                while (it.hasNext()) {
                    Candidate c1 = it.next();
                    if (c1.getValue() == firstCand.getValue()) {
                        candList.add(c1.getIndex());
                        t=ascii.length()-firstCand.getValue();
                        //System.out.println(firstCand.getValue()+":"+all_ascii.substring(t,t+1));
                        dstr=ascii.substring(c1.getIndex(),c1.getIndex()+1)+""+ascii.substring(t,t+1)+"";
                        tmp.append(dstr);
                        tmp.append("");
                        debug.append(c1.getIndex()+"!"+firstCand.getValue()+":"+t);
                        it.remove();
                    }
                }
            }
        }
        
        public static String toString(SolutionStep step,int art) {                        
            SolutionType type=step.getType();            
            String str = "";
            int index = 0;
            StringBuffer tmp;            
            List<Integer>  values=step.getValues();
            List<Integer>  indices=step.getIndices();
            switch (type) {
                case FULL_HOUSE:
                case HIDDEN_SINGLE:
                case NAKED_SINGLE:
                    index = indices.get(0);                           
                    str+=Steps.GetEIIndice(index)+Steps.GetEIValue(values.get(0)) ;
                    break;
                case HIDDEN_QUADRUPLE:
                case NAKED_QUADRUPLE:
                case HIDDEN_TRIPLE:
                case NAKED_TRIPLE:
                case LOCKED_TRIPLE:
                case HIDDEN_PAIR:
                case NAKED_PAIR:
                case LOCKED_PAIR:                                      
                    tmp = new StringBuffer(str);                    
                    Steps.getCandidatesToDelete(step, tmp);                                       
                    str = tmp.toString();
                    break;
                case LOCKED_CANDIDATES:
                case LOCKED_CANDIDATES_1:
                case LOCKED_CANDIDATES_2:
                    tmp = new StringBuffer(str);
                    Steps.getCandidatesToDelete(step, tmp);
                    str = tmp.toString();
                    break;
                case SKYSCRAPER:
                case TWO_STRING_KITE:
                case DUAL_TWO_STRING_KITE:                    
                    tmp = new StringBuffer(str);
                    Steps.getCandidatesToDelete(step, tmp);
                    str = tmp.toString();                    
                    break;
                case EMPTY_RECTANGLE:
                case DUAL_EMPTY_RECTANGLE:
                    tmp = new StringBuffer(str);
                    Steps.getCandidatesToDelete(step, tmp);
                    str = tmp.toString();
                    break;
                case W_WING:
                    tmp = new StringBuffer(str);                        
                    Steps.getCandidatesToDelete(step, tmp);
                    str = tmp.toString();
                    break;
                case XY_WING:
                case XYZ_WING:
                    tmp = new StringBuffer(str);
                    Steps.getCandidatesToDelete(step, tmp);
                    str = tmp.toString();
                    break;
                case SIMPLE_COLORS:
                case SIMPLE_COLORS_TRAP:
                case SIMPLE_COLORS_WRAP:
                case MULTI_COLORS:
                case MULTI_COLORS_1:
                case MULTI_COLORS_2:
                    tmp = new StringBuffer(str);
                    Steps.getCandidatesToDelete(step, tmp);
                    str = tmp.toString();
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
                    tmp = new StringBuffer(str);                    
                    Steps.getCandidatesToDelete(step, tmp);
                    str = tmp.toString();
                    break;
                case FORCING_CHAIN:
                case FORCING_CHAIN_CONTRADICTION:
                case FORCING_CHAIN_VERITY:
                case FORCING_NET:
                case FORCING_NET_CONTRADICTION:
                case FORCING_NET_VERITY:
                    tmp = new StringBuffer(str);
                    Steps.getCandidatesToDelete(step, tmp);
                    str = tmp.toString();
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
                    tmp = new StringBuffer(str);
                    Steps.getCandidatesToDelete(step, tmp);
                    str = tmp.toString();
                    break;
                case BUG_PLUS_1:
                    tmp = new StringBuffer(str);
                    Steps.getCandidatesToDelete(step, tmp);
                    str = tmp.toString();
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
                    tmp = new StringBuffer(str);
                    Steps.getCandidatesToDelete(step, tmp);
                    str = tmp.toString();
                    break;
                case SUE_DE_COQ:
                    tmp = new StringBuffer(str + "");
                    Steps.getCandidatesToDelete(step, tmp);
                    str = tmp.toString();
                    break;
                case ALS_XZ:
                    tmp = new StringBuffer(str + "");
                    Steps.getCandidatesToDelete(step, tmp);
                    str = tmp.toString();
                    break;
                case ALS_XY_WING:
                    tmp = new StringBuffer(str + "");
                    Steps.getCandidatesToDelete(step, tmp);
                    str = tmp.toString();                    
                    break;
                case ALS_XY_CHAIN:
                    tmp = new StringBuffer(str + "");
                    Steps.getCandidatesToDelete(step, tmp);
                    str = tmp.toString();
                    break;
                case DEATH_BLOSSOM:
                    tmp = new StringBuffer(str + "");
                    Steps.getCandidatesToDelete(step, tmp);
                    str = tmp.toString();
                    break;
                case TEMPLATE_SET:
                    tmp = new StringBuffer(str + "");
                    //tmp.append(step.getCompactCellPrint(indices)).append("=").append(values.get(0));
                    Iterator<Integer> it=indices.iterator();
                    while (it.hasNext()) {                        
                        tmp.append(Steps.GetEIIndice(it.next())+Steps.GetEIValue(values.get(0)));
                    }
                    str = tmp.toString();
                    break;
                case TEMPLATE_DEL:
                    tmp = new StringBuffer(str + "");
                    Steps.getCandidatesToDelete(step, tmp);
                    str = tmp.toString();
                    break;
                case BRUTE_FORCE:
                    tmp = new StringBuffer(str + "");
                    //tmp.append(step.getCompactCellPrint(indices)).append("=").append(values.get(0));
                    Iterator<Integer> it1=indices.iterator();
                    while (it1.hasNext()) {                        
                        tmp.append(Steps.GetEIIndice(it1.next())+Steps.GetEIValue(values.get(0)));
                    }
                    str = tmp.toString();
                    break;
                case INCOMPLETE:
                    str += "";// ics 
                    break;
                case GIVE_UP:
                    tmp = new StringBuffer();
                    tmp.append(str);
                    tmp.append("");//GiveUp
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
    private static String str="万亿兆京垓秭穰沟涧正载极";
    
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
        shortCellStr=shortCellStr.replace("=", "");
        shortCellStr=shortCellStr.replace("<>", "!");
        shortCellStr=shortCellStr.replace(",", "");
        shortCellStr=shortCellStr.replace("(", "");
        shortCellStr=shortCellStr.replace(")", "");
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
