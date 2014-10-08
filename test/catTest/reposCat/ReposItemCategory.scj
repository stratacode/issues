import java.util.Set;
import java.util.HashSet;
import jpaCat.SimpleCategory;

public class ReposItemCategory extends SimpleCategory {
   MutableRepositoryItem item;

   public ReposItemCategory(MutableRepositoryItem theItem) {
      item = theItem;
   }

   public String getName() {
      return (String) item.getPropertyValue("name");
   }
   public void setName(String n) {
      item.setPropertyValue("name", n);
   }

   public int getDepth() {
      return (Integer) item.getPropertyValue("depth");
   }
   public void setDepth(int depth) {
      item.setPropertyValue("depth", new Integer(depth));
   }

   public int getChildIndex() {
      return (Integer) item.getPropertyValue("childIndex");
   }
   public void setChildIndex(int depth) {
      item.setPropertyValue("childIndex", new Integer(depth));
   }

   public String getDescription() {
      return (String) item.getPropertyValue("description");
   }
   public void setDescription(String desc) {
      item.setPropertyValue("description", desc);
   }

   public String getLongDescription() {
      return (String) item.getPropertyValue("longDescription");
   }
   public void setLongDescription(String desc) {
      item.setPropertyValue("longDescription", desc);
   }

   public SimpleCategory getParentCategory() {
      return (SimpleCategory) item.getPropertyValue("parentCategory");
   }
   public void setParentCategory(SimpleCategory cat) {
      item.setPropertyValue("parentCategory", ((ReposItemCategory)cat).item);
   }

   private Set<MutableRepositoryItem> getRawChildren() {
      return (Set<MutableRepositoryItem>) item.getPropertyValue("children");
   }

   public Set<SimpleCategory> getChildren() {
      Set<MutableRepositoryItem> rawChildren = getRawChildren();
      Set<SimpleCategory> s = new HashSet<SimpleCategory>(rawChildren.size());
      for (MutableRepositoryItem ri:rawChildren)
         s.add(new ReposItemCategory(ri));
      return s;
   }

   public void setChildren(Set<SimpleCategory> sc) {
      item.setPropertyValue("children", sc);
   }

   static class ChildIterator implements Iterator<SimpleCategory> {
      Iterator rootIt;
      ChildIterator(Iterator root) {
         rootIt = root;
      }
      public void remove() {
      }

      public boolean hasNext() {
         return rootIt.hasNext();
      }

      public SimpleCategory next() {
         return new ReposItemCategory((MutableRepositoryItem)rootIt.next());
      }
   }

   public Iterator<SimpleCategory> getChildrenIterator() {
      return new ChildIterator(getRawChildren().iterator());
   }

   private List<MutableRepositoryItem> getRawFixedChildCategories() {
      return (List<MutableRepositoryItem>) item.getPropertyValue("fixedChildCategories");
   }

   public List<SimpleCategory> getFixedChildCategories() {
      List<MutableRepositoryItem> rawChildren = getRawFixedChildCategories();
      List<SimpleCategory> s = new ArrayList<SimpleCategory>(rawChildren.size());
      for (MutableRepositoryItem ri:rawChildren)
         s.add(new ReposItemCategory(ri));
      return s;
   }

   public void setFixedChildCategories(List<SimpleCategory> sc) {
      item.setPropertyValue("fixedChildCategories", sc);
   }

   public Iterator<SimpleCategory> getFixedChildCategoriesIterator() {
      return new ChildIterator(getRawFixedChildCategories().iterator());
   }

   private Set<MutableRepositoryItem> getRawParentCategories() {
      return (Set<MutableRepositoryItem>) item.getPropertyValue("parentCategories");
   }

   public Set<SimpleCategory> getParentCategories() {
      Set<MutableRepositoryItem> rawChildren = getRawParentCategories();
      Set<SimpleCategory> s = new HashSet<SimpleCategory>(rawChildren.size());
      for (MutableRepositoryItem ri:rawChildren)
         s.add(new ReposItemCategory(ri));
      return s;
   }

   public void setParentCategories(Set<SimpleCategory> sc) {
      //item.setPropertyValue("parentCategories", sc);
   }

   public Iterator<SimpleCategory> getParentCategoriesIterator() {
      return new ChildIterator(getRawParentCategories().iterator());
   }

   public void setParent(SimpleCategory p) {
      ReposItemCategory parent = (ReposItemCategory) p;
      setParentCategory(parent);
      // Don't update this side
      //parent.getRawChildren().add(parent.item);

      // Don't update this side
      //getRawParentCategories().add(parent.item);
      parent.getRawFixedChildCategories().add(item);
   }

   public void addChildCategory(SimpleCategory c) {
      ReposItemCategory child = (ReposItemCategory) c;
      // Update the children (one-to-many)
      child.setParentCategory(this);
      // Only update one side
      //getRawChildren().add(child.item);

      // Replicate in fixedChildCategories/parentCategories (many-to-many)
      getRawFixedChildCategories().add(child.item);
      // Only update one side
      //child.getRawParentCategories().add(item);
   }

   public MutableRepositoryItem getItem() {
      return item;
   }
}
