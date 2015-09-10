class Wildcard {

  protected Class<?>[] getRootConfigClasses() {
    return new Class<?>[] { Wildcard.class };
  }

  protected Class<?>[] getServletConfigClasses() {
    return new Class<?>[] { Wildcard.class };
  }

  protected String[] getServletMappings() {
    return new String[] { "/" };
  }

}


