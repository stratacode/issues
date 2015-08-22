package jpaCat;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

import jpaCat.CacheTest;

test.catTest.baseCat extends jpa.basejpa {
   disabled = true;
   public void start() {
      sc.layer.LayeredSystem system = getLayeredSystem();
      system.addVMParameter("verbosegc", "-verbose:gc");
   }
}
