/*-------------------------------------------------------------------------*\
**  ScalaCheck                                                             **
**  Copyright (c) 2007-2019 Rickard Nilsson. All rights reserved.          **
**  http://www.scalacheck.org                                              **
**                                                                         **
**  This software is released under the terms of the Revised BSD License.  **
**  There is NO WARRANTY. See the file LICENSE for the full text.          **
\*------------------------------------------------------------------------ */

package org.scalacheck

import rng.Seed

import Gen._
import Prop.{forAll, someFailing, noneFailing, sizedProp, secure, propBoolean}
import Arbitrary._
import Shrink._
import java.util.Date
import scala.util.{Try, Success, Failure}

object GenSpecification extends Properties("Gen") with GenSpecificationVersionSpecific {

  implicit val arbSeed: Arbitrary[Seed] = Arbitrary(
    arbitrary[Long] flatMap Seed.apply
  )

  property("posNum[Int]") =
    Prop.forAll(Gen.posNum[Int]) { n => n > 0 }

  property("negNum[Int]") =
    Prop.forAll(Gen.negNum[Int]) { n => n < 0 }

  property("posNum[Float]") =
    Prop.forAll(Gen.posNum[Float]) { n => n > 0.0 }

  property("negNum[Float]") =
    Prop.forAll(Gen.negNum[Float]) { n => n < 0.0 }

  property("posNum[Double] <= 1.0d") = // #439
    Prop.forAll(Gen.resize(1, Gen.posNum[Double])) { _ <= 1.0d }

}
