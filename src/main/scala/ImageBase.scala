trait ImageBase{
  def width: Int
  def height: Int
}
object ImageBase{
  def convert[A,B](from: ImageGetter[A], to: ImageSetter[B], transformer: A => B): ImageSetter[B] = {
    require(from.width == to.width && from.height == to.height)
    for{
      i <- 0 until from.width
      j <- 0 until from.height
    }yield{
      to.set(i,j, transformer(from.get(i,j)))
    }
    to
  }
}

trait ImageSetter[A] extends ImageBase {
  def show(): String
  def set(i: Int, j: Int, a: A): Unit
}

trait ImageGetter[A] extends ImageBase {
  def get(i: Int, j: Int): A
}

