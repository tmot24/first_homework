package module1


object subtyping {


  /**
   * Наследование - это отношение межу типами.
   * Отношение вида подтип / супертип
   * Компилятор способен отслеживать это отношение и следить за его соблюдением
   * В Scala мы имеем возможность указать компилятору на наличие отношения между Generic параметрами
   *
   * Для этого используется комбинация специальных символов(type operator)
   *  <: -   для отношения `подтип`
   *  >: -   для отношения `супертип`
   */

  trait Vehicle
  trait Car        extends Vehicle
  trait Moto       extends Vehicle
  object Harley    extends Moto
  object Mustang   extends Car

  type IsSubtypeOf[A, B >: A]
  type IsSupertypeOf[A, B <: A]


  /**
   *
   * С помощью типа IsSubtypeOf выразить отношение Car и Vehicle
   *
   */


  /**
   *
   * С помощью типа IsSubtypeOf выразить отношение Car и Mustang
   *
   */


  /**
   *
   * С помощью типа выразить отношение Vehicle и Harley, причем чтобы они шли в этом порядке
   *
   */


  /**
   * В этом примере вам нужно правильно выбрать оператор отношения,
   * чтобы среди идущих ниже выражений, те которые корректны по смыслу компилировались, а остальные нет
   */

  def isInstanceOf[A, B](a: A): Unit = ???




  lazy val mustCompile1    = isInstanceOf[Mustang.type, Car](Mustang)
  lazy val mustCompile2    = isInstanceOf[Harley.type, Moto](Harley)
  lazy val mustNotCompile1 = isInstanceOf[Mustang.type, Moto](Mustang)
  lazy val mustNotCompile2 = isInstanceOf[Harley.type, Car](Harley)



  trait Box[T] {
    def get: T
  }

  // val a : IsSubtypeOf[Box[Car], Box[Vehicle]] = ???


  trait Consumer[T] {
    def consume(v: T): Unit
  }

  //val b : IsSubtypeOf[Consumer[Car], Consumer[Vehicle]] = ???






  object adt{


    object tuples{
      /**
       * Products
       * Произведение типов A * B - это такой тип,
       * который позволит закодировать все возможные комбинации значений типов А и В
       */

      // Unit * Boolean


      /**
       * Tuples
       * Наиболее общий способ хранить 2 и более кусочка информации в одно время. По русски - кортеж.
       * Вместе с кортежем мы получаем из коробки конструктор / деконсруктор, сравнение, hashCode, copy,
       * красивое строковое представление
       *
       */


      /**
       * Реализовать тип Person который будет содержать имя и возраст
       */

      type Person = TODO


      /**
       *
       *  Реализовать тип `CreditCard` который может содержать номер (String),
       *  дату окончания (java.time.YearMonth), имя (String), код безопастности (Short)
       */
      type CreditCard = TODO
    }

    object case_classes {
      /**
       * Case classes
       */

      final case class Person(name: String, age: Int)

      val tonyStark: Person = Person("Tony Stark", 42)

      /**
       * используя паттерн матчинг напечатать имя и возраст
       */

       def printNameAndAge: Unit = TODO


      final case class Employee(name: String, address: Address)
      final case class Address(street: String, number: Int)

      val alex = Employee("Alex", Address("XXX", 221))

      /**
       * воспользовавшись паттерн матчингом напечатать номер из поля адрес
       */

       // alex match


      /**
       * Паттерн матчинг может содержать литералы.
       * Реализовать паттерн матчинг на alex с двумя кейсами.
       * 1. Имя должно соотвествовать Alex
       * 2. Все остальные
       */


      /**
       * Паттерны могут содержать условия. В этом случае case сработает,
       * если и паттерн совпал и условие true.
       * Условия в паттерн матчинге называются гардами.
       */


      /**
       * Реализовать паттерн матчинг на alex с двумя кейсами.
       * 1. Имя должно начинаться с A
       * 2. Все остальные
       */


      /**
       *
       * Мы можем поместить кусок паттерна в переменную использую `as` паттерн,
       * x @ ..., где x это любая переменная. Это переменная может использоваться, как в условии,
       * так и внутри кейса
       */



      /**
       * Мы можем использовать вертикальную черту `|` для матчинга на альтернативы
       */
    }


    object either{


      /**
       * Sum
       * Сумма типов A и B - это такой тип,
       * который позволит закодировать все значения типа A и все значения типа B
       */

      // Unit + Boolean


      /**
       * Either - это наиболее общий способ хранить один из двух или более кусочков информации в одно время.
       * Также как и кортежи обладает целым рядом полезных методов
       * Иммутабелен
       */

      type IntOrString = TODO

      /**
       * Реализовать экземпляр типа IntOrString с помощью конструктора Right(_)
       */
      val intOrString: IntOrString = TODO


      /**\
       * Реализовать тип PaymentMethod который может быть представлен одной из альтернатив
       */
      type PaymentMethod = TODO

      final case class CreditCard()
      final case class WireTransfer()

    }

    object sealed_traits{
      /**
       * Также Sum type можно представить в виде sealed trait с набором альтернатив
       */


      sealed trait Card
      object Card {
        final case class Clubs(points: Int)    extends Card // крести
        final case class Diamonds(points: Int) extends Card // бубны
        final case class Spades(points: Int)   extends Card // пики
        final case class Hearts(points: Int)   extends Card // червы
      }

      lazy val card: Card = Card.Spades(10)


      /**
       * Написать паттерн матчинг на 10 пику, и на все остальное
       */


      /**
       * Написать паттерн матчинг который матчит карты номиналом >= 10
       */

    }





  }

  object type_classes {

    /**
     * Type class - это паттерн родом из Haskel
     * Он позволяет расширять существующие типы новым функционалом,
     * без необходимости менять их исходники или использовать наследование
     *
     * Компоненты паттерна:
     * 1. Сам type class
     * 2. Его экземпляры
     * 3. Методы которые его используют
     *
     *
     * Необходимые Scala конструкции
     *  trait
     *  implicit values
     *  implicit parameters
     *  implicit class
     */

    sealed trait JsValue
    object JsValue{
      final case class JsObject(get: Map[String, JsValue]) extends JsValue
      final case class JsString(get: String) extends JsValue
      final case class JsNumber(get: Double) extends JsValue
      final case object JsNull extends JsValue
    }


    /**
     * в Scala есть специальный метод позволяющий получить инстанс type класса из контекста
     */

     // implicitly


    /**
     * Упаковка имплиситов
     * Имплисты могут располагаться либо внутри объектов / классов / трэйтов
     *
     * Имплиситы помещенные в объект компаньон для типа,
     * автоматически попадают в скоуп, где мы используем данный тип
     */


    /**
     * Поиск имплиситов
     *
     *  - локальные либо наследованные
     *  - импортированные
     *  - объект компаньон
     */



  }



}