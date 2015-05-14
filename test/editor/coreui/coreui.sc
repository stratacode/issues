package sc.editor;

@sc.js.JSSettings(jsModuleFile="js/sceditor.js")
public test.editor.coreui extends editor.model, gui.util.core {
   // This is one of those layers which could go either way...
   //defaultSyncMode = sc.obj.SyncMode.Automatic;
   codeType = sc.layer.CodeType.Application;
   codeFunction = sc.layer.CodeFunction.UI;

   liveDynamicTypes = layeredSystem.options.editEditor;
}
