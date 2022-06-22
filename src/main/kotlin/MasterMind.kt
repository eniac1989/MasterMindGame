import java.lang.Integer.min

fun main() {
//    val firstPattern: String = "aabcbdbf"
//    println("insert your String")
//    val secondPattern = readLine()!!
//    println(checkStrings(firstPattern, secondPattern))
    println(evaluateGuess("BAAD", "AMAA"))
}

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {

    var right = 0
    var wrongP = 0
    var wRepository = ArrayList<Char>()
    var rRepository = ArrayList<Char>()
    if (secret.length >= guess.length) {
        for (i in 0..secret.length - 1) {
            for (j in 0..guess.length - 1) {
                if (i == j && secret.get(i).equals(guess.get(j))) {
                    if (secret.count(guess.get(j)) == guess.count(guess.get(j)))
                        right++
                    else if (secret.count(guess.get(j)) != guess.count(guess.get(j))) {
                        rRepository.add(guess.get(j))
                        right++
                    }
                } else if (i == j && !secret.get(i).equals(guess.get(j))) {
                    if (secret.contains(guess.get(j)))
                        if (secret.count(guess.get(j)) == guess.count(guess.get(j)))
                            wrongP++
                        else if (secret.count(guess.get(j)) != guess.count(guess.get(j))) {
                            wRepository.add(guess.get(j))
                        }
                }
            }
        }

        for (i in 0..wRepository.size - 1) {
            val getChar = wRepository.get(i)
            var wReposCount = wRepository.count {
                it == getChar
            }
            var rReposCount = rRepository.count {
                it == getChar
            }
            var checkMin = min(secret.count {
                it == getChar
            }, guess.count { it == getChar })
            var sumWR = rReposCount + wReposCount
//            if (checkMin > sumWR)
            wrongP += (checkMin - rReposCount)
            wRepository.removeAll { it == getChar }
            if (wRepository.size < i + 1)
                break
        }
        return Evaluation(right, wrongP)
    }
    return Evaluation(right, wrongP)
}

fun String.count(item: Char): Int {
    var result = 0
    for (i in 0..this.length - 1) {
        if (this.get(i) == item)
            result++
    }
    return result
}

private fun checkStrings(firstPattern: String, secondPattern: String): String {
    var result = 0
    var output: String = ""
    if (firstPattern.length >= secondPattern.length) {
        for (i in 0..firstPattern.length - 1) {
            for (j in 0..secondPattern.length - 1) {
                if (i == j && firstPattern.get(i).equals(secondPattern.get(j))) {
                    output += firstPattern.get(i)
                    result++
                }

            }
        }
    } else {
        for (j in 0..secondPattern.length - 1) {
            for (i in 0..firstPattern.length - 1) {
                if (i == j && secondPattern.get(j).equals(firstPattern.get(i))) {
                    output += secondPattern.get(j)
                    result++
                }
            }
        }
    }
    return "shared output:$output number of shared letters:$result"
}


