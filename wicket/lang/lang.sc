package sc.wicket;

wicket.lang {
   buildSeparate = true;
   compiledOnly = true;

   public void initialize() {
      excludeRuntimes("js", "android", "gwt");
   }
}
