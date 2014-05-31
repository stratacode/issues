package sc.jetty;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.handler.ResourceHandler;
import org.mortbay.jetty.handler.HandlerList;
import org.mortbay.jetty.handler.DefaultHandler;
import org.mortbay.jetty.Handler;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.webapp.WebAppContext;

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
