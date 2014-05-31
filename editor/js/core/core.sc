package sc.editor;

@sc.js.JSSettings(prefixAlias="sce_", jsModuleFile="js/sceditor.js")
public editor.js.core extends html.core, js.layer, jetty.schtml, gui.util.html, editor.coreui {
  codeType = sc.layer.CodeType.Application;
  codeFunction = sc.layer.CodeFunction.UI;

  // Set this to true for refresh when changing the editor files
  liveDynamicTypes = layeredSystem.options.editEditor;
}
