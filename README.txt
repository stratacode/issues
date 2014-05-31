This is the set of layers for StrataCode.  It is a mix of tests, libraries and demos.

Demos:
  quiz, unitConverter - each contain packages of layers to build various versions of these apps for swing and android
  example

Frameworks:

Swing:
  swing.core: the swing wrapper layer - use for swing data binding, hierarchical objects

Android:
  android.core: the core android layer used for building all android projects - no behavior changes

jpa.openjpa:
  Base openjpa layer.  Requires that you make a link from jpa/openjpa/lib to your /usr/local/openjpa/lib directory.  Handles JPA jar dependencies and registers processors to copy persistence.xml.

junit.main:
  Include junit.debug, put junit.jar into: junit/lib/lib/junit.jar, use @Test annotation.  Run Junit normally or use the "-t .*" option to run all junit tests.

gwt:
  decomissioned - not tested in a long while.  The basic unitConverter app did work at one time.  Compile times are too slow and runtime too complicated.  Need an integrated stratacode client/server framework which generates simple javascript which is updated dynamically, patches layered DOMs and syncs changes from client/server declaratively.

See the tests/scripts/runall script for the command lines to run the various tests.


