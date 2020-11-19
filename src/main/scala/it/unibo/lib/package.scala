package it.unibo

import it.unibo.scafi.space.Point3D.{toPoint1D, toPoint2D}
import it.unibo.scafi.space.{Point2D, Point3D}

package object lib {
  //CONVERSION
  implicit def tupleToVelocity(p : (Double, Double)) : Point3D = new Point3D(p._1, p._2, 0)
  //TYPE ENRICHMENT
  implicit class RichPoint3D(p : Point3D) {
    val module : Double = math.hypot(p.x, p.y)
    lazy val normalized : Point2D = {
      val result = toPoint2D(p / module)
      if(result.x.isNaN || result.y.isNaN) {
        toPoint2D(Zero)
      } else {
        result
      }
    }
    def unary_- : Point3D = Point3D(-p.x, -p.y, -p.z)
    def -(other : Point3D) : Point3D = p + (- other)
    def *(alpha : Double) : Point3D = Point3D(p.x * alpha, p.y * alpha, p.z * alpha)
    def /(alpha : Double) : Point3D = p * (1.0 / alpha)
    def ===(other : Point3D) : Boolean = other.x == p.x && other.y == p.x && other.z == p.z //todo solve the bug in scafi lib
  }

  val Zero = Point3D(0,0,0)
}
