import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Set;

@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
SimpleCategory {
   @Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
   @OneToMany(mappedBy="parentCategory", fetch=FetchType.LAZY)
   private Set<SimpleCategory> children;
   
   @Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
   @ManyToMany(fetch=FetchType.LAZY, mappedBy="parentCategories")
   private List<SimpleCategory> fixedChildCategories;

   @Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
   @ManyToMany(fetch=FetchType.LAZY)
   private Set<SimpleCategory> parentCategories;
}
