UIIcon {
   Icon icon;
   UIIcon(String dir, String p, String d) {
       super(dir, p, d);
       icon = new ImageIcon(UIIcon.class.getResource(dir + path), desc);
    }
}
