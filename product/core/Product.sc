
// Layer: relatedProducts
class RelatedProduct {
  String relationDesc;
  Product relatedProduct;
}

// Making this an interface so we can add it anywhere.  We might want to add layers which
// implement this up at a higher level or add this to more components.
interface TrackableResource {
   Date startDate;
   Date endDate;
   Date lastModified;
   User lastUpdated;
}

class CatalogElement implements TrackableResource {
   String name;
   String shortDesc, longDesc;

   /** If false, this catalog element should be invisible and is not available for purchase */
   boolean liveProduct; 

   Category parentCategory;

   /** Used for the direct linking URL */
   String primaryKeywords;
   /** Used as page meta data for SEO */
   List<String> altKeywords;

   // Layer: multiCategory
   List<Category> parentCategories;

   // Layer: relatedProducts
   List<RelatedProduct> relatedCategories;

   // Layer for product media
   ProductImage productImage;
   ProductImage navImage;
   List<ProductImage> altViews;

   // TODO: encode in parameterized display template.  Using type groups, annotate a given template
   // from the page-type here, we can access the instance at runtime.  It will need to extend
   // the ProductTemplate class which has the annotation... that has a product property which
   // we populate in the dispatch of the template from the Display page.
}

class Category extends CatalogElement {
   /** Display this category in a navigation menu.  Some categories are hidden  */
   boolean navigateable;

   ProductImage subCategoryImage;
}

/** A category which acts as a root category for a tree of child products and categories. */
class Storefront extends Category {

}

class BaseSKU {
   // Backpointer to the product which owns this sku
   @Constant 
   Product product;
   String skuCode;
}

// Media layer
class ProductMedia {
   // TODO: encode in parameterized display template
   String mainURL;
   String captionText;

   // This can be customized to control different representations of the same thing
   // New media properties should be introduced for assets required by new product templates.
   // We can match the base class for the product template against the product to determine
   // a match.
}

class ProductImage extends ProductMedia {
   String thumbURL;
   String zoomedURL;

   int width, height;
   String altText;
}

@interface OptionProperty {
   int priority; // Affects the order of the option in the product's option list, which defaults to the 
                 // order of the option property in the products source.
   String skuPattern = "-{value}-";
}

/** 
  When you set the @OptionProperty annotation on a property in a Product, an instance of this
  class is generated.  Each product can retrieve it's list of PropertyOption's that are defined on it and it's super
  types, in the order specified by priority. 
  */
class PropertyOption {
   String propertyName;
   String skuPattern; 
}

/** 
 * The product will represent a family of skus like in most ecommerce systems.  Here there's
 * always a current sku which reflects the current set of product option properties which 
 * contribute to the sku's definition.  We use regular properties in the product for product
 * options so we can use Java type hierarchy etc.  As those option properties are changed
 * we regenerate the current sku property.  As the sku is changed, we'll similarly adjust
 * the option properties affected by that sku.  The sku-id is generated via a configurable
 * rule from the set of option properties.
 */
class Product extends CatalogElement {
   // Theses are all additional text segments you can display.  As they are populated
   // the template will adjust itself to display them.  
   // TODO remove from the base product?
   String productFeatures;
   String techSpecs;
   String moreInfo;
   String sellTagline;

   // Layer: relatedProducts
   List<RelatedProduct> relatedProducts;

   // When you set the SKU it should populate any associated options and vice versa
   ProductSKU sku;

   // Layer: multiSku
   List<ProductSKU> productSkus;

   // ?? Joda money package or BigDecimal for storing prices?  Wrap in a container
   // so prices are more configurable
   Price price;

   // Promotions layer 
   List<Promotion> promotions; 

   // Discount layer - named
   Price salePrice;

   // Inventory layer
   ProductInventory inventory;

   // Production - options?  we'll represent them as real properties so we get the benefits of
   // the type system, better more efficient storage, etc.  But there's a @ProductOption annotation
   // we use so we can gather up all options for each type and support a reflective way to treat
   // special product options in the code.  
}

class DigitalProduct extends Product {
   
}

class PhysicalProduct extends Product {
   // Product dimensions - 
   double weight, height, width, length;
}


class ProductBundle extends Product {
   List<Product> parts;
}

interface CategoryCrossSell {
   List<Category> categoryCrossSells;
}

interface CategoryUpSell {
   List<Category> categoryUpSells;
}
   
interface ProductCrossSell {
   List<Product> productCrossSells;
}

interface ProductUpSell {
   List<Product> productUpSells;
}

// Accessories layer in an interface
interface ProductAccessories {
   List<Product> accessories;
}

// Layer: brandedCore

class Brand {
   String brandName;
}

interface BrandedProduct {
   Brand brand;
}

// Layer: brandedProduct

Product implements BrandedProduct {
}

