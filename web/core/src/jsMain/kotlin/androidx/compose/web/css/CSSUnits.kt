package org.jetbrains.compose.web.css

interface CSSUnitValue<out T : CSSUnit> : CSSNumericValue {
    val value: Float
    val unit: T
    fun asString(): String = "${value}${unit.value}"
}

private class CSSUnitValueTyped<out T : CSSUnit>(
    override val value: Float,
    override val unit: T
) : CSSUnitValue<T>

typealias CSSSizeValue = CSSUnitValue<CSSUnit>
typealias CSSpxValue = CSSUnitValue<CSSUnit.px>

interface CSSUnitRel : CSSUnit
interface CSSUnitAbs: CSSUnit
interface CSSUnitAngle: CSSUnit
interface CSSUnitTime: CSSUnit
interface CSSUnitFrequency: CSSUnit
interface CSSUnitResolution: CSSUnit
interface CSSUnitFlex: CSSUnit

typealias CSSAngleValue = CSSUnitValue<CSSUnitAngle>

sealed interface CSSUnit {
    val value: String

    object percent: CSSUnitRel {
        override val value: String = "%"
    }

    object em: CSSUnitRel {
        override val value = "em"
    }

    object ex: CSSUnitRel {
        override val value = "ex"
    }

    object ch: CSSUnitRel {
        override val value = "ch"
    }

    object ic: CSSUnitRel {
        override val value = "ic"
    }

    object rem: CSSUnitRel {
        override val value = "rem"
    }

    object lh: CSSUnitRel {
        override val value = "lh"
    }

    object rlh: CSSUnitRel {
        override val value = "rlh"
    }

    object vw: CSSUnitRel {
        override val value = "vw"
    }

    object vh: CSSUnitRel {
        override val value = "vh"
    }

    object vi: CSSUnitRel {
        override val value = "vi"
    }

    object vb: CSSUnitRel {
        override val value = "vb"
    }

    object vmin: CSSUnitRel {
        override val value = "vmin"
    }

    object vmax: CSSUnitRel {
        override val value = "vmax"
    }

    object cm: CSSUnitRel {
        override val value = "cm"
    }

    object mm: CSSUnitRel {
        override val value = "mm"
    }

    object q: CSSUnitRel {
        override val value = "q"
    }

    object pt: CSSUnitAbs {
        override val value = "pt"
    }

    object pc: CSSUnitAbs {
        override val value = "pc"
    }

    object px: CSSUnitAbs {
        override val value = "px"
    }

    object deg: CSSUnitAngle {
        override val value = "deg"
    }

    object grad: CSSUnitAngle {
        override val value = "grad"
    }

    object rad: CSSUnitAngle {
        override val value = "rad"
    }

    object turn: CSSUnitAngle {
        override val value = "turn"
    }

    object s: CSSUnitTime {
        override val value = "s"
    }

    object ms: CSSUnitTime {
        override val value = "ms"
    }

    object hz: CSSUnitFrequency {
        override val value = "hz"
    }

    object khz: CSSUnitFrequency {
        override val value = "khz"
    }

    object dpi: CSSUnitResolution {
        override val value = "dpi"
    }

    object dpcm: CSSUnitResolution {
        override val value = "dpcm"
    }

    object dppx: CSSUnitResolution {
        override val value = "dppx"
    }

    object fr: CSSUnitFlex {
        override val value = "fr"
    }

    object number: CSSUnit {
        override val value = "number"
    }
}


val Number.number
    get(): CSSUnitValue<CSSUnit.number> = CSSUnitValueTyped(this.toFloat(), CSSUnit.number)

val Number.percent
    get() : CSSUnitValue<CSSUnit.percent> = CSSUnitValueTyped(this.toFloat(), CSSUnit.percent)

val Number.em
    get() : CSSUnitValue<CSSUnit.em> = CSSUnitValueTyped(this.toFloat(), CSSUnit.em)

val Number.ex
    get(): CSSUnitValue<CSSUnit.ex> = CSSUnitValueTyped(this.toFloat(), CSSUnit.ex)

val Number.ch
    get(): CSSUnitValue<CSSUnit.ch> = CSSUnitValueTyped(this.toFloat(), CSSUnit.ch)

val Number.cssRem
    get(): CSSUnitValue<CSSUnit.rem> = CSSUnitValueTyped(this.toFloat(), CSSUnit.rem)

val Number.vw
    get(): CSSUnitValue<CSSUnit.vw> = CSSUnitValueTyped(this.toFloat(), CSSUnit.vw)

val Number.vh
    get(): CSSUnitValue<CSSUnit.vh> = CSSUnitValueTyped(this.toFloat(), CSSUnit.vh)

val Number.vmin
    get(): CSSUnitValue<CSSUnit.vmin> = CSSUnitValueTyped(this.toFloat(), CSSUnit.vmin)

val Number.vmax
    get(): CSSUnitValue<CSSUnit.vmax> = CSSUnitValueTyped(this.toFloat(), CSSUnit.vmax)

val Number.cm
    get(): CSSUnitValue<CSSUnit.cm> = CSSUnitValueTyped(this.toFloat(), CSSUnit.cm)

val Number.mm
    get(): CSSUnitValue<CSSUnit.mm> = CSSUnitValueTyped(this.toFloat(), CSSUnit.mm)

val Number.Q
    get() : CSSUnitValue<CSSUnit.q> = CSSUnitValueTyped(this.toFloat(), CSSUnit.q)

val Number.pt
    get(): CSSUnitValue<CSSUnit.pt> = CSSUnitValueTyped(this.toFloat(), CSSUnit.pt)
val Number.pc
    get(): CSSUnitValue<CSSUnit.pc> = CSSUnitValueTyped(this.toFloat(), CSSUnit.pc)
val Number.px
    get(): CSSUnitValue<CSSUnit.px> = CSSUnitValueTyped(this.toFloat(), CSSUnit.px)

val Number.deg
    get(): CSSUnitValue<CSSUnit.deg> = CSSUnitValueTyped(this.toFloat(), CSSUnit.deg)
val Number.grad
    get(): CSSUnitValue<CSSUnit.grad> = CSSUnitValueTyped(this.toFloat(), CSSUnit.grad)
val Number.rad
    get(): CSSUnitValue<CSSUnit.rad> = CSSUnitValueTyped(this.toFloat(), CSSUnit.rad)
val Number.turn
    get(): CSSUnitValue<CSSUnit.turn> = CSSUnitValueTyped(this.toFloat(), CSSUnit.turn)

val Number.s
    get(): CSSUnitValue<CSSUnit.s> = CSSUnitValueTyped(this.toFloat(), CSSUnit.s)
val Number.ms
    get(): CSSUnitValue<CSSUnit.ms> = CSSUnitValueTyped(this.toFloat(), CSSUnit.ms)

val Number.Hz
    get(): CSSUnitValue<CSSUnit.hz> = CSSUnitValueTyped(this.toFloat(), CSSUnit.hz)
val Number.kHz
    get(): CSSUnitValue<CSSUnit.khz> = CSSUnitValueTyped(this.toFloat(), CSSUnit.khz)

val Number.dpi
    get(): CSSUnitValue<CSSUnit.dpi> = CSSUnitValueTyped(this.toFloat(), CSSUnit.dpi)
val Number.dpcm
    get(): CSSUnitValue<CSSUnit.dpcm> = CSSUnitValueTyped(this.toFloat(), CSSUnit.dpcm)
val Number.dppx
    get(): CSSUnitValue<CSSUnit.dppx> = CSSUnitValueTyped(this.toFloat(), CSSUnit.dppx)

val Number.fr
    get(): CSSUnitValue<CSSUnit.fr> = CSSUnitValueTyped(this.toFloat(), CSSUnit.fr)
