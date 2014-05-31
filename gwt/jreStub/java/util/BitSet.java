/*
 * Copyright 2009 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy
 * of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package java.util;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayInteger;

/**
 * This implementation uses bit groups of size 32 to keep track of when bits
 * are set to true or false. This implementation also uses the sparse nature of
 * JavaScript arrays to speed up cases when very few bits are set in a large
 * bit set.
 *
 * Since there is no speed advantage to pre-allocating array sizes in
 * JavaScript the underlying array's length is shrunk to Sun's "logical length"
 * whenever length() is called. This length is the index of the highest true
 * bit, plus one, or zero if there are aren't any. This may cause the size()
 * method to return a different size than in a true Java VM.
 */
public class BitSet {
  // To speed up certain operations this class also uses the index properties
  // of arrays as described in section 15.4 of "Standard ECMA-262" (June 1997),
  // which can currently be found here:
  // http://www.mozilla.org/js/language/E262.pdf
  //
  // 15.4 Array Objects
  // Array objects give special treatment to a certain class of property names.
  // A property name P (in the form of a string value) is an array index if and
  // only if ToString(ToUint32(P)) is equal to P and ToUint32(P) is not equal
  // to (2^32)-1.

  // To avoid any signed/unsigned and integer/floating point issues, modulus 32
  // ("% 32") has been replaced with "& 0x1f". Also, division by 32 ("/ 32")
  // and multiplication by 32 ("* 32") has been replaced with ">>> 5" and
  // "<< 5" respectively.  This is done constantly in both Java and JavaScript
  // code to avoid confusion.


  // check index for negatives, as there is no bit index too high
  private static void checkIndex(int bitIndex) {
    if (bitIndex < 0) {
      throw new IndexOutOfBoundsException("bitIndex < 0: " + bitIndex);
    }
  }

  // check index range to ensure they are not negatives and not reversed
  private static void checkRange(int fromIndex, int toIndex) {
    if (fromIndex < 0) {
      throw new IndexOutOfBoundsException("fromIndex < 0: " + fromIndex);
    }
    if (toIndex < 0) {
      throw new IndexOutOfBoundsException("toIndex < 0: " + toIndex);
    }
    if (fromIndex > toIndex) {
      throw new IndexOutOfBoundsException("fromIndex: " + fromIndex
          + " > toIndex: " + toIndex);
    }
  }

  //
  // none of the following static method perform any bounds checking
  //

  // clears one bit
  private static native void clear(JsArrayInteger array, int bitIndex) /*-{
    var index = bitIndex >>> 5;
    var packed = array[index];
    if (packed !== undefined) {
      // mask the correct bit out
      packed &= ~(1 << (bitIndex & 0x1f));
      // keep 0s out of the array
      if (packed === 0) {
        delete array[index];
      } else {
        array[index] = packed;
      }
    }
  }-*/;

  // clones an array
  private static native JsArrayInteger clone(JsArrayInteger array) /*-{
    return array.slice(0);
  }-*/;

  // compares two sparse arrays
  private static native boolean equals(JsArrayInteger one, JsArrayInteger two) /*-{
    var count = 0;
    for (var property in one) {
      var number = property >>> 0;
      if (String(number) == property && number !== 0xffffffff) {
        if (one[number] !== two[number]) {
          return false;
        }
        count++;
      }
    }

    for (var property in two) {
      var number = property >>> 0;
      if (String(number) == property && number !== 0xffffffff) {
        count--;
      }
    }

    return count === 0;
  }-*/;

  // flips one bit
  private static native void flip(JsArrayInteger array, int bitIndex) /*-{
    // calculate index and offset
    var index = bitIndex >>> 5;
    var offset = bitIndex & 0x1f;

    // figure out if the bit is on or off
    var packed = array[index];
    var on;
    if (packed === undefined) {
      packed = 0;
      on = false;
    } else {
      on = ((packed >>> offset) & 1) == 1;
    }

    if (on) {
      // turn it off
      packed &= ~(1 << offset);
      // keep 0s out of the array
      if (packed === 0) {
        delete array[index];
      } else {
        array[index] = packed;
      }
    } else {
      // turn it on
      array[index] = packed | (1 << offset);
    }
  }-*/;

  // gets one bit
  private static native boolean get(JsArrayInteger array, int bitIndex) /*-{
    // pull out the bits for the given index
    var packed = array[bitIndex >>> 5];
    if (packed === undefined) {
      return false;
    }

    // shift and mask the bit out
    return ((packed >>> (bitIndex & 0x1f)) & 1) == 1;
  }-*/;

  // gets the bit length of the array
  // use JavaScript so we can take the log of an unsigned number
  private static native int getLength(JsArrayInteger array) /*-{
    var last = @java.util.BitSet::trimToSize(Lcom/google/gwt/core/client/JsArrayInteger;)(array);
    if (last == -1) {
      return 0;
    }

    // log2 will give the highest bit
    // use ">>> 0" to make it unsigned, and again to get it back to an int
    return (last << 5) + ((Math.log(array[last] >>> 0) / Math.LN2) >>> 0) + 1;
  }-*/;

  // sets one bit to true
  private static native void set(JsArrayInteger array, int bitIndex) /*-{
    array[bitIndex >>> 5] |= (1 << (bitIndex & 0x1f));
  }-*/;

  // sets all bits to true within the given range
  private static void set(JsArrayInteger array, int fromIndex, int toIndex) {
    int first = fromIndex >>> 5;
    int last = toIndex >>> 5;
    int startBit = fromIndex & 0x1f;
    int endBit = toIndex & 0x1f;

    if (first == last) {
      // clear the bits in between first and last
      rawMaskIn(array, first, startBit, endBit);

    } else {
      // clear the bits from fromIndex to the next 32 bit boundary
      if (startBit != 0) {
        rawMaskIn(array, first++, startBit, 32);
      }

      // clear the bits from the last 32 bit boundary to the toIndex
      if (endBit != 0) {
        rawMaskIn(array, last, 0, endBit);
      }

      //
      // delete everything in between
      //
      for (int i = first; i < last; i++) {
        rawSet(array, i, 0xffffffff);
      }
    }
  }

  // copies a subset of the array
  private static native JsArrayInteger slice(JsArrayInteger array,
      int fromIndex, int toIndex) /*-{
    return array.slice(fromIndex, toIndex);
  }-*/;

  // trims the array to the minimum size it can without losing data
  // returns index of the last element in the array, or -1 if empty
  private static native int trimToSize(JsArrayInteger array) /*-{
    var length = array.length;
    if (length === 0) {
      return -1;
    }

    // check if the last bit is false
    var last = length - 1;
    if (array[last] !== undefined) {
      return last;
    }

    // interleave property checks and linear index checks from the end
    var biggestSeen = -1;
    for (var property in array) {

      // test the index first
      if (--last === -1) {
        return -1;
      }
      if (array[last] !== undefined) {
        return last;
      }

      // now check the property
      var number = property >>> 0;
      if (String(number) == property && number !== 0xffffffff) {
        if (number > biggestSeen) {
          biggestSeen = number;
        }
      }

    }
    array.length = biggestSeen + 1

    return biggestSeen;
  }-*/;

  //
  // raw operations use the literal index into the array, not the bit index
  //

  // deletes an element from the array
  private static native void rawDelete(JsArrayInteger array, int index) /*-{
    delete array[index];
  }-*/;

  // flips all bits stored at a certain index
  private static native void rawFlip(JsArrayInteger array, int index) /*-{
    var packed = array[index];
    if (packed === undefined) {
      array[index] = 0xffffffff;
    } else {
      packed = ~packed;
      // keep 0s out of the array
      if (packed === 0) {
        delete array[index];
      } else {
        array[index] = packed;
      }
    }
  }-*/;

  // flips all bits stored at a certain index within the given range
  private static native void rawFlipMask(JsArrayInteger array, int index,
      int from, int to) /*-{
    // create a mask
    var mask = 0;
    for (var i = from; i < to; i++) {
      mask |= 1 << i;
    }

    // mask it in
    var packed = array[index];
    packed ^= mask;
    // keep 0s out of the array
    if (packed === 0) {
      delete array[index];
    } else {
      array[index] = packed;
    }
  }-*/;

  // returns all bits stored at a certain index
  private static native int rawGet(JsArrayInteger array, int index) /*-{
    var bits = array[index];
    return bits === undefined ? 0 : bits;
  }-*/;

  // sets all bits to true at a certain index within the given bit range
  private static native void rawMaskIn(JsArrayInteger array, int index,
      int from, int to) /*-{
    // shifting by 32 is the same as shifting by 0, this check prevents that
    // from happening in addition to the obvious prevention of extra work
    if (from !== to) {
      // adjust "to" so it will shift out those bits
      to = 32 - to;
      // create a mask and OR it in
      array[index] |= ((0xffffffff >>> from) << (from + to)) >>> to;
    }
  }-*/;

  // sets all bits to false at a certain index within the given bit range
  private static native void rawMaskOut(JsArrayInteger array, int index,
      int from, int to) /*-{
    var packed = array[index];
    // something only happens if packed has bits set
    if (packed !== undefined) {
      // create a mask
      var mask;
      if (from != 0) {
        mask = 0xffffffff >>> (32 - from);
      } else {
        mask = 0;
      }
      // shifting by 32 is the same as shifting by 0
      if (to !== 32) {
        mask |= 0xffffffff << to;
      }

      // mask it out
      packed &= mask;
      // keep 0s out of the array
      if (packed === 0) {
        delete array[index];
      } else {
        array[index] = packed;
      }
    }
  }-*/;

  // sets all bits at a certain index to the given value
  private static native void rawSet(JsArrayInteger array, int index, int value) /*-{
    array[index] = value;
  }-*/;

  // sets the array length
  private static native void rawSetLength(JsArrayInteger array, int length) /*-{
    array.length = length;
  }-*/;

  // our array of bits
  //
  // to keep things constant, never call anything other than array.length(),
  // use the previous static methods to access the data in the array
  private JsArrayInteger array;

  public BitSet() {
    // create a new array
    array = JavaScriptObject.createArray().cast();
  }

  public BitSet(int nbits) {
    this();

    // throw an exception to be consistent
    // but (do we want to be consistent?)
    if (nbits < 0) {
      throw new NegativeArraySizeException("nbits < 0: " + nbits);
    }

    // even though the array's length is loosely kept to that of Sun's "logical
    // length," this might help in some cases where code uses size() to fill in
    // bits after creating a BitSet, or after having one passed in as a
    // parameter.
    rawSetLength(array, (nbits + 31) >>> 5);
  }

  private BitSet(JsArrayInteger array) {
    this.array = array;
  }

  public native void and(BitSet set) /*-{
    // a & a is just a
    if (this === set) {
      return;
    }

    // store arrays for easy access
    var thisArray = this.@java.util.BitSet::array;
    var otherArray = set.@java.util.BitSet::array;

    // trim the second set to avoid extra work
    @java.util.BitSet::trimToSize(Lcom/google/gwt/core/client/JsArrayInteger;)(otherArray)

    // if length is longer than otherLength, that ANDs those bits to false
    var otherLength = otherArray.length;
    if (thisArray.length > otherLength) {
      // shrink the array
      thisArray.length = otherLength;
    }

    // truth table
    //
    // case | a     | b     | a & b | change?
    // 1    | false | false | false | a is already false
    // 2    | false | true  | false | a is already false
    // 3    | true  | false | false | set a to false
    // 4    | true  | true  | true  | a is already true
    //
    // we only need to change something in case 3, so iterate over set a
    for (var property in thisArray) {
      var number = property >>> 0;
      if (String(number) == property && number !== 0xffffffff) {
        // check length to avoid an extra array lookup
        if (number < otherLength) {
          var bits = otherArray[number];
          if (bits === undefined) {
            delete thisArray[number];
          } else {
            var packed = thisArray[number];
            packed &= bits;
            // keep 0s out of the array
            if (packed === 0) {
              delete thisArray[number];
            } else {
              thisArray[number] = packed;
            }
          }
        }
      }
    }
  }-*/;

  public native void andNot(BitSet set) /*-{
    // a & !a is false
    if (this === set) {
      // all falses result in an empty BitSet
      this.@java.util.BitSet::clear()();
      return;
    }

    // store arrays for easy access
    var thisArray = this.@java.util.BitSet::array;
    var otherArray = set.@java.util.BitSet::array;

    // trim the second set to avoid extra work
    @java.util.BitSet::trimToSize(Lcom/google/gwt/core/client/JsArrayInteger;)(thisArray)
    var length = thisArray.length;

    // truth table
    //
    // case | a     | b     | !b    | a & !b | change?
    // 1    | false | false | true  | false  | a is already false
    // 2    | false | true  | false | false  | a is already false
    // 3    | true  | false | true  | true   | a is already true
    // 4    | true  | true  | false | false  | set a to false
    //
    // we only need to change something in case 4
    // whenever b is true, a should be false, so iterate over set b
    for (var property in otherArray) {
      var number = property >>> 0;
      if (String(number) == property && number !== 0xffffffff) {
        // check length to avoid an extra array lookup
        if (number < length) {
          var packed = thisArray[number];
          if (packed !== undefined) {
            packed &= ~otherArray[number];
            // keep 0s out of the array
            if (packed === 0) {
              delete thisArray[number];
            } else {
              thisArray[number] = packed;
            }
          }
        }
      }
    }
  }-*/;

  public native int cardinality() /*-{
    var count = 0;
    var array = this.@java.util.BitSet::array;
    for (var property in array) {
      var number = property >>> 0;
      if (String(number) == property && number !== 0xffffffff) {
        // the code used is faster than the following:
        // count += @java.lang.Integer::bitCount(I)(array[number]);
        var bits = array[number];
        bits = bits - ((bits >>> 1) & 0x55555555);
        bits = (bits & 0x33333333) + ((bits >>> 2) & 0x33333333);
        count += ((bits + (bits >>> 4) & 0xf0f0f0f) * 0x1010101) >>> 24;
      }
    }
    return count;
  }-*/;

  public void clear() {
    // create a new array
    array = JavaScriptObject.createArray().cast();
  }

  public void clear(int bitIndex) {
    checkIndex(bitIndex);
    clear(array, bitIndex);
  }

  public void clear(int fromIndex, int toIndex) {
    checkRange(fromIndex, toIndex);

    int length = length();
    if (fromIndex >= length) {
      // nothing to do
      return;
    }

    // check to see if toIndex is greater than our array length
    if (toIndex >= length) {
      // truncate the array by setting it's length
      int newLength = (fromIndex + 31) >>> 5;
      rawSetLength(array, newLength);

      // remove the extra bits off the end
      if ((newLength << 5) - fromIndex != 0) {
        rawMaskOut(array, newLength - 1, fromIndex & 0x1f, 32);
      }

    } else {
      int first = fromIndex >>> 5;
      int last = toIndex >>> 5;
      int startBit = fromIndex & 0x1f;
      int endBit = toIndex & 0x1f;

      if (first == last) {
        // clear the bits in between first and last
        rawMaskOut(array, first, startBit, endBit);

      } else {
        // clear the bits from fromIndex to the next 32 bit boundary
        if (startBit != 0) {
          rawMaskOut(array, first++, startBit, 32);
        }

        // clear the bits from the last 32 bit boundary to the toIndex
        if (endBit != 0) {
          rawMaskOut(array, last, 0, endBit);
        }

        //
        // delete everything in between
        //
        for (int i = first; i < last; i++) {
          rawDelete(array, i);
        }
      }
    }
  }

  public Object clone() {
    return new BitSet(clone(array));
  }

  @Override
  public boolean equals(Object obj) {
    if (this != obj) {

      if (!(obj instanceof BitSet)) {
        return false;
      }

      BitSet other = (BitSet) obj;

      int length = length();
      if (length != other.length()) {
        return false;
      }

      return equals(array, other.array);
    }

    return true;
  }

  public void flip(int bitIndex) {
    checkIndex(bitIndex);
    flip(array, bitIndex);
  }

  public void flip(int fromIndex, int toIndex) {
    checkRange(fromIndex, toIndex);

    int length = length();

    // if we are flipping bits beyond our length, we are setting them to true
    if (fromIndex >= length) {
      set(array, fromIndex, toIndex);
      return;
    }

    // check to see if toIndex is greater than our array length
    if (toIndex >= length) {
      set(array, length, toIndex);
      toIndex = length;
    }

    int first = fromIndex >>> 5;
    int last = toIndex >>> 5;
    int startBit = fromIndex & 0x1f;
    int end = toIndex & 0x1f;

    if (first == last) {
      // flip the bits in between first and last
      rawFlipMask(array, first, startBit, end);

    } else {
      // clear the bits from fromIndex to the next 32 bit boundary
      if (startBit != 0) {
        rawFlipMask(array, first++, startBit, 32);
      }

      // clear the bits from the last 32 bit boundary to the toIndex
      if (end != 0) {
        rawFlipMask(array, last, 0, end);
      }

      // flip everything in between
      for (int i = first; i < last; i++) {
        rawFlip(array, i);
      }
    }
  }

  public boolean get(int bitIndex) {
    checkIndex(bitIndex);
    return get(array, bitIndex);
  }

  public BitSet get(int fromIndex, int toIndex) {
    checkRange(fromIndex, toIndex);

    // no need to go past our length
    int length = length();
    if (toIndex >= length) {
      toIndex = length();
    }

    // this is the bit shift offset for each group of bits
    int rightShift = fromIndex & 0x1f;

    if (rightShift == 0) {
      int subFrom = fromIndex >>> 5;
      int subTo = (toIndex + 31) >>> 5;
      JsArrayInteger subSet = slice(array, subFrom, subTo);
      int leftOvers = toIndex & 0x1f;
      if (leftOvers != 0) {
        rawMaskOut(subSet, subTo - subFrom - 1, leftOvers, 32);
      }
      return new BitSet(subSet);
    }

    BitSet subSet = new BitSet();

    int first = fromIndex >>> 5;
    int last = toIndex >>> 5;

    if (first == last) {
      // number of bits to cut from the end
      int end = 32 - (toIndex & 0x1f);
      // raw bits
      int packed = rawGet(array, first);
      // shift out those bits
      packed = ((packed << end) >>> end) >>> rightShift;
      // set it
      if (packed != 0) {
        rawSet(subSet.array, 0, packed);
      }

    } else {
      // this will hold the newly packed bits
      int current = 0;

      // this is the raw index into the sub set
      int subIndex = 0;

      // fence post, carry over initial bits
      int packed = rawGet(array, first++);
      current = packed >>> rightShift;

      // a left shift will be used to shift our bits to the top of "current"
      int leftShift = 32 - rightShift;

      // loop through everything in the middle
      for (int i = first; i <= last; i++) {
        packed = rawGet(array, i);

        // shift out the bits from the top, OR them into current bits
        current |= packed << leftShift;

        // flush it out
        if (current != 0) {
          rawSet(subSet.array, subIndex, current);
        }

        // keep track of our index
        subIndex++;

        // carry over the unused bits
        current = packed >>> rightShift;
      }

      // fence post, flush out the extra bits, but don't go past the "end"
      int end = 32 - (toIndex & 0x1f);
      current = (current << (rightShift + end)) >>> (rightShift + end);
      if (current != 0) {
        rawSet(subSet.array, subIndex, current);
      }
    }

    return subSet;
  }

  /**
   * This hash is different than the one described in Sun's documentation. The
   * described hash uses 64 bit integers and that's not practical in
   * JavaScript.
   */
  @Override
  public int hashCode() {
    // FNV constants
    final int fnvOffset = 0x811c9dc5;
    final int fnvPrime = 0x1000193;

    // initialize
    final int last = trimToSize(array);
    int hash = fnvOffset ^ last;

    // loop over the data
    for (int i = 0; i <= last; i++) {
      int value = rawGet(array, i);
      // hash one byte at a time using FNV-1
      hash = (hash * fnvPrime) ^ (value & 0xff);
      hash = (hash * fnvPrime) ^ ((value >>> 8) & 0xff);
      hash = (hash * fnvPrime) ^ ((value >>> 16) & 0xff);
      hash = (hash * fnvPrime) ^ (value >>> 24);
    }

    return hash;
  }

  public native boolean intersects(BitSet set) /*-{
    var thisArray = this.@java.util.BitSet::array;
    var last = @java.util.BitSet::trimToSize(Lcom/google/gwt/core/client/JsArrayInteger;)(thisArray);

    if (this === set) {
      // if it has any bits then it intersects itself
      return last != -1;
    }

    var otherArray = set.@java.util.BitSet::array;
    var lastOther = @java.util.BitSet::trimToSize(Lcom/google/gwt/core/client/JsArrayInteger;)(otherArray);

    var smallArray;
    var bigArray;
    if (last < lastOther) {
      smallArray = thisArray;
      bigArray = otherArray;
    } else {
      smallArray = otherArray;
      bigArray = thisArray;
    }

    for (var property in smallArray) {
      var number = property >>> 0;
      if (String(number) == property && number !== 0xffffffff) {
        if ((bigArray[number] & smallArray[number]) != 0) {
          return true;
        }
      }
    }
    return false;
  }-*/;

  public boolean isEmpty() {
    return length() == 0;
  }

  public int length() {
    return getLength(array);
  }

  public int nextClearBit(int fromIndex) {
    checkIndex(fromIndex);
    int index = fromIndex >>> 5;

    // special case for first index
    int fromBit = fromIndex - (index << 5);
    int packed = rawGet(array, index);
    for (int i = fromBit; i < 32; i++) {
      if ((packed & (1 << i)) == 0) {
        return (index << 5) + i;
      }
    }

    // loop through the rest
    while (true) {
      index++;
      packed = rawGet(array, index);
      if (packed != 0xffffffff) {
        return (index << 5) + Integer.numberOfTrailingZeros(~packed);
      }
    }
  }

  public native int nextSetBit(int fromIndex) /*-{
    @java.util.BitSet::checkIndex(I)(fromIndex);

    var length = this.@java.util.BitSet::length()();
    var array = this.@java.util.BitSet::array;
    var index = fromIndex >>> 5;

    var packed = array[index];
    if (packed !== undefined) {
      for (var i = fromIndex & 0x1f; i < 32; i++) {
        if ((packed & (1 << i)) != 0) {
          return (index << 5) + i;
        }
      }
    }
    index++;

    // interleave property checks and linear "index" checks
    var localMinimum = @java.lang.Integer::MAX_VALUE;
    for (var property in array) {

      // test the index first
      packed = array[index]
      if (packed !== undefined) {
        return (index << 5) +
            @java.lang.Integer::numberOfTrailingZeros(I)(packed);
      }
      if (++index >= length) {
        return -1;
      }

      // now check the property
      var number = property >>> 0;
      if (String(number) == property && number !== 0xffffffff) {
        if (number >= index && number < localMinimum) {
          localMinimum = number;
        }
      }
    }

    // if local minimum is what we started at, we found nothing
    if (localMinimum === @java.lang.Integer::MAX_VALUE) {
      return -1;
    }

    // return the local minimum
    packed = array[localMinimum];
    return (localMinimum << 5) +
        @java.lang.Integer::numberOfTrailingZeros(I)(packed);
  }-*/;

  public native void or(BitSet set) /*-{
    // a | a is just a
    if (this === set) {
      return;
    }

    // store arrays for easy access
    var thisArray = this.@java.util.BitSet::array;
    var otherArray = set.@java.util.BitSet::array;

    // truth table
    //
    // case | a     | b     | a | b | change?
    // 1    | false | false | false | a is already false
    // 2    | false | true  | true  | set a to true
    // 3    | true  | false | true  | a is already true
    // 4    | true  | true  | true  | a is already true
    //
    // we only need to change something in case 2
    // case 2 only happens when b is true, so iterate over set b
    for (var property in otherArray) {
      var number = property >>> 0;
      if (String(number) == property && number !== 0xffffffff) {
        thisArray[number] |= otherArray[number];
      }
    }
  }-*/;

  public void set(int bitIndex) {
    checkIndex(bitIndex);
    set(array, bitIndex);
  }

  public void set(int bitIndex, boolean value) {
    if (value == true) {
      set(bitIndex);
    } else {
      clear(bitIndex);
    }
  }

  public void set(int fromIndex, int toIndex) {
    checkRange(fromIndex, toIndex);
    set(array, fromIndex, toIndex);
  }

  public void set(int fromIndex, int toIndex, boolean value) {
    if (value == true) {
      set(fromIndex, toIndex);
    } else {
      clear(fromIndex, toIndex);
    }
  }

  public int size() {
    // number of bytes that can fit without using more memory
    return array.length() << 5;
  }

  @Override
  public String toString() {
    // possibly faster if done in JavaScript and all numerical properties are
    // put into an array and sorted

    int length = length();
    if (length == 0) {
      return "{}";
    }

    StringBuilder sb = new StringBuilder("{");

    int next = nextSetBit(0);
    sb.append(next);

    while ((next = nextSetBit(next + 1)) != -1) {
      sb.append(", ");
      sb.append(next);
    }

    sb.append("}");
    return sb.toString();
  }

  public native void xor(BitSet set) /*-{
    // a ^ a is false
    if (this === set) {
      // this results in an empty BitSet
      this.@java.util.BitSet::clear()();
      return;
    }

    // store arrays for easy access
    var thisArray = this.@java.util.BitSet::array;
    var otherArray = set.@java.util.BitSet::array;

    // truth table
    //
    // case | a     | b     | a ^ b | change?
    // 1    | false | false | false | a is already false
    // 2    | false | true  | true  | set a to true
    // 3    | true  | false | true  | a is already true
    // 4    | true  | true  | false | set a to false
    //
    // we need to change something in cases 2 and 4
    // cases 2 and 4 only happen when b is true, so iterate over set b
    for (var property in otherArray) {
      var number = property >>> 0;
      if (String(number) == property && number !== 0xffffffff) {
        var packed = thisArray[number];
        packed ^= otherArray[number];
        // keep 0s out of the array
        if (packed === 0) {
          delete thisArray[number];
        } else {
          thisArray[number] = packed;
        }
      }
    }
  }-*/;

}
