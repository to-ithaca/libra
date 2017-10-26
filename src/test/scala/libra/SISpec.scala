package libra

import spire.implicits._
import libra.si._

import org.scalatest._

class SISpec extends WordSpec with Matchers {

  val ε = 1E-6


  "length" should {

    "km value" in {
      assert(3.0.km.to[Metre].value === (3.0E3) +- (ε * 1E-1))
    }

    "m value" in {
      assert(3.0.m.value === (3.0))
    }

    "dm value" in {
      assert(3.0.dm.to[Metre].value === (3.0E-1) +- (ε * 1E-1))
    }

    "cm value" in {
      assert(3.0.cm.to[Metre].value === (3.0E-2) +- (ε * 1E-2))
    }

    "mm value" in {
      assert(3.0.mm.to[Metre].value === (3.0E-3) +- (ε * 1E-3))
    }

    "μm value" in {
      assert(3.0.μm.to[Metre].value === (3.0E-6) +- (ε * 1E-6))
    }

    "nm value" in {
      assert(3.0.nm.to[Metre].value === (3.0E-9) +- (ε * 1E-9))
    }

    "pm value" in {
      assert(3.0.pm.to[Metre].value === (3.0E-12) +- (ε * 1E-12))
    }

    "fm value" in {
      assert(3.0.fm.to[Metre].value === (3.0E-15) +- (ε * 1E-15))
    }


    "show" in {
      assert(3.km.show === ("3 km [L]"))
      assert(3.m.show === ("3 m [L]"))
      assert(3.dm.show === ("3 dm [L]"))
      assert(3.cm.show === ("3 cm [L]"))
      assert(3.mm.show === ("3 mm [L]"))
      assert(3.μm.show === ("3 μm [L]"))
      assert(3.nm.show === ("3 nm [L]"))
      assert(3.pm.show === ("3 pm [L]"))
      assert(3.fm.show === ("3 fm [L]"))
    }
  }

  "mass" should {

    "kg value" in {
      assert(3.0.kg.to[Gram].value === (3.0E3) +- (ε * 1E-1))
    }

    "g value" in {
      assert(3.0.g.value === (3.0))
    }

    "dg value" in {
      assert(3.0.dg.to[Gram].value === (3.0E-1) +- (ε * 1E-1))
    }

    "cg value" in {
      assert(3.0.cg.to[Gram].value === (3.0E-2) +- (ε * 1E-2))
    }

    "mg value" in {
      assert(3.0.mg.to[Gram].value === (3.0E-3) +- (ε * 1E-3))
    }

    "μg value" in {
      assert(3.0.μg.to[Gram].value === (3.0E-6) +- (ε * 1E-6))
    }

    "ng value" in {
      assert(3.0.ng.to[Gram].value === (3.0E-9) +- (ε * 1E-9))
    }

    "pg value" in {
      assert(3.0.pg.to[Gram].value === (3.0E-12) +- (ε * 1E-12))
    }

    "fg value" in {
      assert(3.0.fg.to[Gram].value === (3.0E-15) +- (ε * 1E-15))
    }

    "show" in {
      assert(3.kg.show === ("3 kg [M]"))
      assert(3.g.show === ("3 g [M]"))
      assert(3.dg.show === ("3 dg [M]"))
      assert(3.cg.show === ("3 cg [M]"))
      assert(3.mg.show === ("3 mg [M]"))
      assert(3.μg.show === ("3 μg [M]"))
      assert(3.ng.show === ("3 ng [M]"))
      assert(3.pg.show === ("3 pg [M]"))
      assert(3.fg.show === ("3 fg [M]"))
    }
  }

  "time" should {

    "ks value" in {
      assert(3.0.ks.to[Second].value === (3.0E3) +- (ε * 1E-1))
    }

    "s value" in {
      assert(3.0.s.value === (3.0))
    }

    "ds value" in {
      assert(3.0.ds.to[Second].value === (3.0E-1) +- (ε * 1E-1))
    }

    "cs value" in {
      assert(3.0.cs.to[Second].value === (3.0E-2) +- (ε * 1E-2))
    }

    "ms value" in {
      assert(3.0.ms.to[Second].value === (3.0E-3) +- (ε * 1E-3))
    }

    "μs value" in {
      assert(3.0.μs.to[Second].value === (3.0E-6) +- (ε * 1E-6))
    }

    "ns value" in {
      assert(3.0.ns.to[Second].value === (3.0E-9) +- (ε * 1E-9))
    }

    "ps value" in {
      assert(3.0.ps.to[Second].value === (3.0E-12) +- (ε * 1E-12))
    }

    "fs value" in {
      assert(3.0.fs.to[Second].value === (3.0E-15) +- (ε * 1E-15))
    }

    "show" in {
      assert(3.ks.show === ("3 ks [T]"))
      assert(3.s.show === ("3 s [T]"))
      assert(3.ds.show === ("3 ds [T]"))
      assert(3.cs.show === ("3 cs [T]"))
      assert(3.ms.show === ("3 ms [T]"))
      assert(3.μs.show === ("3 μs [T]"))
      assert(3.ns.show === ("3 ns [T]"))
      assert(3.ps.show === ("3 ps [T]"))
      assert(3.fs.show === ("3 fs [T]"))
    }
  }

  "velocity" should {

    "kmps value" in {
      assert(3.0.kmps.to[Metre].value === (3.0E3) +- (ε * 1E-1))
    }

    "mps value" in {
      assert(3.0.mps.value === (3.0))
    }

    "dmps value" in {
      assert(3.0.dmps.to[Metre].value === (3.0E-1) +- (ε * 1E-1))
    }

    "cmps value" in {
      assert(3.0.cmps.to[Metre].value === (3.0E-2) +- (ε * 1E-2))
    }

    "mmps value" in {
      assert(3.0.mmps.to[Metre].value === (3.0E-3) +- (ε * 1E-3))
    }

    "μmps value" in {
      assert(3.0.μmps.to[Metre].value === (3.0E-6) +- (ε * 1E-6))
    }

    "nmps value" in {
      assert(3.0.nmps.to[Metre].value === (3.0E-9) +- (ε * 1E-9))
    }

    "pmps value" in {
      assert(3.0.pmps.to[Metre].value === (3.0E-12) +- (ε * 1E-12))
    }

    "fmps value" in {
      assert(3.0.fmps.to[Metre].value === (3.0E-15) +- (ε * 1E-15))
    }

    "show" in {
      assert(3.kmps.show === ("3 km s^-1 [L T^-1]"))
      assert(3.mps.show === ("3 m s^-1 [L T^-1]"))
      assert(3.dmps.show === ("3 dm s^-1 [L T^-1]"))
      assert(3.cmps.show === ("3 cm s^-1 [L T^-1]"))
      assert(3.mmps.show === ("3 mm s^-1 [L T^-1]"))
      assert(3.μmps.show === ("3 μm s^-1 [L T^-1]"))
      assert(3.nmps.show === ("3 nm s^-1 [L T^-1]"))
      assert(3.pmps.show === ("3 pm s^-1 [L T^-1]"))
      assert(3.fmps.show === ("3 fm s^-1 [L T^-1]"))
    }
  }

  "acceleration" should {

    "kmps2 value" in {
      assert(3.0.kmps2.to[Metre].value === (3.0E3) +- (ε * 1E-1))
    }

    "mps2 value" in {
      assert(3.0.mps2.value === (3.0))
    }

    "dmps2 value" in {
      assert(3.0.dmps2.to[Metre].value === (3.0E-1) +- (ε * 1E-1))
    }

    "cmps2 value" in {
      assert(3.0.cmps2.to[Metre].value === (3.0E-2) +- (ε * 1E-2))
    }

    "mmps2 value" in {
      assert(3.0.mmps2.to[Metre].value === (3.0E-3) +- (ε * 1E-3))
    }

    "μmps2 value" in {
      assert(3.0.μmps2.to[Metre].value === (3.0E-6) +- (ε * 1E-6))
    }

    "nmps2 value" in {
      assert(3.0.nmps2.to[Metre].value === (3.0E-9) +- (ε * 1E-9))
    }

    "pmps2 value" in {
      assert(3.0.pmps2.to[Metre].value === (3.0E-12) +- (ε * 1E-12))
    }

    "fmps2 value" in {
      assert(3.0.fmps2.to[Metre].value === (3.0E-15) +- (ε * 1E-15))
    }

    "show" in {
      assert(3.kmps2.show === ("3 km s^-2 [L T^-2]"))
      assert(3.mps2.show === ("3 m s^-2 [L T^-2]"))
      assert(3.dmps2.show === ("3 dm s^-2 [L T^-2]"))
      assert(3.cmps2.show === ("3 cm s^-2 [L T^-2]"))
      assert(3.mmps2.show === ("3 mm s^-2 [L T^-2]"))
      assert(3.μmps2.show === ("3 μm s^-2 [L T^-2]"))
      assert(3.nmps2.show === ("3 nm s^-2 [L T^-2]"))
      assert(3.pmps2.show === ("3 pm s^-2 [L T^-2]"))
      assert(3.fmps2.show === ("3 fm s^-2 [L T^-2]"))
    }
  }

  "current" should {

    "mA value" in {
      assert(3.0.mA.to[Ampere].value === (0.003))
    }
    "A value" in {
      assert(3.0.A.value === (3.0))
    }

    "show" in {
      assert(3.mA.show === ("3 mA [I]"))
      assert(3.A.show === ("3 A [I]"))
    }
  }

  "momentum" should {

    "kgmps value" in {
      assert(3.0.kgmps.to[Centimetre].value === (300.0))
    }

    "show" in {
      assert(3.kgmps.show === ("3 kg m s^-1 [M L T^-1]"))
    }
  }
}
