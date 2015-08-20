package com.github.lzenczuk.scala_playground

import org.scalatest.{Matchers, FunSuite}

/**
 *
 * @author lzenczuk 20/08/2015
 */
class ImmutableCollectionsSuite extends FunSuite with Matchers{

  test("Experiments with list methods"){
    val l = List[Int](1,2,1,1,3,2,1,2,2,2,3)

    l ++ List(4,5,6) should contain theSameElementsAs List(1,2,1,1,3,2,1,2,2,2,3,4,5,6)
    5 +: l should contain theSameElementsAs List(5,1,2,1,1,3,2,1,2,2,2,3)
    l :+ 5 should contain theSameElementsAs List(1,2,1,1,3,2,1,2,2,2,3,5)

    // creates partial function that get elements of the list based on index, apply param function to it and return
    val getAndAdd = l.andThen( i => i+10)
    getAndAdd(0) should be (11)
    getAndAdd(1) should be (12)
    getAndAdd(2) should be (11)
    getAndAdd(4) should be (13)

    l.fold(0)((accumulator, value) => accumulator+value) should be(20)
    l.fold(0)((accumulator:Int, value:Int) => accumulator+value) should be(20)
    l.fold(0)(_+_) should be(20)
    l.fold(10)(_+_) should be(30)

    // fold works only with the same type as list. This code will cause error.
    //l.fold("")((s:String, i:Int) => s + i.toString) should be("12113212223")

    // This will work because?
    // TODO - why
    l.foldLeft("")(_ + _) should be("12113212223")
    l.foldLeft("")((s:String, a2:Any) => s + a2) should be("12113212223")

    // foldLeft works with different type then list
    l.foldLeft("")((s:String, i:Int) => s + i) should be("12113212223")
    l.foldLeft("")(_ + _) should be("12113212223")

    l.foldRight("")((i:Int, s:String) =>  s+i) should be ("32221231121")

    // foldRigth change order of parameters so
    l.foldLeft("")(_ + _) should be(l.foldRight("")(_ + _))
  }

}
