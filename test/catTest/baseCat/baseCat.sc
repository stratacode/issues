package jpaCat;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

import jpaCat.CacheTest;

test.catTest.baseCat extends jpa.basejpa {
   public void start() {
      sc.layer.LayeredSystem system = getLayeredSystem();
      // Turns on class load time enhancement 
      system.buildInfo.addVMParameter("verbosegc", "-verbose:gc");
   }
}
