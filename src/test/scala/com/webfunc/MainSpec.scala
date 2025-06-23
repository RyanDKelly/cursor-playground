package com.webfunc

import org.scalatest.funsuite.AnyFunSuite

class MainSpec extends AnyFunSuite {
  test("should pass a simple test") {
    assert(1 + 1 === 2)
  }
  
  test("should calculate perfect game score") {
    val calculator = new BowlingScoreCalculator()
    val perfectGame = "X X X X X X X X X X X X"
    assert(calculator.calculateScore(perfectGame) === 300)
  }
  
  test("should calculate gutter game score") {
    val calculator = new BowlingScoreCalculator()
    val gutterGame = "-- -- -- -- -- -- -- -- -- --"
    assert(calculator.calculateScore(gutterGame) === 0)
  }
  
  test("should calculate all spares game score") {
    val calculator = new BowlingScoreCalculator()
    val allSpares = "5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5"
    assert(calculator.calculateScore(allSpares) === 150)
  }
  
  test("should calculate mixed game score") {
    val calculator = new BowlingScoreCalculator()
    val mixedGame = "9- 9- 9- 9- 9- 9- 9- 9- 9- 9-"
    assert(calculator.calculateScore(mixedGame) === 90)
  }
  
  test("should calculate game with strikes and spares") {
    val calculator = new BowlingScoreCalculator()
    val game = "X 9/ 5- 8/ X X 6- 9/ 9/ X X X"
    assert(calculator.calculateScore(game) === 177)
  }
  
  test("should handle single frame") {
    val calculator = new BowlingScoreCalculator()
    val singleFrame = "X"
    assert(calculator.calculateScore(singleFrame) === 10)
  }
  
  test("should handle spare frame") {
    val calculator = new BowlingScoreCalculator()
    val spareFrame = "5/"
    assert(calculator.calculateScore(spareFrame) === 10)
  }
  
  test("should handle regular frame") {
    val calculator = new BowlingScoreCalculator()
    val regularFrame = "45"
    assert(calculator.calculateScore(regularFrame) === 9)
  }
} 