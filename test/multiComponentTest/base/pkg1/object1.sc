package com.vs.pkg1;

public object object1 extends Class1 {
   member = object2; 
   member2 = object3;

   public static void main(String[]args) {
      System.out.println("11: " + object1.member.foo);
      System.out.println("666: " + object1.member2.x);
   }
}
