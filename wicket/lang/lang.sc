package sc.wicket;

wicket.lang {
   buildSeparate = true;
   compiledOnly = true;

   public void init() {
      excludeRuntimes("js", "android", "gwt");
      excludeProcesses("Desktop");
   }
}
