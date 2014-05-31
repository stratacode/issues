package sc.layer;

import java.util.EnumSet;

public enum CodeFunction {
   Program, Style, UI, Business, Admin;

   public static EnumSet<CodeFunction> allSet = EnumSet.allOf(CodeFunction.class);
}
