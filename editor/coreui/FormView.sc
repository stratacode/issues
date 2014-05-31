import sc.layer.Layer;
import sc.dyn.DynUtil;
import sc.type.CTypeUtil;

import sc.bind.Bind;
import sc.bind.AbstractListener;
import sc.bind.IListener;

import java.util.Iterator;

class FormView {
   EditorModel editorModel;
   boolean viewVisible;

   int numCols = 1;
   int nestWidth = 10;

   int tabSize = 140;

   /** When making change to a type, do we go ahead and update all instances? */
   boolean updateInstances = true;

   editorModel =: invalidateForm();
   viewVisible =: invalidateForm();

   JavaModel currentJavaModel := editorModel.currentJavaModel;
   currentJavaModel =: invalidateForm();

   public void invalidateForm() {
   }

}
