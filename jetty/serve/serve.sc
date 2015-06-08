package sc.jetty;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.webapp.WebAppContext;

/** The jetty server layer.  
 * Adds processors for web files.  Must extend the util layer so we override its processors which do not
 * prepend the "web" prefix 
 */
public jetty.serve extends meta, util, html.core {
   compiledOnly = true;

   codeType = sc.layer.CodeType.Framework;
   codeFunction = sc.layer.CodeFunction.Program;

   public void initialize() {
      // Requires the default runtime.  It and any layers which extend it cannot run on JSP
      // This is in servlet/webApp now
      //addRuntime(null);
   }
}
