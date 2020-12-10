package module1

import module1.list.List.Cons
import module1.opt.Option2.{None2, Some2}

import scala.annotation.tailrec

/**
 *  Реализуем тип Option
 */


 object opt {

  /**
   *
   * Реализовать тип Option, который будет указывать на присутствие либо отсутствие результата
   */

   sealed trait Option2[+A]{
      def isEmpty2: Boolean = this match {
       case Option2.Some2(_) => false
       case Option2.None2 => true
      }

      def get2: A = this match {
       case Option2.Some2(v) => v
       case Option2.None2 => throw new Exception("get on empty Option")
      }

      def printIfAny2: Unit = this match {
        case Option2.Some2(v) => println(v)
        case Option2.None2 => ()
      }

      def orElse2[B](otherOpt: Option2[B]): Option2[Any] = this match {
        case Option2.Some2(_) => this
        case Option2.None2 => otherOpt
      }

      def zip2[A, B](otherOpt: Option2[B]) = {
        if (this.isEmpty2 || otherOpt.isEmpty2) None2
        else Some2(this.get2, otherOpt.get2)
      }

      def filter(f: A => Boolean) = {
        this match {
          case Some2(v) if f(v) => Some2(v)
          case _ => None2
        }
      }
   }

   object Option2 {
    case class Some2[A](v: A) extends Option2[A]
    case object None2 extends Option2[Nothing]
   }




  /**
   * done
   * Реализовать метод printIfAny, который будет печатать значение, если оно есть
   */


  /**
   * done
   * реализовать метод orElse который будет возвращать другой Option, если данный пустой
   */


  /**
   * done
   * Реализовать метод isEmpty, который будет возвращать true если Option не пуст и false в противном случае
   */


  /**
   * done
   * Реализовать метод get, который будет возвращать значение
   */

  /**
   * done
   * Реализовать метод zip, который будет создавать Option от пары значений из 2-х Option
   */


  /**
   * done
   * Реализовать метод filter, который будет возвращать не пустой Option
   * в случае если исходный не пуст и предикат от значения = true
   */

 }

 object recursion {

   /**
    * Реализовать метод вычисления n!
    * n! = 1 * 2 * ... n
    */

   def fact(n: Int): Long = {
    var _n = 1L
    var i = 2
    while (i <= n) {
     _n *= i
     i += 1
    }
    _n
   }

   def !!(n: Int): Long = {
     if(n <= 1) 1
     else n * !!(n - 1)
   }

  def !(n: Int): Long = {
   @tailrec
   def loop(n1: Int, acc: Long): Long = {
     if(n <= 1) acc
     else loop(n1 - 1, n1 * acc)
    }
   loop(n, 1)
  }

 }

 object list {
   /**
    *
    * Реализовать односвязанный имутабельный список List
    */

   sealed trait List[+A]{
     def ::[AA >: A](head: AA): List[AA] = Cons(head, this)

    def mkString: String = mkString(", ")

    def mkString(sep: String): String = {
       import List._

      def loop(l: List[A], acc: StringBuilder): StringBuilder = {
        l match {
         case List.Nil => acc
         case h :: Nil => acc.append(s"$h")
         case h :: t => loop(t, acc.append(s"$h$sep"))
        }
       }
      loop(this, new StringBuilder()).toString()
     }

     def reverse: List[A] = {
       def reverse2(input: List[A], acc: List[A] = List[A]()): List[A] = {
         input match {
           case List.Nil => acc
           case List.::(head, tail) => reverse2(tail, head :: acc)
         }
       }
       reverse2(this)
     }

     def funcElem[B](f: A => B): List[B] = {
       def funcElem2(xs: List[A], acc: List[B] = List[B]()): List[B] = {
         xs match {
           case List.Nil => acc
           case List.::(head, tail) => funcElem2(tail, f(head) :: acc)
         }
       }
       funcElem2(this)
     }
   }


   object List{
    case object Nil extends List[Nothing]
    case class ::[A](head: A, tail: List[A]) extends List[A]
    val Cons = ::

    def apply[T](arg: T*): List[T] = {
     var l: List[T] = List.Nil
     arg.foreach(el => l = el :: l)
     l
    }

     def incList(xs: List[Int]): List[Int] = {
       def incList2(xs: List[Int], acc: List[Int] = List[Int]()): List[Int] = {
         xs match {
           case List.Nil => acc
           case List.::(head, tail) => incList2(tail, head + 1 :: acc)
         }
       }
       incList2(xs)
     }

     def shoutString(xs: List[String]): List[String] = {
       def shoutString2(xs: List[String], acc: List[String] = List[String]()): List[String] = {
         xs match {
           case Nil => acc
           case ::(head, tail) => shoutString2(tail, "!" + head :: acc )
         }
       }
       shoutString2(xs)
     }
   }

   val list = 1 :: List.Nil

   /**
    * done
    * Реализовать метод конс :: который позволит добавлять элемент в голову списка
    */


   /**
    * done
    * Реализовать конструктор, для создания списка n элементов
    */


   /**
    * done
    * Реализовать метод mkString который позволит красиво представить список в виде строки
    */


   /**
    * done
    * Реализовать метод reverse который позволит заменить порядок элементов в списке на противоположный
    */


   /**
    * done
    * Написать функцию incList которая будет принимать список Int и возвращать список,
    * где каждый элемент будет увеличен на 1
    */


   /**
    * done
    * Написать функцию shoutString которая будет принимать список String и возвращать список,
    * где к каждому элементу будет добавлен префикс в виде '!'
    */


   /**
    * done
    * Реализовать метод для списка который будет применять некую ф-цию к элементам данного списка
    */



 }