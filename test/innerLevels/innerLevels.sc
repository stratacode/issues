/** These are simple language tests to validate name resolution 
   in a type hierarchy where inner-subclasses extend other inner 
   classes at different points in the hierarchy. 

   There are two variants of each test.  With toString methods, we get 
   lots of dynamic stubs.  With getString methods, it is all dynamic.

   Run both with -dyn and regular - includes automatic validation.
   */
public test.innerLevels extends junit.main {}
