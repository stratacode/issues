This is the set of layers for StrataCode.  It is a mix of tests, libraries and demos.

example/
  quiz, unitConverter, todo, expertSystem, and more - each contain packages of layers to build various versions of these apps for different frameworks. 

Frameworks:

   swing:
     swing.core: the swing wrapper layer - use for swing data binding, hierarchical objects

   html - a group of layers which implement an HTML templating system including: schtml 

   js - extends the html layers and adds Java to JS conversion

   servlet, jetty - HTTP Java based server frameworks


   android - framework layers to integrate with the android system

   jpa.openjpa:
     Base openjpa layer.  Requires that you make a link from jpa/openjpa/lib to your /usr/local/openjpa/lib directory.  Handles JPA jar dependencies and registers processors to copy persistence.xml.

   junit.main:
     Include junit.debug, put junit.jar into: junit/lib/lib/junit.jar, use @Test annotation.  Run Junit normally or use the "-t .*" option to run all junit tests.

   wicket: integration with wicket web framework

   gwt:
     decomissioned for now - integration with the GWT system
   
test - tests and the scripts to run them in scripts/runAllAuto, runAllManual, etc.


