package module1

import module1.list.List
import module1.list.List.{incList, shoutString}


object App {


  def main(args: Array[String]): Unit = {

    val list = 1 :: 2 :: 3 :: List.Nil
    val list2 = List(1, 2, 3)
    println(list.mkString)
    println(list2.mkString(" "))
    println(list.reverse.mkString)

    println(incList(List(4, 5, 6)).mkString)
    println(shoutString(List("asd", "dsa", "zxc")).mkString)

    println(List(1, 2, 3).funcElem(_ * 2).mkString)
  }
}
