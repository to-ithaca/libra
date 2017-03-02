package libra
package syntax

import libra.ops.quantity._

final class QuantityOps[P0 <: Singleton with Int, D0 <: Dimensions[_, _]](val q0: Quantity[P0, D0]) {
  def add[P1 <: Singleton with Int, D1 <: Dimensions[_, _]](q1: Quantity[P1, D1])(implicit a: Add[Quantity[P0, D0], Quantity[P1, D1]]): a.Out = a(q0, q1)
  def prefix[P1 <: Singleton with Int]()(implicit p: Prefix[Quantity[P0, D0], P1]): p.Out = p(q0)
  def invert()(implicit i: Invert[Quantity[P0, D0]]): i.Out = i(q0)
  def multiply[P1 <: Singleton with Int, D1 <: Dimensions[_, _]](q1: Quantity[P1, D1])(implicit m: Multiply[Quantity[P0, D0], Quantity[P1, D1]]): m.Out = m(q0, q1)
  def divide[P1 <: Singleton with Int, D1 <: Dimensions[_, _]](q1: Quantity[P1, D1])(implicit d: Divide[Quantity[P0, D0], Quantity[P1, D1]]): d.Out = d(q0, q1)
  def power[P <: Singleton with Int]()(implicit p: Power[Quantity[P0, D0], P]): p.Out = p(q0)
  def show(implicit s: Show[Quantity[P0, D0]]): String = s(q0)
}
