package sc.wicket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.Request;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.Response;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WebRequest;
import org.apache.wicket.settings.ISecuritySettings;
import org.apache.wicket.util.crypt.ClassCryptFactory;
import org.apache.wicket.util.crypt.NoCrypt;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.Model;

import org.apache.wicket.feedback.FeedbackMessage;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.string.Strings;

import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.util.time.Duration;

import sc.wicket.BoxBorder;
import sc.wicket.ApplicationPath;
import sc.wicket.WicketApplication;

import org.apache.wicket.util.resource.*;

import org.apache.wicket.settings.*;

public wicket.lib extends jetty.servlet, lang, log4j {
   compiledOnly = true;
   //classPath=sc.util.FileUtil.listFiles(getRelativeFile("./lib"),".*\\.jar");

   public void init() {
      layeredSystem.serverEnabled = true;
   }

   object wicketPkg extends MvnRepositoryPackage {
      url = "mvn://org.apache.wicket/wicket/1.4.6";
   }

   object portletPkg extends MvnRepositoryPackage {
      url = "mvn://javax.portlet/portlet-api/2.0";
   }

   public void start() {
      sc.layer.LayeredSystem system = getLayeredSystem();

      sc.layer.LayerFileProcessor htmlFileProcessor = new sc.layer.LayerFileProcessor();

      // We do use the package hierarchy.  An alternate design is to turn this off and pick
      // a fixed doc root.  This design ties paths in the doc root to types in Java.  I like this
      // design but where we can map the web root to some package in the hierarchy.
      htmlFileProcessor.prependLayerPackage = true;
      htmlFileProcessor.useSrcDir = false;
      // Store them into WEB-INF/classes as configured as the buildClassesSubDir.
      htmlFileProcessor.useClassesDir = true;
      // Do not copy html files in dynamic layers - instead, those directories are put into
      // the resource path (with package prefix prepended)
      htmlFileProcessor.compiledLayersOnly = true;
      registerFileProcessor(htmlFileProcessor, "html");

      // Override the default jetty html processor entirely, even for the jetty server's index.html file
      htmlFileProcessor.definedInLayer = null;    

/*
      sc.repos.RepositoryPackage pkg = addRepositoryPackage("wicketLibs", "scp", "vsgit@stratacode.com:/home/vsgit/wicketLibs", false);
      if (pkg.installedRoot != null && !disabled) {
         classPath=sc.util.FileUtil.listFiles(pkg.installedRoot,".*\\.jar");
      }
*/
   }
}
