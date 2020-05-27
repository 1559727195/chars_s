package com.massky.chars_s.view

import android.animation.TypeEvaluator
import java.util.*

class PointEvaluator : TypeEvaluator<Point> {
    override fun evaluate(fraction: Float, startValue: Point?, endValue: Point?): Point? {
        var startPoint = startValue
        var endPoint = endValue
        var x = startPoint!!.getX() + fraction * (endPoint!!.getX() - startPoint.getX());
        var y = startPoint.getY() + fraction * (endPoint.getY() - startPoint.getY());
        var point = Point(x, y);
        return point
    }





}