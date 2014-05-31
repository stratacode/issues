// Note - need the server layer to be in front of the ui layer here.  Those so the server layer is available for the ui layer to
// use in remote methods.
public example.remoteMethod.main extends js.schtml, jetty.schtml, example.remoteMethod.server, example.remoteMethod.ui {
}
