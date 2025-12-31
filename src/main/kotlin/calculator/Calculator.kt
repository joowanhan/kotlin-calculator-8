package calculator

class Calculator(val line: String) {
	
	val endCustomDelimiters = line.indexOf("\n")
	var delimiters = mutableListOf(',', ':')
	var numbersString = ""
	lateinit var numbers: List<Int>
	
	fun parseCustomDelimiters() {
		// 커스텀 구분자(//와 \n 사이) 추출 로직
		if (line.startsWith("//")) {
			val customDelimiters = line.substring(2, endCustomDelimiters).map { it.toChar() }
			delimiters.addAll(customDelimiters)
		}
	}
	
}