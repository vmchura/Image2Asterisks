import com.sksamuel.scrimage.ImmutableImage

import java.io.File

object Main extends App {
  val file = new File("C:/Users/User/Pictures/lapadula2.jpg")
  val image = ImmutableImage.loader().fromFile(file)
  val newWidth = 255
  val newHeight = (newWidth*image.height)/image.width
  val imageGetterRGB = new ImageGetterRGB(newWidth, newHeight, image)
  val imageSetterBinary = new ImageSetterBinary(newWidth, newHeight)


  def converter(megaPixelRGB: MegaPixelRGB): Boolean = {
    val pixelsGrayScale = megaPixelRGB.rangeRow.flatMap{ i =>
      megaPixelRGB.rangeColumn.map{ j =>
        // 0.299 ∙ Red + 0.587 ∙ Green + 0.114 ∙ Blue.
        val pixel =  megaPixelRGB.image.pixel(i,j)
        val grayScale = (0.299*pixel.red() +
          0.587*pixel.green() +
          0.114*pixel.blue()).toInt

        grayScale

      }
    }
    val sorted = pixelsGrayScale.sorted
    val pixelRes = if(sorted.length == 1) sorted.head
    else sorted(sorted.length/2)
    pixelRes < 0.4*255.0
  }


  ImageBase.convert(imageGetterRGB, imageSetterBinary, converter)

  println(imageSetterBinary.show())
}
