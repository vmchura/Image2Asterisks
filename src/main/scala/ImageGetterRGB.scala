import com.sksamuel.scrimage.ImmutableImage

class ImageGetterRGB(val width: Int, val height: Int, image: ImmutableImage) extends ImageGetter[MegaPixelRGB]{
  val widthMegaPixel: Int = image.width / width
  val heightMegaPixel: Int = image.height / height
  override def get(i: Int, j: Int): MegaPixelRGB = {
    val rowPixelStarter = (i*image.width)/width
    val columnPixelStarter = (j*image.height)/height
    MegaPixelRGB(
      rowPixelStarter until (rowPixelStarter+ widthMegaPixel),
      columnPixelStarter until (columnPixelStarter + heightMegaPixel),
      image)
  }
}
