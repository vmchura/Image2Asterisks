class ImageSetterBinary(val width: Int, val height: Int) extends ImageSetter[Boolean]{
  private val data = Array.fill(height, width)(false)
  override def show(): String = {
    val stringBuilder = new StringBuilder()
    println(data.length)
    println(data(0).length)
    data.foreach{ row =>
      row.foreach{ pixel =>
        if(pixel)
          stringBuilder += ' '
        else
          stringBuilder += '*'
      }
      stringBuilder ++= System.lineSeparator()
    }
    stringBuilder.toString()
  }

  override def set(i: Int, j: Int, a: Boolean): Unit = data(j)(i) = a
}
