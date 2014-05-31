import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface GWTModule {
   /** Comma separated list of module names in dotted format which this module inherits from.  Default is to inherit from the User module. */
   String inherits() default "";      
   /** Comma separated list of the Java class names of classes to be invoked when the module is loaded, in addition to GWTModules which are loaded by default */
   String entryPoints() default "";
   /** Additional source files to be compiled as client code and included (if used) in the .js file when compiled.  By default, all source files used by the module itself are included individually. */
   String source() default "";
   /** External name of the module, type name is the default. */
   String name() default "";
   /** When true (the default), this module is included in the index page's default script tags. */
   boolean defaultModule() default true;
}
