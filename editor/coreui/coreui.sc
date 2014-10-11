package sc.editor;

@sc.js.JSSettings(jsModuleFile="js/sceditor.js")
public editor.coreui extends model, gui.util.core {
   defaultSyncMode = sc.obj.SyncMode.Automatic;
   codeType = sc.layer.CodeType.Application;
   codeFunction = sc.layer.CodeFunction.UI;

   liveDynamicTypes = layeredSystem.options.editEditor;
}
