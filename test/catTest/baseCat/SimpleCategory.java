import java.util.Set;

@Entity
public class SimpleCategory {
   @Id
   private int id;

   public void setId(int i) {
      id = i;
   }
   public int getId() {
      return id;
   }

   private String name;
   public String getName() {
      return name;
   }
   public void setName(String n) {
      name = n;
   }

   private int depth, childIndex;

   public void setDepth(int d) {
      depth = d;
   }

   public void setChildIndex(int ci) {
      childIndex = ci;
   }

   public int getDepth() {
      return depth;
   }

   public int getChildIndex() {
      return childIndex;
   }

   @Column(length=100)
   private String description;
   public String getDescription() {
      return description;
   }
   public void setDescription(String d) {
      description = d;
   }

   @Column(length=1024)
   private String longDescription;
   public String getLongDescription() {
      return longDescription;
   }
   public void setLongDescription(String ld) {
      longDescription = ld;
   }

   @ManyToOne
   @JoinColumn(name="parentCategoryId")
   private SimpleCategory parentCategory;
   public SimpleCategory getParentCategory() {
      return parentCategory;
   }
   public void setParentCategory(SimpleCategory c) {
      parentCategory = c;
   }

   @OneToMany(mappedBy="parentCategory", fetch=FetchType.LAZY)
   private Set<SimpleCategory> children;

   public Set<SimpleCategory> getChildren() {
      if (children == null) 
         children = new HashSet<SimpleCategory>(BaseCatalogManager.numChildren);
      return children;
   }
   public void setChildren(Set<SimpleCategory> c) {
      children = c;
   }
   public Iterator<SimpleCategory> getChildrenIterator() {
      return children.iterator();
   }

   @ManyToMany(fetch=FetchType.LAZY, mappedBy="parentCategories")
   private List<SimpleCategory> fixedChildCategories;

   public List<SimpleCategory> getFixedChildCategories() {
      if (fixedChildCategories == null) 
         fixedChildCategories = new ArrayList<SimpleCategory>(BaseCatalogManager.numChildren);
      return fixedChildCategories;
   }
   public void setFixedChildCategories(List<SimpleCategory> fc) {
      fixedChildCategories = fc;
   }
   public Iterator<SimpleCategory> getFixedChildCategoriesIterator() {
      return getFixedChildCategories().iterator();
   }

   public void setParent(SimpleCategory parent) {
      setParentCategory(parent);
      parent.getChildren().add(this);

      getParentCategories().add(parent);
      parent.getFixedChildCategories().add(this);
   }

   public void addChildCategory(SimpleCategory child) {
      // Update the children (one-to-many)
      child.setParentCategory(this);
      getChildren().add(child);

      // Replicate in fixedChildCategories/parentCategories (many-to-many)
      getFixedChildCategories().add(child);
      child.getParentCategories().add(this);
   }

   @ManyToMany(fetch=FetchType.LAZY)
   private Set<SimpleCategory> parentCategories;

   public Set<SimpleCategory> getParentCategories() {
      if (parentCategories == null) 
         parentCategories = new HashSet(3);
      return parentCategories;
   }
   public void setParentCategories(Set<SimpleCategory> fc) {
      parentCategories = fc;
   }
   public Iterator<SimpleCategory> getParentCategoriesIterator() {
      return getParentCategories().iterator();
   }

   public boolean equals(Object other) {
      if (other instanceof SimpleCategory) {
         return other == this || ((SimpleCategory)other).getId() == this.getId();
      }
      return false;
   }

   public int hashCode() {
      return id * 17;
   }
}
/*
   @JoinTable(name="dcs_cat_chldcat",
              joinColumns=
              @JoinColumn(name="child_cat_id", referencedColumnName="child_cat_id"),
         inverseJoinColumns=
               @JoinColumn(name="category_id", referencedColumnName="category_id"))
               */
