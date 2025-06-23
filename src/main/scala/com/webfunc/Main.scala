package com.webfunc

object Main {
  def main(args: Array[String]): Unit = {
    val calculator = new BowlingScoreCalculator()
    if (args.nonEmpty) {
      val scores = args.mkString(" ")
      val total = calculator.calculateScore(scores)
      println(s"Bowling scores: $scores")
      println(s"Total score: $total")
    } else {
      println("Usage: sbt run -- <scores>")
      println("Example: sbt run -- 'X 9/ 5- 8/ X X 6- 9/ 9/ X X X'")
    }
  }
}

class BowlingScoreCalculator {
  def calculateScore(scores: String): Int = {
    val rolls = parseRolls(scores)
    scoreFromRolls(rolls)
  }

  private def parseRolls(scores: String): List[Int] = {
    val tokens = scores.trim.split("\\s+").toList
    val rolls = scala.collection.mutable.ListBuffer[Int]()
    for (token <- tokens) {
      var prev: Option[Int] = None
      token.foreach {
        case 'X' =>
          rolls += 10
          prev = None
        case '/' =>
          val last = prev.getOrElse(0)
          rolls += (10 - last)
          prev = None
        case '-' =>
          rolls += 0
          prev = Some(0)
        case d if d.isDigit =>
          rolls += d.asDigit
          prev = Some(d.asDigit)
        case _ =>
      }
    }
    rolls.toList
  }

  private def scoreFromRolls(rolls: List[Int]): Int = {
    var total = 0
    var rollIndex = 0
    var frame = 1
    
    while (frame <= 10 && rollIndex < rolls.length) {
      if (rolls(rollIndex) == 10) { // Strike
        if (frame == 10) {
          // 10th frame strike: add next two balls as bonus
          val bonus1 = rolls.lift(rollIndex + 1).getOrElse(0)
          val bonus2 = rolls.lift(rollIndex + 2).getOrElse(0)
          total += 10 + bonus1 + bonus2
          rollIndex += 3 // Consume all three balls
        } else {
          // Regular frame strike: add next two balls as bonus
          val bonus1 = rolls.lift(rollIndex + 1).getOrElse(0)
          val bonus2 = rolls.lift(rollIndex + 2).getOrElse(0)
          total += 10 + bonus1 + bonus2
          rollIndex += 1 // Only consume one ball for the frame
        }
      } else if (rolls.isDefinedAt(rollIndex + 1) && rolls(rollIndex) + rolls(rollIndex + 1) == 10) { // Spare
        if (frame == 10) {
          // 10th frame spare: add next ball as bonus
          val bonus = rolls.lift(rollIndex + 2).getOrElse(0)
          total += 10 + bonus
          rollIndex += 3 // Consume all three balls
        } else {
          // Regular frame spare: add next ball as bonus
          val bonus = rolls.lift(rollIndex + 2).getOrElse(0)
          total += 10 + bonus
          rollIndex += 2 // Consume two balls for the frame
        }
      } else if (rolls.isDefinedAt(rollIndex + 1)) { // Open frame
        total += rolls(rollIndex) + rolls(rollIndex + 1)
        rollIndex += 2
      } else {
        // Incomplete frame
        total += rolls(rollIndex)
        rollIndex += 1
      }
      frame += 1
    }
    total
  }
} 